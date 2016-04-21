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

    @RequestMapping("guide/tenderGuide")
    public ModelAndView tenderGuide() {
        return new ModelAndView("tender/tender-guide");
    }

    @RequestMapping("guide/suggestGuide")
    public ModelAndView suggestGuide() {
        return new ModelAndView("suggestion/suggest-guide");
    }

    @RequestMapping("guide/complainGuide")
    public ModelAndView complainGuide() {
        return new ModelAndView("suggestion/complain-guide");
    }
}
