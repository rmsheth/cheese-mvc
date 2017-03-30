package org.launchcode.controllers;

import org.launchcode.models.InvalidUserException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Andrew Bell on 3/30/17.
 */
//@Controller
public class ExceptionHandlingController {

//    // Total control - setup a model and return the view name yourself. Or
//    // consider subclassing ExceptionHandlerExceptionResolver (see below).
//    @ExceptionHandler(InvalidUserException.class)
//    public String handleError(HttpServletRequest req, Exception ex, Model model) {
//        model.addAttribute("exception", ex);
//        model.addAttribute("url", req.getRequestURL());
//        return "error";
//    }

}
