package kybmig.ssm.controller;

import kybmig.ssm.Utility;
import kybmig.ssm.model.TopicCommentModel;
import kybmig.ssm.model.TopicModel;
import kybmig.ssm.model.UserModel;
import kybmig.ssm.service.TopicCommentService;
import kybmig.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
public class TopicCommentController {

    private HashMap<String, String> tokenMap = new HashMap<>();

    // 现在如果要用 todoService, 必须在 Controller 里面声明一个 TodoService 属性
    // 同时在构造器函数中, 传入 TodoService 这个参考, 并赋值
    private TopicCommentService TopicCommentService;
    private UserService userService;

    public TopicCommentController(TopicCommentService TopicCommentService, UserService userService) {
        this.TopicCommentService = TopicCommentService;
        this.userService = userService;
    }

    @GetMapping("/TopicComment")
    public ModelAndView index(HttpServletRequest request, Integer topicId) {//从网页请求中拿到的topicId
        ModelAndView m = new ModelAndView("TopicComment/TopicComment_index");//选择准备渲染的网页
        String token = UUID.randomUUID().toString();
        UserModel user = userService.currentUser(request);
        tokenMap.put(user.getId().toString(), token);
        m.addObject("topicId", topicId);//渲染网页中的字段
        m.addObject("token", token);
        return m;
    }

    @GetMapping("/TopicComment/detail/{id}")
    public ModelAndView detail(@PathVariable Integer id, Integer TopicId) {
        TopicCommentModel TopicComment = TopicCommentService.findById(id);
        Utility.log("--------------帖子(%s)",TopicComment);
        ModelAndView m = new ModelAndView("TopicComment/TopicComment_detail");
        m.addObject("TopicComment", TopicComment);
        return m;
    }

    @PostMapping("/TopicComment/add")
    public ModelAndView add(String content, Integer userId,  Integer topicId, HttpSession session, String createdTime, String updatedTime) {
        content = content.replace(">", "&gt");
        content = content.replace("<", "&lt");
        Integer uid = (Integer) session.getAttribute("user_id");
        TopicCommentModel TopicComment = TopicCommentService.add(uid, content,topicId, createdTime, updatedTime );
        return new ModelAndView("redirect:/topic");
    }


    @GetMapping("/TopicComment/delete")
    public ModelAndView deleteMapper(HttpServletRequest request) {
        Integer uid = (Integer) request.getSession().getAttribute("user_id");
        Integer id = Integer.valueOf(request.getParameter("id"));
        String token = request.getParameter("token");
        String userToken = tokenMap.get(uid.toString());

        Utility.log("token(%s), userToken(%s)", token, userToken);
        if (userToken.equals(token)) {
                TopicCommentService.deleteById(id);
            return new ModelAndView("redirect:/TopicComment");
        } else {
            return new ModelAndView("redirect:/login");
        }
    }


    @GetMapping("/TopicComment/TopicComment_edit")
    public ModelAndView edit(Integer id) {
        TopicCommentModel TopicComment = TopicCommentService.findById(id);
        ModelAndView m = new ModelAndView("TopicComment/TopicComment_edit");
        m.addObject("TopicComment", TopicComment);
        return m;
    }

    @PostMapping("/TopicComment/update")
    public ModelAndView updateMapper(Integer id, String content, String updatedTime) {
        TopicCommentService.update(id, content, updatedTime);
        return new ModelAndView("redirect:/TopicComment");
    }
}
