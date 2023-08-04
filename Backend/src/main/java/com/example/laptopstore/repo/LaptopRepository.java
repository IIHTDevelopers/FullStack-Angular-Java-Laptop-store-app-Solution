package com.example.laptopstore.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.laptopstore.entity.Laptop;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    List<Laptop> findByName(String name);
    List<Laptop> findByPrice(Double price);
    List<Laptop> findByBrand(String brand);
    List<Laptop> findByNameOrPriceOrBrand(String name, Double price, String brand);
}

