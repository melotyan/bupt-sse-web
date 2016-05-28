package cn.sse.bupt.controller;

import cn.sse.bupt.enums.SuggestionTypeEnum;
import cn.sse.bupt.enums.UserTypeEnum;
import cn.sse.bupt.model.ResultModel;
import cn.sse.bupt.model.SuggestionModel;
import cn.sse.bupt.model.UserModel;
import cn.sse.bupt.service.SuggestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resources;
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
    public ResultModel makeSuggestion(@RequestParam(value = "title", defaultValue = "") String title, @RequestParam(value = "content", defaultValue = "") String content, @RequestParam(value = "type") int type) {
        if (title.equals(""))
            return ResultModel.failed("标题不能为空");
        if (content.equals(""))
            return ResultModel.failed("内容不能为空");
		int uid = getLoginUser().getId();
        SuggestionModel suggestionModel = new SuggestionModel();
        suggestionModel.setUid(uid);
        suggestionModel.setTitle(title);
        suggestionModel.setType(type);
        suggestionModel.setContent(content);
        suggestionModel.setCreateDate(new Date());
        suggestionService.makeSuggestion(suggestionModel);
        LOGGER.info("user {} make a suggestion success", uid);
        return ResultModel.success();
    }

    @RequestMapping("preMakeSuggestion/type/{type}")
    public ModelAndView preMakeSuggestion(@PathVariable Integer type) {
        if (type != SuggestionTypeEnum.COMPLAINT.getValue() && type != SuggestionTypeEnum.SEEK_HELP.getValue()
                && type != SuggestionTypeEnum.SUGGESTION.getValue())
            return new ModelAndView("common/404");
        return new ModelAndView("suggestion/create", "type", type);
    }

    @RequestMapping("listSuggestions/type/{type}/{page}")
    public ModelAndView listSuggestions(@PathVariable Integer type, @PathVariable Integer page) {
        if (page == null || page <= 0)
            page = 1;
        int offset = (page - 1) * PAGE_SIZE;
        List<SuggestionModel> list = suggestionService.listSuggestionsByType(type, offset, PAGE_SIZE);
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
            return ResultModel.failed("没有删除权限");
        }
        suggestionService.deleteSuggestion(id);
        return ResultModel.success("反馈:" + suggestionModel.getTitle() + " 删除成功");
    }

    @RequestMapping("preEditSuggestion/id/{id}")
    public ModelAndView preEditSuggestion(@PathVariable Integer id) {
        SuggestionModel suggestionModel = suggestionService.viewSuggestion(id);
        if (suggestionModel == null) {
            LOGGER.info("suggestion {} not exist", id);
            return new ModelAndView("common/404");
        }
        return new ModelAndView("suggestion/edit", "suggestion", suggestionModel);
    }
    @RequestMapping(value = "editSuggestion", method = RequestMethod.POST)
    public ResultModel editSuggestion(@RequestParam("id") Integer id, @RequestParam("title") String title, @RequestParam("content") String content) {
        SuggestionModel suggestionModel = suggestionService.viewSuggestion(id);
        if (suggestionModel == null) {
            LOGGER.info("suggestion {} not exist", id);
            return ResultModel.failed("不存在此建议");
        }
        UserModel userModel = getLoginUser();
        if (userModel.getId() != suggestionModel.getUid()) {
            LOGGER.info("user {} no auth to edit suggestion {}", userModel.getId(), suggestionModel.getId());
            return ResultModel.failed("没有编辑权限");
        }
        suggestionService.editSuggestion(id, title, content);
        return ResultModel.success("反馈:" + title + " 编辑成功");
    }
}
