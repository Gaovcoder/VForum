package kybmig.ssm.controller;

import kybmig.ssm.Utility;
import kybmig.ssm.mapper.TopicMapper;
import kybmig.ssm.model.TodoModel;
import kybmig.ssm.model.TopicCommentModel;
import kybmig.ssm.model.TopicModel;
import kybmig.ssm.model.UserModel;
import kybmig.ssm.service.TodoService;
import kybmig.ssm.service.TopicCommentService;
import kybmig.ssm.service.TopicService;
import kybmig.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
public class TopicController {

    private HashMap<String, String> tokenMap = new HashMap<>();

    // 现在如果要用 todoService, 必须在 Controller 里面声明一个 TodoService 属性
    // 同时在构造器函数中, 传入 TodoService 这个参考, 并赋值
    private TopicService topicService;
    private UserService userService;
    private TopicCommentService topicCommentService;

    public TopicController(TopicService topicService, UserService userService, TopicCommentService topicCommentService) {
        this.topicService = topicService;
        this.userService = userService;
        this.topicCommentService = topicCommentService;
    }

    @GetMapping("/topic")//原TOPIC
    public ModelAndView index(HttpServletRequest request) {
        List<TopicModel> topics = topicService.all();
        ModelAndView m = new ModelAndView("topic/topic_index");
        String token = UUID.randomUUID().toString();
        UserModel user = userService.currentUser(request);
        tokenMap.put(user.getId().toString(), token);
        m.addObject("topics", topics);
        m.addObject("token", token);
        return m;
    }

    @GetMapping("/topic/detail/{id}")
    public ModelAndView detail(@PathVariable Integer id, Integer TopicId) {
        TopicModel topic = topicService.findByIdWithCommeentsAndUser(id);
        UserModel user = userService.findAuthorNameWithTopicId(id);
        //Utility.log("-----------------------username(%s)",username);
        for (TopicCommentModel comment :
                topic.getCommentList()) {
        }
        ModelAndView m = new ModelAndView("topic/topic_detail");
        m.addObject("topic", topic);
        m.addObject("user", user);
        return m;
    }

    @PostMapping("/topic/add")
    public ModelAndView add(String title, String content, HttpSession session, String createdTime, String updatedTime ) {
        title = title.replace(">", "&gt");
        title = title.replace("<", "&lt");
        Integer uid = (Integer) session.getAttribute("user_id");
        TopicModel topic = topicService.add(uid, title, content, createdTime, updatedTime );
        return new ModelAndView("redirect:/topic");
    }


    @GetMapping("/topic/delete")
    public ModelAndView deleteMapper(HttpServletRequest request) {
        Integer uid = (Integer) request.getSession().getAttribute("user_id");
        Integer id = Integer.valueOf(request.getParameter("id"));
        String token = request.getParameter("token");
        String userToken = tokenMap.get(uid.toString());

        Utility.log("token(%s), userToken(%s)", token, userToken);
        if (userToken.equals(token)) {
                topicService.deleteById(id);
            return new ModelAndView("redirect:/topic");
        } else {
            return new ModelAndView("redirect:/login");
        }
    }


    @GetMapping("/topic/edit")
    public ModelAndView edit(Integer id) {
        TopicModel topic = topicService.findById(id);
        ModelAndView m = new ModelAndView("topic/topic_edit");
        m.addObject("topic", topic);
        return m;
    }

    @PostMapping("/topic/update")
    public ModelAndView updateMapper(Integer id, String title, String content, String updatedTime) {
        topicService.update(id, title, content, updatedTime);
        return new ModelAndView("redirect:/topic");
    }
}
