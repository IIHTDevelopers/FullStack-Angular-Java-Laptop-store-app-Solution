package com.example.laptopstore.exception;

import static com.example.laptopstore.utils.MasterData.getLaptopDTO;
import static com.example.laptopstore.utils.TestUtils.currentTest;
import static com.example.laptopstore.utils.TestUtils.exceptionTestFile;
import static com.example.laptopstore.utils.TestUtils.testReport;
import static com.example.laptopstore.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.laptopstore.controller.LaptopController;
import com.example.laptopstore.dto.LaptopDTO;
import com.example.laptopstore.service.LaptopService;
import com.example.laptopstore.utils.MasterData;

@WebMvcTest(LaptopController.class)
@AutoConfigureMockMvc
public class LaptopExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LaptopService laptopService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateLaptopInvalidDataException() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		laptopDTO.setName(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/laptops")
				.content(MasterData.asJsonString(laptopDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testUpdateLaptopInvalidDataException() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		laptopDTO.setPrice(100000D);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/laptops/" + laptopDTO.getId())
				.content(MasterData.asJsonString(laptopDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testGetLaptopByIdResourceNotFoundException() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		ExceptionHandlerController.ErrorResponse exResponse = new ExceptionHandlerController.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "Laptop not found with id: " + laptopDTO.getId());

		when(this.laptopService.getLaptopById(laptopDTO.getId()))
				.thenThrow(new ResourceNotFoundException("Laptop not found with id: " + laptopDTO.getId()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/laptops/" + laptopDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testUpdateLaptopByIdResourceNotFoundException() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		ExceptionHandlerController.ErrorResponse exResponse = new ExceptionHandlerController.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "Laptop not found with id: " + laptopDTO.getId());

		when(this.laptopService.updateLaptop(eq(laptopDTO.getId()), any()))
				.thenThrow(new ResourceNotFoundException("Laptop not found with id: " + laptopDTO.getId()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/laptops/" + laptopDTO.getId())
				.content(MasterData.asJsonString(laptopDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testDeleteLaptopByIdResourceNotFoundException() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		ExceptionHandlerController.ErrorResponse exResponse = new ExceptionHandlerController.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "Laptop not found with id: " + laptopDTO.getId());

		when(this.laptopService.deleteLaptop(laptopDTO.getId()))
				.thenThrow(new ResourceNotFoundException("Laptop not found with id: " + laptopDTO.getId()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/laptops/" + laptopDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

}
