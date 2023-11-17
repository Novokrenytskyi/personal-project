package com.novo.personalproject;

import com.novo.personalproject.dao.UserRepository;
import com.novo.personalproject.model.entity.Gender;
import com.novo.personalproject.model.entity.Role;
import com.novo.personalproject.model.entity.User;
import com.novo.personalproject.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class PersonalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalProjectApplication.class, args);



//		User user = User.builder()
//				.email("andrey@gmail.com")
//				.firstName("Andrey")
//				.lastName("Nudnov")
//				.gender(Gender.MALE)
//				.role(Role.USER)
//				.birthDate(LocalDate.of(1995, 6, 29))
//				.build();
//
//
	}

}
