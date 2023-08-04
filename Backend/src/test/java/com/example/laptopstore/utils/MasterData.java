package com.example.laptopstore.utils;

import java.util.ArrayList;
import java.util.List;

import com.example.laptopstore.dto.LaptopDTO;
import com.example.laptopstore.entity.Laptop;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class MasterData {

	public static LaptopDTO getLaptopDTO() {
		LaptopDTO laptopDTO = new LaptopDTO();
		laptopDTO.setId(1L);
		laptopDTO.setName("AIR");
		laptopDTO.setBrand("DELL");
		laptopDTO.setPrice(1000D);
		laptopDTO.setStorage("1 TB");
		laptopDTO.setRam("8 GB");
		laptopDTO.setProcessor("Intel");
		return laptopDTO;
	}

	public static List<LaptopDTO> getLaptopDTOList() {
		List<LaptopDTO> laptopDTOS = new ArrayList<>();
		LaptopDTO laptopDTO = new LaptopDTO();
		laptopDTO.setId(1L);
		laptopDTO.setName("AIR");
		laptopDTO.setBrand("DELL");
		laptopDTO.setPrice(1000D);
		laptopDTO.setStorage("1 TB");
		laptopDTO.setRam("8 GB");
		laptopDTO.setProcessor("Intel");
		laptopDTOS.add(laptopDTO);

		laptopDTO = new LaptopDTO();
		laptopDTO.setId(2L);
		laptopDTO.setName("Mac Book");
		laptopDTO.setBrand("Apple");
		laptopDTO.setPrice(1000D);
		laptopDTO.setStorage("2 TB");
		laptopDTO.setRam("16 GB");
		laptopDTO.setProcessor("IOS");
		laptopDTOS.add(laptopDTO);

		return laptopDTOS;
	}

	public static Laptop getLaptop() {
		Laptop laptop = new Laptop();
		laptop.setId(1L);
		laptop.setName("AIR");
		laptop.setBrand("DELL");
		laptop.setPrice(1000D);
		laptop.setStorage("1 TB");
		laptop.setRam("8 GB");
		laptop.setProcessor("Intel");
		return laptop;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String randomStringWithSize(int size) {
		String s = "";
		for (int i = 0; i < size; i++) {
			s.concat("A");
		}
		return s;
	}
}
