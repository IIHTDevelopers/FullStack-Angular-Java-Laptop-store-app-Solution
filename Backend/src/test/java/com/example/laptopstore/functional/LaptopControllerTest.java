package com.example.laptopstore.functional;

import static com.example.laptopstore.utils.MasterData.getLaptopDTO;
import static com.example.laptopstore.utils.MasterData.getLaptopDTOList;
import static com.example.laptopstore.utils.TestUtils.businessTestFile;
import static com.example.laptopstore.utils.TestUtils.currentTest;
import static com.example.laptopstore.utils.TestUtils.testReport;
import static com.example.laptopstore.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class LaptopControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LaptopService laptopService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateLaptop() throws Exception {
		LaptopDTO savedLaptopDto = getLaptopDTO();

		when(laptopService.createLaptop(any())).thenReturn(savedLaptopDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/laptops")
				.content(MasterData.asJsonString(savedLaptopDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedLaptopDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testCreateLaptopIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		LaptopDTO savedLaptopDto = getLaptopDTO();
		when(this.laptopService.createLaptop(any())).then(new Answer<LaptopDTO>() {

			@Override
			public LaptopDTO answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedLaptopDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/laptops")
				.content(MasterData.asJsonString(savedLaptopDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetLaptopById() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		when(this.laptopService.getLaptopById(laptopDTO.getId())).thenReturn(laptopDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/laptops/" + laptopDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(laptopDTO)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetLaptopByIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		LaptopDTO laptopDTO = getLaptopDTO();
		when(this.laptopService.getLaptopById(laptopDTO.getId())).then(new Answer<LaptopDTO>() {

			@Override
			public LaptopDTO answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return laptopDTO;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/laptops/" + laptopDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetAllLaptops() throws Exception {
		List<LaptopDTO> laptopDTOS = getLaptopDTOList();

		when(this.laptopService.getAllLaptops()).thenReturn(laptopDTOS);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/laptops").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(laptopDTOS)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllLaptopsIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<LaptopDTO> laptopDTOS = getLaptopDTOList();

		when(this.laptopService.getAllLaptops()).then(new Answer<List<LaptopDTO>>() {

			@Override
			public List<LaptopDTO> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return laptopDTOS;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/laptops/").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testUpdateLaptop() throws Exception {
		LaptopDTO updateLaptopDTO = getLaptopDTO();

		when(this.laptopService.updateLaptop(eq(updateLaptopDTO.getId()), any())).thenReturn(updateLaptopDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/laptops/" + updateLaptopDTO.getId())
				.content(MasterData.asJsonString(updateLaptopDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(updateLaptopDTO))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testUpdateLaptopIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		LaptopDTO updateLaptopDTO = getLaptopDTO();

		when(this.laptopService.updateLaptop(eq(updateLaptopDTO.getId()), any())).then(new Answer<LaptopDTO>() {

			@Override
			public LaptopDTO answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return updateLaptopDTO;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/laptops/" + updateLaptopDTO.getId())
				.content(MasterData.asJsonString(updateLaptopDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testDeleteLaptop() throws Exception {
		LaptopDTO laptopDTO = getLaptopDTO();
		when(this.laptopService.deleteLaptop(laptopDTO.getId())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/laptops/" + laptopDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals("") ? "true" : "false"),
				businessTestFile);

	}

	@Test
	public void testDeleteLaptopIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		LaptopDTO laptopDTO = getLaptopDTO();
		when(this.laptopService.deleteLaptop(laptopDTO.getId())).then(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return true;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/laptops/" + laptopDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testSearchLaptopsByName() throws Exception {
		List<LaptopDTO> laptopDTOS = getLaptopDTOList();
		String name = "Gaming";
		when(this.laptopService.searchLaptops(name, null, null)).thenReturn(laptopDTOS);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/laptops/search").queryParam("name", name)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(laptopDTOS)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testSearchLaptopsByNameIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<LaptopDTO> laptopDTOS = getLaptopDTOList();
		String name = "Gaming";
		when(this.laptopService.searchLaptops(name, null, null)).then(new Answer<List<LaptopDTO>>() {

			@Override
			public List<LaptopDTO> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return laptopDTOS;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/laptops/search").queryParam("name", name)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testSearchLaptopsByPrice() throws Exception {
		List<LaptopDTO> laptopDTOS = getLaptopDTOList();
		Double price = 5555D;
		when(this.laptopService.searchLaptops(null, price, null)).thenReturn(laptopDTOS);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/laptops/search")
				.queryParam("price", price.toString()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(laptopDTOS)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testSearchLaptopsByPriceIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<LaptopDTO> laptopDTOS = getLaptopDTOList();
		Double price = 5555D;
		when(this.laptopService.searchLaptops(null, price, null)).then(new Answer<List<LaptopDTO>>() {

			@Override
			public List<LaptopDTO> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return laptopDTOS;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/laptops/search")
				.queryParam("price", price.toString()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testSearchLaptopsByBrand() throws Exception {
		List<LaptopDTO> laptopDTOS = getLaptopDTOList();
		String brand = "DELL";
		when(this.laptopService.searchLaptops(null, null, brand)).thenReturn(laptopDTOS);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/laptops/search").queryParam("brand", brand)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(laptopDTOS)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testSearchLaptopsByBrandIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<LaptopDTO> laptopDTOS = getLaptopDTOList();
		String brand = "DELL";
		when(this.laptopService.searchLaptops(null, null, brand)).then(new Answer<List<LaptopDTO>>() {

			@Override
			public List<LaptopDTO> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return laptopDTOS;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/laptops/search").queryParam("brand", brand)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testSearchLaptopsByNameBrandAndPrice() throws Exception {
		List<LaptopDTO> laptopDTOS = getLaptopDTOList();
		String brand = "DELL";
		Double price = 5555D;
		String name = "Gaming";
		when(this.laptopService.searchLaptops(name, price, brand)).thenReturn(laptopDTOS);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/laptops/search").queryParam("name", name)
				.queryParam("price", price.toString()).queryParam("brand", brand)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(laptopDTOS)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testSearchLaptopsByBrandNameAndPriceIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<LaptopDTO> laptopDTOS = getLaptopDTOList();
		String brand = "DELL";
		Double price = 5555D;
		String name = "Gaming";
		when(this.laptopService.searchLaptops(name, price, brand)).then(new Answer<List<LaptopDTO>>() {

			@Override
			public List<LaptopDTO> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return laptopDTOS;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/laptops/search").queryParam("name", name)
				.queryParam("price", price.toString()).queryParam("brand", brand)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testSearchLaptopsByWithoutFilter() throws Exception {
		List<LaptopDTO> laptopDTOS = getLaptopDTOList();
		when(this.laptopService.searchLaptops(null, null, null)).thenReturn(laptopDTOS);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/laptops/search")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(laptopDTOS)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testSearchLaptopsByWithoutFilterIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<LaptopDTO> laptopDTOS = getLaptopDTOList();
		when(this.laptopService.searchLaptops(null, null, null)).then(new Answer<List<LaptopDTO>>() {

			@Override
			public List<LaptopDTO> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return laptopDTOS;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/laptops/search")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

}
