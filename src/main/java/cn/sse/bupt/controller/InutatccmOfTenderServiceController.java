package cn.sse.bupt.controller;

import cn.sse.bupt.enums.UserTypeEnum;
import cn.sse.bupt.model.InutatccmOfTenderModel;
import cn.sse.bupt.model.ResultModel;
import cn.sse.bupt.model.UserModel;
import cn.sse.bupt.service.InutatccmOfTenderService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Map;

/**
 * Created by melot on 2016/4/5.
 */
@RestController
@RequestMapping("inutatccmOfTenderService")
public class InutatccmOfTenderServiceController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(InutatccmOfTenderServiceController.class);
    private final int PAGE_SIZE = 50;
    private Gson gson = new Gson();
    @Autowired
    private InutatccmOfTenderService inutatccmOfTenderService;

    @RequestMapping("listTenderInfo/{page}")
    public ModelAndView listTenderInfo(@PathVariable Integer page) {
        if (page == null || page <= 0)
            page = 1;
        ModelAndView modelAndView = new ModelAndView("tender/list");
        List<InutatccmOfTenderModel> list = inutatccmOfTenderService.listTenderInfos(page, PAGE_SIZE);
        if (list == null || list.isEmpty())
            LOGGER.info("tender info list is empty");
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    @RequestMapping("listTenderInfo/data")
    public List<InutatccmOfTenderModel> listTenderInfoData() {
        return inutatccmOfTenderService.listTenderInfos(1, 10);
    }

    @RequestMapping("preCreateTenderInfo")
    public ModelAndView preCreateTenderInfo() {
        return new ModelAndView("tender/create");
    }

    @RequestMapping(value = "createTenderInfo", method = RequestMethod.POST)
    public ResultModel makeTenderInfo(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("fileUrl") String fileUrl) {
        InutatccmOfTenderModel inutatccmOfTenderModel = new InutatccmOfTenderModel();
        inutatccmOfTenderModel.setUid(getLoginUser().getId());
        inutatccmOfTenderModel.setTitle(title);
        inutatccmOfTenderModel.setContent(content);
        inutatccmOfTenderModel.setFileUrl(fileUrl);
        inutatccmOfTenderService.createTenderInfo(inutatccmOfTenderModel);
        return ResultModel.success();
    }

    @RequestMapping("deleteTenderInfo/{id}")
    public ResultModel deleteTenderInfo(@PathVariable Integer id) {
        InutatccmOfTenderModel inutatccmOfTenderModel = inutatccmOfTenderService.viewTenderDetail(id);
        if (inutatccmOfTenderModel == null) {
            LOGGER.info("no such tender id:{} in delete method", id);
            return ResultModel.failed("此招标信息不存在");
        }
        UserModel userModel = getLoginUser();
        if (userModel.getUserType() != UserTypeEnum.EXAMINER.getValue() &&
                userModel.getId() != inutatccmOfTenderModel.getUid()) {
            LOGGER.info("user:{} can't not access to delete tender:{}", userModel.getId(), inutatccmOfTenderModel.getId());
            return ResultModel.failed("没有删除权限");
        }
        inutatccmOfTenderService.deleteTenderInfo(id);
        return ResultModel.success("删除成功");
    }

    @RequestMapping("preEditTenderInfo/{id}")
    public ModelAndView preEditTenderInfo(@PathVariable Integer id) {
        InutatccmOfTenderModel inutatccmOfTenderModel = inutatccmOfTenderService.viewTenderDetail(id);
        if (inutatccmOfTenderModel == null) {
            LOGGER.info("no such tender id:{} in preEdit", id);
            return new ModelAndView("common/404");
        }
        ModelAndView modelAndView = new ModelAndView("tender/edit");
        modelAndView.addObject("tender", inutatccmOfTenderModel);
        modelAndView.addObject("fileMap", gson.fromJson(inutatccmOfTenderModel.getFileUrl(), new TypeToken<Map<String, String>>(){}.getType()));
        return modelAndView;
    }

    @RequestMapping(value = "editTenderInfo", method = RequestMethod.POST)
    public ResultModel editTenderInfo(@RequestParam("id") int id, @RequestParam("title") String title, @RequestParam("content") String content) {
        InutatccmOfTenderModel inutatccmOfTenderModel = inutatccmOfTenderService.viewTenderDetail(id);
        if (inutatccmOfTenderModel == null) {
            LOGGER.info("no such tender id:{} in edit method", id);
            return ResultModel.failed("不存在相应的招标信息");
        }
        inutatccmOfTenderService.editTenderInfo(id, title, content, inutatccmOfTenderModel.getFileUrl());
        return ResultModel.success("修改成功");
    }

    @RequestMapping("viewTenderDetail/{id}")
    public ModelAndView viewTenderDetail(@PathVariable Integer id) {
        InutatccmOfTenderModel inutatccmOfTenderModel = inutatccmOfTenderService.viewTenderDetail(id);
        if (inutatccmOfTenderModel == null) {
            LOGGER.info("tender:{} is not exists", id);
            return new ModelAndView("common/404");
        }
        ModelAndView modelAndView = new ModelAndView("tender/detail");
        modelAndView.addObject("tender", inutatccmOfTenderModel);
        modelAndView.addObject("fileMap", gson.fromJson(inutatccmOfTenderModel.getFileUrl(), new TypeToken<Map<String, String>>(){}.getType()));
        return modelAndView;
    }
}
