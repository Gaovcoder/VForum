package kybmig.ssm.controller;
import kybmig.ssm.Utility;
import kybmig.ssm.model.TodoModel;
import kybmig.ssm.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TodoController {

    // 现在如果要用 todoService, 必须在 Controller 里面声明一个 TodoService 属性
    // 同时在构造器函数中, 传入 TodoService 这个参考, 并赋值
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/todo/add")
    public ModelAndView add(String content) {
        TodoModel todo = todoService.add(content);
        Utility.log("todo add id %s", todo.getId());
        return new ModelAndView("redirect:/todo");
    }

    @GetMapping("/todo/delete")
    public String deleteMapper(Integer id) {
        todoService.deleteById(id);
        return "redirect:/todo";
    }

    // 相当于下面的写法
    // @GetMapping("/todo/delete")
    // public String deleteMapper(HttpServletRequest request) {
    //     Integer id = Integer.valueOf(request.getParameter("id");)
    //     todoService.deleteById(id);
    //     return "redirect:/todo";
    // }


    @GetMapping("/todo/edit")
    public ModelAndView edit(Integer id) {
        TodoModel todo = todoService.findById(id);

        ModelAndView m = new ModelAndView("/todo/todo_edit");
        m.addObject("todo", todo);
        return m;
    }

    @PostMapping("/todo/update")
    public String updateMapper(Integer id, String content) {
        todoService.update(id, content);
        return "redirect:/todo";
    }

    @GetMapping("/todo")
    public ModelAndView index() {
        Utility.log("这是todo 主页");
        List<TodoModel> todos = todoService.all();
        ModelAndView m = new ModelAndView("/todo/todo_index");
        m.addObject("todos", todos);
        return m;
    }
}
