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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
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

    @RequestMapping("home")
    public ModelAndView mailHome() {
        return new ModelAndView("mail/mail");
    }

    @RequestMapping("readMail/id/{id}")
    public ModelAndView readMail(@PathVariable Integer id) {
        MailboxModel mailboxModel = mailboxService.readMail(id);
        if (mailboxModel == null || mailboxModel.getUid() != getLoginUser().getId()) {
            LOGGER.info("mailbox model is {}", mailboxModel);
            return new ModelAndView("coommon/404");
        }
        return new ModelAndView("mail/detail", "mail", mailboxModel);
    }

    @RequestMapping("preEditDraft/id/{id}")
    public ModelAndView preEditDraft(@PathVariable Integer id) {
        MailboxModel mailboxModel = mailboxService.readMail(id);
        if (mailboxModel == null || mailboxModel.getUid() != getLoginUser().getId()) {
            LOGGER.info("cannot find mail or mailUid not equal to loginUser id mailId:{}", id);
            return new ModelAndView("common/404");
        }
        return new ModelAndView("mail/edit", "mail", mailboxModel);

    }
    @RequestMapping("preResponseMail/id/{id}")
    public ModelAndView preResponseMail(@PathVariable Integer id) {
        MailboxModel mailboxModel = mailboxService.readMail(id);
        if (mailboxModel == null || mailboxModel.getUid() != getLoginUser().getId()) {
            LOGGER.info("no such mail id:{}", id);
            return new ModelAndView("common/404");
        }
        return new ModelAndView("mail/create", "mail", mailboxModel);
    }

    @RequestMapping("preMakeMail")
    public ModelAndView preMakeMail(@RequestParam(value = "receiver", defaultValue = "") String receiver) {
        return new ModelAndView("mail/create", "receiver", receiver);
    }

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
        return new ModelAndView("mail/outbox", "list", list);    }

    @RequestMapping("viewDrafts")
    public ModelAndView viewDrafts() {
        UserModel userModel = getLoginUser();
        List<MailboxModel> list = mailboxService.viewDrafts(userModel.getUsername());
        return new ModelAndView("mail/drafts", "list", list);    }

    @RequestMapping(value = "sendMail", method = RequestMethod.POST)
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
        mailboxModel.setSendTime(new Date());
        mailboxService.sendMail(mailboxModel);
        return ResultModel.success("信件发送成功");
    }

    @RequestMapping(value = "saveDraft", method = RequestMethod.POST)
    public ResultModel saveDraft(@RequestParam(value="receiver", defaultValue = "") String receiver, @RequestParam(value = "title", defaultValue = "") String title, @RequestParam(value = "content", defaultValue = "") String content) {
        UserModel userModel = getLoginUser();
        MailboxModel mailboxModel = new MailboxModel();
        mailboxModel.setUid(userModel.getId());
        mailboxModel.setSenderName(userModel.getUsername());
        mailboxModel.setReceiverName(receiver);
        mailboxModel.setSenderStatus(SenderStatusEnum.DRAFT.getValue());
        mailboxModel.setReceiverStatus(ReceiverStatusEnum.NOT_READ.getValue());
        mailboxModel.setTitle(title);
        mailboxModel.setContent(content);
        mailboxModel.setSendTime(new Date());
        mailboxService.sendMail(mailboxModel);
        return ResultModel.success("信件已存入草稿箱");
    }

    @RequestMapping(value = "editDraft", method = RequestMethod.POST)
    public ResultModel editDraft(@RequestParam("id") int id, @RequestParam("receiver") String receiver,
                                 @RequestParam("title") String title, @RequestParam("content") String content) {
        MailboxModel mailboxModel =mailboxService.readMail(id);
        if (mailboxModel == null || mailboxModel.getSenderStatus() != SenderStatusEnum.DRAFT.getValue()) {
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

    @RequestMapping("sendDraft/id/{id}")
    public ResultModel sendDraft(@PathVariable Integer id) {
        MailboxModel mailboxModel = mailboxService.readMail(id);
        if (mailboxModel == null || mailboxModel.getSenderStatus() != SenderStatusEnum.DRAFT.getValue()) {
            LOGGER.info("no such MailboxModel id:{}", id);
            return ResultModel.failed("此草稿已经不存在");
        }
        if (mailboxModel.getUid() != getLoginUser().getId()) {
            return ResultModel.failed("对不起,您没有发送此邮件的权限");
        }
        mailboxService.sendDraft(id);
        return ResultModel.success("信件发送成功");
    }

    @RequestMapping("deleteSendedMail/id/{id}")
    public ResultModel deleteSendedMail(@PathVariable Integer id) {
        MailboxModel mailboxModel = mailboxService.readMail(id);
        if (mailboxModel == null || mailboxModel.getSenderStatus() == SenderStatusEnum.DELETED.getValue()) {
            LOGGER.info("no such mail id :{}", id);
            return ResultModel.failed("此信件不存在");
        }
        if (mailboxModel.getUid() != getLoginUser().getId()) {
            LOGGER.info("user {} has no permission to delete mail {}", getLoginUser().getUsername(), mailboxModel.getId());
            return ResultModel.failed("对不起，您没有删除些邮件的权限");
        }
        mailboxService.deleteSendedMail(id);
        return ResultModel.success("删除成功");
    }

    @RequestMapping("deleteReceivedMail/id/{id}")
    public ResultModel deleteReceivedMail(@PathVariable Integer id) {
        MailboxModel mailboxModel = mailboxService.readMail(id);
        if (mailboxModel == null || mailboxModel.getReceiverStatus() == ReceiverStatusEnum.DELETED.getValue()) {
            LOGGER.info("no such mail id :{}", id);
            return ResultModel.failed("此信件不存在");
        }
        if (mailboxModel.getUid() != getLoginUser().getId()) {
            LOGGER.info("user {} has no permission to delete mail {}", getLoginUser().getUsername(), mailboxModel.getId());
            return ResultModel.failed("对不起，您没有删除此邮件的权限");
        }
        mailboxService.deleteReceivedMail(id);
        return ResultModel.success("删除成功");
    }

    @RequestMapping("setMailReaded/id/{id}")
    public ResultModel setMailReaded(@PathVariable Integer id) {
        MailboxModel mailboxModel = mailboxService.readMail(id);
        if (mailboxModel == null || mailboxModel.getReceiverStatus() == ReceiverStatusEnum.DELETED.getValue()) {
            LOGGER.info("no such mail id :{}", id);
            return ResultModel.failed("此信件不存在");
        }
        if (mailboxModel.getReceiverStatus() == ReceiverStatusEnum.READED.getValue())
            return ResultModel.success();

        if (mailboxModel.getUid() != getLoginUser().getId()) {
            LOGGER.info("user {} has no permission to delete mail {}", getLoginUser().getUsername(), mailboxModel.getId());
            return ResultModel.failed("对不起，您没有修改此邮件的权限");
        }
        mailboxService.setMailReaded(id);
        return ResultModel.success();
    }

    @RequestMapping("deleteMail/id/{id}")
    public ResultModel deleteMail(@PathVariable Integer id) {
        MailboxModel mailboxModel = mailboxService.readMail(id);
        UserModel userModel = getLoginUser();
        if (mailboxModel == null) {
            LOGGER.info("mail id :{} is not exists", id);
            return ResultModel.failed("信件不存在");
        }
        if (mailboxModel.getUid() != userModel.getId()) {
            LOGGER.info("user id:{} has no permission to delete mail {}", userModel.getId(), mailboxModel.getId());
            return ResultModel.failed("没有删除权限");
        }
        if (userModel.getUsername().equals(mailboxModel.getReceiverName()))
            deleteReceivedMail(id);
        if (userModel.getUsername().equals(mailboxModel.getSenderName()))
            deleteSendedMail(id);
        return ResultModel.success("删除信件成功");
    }


}
