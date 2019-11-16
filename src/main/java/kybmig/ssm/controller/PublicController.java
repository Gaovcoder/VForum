package kybmig.ssm.controller;

import kybmig.ssm.Utility;
import kybmig.ssm.model.TodoModel;
import kybmig.ssm.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PublicController {

    @GetMapping("/alert")
    public ModelAndView alert() {
        ModelAndView m = new ModelAndView("public/alert");
        return m;
    }

    @GetMapping("/login")
    public ModelAndView loginView() {
        ModelAndView m = new ModelAndView("user/login");
        return m;
    }

    @GetMapping("/register")
    public ModelAndView registerView() {
        ModelAndView m = new ModelAndView("user/register");
        return m;
    }
}
