package com.tourism.khvtourism.service;

import com.tourism.khvtourism.model.ServiceSeason;
import com.tourism.khvtourism.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;


    @Override
    @Transactional
    public void addService(com.tourism.khvtourism.model.Service service) {
        this.serviceRepository.save(service);
    }

    @Override
    @Transactional
    public void updateService(com.tourism.khvtourism.model.Service service) {
        this.serviceRepository.save(service);
    }

    @Override
    @Transactional
    public List<com.tourism.khvtourism.model.Service> getServices() {
        return StreamSupport.stream(serviceRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    private int seasonToInt(ServiceSeason serviceSeason) {
        int seasonValue = 1;
        if(serviceSeason == ServiceSeason.WINTER)
            seasonValue = 2;
        if(serviceSeason == ServiceSeason.SUMMER)
            seasonValue = 3;
        return seasonValue;
    }

    @Override
    @Transactional
    public List<com.tourism.khvtourism.model.Service> getServicesBySeason(ServiceSeason serviceSeason) {
        return serviceRepository.findAllBySeason(seasonToInt(serviceSeason));
    }

    @Override
    @Transactional
    public List<com.tourism.khvtourism.model.Service> getServiceByRange(double minimalPrice, double maximalPrice) {
        return serviceRepository.findAllByPriceBetween(minimalPrice, maximalPrice);
    }

    @Override
    public List<com.tourism.khvtourism.model.Service> getServiceByRangeAndBySeason(double minimalPrice, double maximalPrice, ServiceSeason serviceSeason) {
        return serviceRepository.findAllBySeasonAndPriceBetween(seasonToInt(serviceSeason), minimalPrice, maximalPrice);
    }

    @Override
    @Transactional
    public com.tourism.khvtourism.model.Service getServiceById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public com.tourism.khvtourism.model.Service deleteService(Long id) {
        return null;
    }
}
