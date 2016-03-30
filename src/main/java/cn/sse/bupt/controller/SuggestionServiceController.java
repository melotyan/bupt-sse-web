package cn.sse.bupt.controller;

import cn.sse.bupt.enums.UserTypeEnum;
import cn.sse.bupt.model.ResultModel;
import cn.sse.bupt.model.SuggestionModel;
import cn.sse.bupt.model.UserModel;
import cn.sse.bupt.service.SuggestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by hao.yan on 2016/1/13.
 */
@RestController
@RequestMapping("suggestionService")
public class SuggestionServiceController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuggestionServiceController.class);
    private final int PAGE_SIZE = 30;

    @Autowired
    private SuggestionService suggestionService;

    @RequestMapping("makeSuggestion")
    public ResultModel makeSuggestion(@RequestParam(value = "title", defaultValue = "") String title, @RequestParam(value = "content", defaultValue = "") String content) {
        int uid = getLoginUser().getId();
        SuggestionModel suggestionModel = new SuggestionModel();
        suggestionModel.setUid(uid);
        suggestionModel.setTitle(title);
        suggestionModel.setContent(content);
        suggestionModel.setCreateDate(new Date());
        suggestionService.makeSuggestion(suggestionModel);
        LOGGER.info("user {} make a suggestion success", uid);
        return ResultModel.success();
    }

    @RequestMapping("listSuggestions/{page}")
    public ModelAndView listSuggestions(@PathVariable Integer page) {
        if (page == null || page <= 0)
            page = 1;
        int offset = (page - 1) * PAGE_SIZE;
        List<SuggestionModel> list = suggestionService.listSuggestions(offset, PAGE_SIZE);
        LOGGER.info("number of suggestion is {} in page {}", list.size(), page);
        return new ModelAndView("suggestion/list", "list", list);
    }

    @RequestMapping("viewSuggestion/{id}")
    public ModelAndView viewSuggestion(@PathVariable Integer id) {
        SuggestionModel suggestionModel = suggestionService.viewSuggestion(id);
        if (suggestionModel == null) {
            LOGGER.info("suggestion {} is null", id);
            return new ModelAndView("common/404");
        }
        return new ModelAndView("suggestion/detail", "suggestion", suggestionModel);

    }

    @RequestMapping("deleteSuggestion/{id}")
    public ResultModel deleteSuggestion(@PathVariable Integer id) {
        SuggestionModel suggestionModel = suggestionService.viewSuggestion(id);
        if (suggestionModel == null) {
            LOGGER.info("suggestion {} not exist", id);
            return ResultModel.failed("不存在此建议");
        }
        UserModel userModel = getLoginUser();
        if (userModel.getUserType() == UserTypeEnum.CUSTOMER.getValue() && userModel.getId() != suggestionModel.getUid()) {
            LOGGER.info("user {} no auth to delete suggestion {}", userModel.getId(), suggestionModel.getId());
            return ResultModel.failed("用户没有权限");
        }
        suggestionService.deleteSuggestion(id);
        return ResultModel.success();
    }

}
