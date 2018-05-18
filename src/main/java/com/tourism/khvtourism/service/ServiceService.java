package com.tourism.khvtourism.service;

import com.tourism.khvtourism.model.Service;
import com.tourism.khvtourism.model.ServiceSeason;

import java.util.List;

public interface ServiceService {
    void addService(Service service);
    void updateService(Service service);
    List<Service> getServices();
    List<Service> getServices(int limit);
    List<Service> getServicesBySeason(ServiceSeason serviceSeason);
    Service getServiceById(Long id);
    void deleteService(Long id);
}
