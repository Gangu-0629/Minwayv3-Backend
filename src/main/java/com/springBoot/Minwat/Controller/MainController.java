package com.springBoot.Minwat.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.Minwat.CustomsSecutiryModels.CustomsUserDetails;
import com.springBoot.Minwat.JWTModels.Update;
import com.springBoot.Minwat.Models.LoginModel;
import com.springBoot.Minwat.Models.Registerform;
import com.springBoot.Minwat.Models.User;
import com.springBoot.Minwat.Services.Userservice;

import ch.qos.logback.core.status.Status;


@RestController

@RequestMapping("/info")
@CrossOrigin(origins = "*")
public class MainController {

	@Autowired
	private Userservice userservice;

	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	
	@PostMapping("/adddata")
	public ResponseEntity<Integer> add(@RequestBody Update data){
		System.out.println("add Called");
		Integer num=userservice.adddata(data);
		return new ResponseEntity<>(num,HttpStatus.OK);
	}
	@PostMapping("/decreasedata")
	public ResponseEntity<Integer> decrease(@RequestBody Update data){
		System.out.println("decrease Called");
		Integer num=userservice.decresedata(data);
		return new ResponseEntity<>(num,HttpStatus.OK);
	}
	@PostMapping("/getuser")
	public ResponseEntity<User> getUser(@RequestBody Update data) {
	   System.out.println("IN getuser");
		User us= userservice.getUser(data.getEmail());
		return new ResponseEntity<>(us,HttpStatus.OK);
	}
	@GetMapping("/getit")
	public ResponseEntity<User> getit(){
		User us=userservice.getUser("sarada@gmail.com");
		return new ResponseEntity<>(us,HttpStatus.OK);
	}
	
	
	
}
