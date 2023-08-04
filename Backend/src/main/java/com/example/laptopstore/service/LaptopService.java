package com.example.laptopstore.service;

import java.util.List;

import com.example.laptopstore.dto.LaptopDTO;

public interface LaptopService {
    List<LaptopDTO> getAllLaptops();
    LaptopDTO getLaptopById(Long id);
    LaptopDTO createLaptop(LaptopDTO laptopDTO);
    LaptopDTO updateLaptop(Long id, LaptopDTO laptopDTO);
    boolean deleteLaptop(Long id);
    List<LaptopDTO> searchLaptopsByName(String name);
    List<LaptopDTO> searchLaptopsByPrice(Double price);
    List<LaptopDTO> searchLaptopsByBrand(String brand);
    List<LaptopDTO> searchLaptops(String name, Double price, String brand);
}

