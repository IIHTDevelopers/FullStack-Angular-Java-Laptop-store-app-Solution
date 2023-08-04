package com.example.laptopstore.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LaptopDTO {

	private Long id;

	@NotBlank
	@Size(min = 3, max = 20)
	private String name;

	@NotNull
	@Min(0)
	@Max(9999)
	private Double price;

	@NotBlank
	private String brand;

	@NotBlank
	private String storage;

	@NotBlank
	private String ram;

	@NotBlank
	private String processor;

	public LaptopDTO() {
	}

	public LaptopDTO(Long id, String name, Double price, String brand, String storage, String ram, String processor) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.brand = brand;
		this.storage = storage;
		this.ram = ram;
		this.processor = processor;
	}

	// Getters and Setters
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}
}
