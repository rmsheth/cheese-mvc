package org.launchcode.controllers;

import org.launchcode.models.User;
import org.launchcode.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This resembles our `CheeseController` very closely, see that class for additional comments.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("users", UserData.getAll());
        model.addAttribute("title", "My Users");
        return "user/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddUserForm(Model model) {
        model.addAttribute("title", "Add User");
        // We add an empty user, that way our template has an model to work with inside the form
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddUserForm(@RequestParam String verify, @ModelAttribute @Valid User newUser, Errors errors, Model model) {

        // Here, we see checking for validation errors, as well as checking the verification password together
        // This is done to reduce code repeat, and ensure that we can return both validation errors,
        // as well as our password verification error together
        if (errors.hasErrors() || !newUser.getPassword().equals(verify)) {
            model.addAttribute("title", "Add User");
            if (!newUser.getPassword().equals(verify)) {
                model.addAttribute("verifyError", "Password did not match verification.");
            }
            return "user/add";
        }

        UserData.add(newUser);
        return "redirect:";
    }

    @RequestMapping(value = "edit/{userId}", method = RequestMethod.GET)
    public String displayEditCheeseForm(@PathVariable("userId") int userId, Model model) {
        model.addAttribute("title", "Edit User");
        model.addAttribute(UserData.getById(userId));
        return "user/edit";
    }

    @RequestMapping(value = "edit/{userId}", method = RequestMethod.POST)
    public String processEditCheeseForm(@ModelAttribute @Valid User editedUser, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit User");
            return "user/edit";
        }

        UserData.remove(editedUser.getUserId());
        UserData.add(editedUser);

        return "redirect:..";
    }

}
