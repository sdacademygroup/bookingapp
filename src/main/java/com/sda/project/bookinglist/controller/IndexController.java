package com.sda.project.bookinglist.controller;

import com.sda.project.bookinglist.model.NewsletterModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView indexPage() {
        return new ModelAndView("index")
                .addObject("newsletter", new NewsletterModel());
    }
}
