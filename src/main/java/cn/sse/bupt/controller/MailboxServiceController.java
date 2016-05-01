package cn.sse.bupt.controller;

import cn.sse.bupt.enums.ReceiverStatusEnum;
import cn.sse.bupt.enums.SenderStatusEnum;
import cn.sse.bupt.model.MailboxModel;
import cn.sse.bupt.model.ResultModel;
import cn.sse.bupt.model.UserModel;
import cn.sse.bupt.service.MailboxService;
import cn.sse.bupt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by melot on 2016/5/1.
 */
@RestController
@RequestMapping("mailboxService")
public class MailboxServiceController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(MailboxServiceController.class);

    @Autowired
    private MailboxService mailboxService;
    @Autowired
    private UserService userService;

    @RequestMapping("viewInbox")
    public ModelAndView viewInbox() {
        UserModel userModel = getLoginUser();
        List<MailboxModel> list = mailboxService.viewInbox(userModel.getUsername());
        return new ModelAndView("mail/inbox", "list", list);
    }

    @RequestMapping("viewOutbox")
    public ModelAndView viewOutbox() {
        UserModel userModel = getLoginUser();
        List<MailboxModel> list = mailboxService.viewOutbox(userModel.getUsername());
        return new ModelAndView("mail/outbox", "list", list);
    }

    @RequestMapping("viewDrafts")
    public ModelAndView viewDrafts() {
        UserModel userModel = getLoginUser();
        List<MailboxModel> list = mailboxService.viewDrafts(userModel.getUsername());
        return new ModelAndView("mail/drafts", "list", list);
    }

    @RequestMapping("sendMail")
    public ResultModel sendMail(@RequestParam("receiver") String receiver, @RequestParam("title") String title, @RequestParam("content") String content) {
        if (userService.findUserByUsername(receiver) == null) {
            LOGGER.info("user {} is not exists", receiver);
            return ResultModel.failed("收件人不存在，请重新确认");
        }
        if (title == null || title.equals(""))
            return ResultModel.failed("信件标题不能为空");
        if (content == null || content.equals(""))
            return ResultModel.failed("信件内容不能为空");
        UserModel userModel = getLoginUser();
        MailboxModel mailboxModel = new MailboxModel();
        mailboxModel.setUid(userModel.getId());
        mailboxModel.setSenderName(userModel.getUsername());
        mailboxModel.setReceiverName(receiver);
        mailboxModel.setSenderStatus(SenderStatusEnum.SENDED.getValue());
        mailboxModel.setReceiverStatus(ReceiverStatusEnum.NOT_READ.getValue());
        mailboxModel.setTitle(title);
        mailboxModel.setContent(content);
        mailboxService.sendMail(mailboxModel);
        return ResultModel.success("信件发送成功");
    }

    @RequestMapping("saveDraft")
    public ResultModel saveDraft(@RequestParam("receiver") String receiver, @RequestParam("title") String title, @RequestParam("content") String content) {
        UserModel userModel = getLoginUser();
        MailboxModel mailboxModel = new MailboxModel();
        mailboxModel.setUid(userModel.getId());
        mailboxModel.setSenderName(userModel.getUsername());
        mailboxModel.setReceiverName(receiver);
        mailboxModel.setSenderStatus(SenderStatusEnum.DRAFT.getValue());
        mailboxModel.setReceiverStatus(ReceiverStatusEnum.NOT_READ.getValue());
        mailboxModel.setTitle(title);
        mailboxModel.setContent(content);
        mailboxService.sendMail(mailboxModel);
        return ResultModel.success("信件已存入草稿箱");
    }

    @RequestMapping("editDraft")
    public ResultModel editDraft(@RequestParam("id") int id, @RequestParam("receiver") String receiver,
                                 @RequestParam("title") String title, @RequestParam("content") String content) {
        MailboxModel mailboxModel =mailboxService.readMail(id);
        if (mailboxModel == null) {
            LOGGER.info("no such MailboxModel id:{}", id);
            return ResultModel.failed("此草稿已经不存在");
        }
        if (userService.findUserByUsername(receiver) == null) {
            LOGGER.info("user {} is not exists", receiver);
            return ResultModel.failed("收件人不存在，请重新确认");
        }
        if (title == null || title.equals(""))
            return ResultModel.failed("信件标题不能为空");
        if (content == null || content.equals(""))
            return ResultModel.failed("信件内容不能为空");
        if (mailboxModel.getUid() != getLoginUser().getId()) {
            return ResultModel.failed("对不起,您没有修改权限");
        }
        mailboxService.editDraft(id, receiver, title, content);
        return ResultModel.success("草稿修改成功");
    }

    @RequestMapping("sendDraft")
    public ResultModel sendDraft(@RequestParam("id") int id) {
        MailboxModel mailboxModel =mailboxService.readMail(id);
        if (mailboxModel == null) {
            LOGGER.info("no such MailboxModel id:{}", id);
            return ResultModel.failed("此草稿已经不存在");
        }
        if (mailboxModel.getUid() != getLoginUser().getId()) {
            return ResultModel.failed("对不起,您没有发送此邮件的权限");
        }
        mailboxService.sendDraft(id);
        return ResultModel.success("信件发送成功");
    }


}
