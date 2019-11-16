package kybmig.ssm.aspect;


import kybmig.ssm.Utility;
import kybmig.ssm.model.TopicModel;
import kybmig.ssm.model.UserModel;
import kybmig.ssm.service.TopicService;
import kybmig.ssm.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

// 会被注册成切面，这样方法才会在事件发生的时候执行
@Aspect
// 自动注册成 spring bean，这样 spring 就能认得出这个类
@Component
// Aspect 切面：注册事件，在某类事情发生的时候调用
public class PermissionAspect {
    private UserService userService;
    private TopicService topicService;
    private HttpServletRequest request;

    public PermissionAspect(UserService service, TopicService topicService, HttpServletRequest request) {
        Utility.log("PermissionAspect 注入需要的依赖", request, service);
        this.request = request;
        this.userService = service;
        this.topicService = topicService;
    }


    // @Before("execution(* kybmig.ssm.controller.TodoController.*(..))")
    // public void matchSingle() {
    //     Utility.log("最简单的单方法匹配 %s", request.getRequestURI());
    // }


    @Around("execution(* kybmig.ssm.controller.TodoController.index(..)) || execution(* kybmig.ssm.controller.TodoController.add(..))")
    public ModelAndView matchSingle(ProceedingJoinPoint joint) throws Throwable {
        Utility.log("路由函数之前执行 %s", request.getRequestURI());

        // 这一句就是在执行路由函数
        ModelAndView result = (ModelAndView) joint.proceed();

        Utility.log("路由函数之后执行 %s", request.getRequestURI());
        return result;
    }

    @Around("execution(* kybmig.ssm.controller.TopicController.*(..)) || execution(* kybmig.ssm.controller.UserController.profile(..))")
    public ModelAndView loginRequired(ProceedingJoinPoint joint) throws Throwable {
        // 在 TodoController 下面所有方法被执行的时候调用
        // @Around 能在执行方法之前和之后处理。由 loginRequired 决定什么时候调用 controller 的方法。
        // execution 匹配方法执行。Aspect 功能非常多，除了能在方法执行的时候插入，还能在其他地方插入。
        // * kybmig.ssm.controller.TodoController.*(..)
        // 第一个 *，匹配任意的方法返回值
        // 第二个 *，匹配 TodoController 下的任意方法，可以把 * 换成具体方法名。这里有自动补全。
        // (..) 匹配任意参数签名
        // 简写 @Around("within(kybmig.ssm.controller.TodoController)")
        // ProceedingJoinPoint 正在被调用的方法
        // 返回值类型要和被处理的控制器方法类型一样。所以 TodoController 的所有方法返回值都要是 ModelAndView
        Utility.log("loginRequired 正在访问的 url %s", request.getRequestURI());
        Utility.log("loginRequired 正在执行的方法 %s %s", joint.getSignature(), joint.getArgs());
        Integer userID = (Integer) request.getSession().getAttribute("user_id");
        if (userID == null) {
            // 跳转回登陆页面
            Utility.log("loginRequired 没有 session");
            return new ModelAndView("redirect:/login");
        } else {
            UserModel u = userService.findById(userID);
            if (u == null) {
                // 跳转回登陆页面
                Utility.log("loginRequired 用户不存在 %s", userID);
                return new ModelAndView("redirect:/login");
            } else {
                // 执行被插入的方法
                return (ModelAndView) joint.proceed();
            }
        }
    }


    @Around("execution(* kybmig.ssm.controller.TopicController.edit(..)) || execution(* kybmig.ssm.controller.TopicController.deleteMapper(..))")
    public ModelAndView owerRequired(ProceedingJoinPoint joint) throws Throwable {
        Utility.log("owerRequired 正在访问的 url %s", request.getRequestURI());
        Utility.log("owerRequired 正在执行的方法 %s %s", joint.getSignature(), joint.getArgs());
        Integer userID = (Integer) request.getSession().getAttribute("user_id");
        String topicIdParameter = request.getParameter("id");
        Utility.log("currentUser(%s), topic Owner(%s)", userID, topicIdParameter);

        if (userID == null || topicIdParameter == null) {
            return new ModelAndView("redirect:/login");
        } else {
            UserModel currentUser = userService.findById(userID);
            TopicModel topicAccessed = topicService.findById(Integer.valueOf(topicIdParameter));
            if (currentUser == null || topicAccessed == null) {
                return new ModelAndView("redirect:/login");
            } else {
                if (currentUser.getId().equals(topicAccessed.getUserId())) {
                    // 执行被插入的方法
                    return (ModelAndView) joint.proceed();
                } else {
                    Utility.log("警告页面");
                    return new ModelAndView("redirect:/alert");
                }
            }
        }
    }


}
