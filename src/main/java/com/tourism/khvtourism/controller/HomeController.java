package com.tourism.khvtourism.controller;

import com.tourism.khvtourism.model.Contact;
import com.tourism.khvtourism.model.Service;
import com.tourism.khvtourism.service.ContactService;
import com.tourism.khvtourism.service.ServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {
    @ModelAttribute("contact")
    public Contact getContact() {
        return new Contact();
    }

    @Autowired
    ServiceService serviceService;

    @Autowired
    ContactService contactService;

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @GetMapping("/")
    public ModelAndView index(@ModelAttribute("contact") Contact contact)
    {
        List<Service> services = serviceService.getServices(3);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("top", services);
        modelAndView.addObject("contact", contact);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/contact")
    public ModelAndView viewContact(@ModelAttribute("contact") Contact contact) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("contact", contact);
        modelAndView.setViewName("home/contact");

        return modelAndView;
    }

    @PostMapping("/contact")
    public String contact(@Valid @ModelAttribute("contact") Contact contact, BindingResult bindingResult) {
        logger.info(contact.toString());
        if(bindingResult.hasErrors())
            return "index";

        contactService.sendContact(contact);

        return "redirect:/";
    }

    @GetMapping("/login")
    public ModelAndView login()
    {
        return new ModelAndView("home/login");
    }
}
