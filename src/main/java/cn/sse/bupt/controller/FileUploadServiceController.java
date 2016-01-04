package cn.sse.bupt.controller;

import cn.sse.bupt.model.FileModel;
import cn.sse.bupt.model.ResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;


/**
 * Created by hao.yan on 2015/12/21.
 */
@RestController
@RequestMapping("fileUploadService")
public class FileUploadServiceController {
    private final static Logger LOGGER = LoggerFactory.getLogger(FileUploadServiceController.class);
    private final  String FILE_PATH = "/ROOT/file/web/bupt-sse-web";

    @RequestMapping("preUploadFile")
    public ModelAndView preUploadFile() {
        return new ModelAndView("file/file_upload");
    }

    @RequestMapping("uploadFile")
    public FileModel uploadFile(@RequestParam("file") MultipartFile file) {
        FileModel fileModel = new FileModel();
        if (file == null || file.isEmpty()) {
            LOGGER.warn("file is null or empty");
            return null;
        }
        String filename = file.getOriginalFilename();
        String extensionName = filename.substring(filename.lastIndexOf(".") + 1);
        String saveName = System.currentTimeMillis() + extensionName;
        File localFile = new File(FILE_PATH, saveName);
        if (!localFile.exists())
            localFile.mkdirs();
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
        LOGGER.info("upload file success, file path:{}", localFile.getName());
        fileModel.setTitle(filename);
        fileModel.setUrl(localFile.getName());
        return fileModel;
    }
}
