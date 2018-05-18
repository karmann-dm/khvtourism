package com.tourism.khvtourism.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.tourism.khvtourism.model.Card;
import com.tourism.khvtourism.model.CardDTO;
import com.tourism.khvtourism.model.Service;
import com.tourism.khvtourism.model.ServiceSeason;
import com.tourism.khvtourism.service.CardService;
import com.tourism.khvtourism.service.ServiceService;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("card")
public class ServiceController {
    private Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @ModelAttribute("card")
    Card getCard()
    {
        return new Card();
    }

    @ModelAttribute("dto")
    CardDTO getCardDTO() { return new CardDTO(); }

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private CardService cardService;

    @GetMapping("/service")
    public ModelAndView list(@RequestParam(required = false) String season, @ModelAttribute("card") Card card) {
        ModelAndView modelAndView = new ModelAndView();

        if(season == null)
            season = "all";

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
        modelAndView.addObject("card", card);
        modelAndView.setViewName("service/list");
        return modelAndView;
    }

    @GetMapping("/service/view/{id}")
    public ModelAndView view(@PathVariable Long id, @ModelAttribute("card") Card card, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();

        Service service = serviceService.getServiceById(id);

        modelAndView.addObject("service", service);
        modelAndView.addObject("card", card);
        modelAndView.addObject("backUrl", request.getHeader("Referer"));

        modelAndView.setViewName("service/view");

        return modelAndView;
    }

    @GetMapping("/service/card")
    public ModelAndView card(HttpServletRequest request, @ModelAttribute("card") Card card) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("backUrl", "/service?season=all");
        modelAndView.addObject("card", card);
        modelAndView.setViewName("service/card");
        return modelAndView;
    }

    @GetMapping("/service/success")
    public ModelAndView success() {
        return new ModelAndView("service/success");
    }

    @GetMapping("/service/confirm")
    public ModelAndView confirm(@ModelAttribute("card") Card card, @ModelAttribute("dto") CardDTO cardDTO) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("backUrl", "/service/card");
        modelAndView.addObject("card", card);
        modelAndView.addObject("dto", cardDTO);
        modelAndView.setViewName("service/confirm");
        return modelAndView;
    }

    @PostMapping("/service/confirm")
    public String confirm(@ModelAttribute("card") Card card, @Valid @ModelAttribute("dto") CardDTO cardDTO, BindingResult bindingResult) {
        logger.info(cardDTO.getName() + " " + cardDTO.getEmail());
        if(bindingResult.hasErrors())
            return "service/confirm";

        card.setEmail(cardDTO.getEmail());
        card.setName(cardDTO.getName());

        cardService.saveCard(card);

        card.clearServices();

        return "redirect:/service/success";
    }

    @PostMapping("/service/add")
    @ResponseBody
    public RedirectView addToCard(HttpServletRequest request, @ModelAttribute("card") Card card) {
        Long serviceId = Long.parseLong(request.getParameter("service"));

        Service service = serviceService.getServiceById(serviceId);
        card.addToCard(service);

        logger.info(card.getServicesJson());

        return new RedirectView(request.getHeader("Referer"));
    }

    @PostMapping("/service/remove")
    @ResponseBody
    public RedirectView removeFromCard(HttpServletRequest request, @ModelAttribute("card") Card card) {
        Integer serviceId = Integer.parseInt(request.getParameter("index"));

        card.removeFromCard(serviceId);

        logger.info(card.getServicesJson());

        return new RedirectView(request.getHeader("Referer"));
    }
}
