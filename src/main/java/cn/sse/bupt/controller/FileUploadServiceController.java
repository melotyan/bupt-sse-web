package cn.sse.bupt.controller;

import cn.sse.bupt.model.FileModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Random;


/**
 * Created by hao.yan on 2015/12/21.
 */
@RestController
@RequestMapping("fileUploadService")
public class FileUploadServiceController {
    private final static Logger LOGGER = LoggerFactory.getLogger(FileUploadServiceController.class);
    private final  String FILE_PATH = "D:/file/";

    @RequestMapping("preUploadFile")
    public ModelAndView preUploadFile() {
        return new ModelAndView("file/file_upload");
    }

    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    public FileModel uploadFile(@RequestParam("file") MultipartFile file) {
        FileModel fileModel = new FileModel();
        if (file == null || file.isEmpty()) {
            LOGGER.info("file is null or empty");
            return null;
        }
        String filename = file.getOriginalFilename();
        String extensionName = filename.substring(filename.lastIndexOf("."));
        String saveName = System.currentTimeMillis() + extensionName;
        File localFile = new File(FILE_PATH, saveName);
        if (!localFile.exists())
            localFile.mkdirs();
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
        LOGGER.info("upload file success, filename:{}, file path:{}", filename, FILE_PATH + localFile.getName());
        fileModel.setTitle(filename);
        fileModel.setUrl(FILE_PATH + localFile.getName());
        return fileModel;
    }
}
