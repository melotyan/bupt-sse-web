package cn.sse.bupt.controller;

import cn.sse.bupt.enums.UserTypeEnum;
import cn.sse.bupt.model.InutatccmOfTenderModel;
import cn.sse.bupt.model.ResultModel;
import cn.sse.bupt.model.TenderModel;
import cn.sse.bupt.model.UserModel;
import cn.sse.bupt.service.InutatccmOfTenderService;
import cn.sse.bupt.service.TenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by melot on 2016/4/12.
 */
@RestController
@RequestMapping("tenderService")
public class TenderServiceController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(TenderServiceController.class);
    private final int PAGE_SIZE = 50;

    @Autowired
    private TenderService tenderService;

    @Autowired
    private InutatccmOfTenderService inutatccmOfTenderService;

    @RequestMapping("preCompetitiveTender/tid/{tid}")
    public ModelAndView preCompetitiveTender(@PathVariable int tid) {
        InutatccmOfTenderModel inutatccmOfTenderModel = inutatccmOfTenderService.viewTenderDetail(tid);
        if (inutatccmOfTenderModel == null) {
            LOGGER.info("no such tender:{}", tid);
            return new ModelAndView("common/404");
        }
        ModelAndView modelAndView = new ModelAndView("tender/competitive");
        modelAndView.addObject("tid", tid);
        modelAndView.addObject("title", inutatccmOfTenderModel.getTitle());
        return modelAndView;
    }

    @RequestMapping(value = "competitiveTender", method = RequestMethod.POST)
    public ResultModel competitiveTender(@RequestParam("tid") int tid, @RequestParam("title") String title, @RequestParam("fileUrl") String fileUrl) {
        if (fileUrl == null || fileUrl.equals("{}")) {
            return ResultModel.failed("必须上传竞标文件");
        }
        UserModel userModel = getLoginUser();
        if (userModel.getUserType() != UserTypeEnum.CUSTOMER.getValue()) {
            LOGGER.info("user:{} is not customer", userModel.getId());
            return ResultModel.failed("政府工作人员不得参与竞标");
        }
        tenderService.competitiveTender(userModel.getId(), tid, title, fileUrl);
        return ResultModel.success("竞标成功，请耐心等待结果");
    }

    @RequestMapping("listMyCompetitive")
    public ModelAndView listMyCompetitive() {
        UserModel userModel = getLoginUser();
        List<TenderModel> list = tenderService.listTenderByUid(userModel.getId());
        ModelAndView modelAndView = new ModelAndView("tender/list-competitive");
        modelAndView.addObject("list", list);
        modelAndView.addObject("type", "uid");
        return modelAndView;
    }

    @RequestMapping("listCompetitive/tid/{tid}")
    public ModelAndView listCompetitive(@PathVariable Integer tid) {
        InutatccmOfTenderModel inutatccmOfTenderModel = inutatccmOfTenderService.viewTenderDetail(tid);
        if (inutatccmOfTenderModel == null) {
            LOGGER.info("no such tender:{}", tid);
            return new ModelAndView("common/404");
        }
        List<TenderModel> list = tenderService.listTenderByTid(tid);
        ModelAndView modelAndView = new ModelAndView("tender/list-competitive");
        modelAndView.addObject("list", list);
        modelAndView.addObject("type", "tid");
        modelAndView.addObject("tender", inutatccmOfTenderModel);
        return modelAndView;
    }


}
