package kybmig.ssm.controller;

import kybmig.ssm.Utility;
import kybmig.ssm.model.TopicModel;
import kybmig.ssm.model.UserModel;
import kybmig.ssm.service.TopicCommentService;
import kybmig.ssm.service.TopicService;
import kybmig.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    // 现在如果要用 todoService, 必须在 Controller 里面声明一个 TodoService 属性
    // 同时在构造器函数中, 传入 TodoService 这个参考, 并赋值
    private UserService userService;
    private TopicService topicService;
    private TopicCommentService topicCommentService;

    public UserController(UserService userService,TopicService topicService, TopicCommentService topicCommentService) {
        this.userService = userService;
        this.topicService = topicService;
        this.topicCommentService = topicCommentService;
    }

    @GetMapping("/")
    public ModelAndView rootView(HttpServletRequest request) {
        UserModel current = userService.currentUser(request);
        ModelAndView m = new ModelAndView("index");
        m.addObject("currentUser", current);
        return m;
    }
    @GetMapping("/index")
    public ModelAndView indexView(HttpServletRequest request) {
        UserModel current = userService.currentUser(request);
        ModelAndView m = new ModelAndView("index");
        m.addObject("currentUser", current);
        return m;
    }



    @PostMapping("/user/login")
    public ModelAndView login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userService.validateLogin(username, password)) {
            UserModel current = userService.findByUsername(username);
            HttpSession session = request.getSession();
            session.setAttribute("user_id", current.getId());
            return new ModelAndView("redirect:/index");
        } else {
            return new ModelAndView("redirect:/login");
        }
    }

    @PostMapping("/user/register")
    public ModelAndView register(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String mail = request.getParameter("mail");
        String createdTime = request.getParameter("createdTime");
        String updatedTime = request.getParameter("updatedTime");
        userService.add(username, password, mail, createdTime, updatedTime);
        ModelAndView m = new ModelAndView("redirect:/login");
        return m;
    }

    @GetMapping("/user/profile")
    public ModelAndView profile(int id, int userId) {
        UserModel avatar = userService.findById(id);
        UserModel createdTime = userService.findById(id);
        List<TopicModel> topics = topicService.findByIdList(userId);
        List<TopicModel> ComOnTopics = topicService.findComOnTopicsById(userId);
        List<TopicModel> comments = topicCommentService.findCommentByuserId(userId);
        ModelAndView m = new ModelAndView("profile");
        m.addObject("avatar", avatar);
        m.addObject("createdTime", createdTime);
        m.addObject("topics", topics);
        m.addObject("comments", comments);
        m.addObject("ComOnTopics", ComOnTopics);
        return m;
    }

}
