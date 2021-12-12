package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.Role;
import web.models.User;
import web.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllUsers (Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users_all";
    }

    @GetMapping("/new")
    public String createUserForm(Model model){
        model.addAttribute("user", new User());
        return "user_create";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user, @RequestParam(required = false) String roleAdmin, @RequestParam(required = false) String roleUser){
        Set<Role> roleSet = new HashSet<>();
        if (roleAdmin != null){
            Role role = new Role(1, roleAdmin);
            roleSet.add(role);
        } if (roleUser != null){
            Role role = new Role(2, roleUser);
            roleSet.add(role);
        } else {
            Role role1 = new Role(1, roleAdmin);
            Role role2 = new Role(2, roleUser);
            roleSet.add(role1);
            roleSet.add(role2);
        }
        user.setRoles(roleSet);
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @DeleteMapping("/user_delete/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.deleteUserById(id);
        return "redirect:/admin/";
    }

    @GetMapping("/edit/{id}")
    public String updateUserForm(@PathVariable("id") long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user_update";
    }

    @PutMapping("/{id}")
    public String updateUser(@ModelAttribute ("user") User user, @PathVariable("id") long id) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String userPage(@PathVariable("id") long id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "user_page";
    }
}