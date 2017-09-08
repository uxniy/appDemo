package com.example.appDemo;

import com.example.appDemo.model.Person;
import com.example.appDemo.repo.PeopleRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@EnableJpaRepositories("com.example.appDemo.repo")
@EntityScan("com.example.appDemo.model")
@SpringBootApplication
public class AppDemoApplication
//		extends SpringBootServletInitializer
{

	public static void main(String[] args) {
		SpringApplication.run(AppDemoApplication.class, args);
	}

//	/***
//	 * これはjar->warにするため
//	 * SpringBootServletInitializerを継承することで、
//	 * このクラスをweb.xmlの作用になる
//	 * @param builder
//	 * @return
//	 */
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
//		return builder.sources(AppDemoApplication.class);
//	}

	@Bean
	CommandLineRunner init(PeopleRepository peopleRepository){

		Person person1 = new Person("a",1);
		Person person2 = new Person("b",2);
		Person person3 = new Person("c",3);

		List<Person> people = new ArrayList<>();
		people.add(person1);
		people.add(person2);
		people.add(person3);

		return (evt)->{people.forEach((person -> peopleRepository.save(person)));};

	}

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}




}
