package cn.sse.bupt.controller;


import cn.sse.bupt.common.SessionConstants;
import cn.sse.bupt.enums.NoticeStatusEnum;
import cn.sse.bupt.model.NoticeModel;
import cn.sse.bupt.model.ResultModel;
import cn.sse.bupt.model.UserModel;
import cn.sse.bupt.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by hao.yan on 2015/12/21.
 */
@RestController
@RequestMapping("noticeService")
public class NoticeServiceController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(NoticeServiceController.class);

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("prePublishNotice")
    public ModelAndView prePublishNotice() {
        return new ModelAndView("notice/notice_publish");
    }

    @RequestMapping("publishNotice")
    public ResultModel publishNotice(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("fileUrls") String fileUrls) {
        UserModel userModel = getLoginUser();
        NoticeModel noticeModel = new NoticeModel();
        noticeModel.setTitle(title);
        noticeModel.setContent(content);
        noticeModel.setUid(userModel.getId());
        noticeModel.setUpdateUid(userModel.getId());
        noticeModel.setFileUrls(fileUrls);
        noticeModel.setCreateTime(new Date());
        noticeModel.setNoticeStatus(NoticeStatusEnum.NORMAL.getValue());
        noticeService.publishNotice(noticeModel);
        LOGGER.info("user {} publish a new notice", userModel.getId());
        return ResultModel.success();
    }





}
