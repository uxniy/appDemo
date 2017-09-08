package com.example.appDemo;

import com.example.appDemo.controller.api.PersonController;
import com.example.appDemo.model.Person;
import com.example.appDemo.repo.PeopleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AppDemoApplicationTests {


	@Autowired
	private PeopleRepository peopleRepository;

	private ObjectMapper objectMapper;


	private MockMvc mockMvc;

	@Autowired
	private PersonController personController;

	@Before
	public void setUp(){
		mockMvc = standaloneSetup(personController).build();
		objectMapper = new ObjectMapper();

	}

	@Test
	public void testFindAll() throws Exception{
		RequestBuilder request = null;
		request = get("/api/people");
		mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id",equalTo(1)))
				.andExpect(jsonPath("$[1].id",equalTo(2)))
				.andExpect(jsonPath("$[2].id",equalTo(3)));
	}

	@Test
	public void testFindOne() throws  Exception{
		RequestBuilder request = get("/api/people/name/{name}","a");
		mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"a\",\"age\":1}]")));
	}

	@Test
	public void testCreateNewPerson() throws  Exception{
		String jsonData = objectMapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(new Person("New",88));
		System.out.println(jsonData);
		RequestBuilder request = post("/api/people")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonData)
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().encoding("UTF-8"))
				.andExpect(jsonPath("$.id",is(4)))
				.andExpect(jsonPath("$.name",equalTo("New")));
	}

	@Test
	public void testUpdatePersonById() throws Exception{
		String jasonData = objectMapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(new Person("bbb",77));
		RequestBuilder request = put("/api/people/id/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jasonData)
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"id\":2,\"name\":\"bbb\",\"age\":77}")));
	}




}
