package cn.sse.bupt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by hao.yan on 2015/12/29.
 */
@RestController
@RequestMapping("/")
public class HomeController {

    @RequestMapping("")
    public ModelAndView home() {
        return new ModelAndView("index");
    }
}
