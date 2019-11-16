package kybmig.ssm.controller;
import kybmig.ssm.Utility;
import kybmig.ssm.model.TodoModel;
import kybmig.ssm.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
class countController {

    @GetMapping("/count")
    public ModelAndView index() {
        Utility.log("这是todo 主页");
        ModelAndView m = new ModelAndView("/richcity/count");
        return m;
    }
}