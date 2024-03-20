package com.example.WebSite;

import com.example.WebSite.request.CreateUserRequest;
import com.example.WebSite.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

import static com.example.WebSite.entity.Role.ROLE_ADMIN;
import static com.example.WebSite.entity.Role.ROLE_USER;


@SpringBootApplication
public class WebSiteProject implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WebSiteProject.class, args);
	}

	private final UserService userService;

	public WebSiteProject(UserService userService){
		this.userService=userService;
	}

	@Override
	public void run(String... args) throws Exception{

	}

	private void createDummyData(){
		CreateUserRequest request1=CreateUserRequest.builder()
				.username("berk")
				.password("ts")
				.authorities(Set.of(ROLE_ADMIN))
				.build();
		userService.addUser(request1);
		CreateUserRequest request2=CreateUserRequest.builder()
				.username("ayse")
				.password("1234")
				.authorities(Set.of(ROLE_USER))
				.build();
		userService.addUser(request2);
		CreateUserRequest request3=CreateUserRequest.builder()
				.username("asli")
				.password("6161")
				.authorities(Set.of(ROLE_ADMIN))
				.build();
		userService.addUser(request3);
	}

}
