package com.example.laptopstore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.laptopstore.dto.LaptopDTO;
import com.example.laptopstore.entity.Laptop;
import com.example.laptopstore.exception.ResourceNotFoundException;
import com.example.laptopstore.repo.LaptopRepository;
import com.example.laptopstore.service.LaptopService;

@Service
public class LaptopServiceImpl implements LaptopService {
    private final LaptopRepository laptopRepository;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @Override
    public List<LaptopDTO> getAllLaptops() {
        List<Laptop> laptops = laptopRepository.findAll();
        return mapLaptopsToDTOs(laptops);
    }

    @Override
    public LaptopDTO getLaptopById(Long id) {
        Optional<Laptop> optionalLaptop = laptopRepository.findById(id);
        if (optionalLaptop.isPresent()) {
            Laptop laptop = optionalLaptop.get();
            return mapLaptopToDTO(laptop);
        }
        throw new ResourceNotFoundException("Laptop not found with id: " + id);
    }

    @Override
    public LaptopDTO createLaptop(LaptopDTO laptopDTO) {
        Laptop laptop = mapDTOToLaptop(laptopDTO);
        Laptop savedLaptop = laptopRepository.save(laptop);
        return mapLaptopToDTO(savedLaptop);
    }

    @Override
    public LaptopDTO updateLaptop(Long id, LaptopDTO laptopDTO) {
        Optional<Laptop> optionalLaptop = laptopRepository.findById(id);
        if (optionalLaptop.isPresent()) {
            Laptop laptop = optionalLaptop.get();
            laptop.setName(laptopDTO.getName());
            laptop.setPrice(laptopDTO.getPrice());
            laptop.setBrand(laptopDTO.getBrand());
            laptop.setStorage(laptopDTO.getStorage());
            laptop.setRam(laptopDTO.getRam());
            laptop.setProcessor(laptopDTO.getProcessor());

            Laptop updatedLaptop = laptopRepository.save(laptop);
            return mapLaptopToDTO(updatedLaptop);
        }
        throw new ResourceNotFoundException("Laptop not found with id: " + id);
    }

    @Override
    public boolean deleteLaptop(Long id) {
        if (!laptopRepository.existsById(id)) {
        	throw new ResourceNotFoundException("Laptop not found with id: " + id);
              }
        laptopRepository.deleteById(id);
        return true;
    }

    @Override
    public List<LaptopDTO> searchLaptopsByName(String name) {
        List<Laptop> laptops = laptopRepository.findByName(name);
        return mapLaptopsToDTOs(laptops);
    }

    @Override
    public List<LaptopDTO> searchLaptopsByPrice(Double price) {
        List<Laptop> laptops = laptopRepository.findByPrice(price);
        return mapLaptopsToDTOs(laptops);
    }

    @Override
    public List<LaptopDTO> searchLaptopsByBrand(String brand) {
        List<Laptop> laptops = laptopRepository.findByBrand(brand);
        return mapLaptopsToDTOs(laptops);
    }

    private LaptopDTO mapLaptopToDTO(Laptop laptop) {
        LaptopDTO laptopDTO = new LaptopDTO();
        laptopDTO.setId(laptop.getId());
        laptopDTO.setName(laptop.getName());
        laptopDTO.setPrice(laptop.getPrice());
        laptopDTO.setBrand(laptop.getBrand());
        laptopDTO.setStorage(laptop.getStorage());
        laptopDTO.setRam(laptop.getRam());
        laptopDTO.setProcessor(laptop.getProcessor());
        return laptopDTO;
    }

    private List<LaptopDTO> mapLaptopsToDTOs(List<Laptop> laptops) {
        return laptops.stream()
                .map(this::mapLaptopToDTO)
                .collect(Collectors.toList());
    }

    private Laptop mapDTOToLaptop(LaptopDTO laptopDTO) {
        Laptop laptop = new Laptop();
        laptop.setName(laptopDTO.getName());
        laptop.setPrice(laptopDTO.getPrice());
        laptop.setBrand(laptopDTO.getBrand());
        laptop.setStorage(laptopDTO.getStorage());
        laptop.setRam(laptopDTO.getRam());
        laptop.setProcessor(laptopDTO.getProcessor());
        return laptop;
    }
    
    @Override
    public List<LaptopDTO> searchLaptops(String name, Double price, String brand) {
        List<Laptop> laptops = laptopRepository.findByNameOrPriceOrBrand(name, price, brand);
        return mapLaptopsToDTOs(laptops);
    }
}
