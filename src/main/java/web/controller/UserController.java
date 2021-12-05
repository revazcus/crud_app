package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;

import java.util.List;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String showAllUsers (Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users_all";
    }

    @GetMapping("/user_create")
    public String createUserForm(Model model){
        model.addAttribute("user", new User());
        return "user_create";
    }

    @PostMapping("/user_create")
    public String createUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/";
    }

    @DeleteMapping("user_delete/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.deleteUserById(id);
        return "redirect:/";
    }

    @GetMapping("/user_update/{id}")
    public String updateUserForm(@PathVariable("id") long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user_update";
    }

    @PutMapping("/{id}")
    public String updateUser(@ModelAttribute ("user") User user, @PathVariable("id") long id) {
        userService.updateUser(id, user);
        return "redirect:/";
    }

}
