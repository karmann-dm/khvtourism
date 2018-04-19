package com.tourism.khvtourism.controller;

import com.tourism.khvtourism.model.Service;
import com.tourism.khvtourism.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ServiceService serviceService;

    @GetMapping("/")
    public ModelAndView index()
    {
        List<Service> services = serviceService.getServices(3);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("top", services);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
