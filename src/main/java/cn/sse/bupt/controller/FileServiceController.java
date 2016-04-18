package cn.sse.bupt.controller;

import cn.sse.bupt.enums.FileClassEnum;
import cn.sse.bupt.model.FileModel;
import cn.sse.bupt.service.FileService;
import cn.sse.bupt.util.RequestUtil;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by hao.yan on 2015/12/21.
 */
@RestController
@RequestMapping("fileService")
public class FileServiceController {
    private final static Logger LOGGER = LoggerFactory.getLogger(FileServiceController.class);
    private final String FILE_PATH = "/resources/file/";
    private Gson gson = new Gson();

    @Autowired
    private FileService fileService;

    @RequestMapping("preUploadFile")
    public ModelAndView preUploadFile() {
        return new ModelAndView("file/file_upload");
    }

    @RequestMapping(value = "uploadFiles", method = RequestMethod.POST)
    public String uploadFiles(@RequestParam("files") MultipartFile[] files,
                             @RequestParam(value = "type", defaultValue = "0") int type) {
        Map<String, String> fileMap = new HashMap<String, String>();
        String savePath = RequestUtil.getRequest().getSession().getServletContext().getRealPath(FILE_PATH);
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                LOGGER.info("file is Empty");
                continue;
            }
            String filename = file.getOriginalFilename();
            String extensionName = filename.substring(filename.lastIndexOf("."));
            String saveName = System.currentTimeMillis()  + extensionName;
            File localFile = new File(savePath, saveName);
            if (!localFile.exists()) {
                LOGGER.info("make a new director");
                localFile.mkdirs();
            }
            try {
                file.transferTo(localFile);
            } catch (IOException e) {
                LOGGER.error("filer upload error:{}", e.toString());
            }
            fileMap.put(FILE_PATH + saveName, filename);
            LOGGER.info("upload file success, filename:{}, file path:{}", filename, FILE_PATH + saveName);
            FileModel fileModel = new FileModel();
            fileModel.setTitle(filename);
            fileModel.setNid(type);
            fileModel.setUrl(FILE_PATH + saveName);
            fileService.saveFile(fileModel);
        }
        String fileUrls = gson.toJson(fileMap);
        StringBuilder sb = new StringBuilder();
        for (String value : fileMap.values()) {
            sb.append(value).append("\r\n");
        }
        LOGGER.info("file urls is {}", fileUrls);
        return fileUrls;
    }

    @RequestMapping(value = "download", method = RequestMethod.POST)
    public ResponseEntity<byte[]> download(@RequestParam("filename") String filename, @RequestParam("path") String path)  {
        File file = new File(path);
        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            httpHeaders.setContentDispositionFormData("attachment", new String(filename.getBytes("UTF-8"), "iso-8859-1"));
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                    httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("{}", e);
        }
        return null;
    }

    @RequestMapping("listFiles/type/{type}")
    public ModelAndView listFiles(@PathVariable Integer type) {
        if (type != FileClassEnum.LOWS.getValue() && type != FileClassEnum.POLICY.getValue()
                &&  type != FileClassEnum.REPORT.getValue())
            return new ModelAndView("common/404");
        List<FileModel> list = fileService.getFileByNid(type);
        ModelAndView modelAndView = new ModelAndView("file/list", "list", list);
        modelAndView.addObject("type", type);
        return modelAndView;
    }
}
