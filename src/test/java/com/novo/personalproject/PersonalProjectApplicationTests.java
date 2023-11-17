package com.novo.personalproject;

import com.novo.personalproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PersonalProjectApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		UserService userService = context.getBean(UserService.class);
		assertNotNull(userService, "SomeBean должен быть в контексте");
	}

}
