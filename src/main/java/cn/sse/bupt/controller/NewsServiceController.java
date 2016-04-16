package cn.sse.bupt.controller;

import cn.sse.bupt.enums.UserTypeEnum;
import cn.sse.bupt.model.NewsModel;
import cn.sse.bupt.model.ResultModel;
import cn.sse.bupt.model.UserModel;
import cn.sse.bupt.service.NewsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by melot on 2016/4/16.
 */
@RestController
@RequestMapping("newsService")
public class NewsServiceController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsServiceController.class);
    private Gson gson = new Gson();
    private final int PAGE_SIZE = 50;

    @Autowired
    private NewsService newsService;

    @RequestMapping("prePublishNews")
    public ModelAndView prePublishNews() {
        return new ModelAndView("news/create");
    }

    @RequestMapping("publishNews")
    public ResultModel publishNews(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("files") String fileUrls) {
        if (title.equals(""))
            return ResultModel.failed("标题不能为空");
        if (content.equals(""))
            return ResultModel.failed("内容不能为空");
        UserModel userModel = getLoginUser();
        NewsModel newsModel = new NewsModel();
        newsModel.setTitle(title);
        newsModel.setContent(content);
        newsModel.setUid(userModel.getId());
        newsModel.setUpdateUid(userModel.getId());
        newsModel.setCreateTime(new Date());
        newsModel.setFileUrls(fileUrls);
        if (!fileUrls.equals("{}")) {
            Map<String, String> map = gson.fromJson(fileUrls, new TypeToken<Map<String, String>>(){}.getType());
            for (String key : map.keySet()) {
                newsModel.setFirstPic(key);
                LOGGER.info("first pic url is {}", key);
                break;
            }
        }
        newsService.publishNews(newsModel);
        return ResultModel.success("发布成功");
    }

    @RequestMapping("listNews/page/{page}")
    public ModelAndView listNews(@PathVariable Integer page) {
        if (page == null || page < 1)
            page = 1;
        List<NewsModel> list =newsService.listNews(page, PAGE_SIZE);
        return new ModelAndView("news/list", "list", list);
    }

    @RequestMapping("viewNewsDetail/id/{id}")
    public ModelAndView viewNewsDetail(@PathVariable Integer id) {
        NewsModel newsModel = newsService.viewNewsDetail(id);
        if (newsModel == null) {
            LOGGER.info("no such news {}", id);
            return new ModelAndView("common/404");
        }
        Map<String, String> fileMap = gson.fromJson(newsModel.getFileUrls(), new TypeToken<Map<String, String>>() {}.getType());
        ModelAndView modelAndView = new ModelAndView("news/detail", "newsModel", newsModel);
        modelAndView.addObject("fileMap", fileMap);
        return modelAndView;
    }

    @RequestMapping("deleteNews/id/{id}")
    public ResultModel deleteNews(@PathVariable Integer id) {
        NewsModel newsModel = newsService.viewNewsDetail(id);
        if (newsModel == null) {
            LOGGER.info("no such news {}", id);
            return ResultModel.failed("不存在相应的新闻");
        }
        UserModel userModel = getLoginUser();
        if (userModel.getId() != newsModel.getUid() && userModel.getUserType() != UserTypeEnum.EXAMINER.getValue()) {
            LOGGER.info("user {} has no delete privilege", userModel.getUsername());
            return ResultModel.failed("没有删除权限");
        }
        newsService.deleteNews(id);
        LOGGER.info("delete success");
        return ResultModel.success("删除成功");
    }

    @RequestMapping("preUpdateNews/id/{id}")
    public ModelAndView preUpdateNews(@PathVariable Integer id) {
        NewsModel newsModel = newsService.viewNewsDetail(id);
        if (newsModel == null) {
            LOGGER.info("no such news {}", newsModel.getId());
            return new ModelAndView("common/404");
        }
        ModelAndView mav = new ModelAndView("news/edit", "news", newsModel);
        Map<String, String> map = gson.fromJson(newsModel.getFileUrls(), new TypeToken<Map<String, String>>(){}.getType());
        mav.addObject("fileMap", map);
        return mav;
    }

    @RequestMapping("updateNews")
    public ResultModel updateNews(@RequestParam("id") Integer id, @RequestParam("title") String title, @RequestParam("content") String content) {
        NewsModel newsModel = newsService.viewNewsDetail(id);
        if (newsModel == null) {
            return ResultModel.failed("不存在此新闻");
        }
        UserModel userModel = getLoginUser();
        if (userModel.getUserType() != UserTypeEnum.EXAMINER.getValue() && userModel.getId() != newsModel.getUid()) {
            return ResultModel.failed("没有编辑权限");
        }
        newsModel.setTitle(title);
        newsModel.setContent(content);
        newsModel.setUpdateUid(userModel.getId());
        newsService.editNews(newsModel);
        return ResultModel.success("编辑成功");
    }

}
