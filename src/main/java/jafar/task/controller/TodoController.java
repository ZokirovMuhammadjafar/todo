package jafar.task.controller;

import jafar.task.dto.TodoCreateDto;
import jafar.task.dto.TodoDto;
import jafar.task.dto.TodoUpdateDto;
import jafar.task.repository.AuthRoleRepository;
import jafar.task.service.TodoService;
import jafar.task.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo/*")
public class TodoController {
    private final AuthRoleRepository repository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TodoService todoService;

    public TodoController(AuthRoleRepository repository,
                          UserService userService,
                          PasswordEncoder passwordEncoder,
                          TodoService todoService) {
        this.repository = repository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.todoService = todoService;
    }

    @RequestMapping("")
    public String todo(Model model) {
//        AuthRole byId = repository.findByCode("ADMIN");
//        AuthRole byId1 = repository.findByCode("MANAGER");
//        AuthRole byId2= repository.findByCode("EMPLOYEE");
//
//        userService.saveUser(new AuthUser( "admin", passwordEncoder.encode("jafar"),byId));
//        userService.saveUser(new AuthUser( "manager", passwordEncoder.encode("Aziza"), byId1));
//        userService.saveUser(new AuthUser( "employee", passwordEncoder.encode("Ahrullo"), byId2));
        model.addAttribute("todos", todoService.getAll());
        return "todo/all";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createPage(Model model) {
        model.addAttribute("dto", new TodoCreateDto());
        return "todo/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("dto") TodoCreateDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "todo/create";
        }
        todoService.create(dto);
        return "redirect:/todo/all";
    }
    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Long id){
        todoService.delete(id);
        return "redirect:/todo/all";
    }
    @RequestMapping(value = "update/{id}",method = RequestMethod.GET)
    public String updatePage(Model model,@PathVariable Long id){
        TodoDto todo = todoService.getTodo(id);
        System.out.println(todo.getId());
        model.addAttribute("todo",todo);
        return "todo/update";
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String update(@ModelAttribute TodoUpdateDto dto){
        todoService.update(dto);
        return "redirect:/todo/all";
    }
    @RequestMapping(value = "completed/{id}",method = RequestMethod.GET)
    public String complete(@PathVariable Long id){
        todoService.complete(id);
        return "redirect:/todo/all";
    }

}
