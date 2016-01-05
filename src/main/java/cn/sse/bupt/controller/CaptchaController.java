package cn.sse.bupt.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * Created by hao.yan on 2016/1/4.
 */
@RestController
@RequestMapping("/")
public class CaptchaController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CaptchaController.class);

    @Autowired
    private Producer producer;

    @RequestMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Progma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = producer.createText();
        LOGGER.info("the captcha is:{}", capText);
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        BufferedImage bufferedImage = producer.createImage(capText);
        try {
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpg", out);
            out.flush();
            out.close();
        } catch (Exception e) {
            LOGGER.error("{}", e);
        }

    }
}
