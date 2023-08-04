package com.example.laptopstore.boundary;

import static com.example.laptopstore.utils.MasterData.getLaptopDTO;
import static com.example.laptopstore.utils.MasterData.randomStringWithSize;
import static com.example.laptopstore.utils.TestUtils.boundaryTestFile;
import static com.example.laptopstore.utils.TestUtils.currentTest;
import static com.example.laptopstore.utils.TestUtils.testReport;
import static com.example.laptopstore.utils.TestUtils.yakshaAssert;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.laptopstore.dto.LaptopDTO;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testNameNotNull() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		laptopDTO.setName(null);
		Set<ConstraintViolation<LaptopDTO>> violations = validator.validate(laptopDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNameNotBlank() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		laptopDTO.setName("");
		Set<ConstraintViolation<LaptopDTO>> violations = validator.validate(laptopDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNameMinThree() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		laptopDTO.setName(randomStringWithSize(2));
		Set<ConstraintViolation<LaptopDTO>> violations = validator.validate(laptopDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNameMaxTwenty() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		laptopDTO.setName(randomStringWithSize(21));
		Set<ConstraintViolation<LaptopDTO>> violations = validator.validate(laptopDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPriceNotNull() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		laptopDTO.setPrice(null);
		Set<ConstraintViolation<LaptopDTO>> violations = validator.validate(laptopDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPriceMinZero() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		laptopDTO.setPrice(-1D);
		Set<ConstraintViolation<LaptopDTO>> violations = validator.validate(laptopDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPriceMax9999() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		laptopDTO.setPrice(100000D);
		Set<ConstraintViolation<LaptopDTO>> violations = validator.validate(laptopDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBrandNotNull() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		laptopDTO.setBrand(null);
		Set<ConstraintViolation<LaptopDTO>> violations = validator.validate(laptopDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testBrandNotBlank() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		laptopDTO.setBrand("");
		Set<ConstraintViolation<LaptopDTO>> violations = validator.validate(laptopDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStorageNotNull() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		laptopDTO.setStorage(null);
		Set<ConstraintViolation<LaptopDTO>> violations = validator.validate(laptopDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testStorageNotBlank() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		laptopDTO.setStorage("");
		Set<ConstraintViolation<LaptopDTO>> violations = validator.validate(laptopDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testRamNotNull() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		laptopDTO.setRam(null);
		Set<ConstraintViolation<LaptopDTO>> violations = validator.validate(laptopDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testRamNotBlank() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		laptopDTO.setRam("");
		Set<ConstraintViolation<LaptopDTO>> violations = validator.validate(laptopDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testProcessorNotNull() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		laptopDTO.setProcessor(null);
		Set<ConstraintViolation<LaptopDTO>> violations = validator.validate(laptopDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testProcessorNotBlank() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		laptopDTO.setProcessor("");
		Set<ConstraintViolation<LaptopDTO>> violations = validator.validate(laptopDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
}
