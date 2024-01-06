package com.springBoot.Minwat.Controller;

import javax.security.auth.login.AccountLockedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AccountStatusException;
import com.springBoot.Minwat.CustomsSecutiryModels.CustomsUserDetails;
import com.springBoot.Minwat.JWTModels.RequestToken;
import com.springBoot.Minwat.JWTModels.ResponseToken;
import com.springBoot.Minwat.JWTokens.JwtHelper;
import com.springBoot.Minwat.Models.ErrorModel;
import com.springBoot.Minwat.Models.Registerform;
import com.springBoot.Minwat.Models.User;
import com.springBoot.Minwat.Services.Userservice;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	@Autowired
	private Userservice userservice;
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private  JwtHelper helper;
	
	@PostMapping("/register")
public ResponseEntity<User>  register(@RequestBody Registerform form) 
{
		
	User user=userservice.getUser(form.getEmail());
	if(user!=null) {
		  return new ResponseEntity<>(user,HttpStatus.NOT_ACCEPTABLE);
	}
	return new ResponseEntity<>(userservice.registeruser(form),HttpStatus.OK);
}

@PostMapping("/Userlogin")
public ResponseEntity<ResponseToken> login(@RequestBody RequestToken request) {
	System.out.println("Loggedin");
	this.doAuthenticate(request.getEmail(), request.getPassword());
	CustomsUserDetails user= userservice.loadUserByUsername(request.getEmail());
	  String token = this.helper.generateToken(user);

        ResponseToken response = ResponseToken.builder()
                .token(token)
                .email(user.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
	
	
}
  @PostMapping("/changePassword")
  public ResponseEntity<User> changePassword(@RequestBody RequestToken request){
	  User us=userservice.changePassword(request);
	  if(us==null) {
		  return new ResponseEntity<>(us,HttpStatus.NOT_FOUND); 
	  }
	  return new ResponseEntity<>(us,HttpStatus.OK);
  }

   private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorModel> exceptionHandler() {
    	ErrorModel error=ErrorModel.builder().err("Invalid cerdentials").build();
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
  



}
