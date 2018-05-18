package com.tourism.khvtourism.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CardDTO {
    @NotNull
    @NotEmpty(message = "Имя не может быть пустым")
    private String name;

    @NotNull
    @NotEmpty(message = "Email не может быть пуст")
    @Email
    private String email;

    private List<Service> services;

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

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
