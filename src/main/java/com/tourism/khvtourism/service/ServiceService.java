package com.tourism.khvtourism.service;

import com.tourism.khvtourism.model.Service;
import com.tourism.khvtourism.model.ServiceSeason;

import java.util.List;

public interface ServiceService {
    void addService(Service service);
    void updateService(Service service);
    List<Service> getServices();
    List<Service> getServicesBySeason(ServiceSeason serviceSeason);
    List<Service> getServiceByRange(double minimalPrice, double maximalPrice);
    List<Service> getServiceByRangeAndBySeason(double minimalPrice, double maximalPrice, ServiceSeason serviceSeason);
    Service getServiceById(Long id);
    Service deleteService(Long id);
}
