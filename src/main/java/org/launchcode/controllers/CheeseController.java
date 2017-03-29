package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.launchcode.models.CheeseData;
import org.launchcode.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("cheese")
public class CheeseController {

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute  @Valid Cheese newCheese,
                                       Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            // Make sure you return any extra data when you send back your errors!
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/add";
        }

        CheeseData.add(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            CheeseData.remove(cheeseId);
        }

        return "redirect:";
    }

    /**
     * Our displayEditCheeseForm serves up our edit cheese template.
     * <p>
     * Recall that @PathVariable allows us to extract our `cheeseId` from the URL path
     *
     * @param cheeseId of the cheese to be edited
     * @param model    attaches data to be displayed in our view
     */
    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditCheeseForm(@PathVariable("cheeseId") int cheeseId, Model model) {
        // Attach the "title" to our page
        model.addAttribute("title", "Edit Cheese");

        // Look up our Cheese via our CheeseData class, and add it to our model
        model.addAttribute(CheeseData.getById(cheeseId));

        // Be sure to attach all of our CheeseTypes so we can display them in our dropdown
        model.addAttribute("cheeseTypes", CheeseType.values());

        return "cheese/edit";
    }

    /**
     * Our processEditCheeseForm (sometimes called our handler) will "update" the cheese we have stored
     * based on the edits made by the user on the edit page
     *
     * @param editedCheese is a 'new' cheese object that has been modified by the user. It is a new instance of the
     *                     cheese object, created by Spring based on the data passed in via request paramaters. Our
     *                     hidden `cheeseId` field has supplied us with the id for our cheese, but remember that this is
     *                     not technically the same object we have stored in CheeseData
     * @param errors       Remember that errors must directly follow our model object (editedCheese) in the method signature
     * @param model        attaches data to be displayed in our view
     */
    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.POST)
    public String processEditCheeseForm(@ModelAttribute @Valid Cheese editedCheese, Errors errors, Model model) {

        if (errors.hasErrors()) {
            // Attach our "title" to our page
            model.addAttribute("title", "Edit Cheese");
            // Be sure to attach all of our CheeseTypes so we can display them in our dropdown
            model.addAttribute("cheeseTypes", CheeseType.values());
            // Return our template. Our editedCheese and errors will automatically be passed to our template view
            return "cheese/edit";
        }

        // We remove the existing cheese, and then add our new cheese.
        // This may seem funny, but we need to remove the 'old' cheese.
        // You could also consider adding an "Update()" method to your CheeseData
        // to hide this logic from our controller (Separation of concerns)
        CheeseData.remove(editedCheese.getCheeseId());
        CheeseData.add(editedCheese);

        // Redirect, because we don't want to have to re-implementing populating our index page
        // we use `..` to return the path "one higher" than where we are (so, /cheese)
        return "redirect:..";
    }

}
