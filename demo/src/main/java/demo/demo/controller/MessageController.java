package demo.demo.controller;

import java.util.Date;

import demo.demo.entity.Message;
import demo.demo.entity.User;
import demo.demo.service.Impl.MessageServiceImpl;
import demo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class MessageController {


    @Autowired
    private MessageServiceImpl messageServiceImpl;

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @PostMapping("/admin")
    public String actionUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model) {
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

    @GetMapping("/news/{id}")
    public String message(
            @PathVariable(name = "id") Long id,
            Model model) {
        Iterable<Message> messages = messageServiceImpl.getAllByUserId(userService.getLoginUser().getId());
        model.addAttribute("message", messages);
//        Iterable<Message> messages1 = messageServiceImpl.getAllByUserId(id);
//        model.addAttribute("message1", messages1);
        User user = userService.findUserById(id);
        model.addAttribute("user",user);
        return "news";
    }


    @PostMapping("/news/{id}")
    public String sendMessage(
            @PathVariable(name = "id") Long id,
            @RequestParam String text) {

        Date time = new Date();
        Message message = new Message(text, time);
        message.setUser(userService.getLoginUser());
        message.setToUser(userService.findUserById(id));
        messageServiceImpl.save(message);
        return "redirect:/news/" + id;
    }


}
