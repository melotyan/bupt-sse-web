package cn.sse.bupt.controller;


import cn.sse.bupt.common.SessionConstants;
import cn.sse.bupt.enums.NoticeStatusEnum;
import cn.sse.bupt.model.NoticeModel;
import cn.sse.bupt.model.ResultModel;
import cn.sse.bupt.model.UserModel;
import cn.sse.bupt.service.NoticeService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by hao.yan on 2015/12/21.
 */
@RestController
@RequestMapping("noticeService")
public class NoticeServiceController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(NoticeServiceController.class);
    private final  String FILE_PATH = "D:/file/";

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("prePublishNotice")
    public ModelAndView prePublishNotice() {
        return new ModelAndView("notice/notice_publish");
    }

    @RequestMapping("publishNotice")
    public ResultModel publishNotice(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("files")MultipartFile[] files) {
        UserModel userModel = getLoginUser();
        NoticeModel noticeModel = new NoticeModel();
        noticeModel.setTitle(title);
        noticeModel.setContent(content);
        noticeModel.setUid(userModel.getId());
        noticeModel.setUpdateUid(userModel.getId());
        noticeModel.setFileUrls(getFileUrlJson(files));
        noticeModel.setCreateTime(new Date());
        noticeModel.setNoticeStatus(NoticeStatusEnum.NORMAL.getValue());
        noticeService.publishNotice(noticeModel);
        LOGGER.info("user {} publish a new notice", userModel.getId());
        return ResultModel.success();
    }

    private String getFileUrlJson(MultipartFile[] files) {
        Map<String, String> fileMap = new HashMap<String, String>();
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                LOGGER.info("file is Empty");
                continue;
            }
            String filename = file.getOriginalFilename();
            String extensionName = filename.substring(filename.lastIndexOf("."));
            String saveName = System.currentTimeMillis()  + extensionName;
            File localFile = new File(FILE_PATH, saveName);
            if (!localFile.exists())
                localFile.mkdirs();
            try {
                file.transferTo(localFile);
            } catch (IOException e) {
                LOGGER.error(e.toString());
            }
            fileMap.put(FILE_PATH + localFile.getName(), filename);
            LOGGER.info("upload file success, filename:{}, file path:{}", filename, FILE_PATH + localFile.getName());
        }
        String fileUrls = new Gson().toJson(fileMap);
        LOGGER.info("file urls is {}", fileUrls);
        return fileUrls;
    }



}
