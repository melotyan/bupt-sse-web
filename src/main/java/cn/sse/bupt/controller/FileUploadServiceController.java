package cn.sse.bupt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by hao.yan on 2015/12/21.
 */
@RestController
@RequestMapping("fileUploadService")
public class FileUploadServiceController {
    private final static Logger LOGGER = LoggerFactory.getLogger(FileUploadServiceController.class);

    @RequestMapping("preUploadFile")
    public ModelAndView preUploadFile() {
        return new ModelAndView("file/file_upload");
    }

    @RequestMapping("uploadFile")
    public ModelAndView uploadFile(HttpServletRequest request) {
        return new ModelAndView();
//        MultipartRequest mRequest = (MultipartRequest) request;
//        MultipartFile file = mRequest.getFile("file");
//        String url = "";
//        if (file != null) {
//            try {
//                UploadReturnModel model = FileUploadService.uploadFromBytes(file.getBytes(),
//                        file.getOriginalFilename(), FileSavedType.toast_image);
//                if (model.getStatus() > 0) {
//                    url = model.getLocalUrl();
//                }
//            } catch (IOException e) {
//                LOGGER.warn(e.getMessage());
//            }
//        }
//        Map<String, Object> result = new HashMap<String, Object>();
//        result.put("result", 1);
//        result.put("message", "");
//        result.put("curl", url);
//        if (url.startsWith("n")) {
//            result.put("url", FileService.url(url, "c"));
//        } else {
//            result.put("url", url);
//        }
//
//        return result;
//    }
}
}
