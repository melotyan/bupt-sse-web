package cn.sse.bupt.controller;


import cn.sse.bupt.enums.NoticeStatusEnum;
import cn.sse.bupt.enums.UserTypeEnum;
import cn.sse.bupt.model.NoticeModel;
import cn.sse.bupt.model.ResultModel;
import cn.sse.bupt.model.UserModel;
import cn.sse.bupt.service.NoticeService;
import com.alibaba.dubbo.common.json.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
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
import java.util.Map;

/**
 * Created by hao.yan on 2015/12/21.
 */
@RestController
@RequestMapping("noticeService")
public class NoticeServiceController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(NoticeServiceController.class);
    private final int PAGE_SIZE = 50;
    private Gson gson = new Gson();

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("prePublishNotice")
    public ModelAndView prePublishNotice() {
        return new ModelAndView("notice/notice_publish");
    }

    @RequestMapping("publishNotice")
    public ResultModel publishNotice(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("files")String files) {
        UserModel userModel = getLoginUser();
        NoticeModel noticeModel = new NoticeModel();
        noticeModel.setTitle(title);
        noticeModel.setContent(content);
        noticeModel.setUid(userModel.getId());
        noticeModel.setUpdateUid(userModel.getId());
        noticeModel.setFileUrls(files);
        noticeModel.setCreateTime(new Date());
        noticeModel.setNoticeStatus(NoticeStatusEnum.NORMAL.getValue());
        noticeService.publishNotice(noticeModel);
        LOGGER.info("user {} publish a new notice", userModel.getId());
        return ResultModel.success();
    }

    @RequestMapping("viewNoticeDetail/{id}")
    public ModelAndView viewNotice(@PathVariable Integer id) {
        NoticeModel noticeModel = noticeService.findNoticeById(id);
        if (noticeModel == null)
            return new ModelAndView("common/404");
        ModelAndView mav = new ModelAndView("notice/detail", "noticeModel", noticeModel);
        mav.addObject("fileMap", gson.fromJson(noticeModel.getFileUrls(), new TypeToken<Map<String, String>>(){}.getType()));
        LOGGER.info("view notice detail success, notice id:{}", id);
        return mav;
    }

    @RequestMapping("listAllNotices/{page}")
    public ModelAndView listNotices(@PathVariable Integer page) {
        if (page == null || page <= 0)
            page = 1;
        int offset = (page - 1) * PAGE_SIZE;
        List<NoticeModel> noticeModels = noticeService.listNotice(offset, PAGE_SIZE);
        return new ModelAndView("notice/list", "notices", noticeModels);
    }

    @RequestMapping("listAllNotices/data")
    public List<NoticeModel>  listNoticesData() {
        return noticeService.listNotice(1, 10);
    }

    @RequestMapping("deleteNotice/{id}")
    public ResultModel deleteNotice(@PathVariable Integer id) {
        NoticeModel noticeModel = noticeService.findNoticeById(id);
        if (noticeModel == null) {
            return ResultModel.failed("不存在相关的公告");
        }
        UserModel userModel = getLoginUser();
        if (userModel.getUserType() != UserTypeEnum.EXAMINER.getValue() && noticeModel.getUid() != userModel.getId()) {
            return ResultModel.failed("没有相关权限");
        }
        noticeService.deleteNotice(id);
        LOGGER.info("success delete notice {}", id);
        return ResultModel.success();
    }

    @RequestMapping("updateNotice")
    public ResultModel updateNotice(@RequestParam("id") Integer id, @RequestParam("title") String title, @RequestParam("content") String content) {
        NoticeModel noticeModel = noticeService.findNoticeById(id);
        if (noticeModel == null) {
            return ResultModel.failed("不存在相关的公告");
        }
        UserModel userModel = getLoginUser();
        if (userModel.getUserType() != UserTypeEnum.EXAMINER.getValue() && noticeModel.getUid() != userModel.getId()) {
            return ResultModel.failed("没有相关权限");
        }
        noticeService.updateNotice(id, userModel.getId(), title, content);
        return ResultModel.success();
    }

    @RequestMapping("preUpdateNotice/{id}")
    public ModelAndView preUpdateNotice(@PathVariable Integer id) {
        NoticeModel noticeModel = noticeService.findNoticeById(id);
        if (noticeModel == null)
            return new ModelAndView("common/404");
        ModelAndView mav = new ModelAndView("notice/edit", "notice", noticeModel);
        Map<String, String> map = gson.fromJson(noticeModel.getFileUrls(), new TypeToken<Map<String, String>>(){}.getType());
        mav.addObject("fileMap", map);
        return mav;
    }


}
