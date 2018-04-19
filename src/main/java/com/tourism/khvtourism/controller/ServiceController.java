package com.tourism.khvtourism.controller;

import com.tourism.khvtourism.model.Service;
import com.tourism.khvtourism.model.ServiceSeason;
import com.tourism.khvtourism.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/service")
    public ModelAndView list(@RequestParam String season) {
        ModelAndView modelAndView = new ModelAndView();

        ServiceSeason seasonValue = ServiceSeason.ALL;
        if(season.equals("winter"))
            seasonValue = ServiceSeason.WINTER;
        if(season.equals("summer"))
            seasonValue = ServiceSeason.SUMMER;

        List<Service> result = new ArrayList<>();

        if(seasonValue == ServiceSeason.ALL)
            result = serviceService.getServices();
        else
            result = serviceService.getServicesBySeason(seasonValue);

        modelAndView.addObject("results", result);
        modelAndView.setViewName("service/list");
        return modelAndView;
    }
}
