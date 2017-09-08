package com.example.appDemo.repo;

import com.example.appDemo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

/**
 * Created by xu.yin on 8/24/17.
 */
public interface PeopleRepository extends JpaRepository<Person,Long> {
    List<Person> findByName(String name);

}
