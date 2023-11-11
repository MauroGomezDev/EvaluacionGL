package com.globallogic.evaluacion.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.globallogic.evaluacion.controller.EvaluacionController;
import com.globallogic.evaluacion.model.Phone;
import com.globallogic.evaluacion.model.User;
import com.globallogic.evaluacion.service.UserService;
import com.globallogic.evaluacion.util.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class EvaluacionControllerTest {

	private final static String BASE_URL = "/api/evaluacion";
	MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Mock
	private UserService userService;

	@InjectMocks
	EvaluacionController evaluacionController;

	LocalDate now = LocalDate.now();
	Long id = Long.valueOf(1);
	Phone phone;
	List<Phone> phones;
	User user;

	@BeforeEach
	void setUp() throws JsonProcessingException {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		MockitoAnnotations.openMocks(this);
		phone = new Phone(id, 123456789, 8320000, "SCL");
		user = new User("1","Juan Perez", "juan.perez@gmail.com", "Password12", Arrays.asList(phone), now, now, "token", true);
	}

	@Test
	void signUp() throws Exception {
		MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL+"/sign-up")
				.accept(MediaType.APPLICATION_JSON_VALUE)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(Utils.mapToJson(user)))
				.andReturn();
		assertEquals(201, mockMvcResult.getResponse().getStatus());
	}

	@Test
	void getAllUsers() throws Exception {
		MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+"/list")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		assertEquals(200, mockMvcResult.getResponse().getStatus());
	}

}
