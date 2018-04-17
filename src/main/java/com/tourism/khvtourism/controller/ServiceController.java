package com.tourism.khvtourism.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ServiceController {
    @GetMapping("/service/{season}")
    public ModelAndView list(@PathVariable String season) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("season", season);
        modelAndView.setViewName("service/list");
        return modelAndView;
    }
}
