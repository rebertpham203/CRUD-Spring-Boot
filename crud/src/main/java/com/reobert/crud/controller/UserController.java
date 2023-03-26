package com.reobert.crud.controller;

import com.reobert.crud.entity.User;
import com.reobert.crud.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getAll(Model model) {
        model.addAttribute("listUser", userService.getAll());
        return "index";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        User user = userService.getOne(id);
        model.addAttribute("user", user);
        return "view-update";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") Integer id,
                         RedirectAttributes redirect) {
        User userUpdate = userService.getOne(id);
        userUpdate.setId(id);
        userUpdate.setName(user.getName());
        userUpdate.setRole(user.getRole());
        userUpdate.setGender(user.getGender());
        userUpdate.setEmail(user.getEmail());
        userService.save(user);
        redirect.addFlashAttribute("success", "Update is the success");
        return "redirect:/admin/users/";
    }
    @GetMapping("/view-add")
    public String viewAdd(User user, Model model) {
        model.addAttribute("user", user);
        return "view-add";
    }

    @PostMapping("/add")
    public String save(@Valid User user, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "view-add";
        }
        userService.save(user);
        redirect.addFlashAttribute("success", "Save is the success");
        return "redirect:/admin/users/";
    }



    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, RedirectAttributes ra) {
        User user = userService.getOne(id);
        userService.delete(user);
        ra.addFlashAttribute("success","Delete is the success");
        return "redirect:/admin/users/";
    }
}
