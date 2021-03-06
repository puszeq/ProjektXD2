package com.jp.pai.controllers;

import com.jp.pai.dao.UserDao;
import com.jp.pai.entities.User;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao dao;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/test")
    public String testPage() {
        return "test";
    }

    @GetMapping("/register")
    public String registerPage(Model m) {
        m.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/register")
    public String registerPagePOST(@Valid @ModelAttribute(value = "user") User user, BindingResult res) {
        if(res.hasErrors()) {
            return "register";
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);

        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profilePage(Model m, Principal principal) {
        m.addAttribute("user", dao.findByLogin(principal.getName()));

        return "profile";
    }
    
    @GetMapping("/users")
    public String usersPage(Model m, Principal principal) {
        m.addAttribute("users", dao.findAll());

        return "users";
    }
    
    @GetMapping("/edit")
    public String editPage(Model m, Principal principal) {
        m.addAttribute("editedUser", dao.findByLogin(principal.getName()));
        
        return "edit";
    }
    
    @PostMapping("/confirmEdit")
    public String editUser(@Valid User user, BindingResult res) {
        if(res.hasErrors()) {
            return "edit";
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
                      
        return "redirect:/profile";
    }
    
    @GetMapping("/delete")
    public String deleteUser(Model m, Principal principal) {
        User user = dao.findByLogin(principal.getName());
        dao.deleteById(user.getUserid());
        
        return "redirect:/logout";
    }
}
