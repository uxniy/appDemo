package com.example.appDemo.controller.page;

import com.example.appDemo.model.Person;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;

/**
 * Created by xu.yin on 8/23/17.
 */
@Controller
public class PageController {


    @Autowired
    private RestTemplate restTemplate;

    private static final String ROOT_URL = "http://localhost:8080/appdemo/";

    @RequestMapping("/")
    public String index(Model model) throws Exception{

//        restTemplate.postForObject(ROOT_URL + "/api/people" , new Person("NewOne",90),Person.class);

        Person[] people = restTemplate.getForObject(ROOT_URL + "/api/people",Person[].class);

        if(people.length > 0){
            model.addAttribute("singlePerson", people[0]);
        }else{
            throw new Exception("There is no person here.");
        }

        List<Person> peoplelist = Arrays.asList(people);


        model.addAttribute("people",peoplelist);

        return "index";


    }


}
