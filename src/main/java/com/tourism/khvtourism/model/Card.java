package com.tourism.khvtourism.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "services")
    private String servicesJson;

    @Transient
    private List<Service> services = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getServicesJson() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for(int i = 0; i < services.size(); i++) {
            stringBuilder.append(services.get(i).getId().toString());
            if(i != services.size() - 1) {
                stringBuilder.append(",");
            }
        }

        stringBuilder.append("]");

        servicesJson = stringBuilder.toString();

        return servicesJson;
    }

    public void clearServices() {
        services.clear();
    }

    public void setServicesJson(String servicesJson) {
        this.servicesJson = servicesJson;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public void addToCard(Service id) {
        services.add(id);
    }

    public void removeFromCard(int id) {
        services.remove(id);
    }

    public int getSum() {
        int sum = 0;
        for(Service service : services) {
            sum += service.getPrice();
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("");
        for(Service service : services) {
            stringBuilder.append(service.getTitle());
            stringBuilder.append(" ");
            stringBuilder.append(service.getPrice());
            stringBuilder.append(" | ");
        }
        return stringBuilder.toString();
    }
}