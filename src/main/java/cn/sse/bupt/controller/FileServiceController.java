package cn.sse.bupt.controller;

import cn.sse.bupt.model.FileModel;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;


/**
 * Created by hao.yan on 2015/12/21.
 */
@RestController
@RequestMapping("fileService")
public class FileServiceController {
    private final static Logger LOGGER = LoggerFactory.getLogger(FileServiceController.class);
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

    @RequestMapping(value = "download", method = RequestMethod.POST)
    public ResponseEntity download(@RequestParam("filename") String filename, @RequestParam("path") String path) throws Exception {
        File file = new File(path);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment", filename);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                httpHeaders, HttpStatus.CREATED);
    }
}
