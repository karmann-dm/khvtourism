package com.tourism.khvtourism.repository;

import com.tourism.khvtourism.model.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long> {
    List<Service> findAllBySeason(int season);
    List<Service> findAllByPriceBetween(double minPrice, double maxPrice);

    @Query("from Service")
    List<Service> randomLimit();

    Service findByTitle(String title);
}
