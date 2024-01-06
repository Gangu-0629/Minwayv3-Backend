package com.springBoot.Minwat.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springBoot.Minwat.CustomsSecutiryModels.CustomsUserDetails;
import com.springBoot.Minwat.JWTModels.RequestToken;
import com.springBoot.Minwat.JWTModels.Update;
import com.springBoot.Minwat.Models.Registerform;
import com.springBoot.Minwat.Models.User;
import com.springBoot.Minwat.Repositories.Userrepo;

@Service
public class Userservice implements UserDetailsService {
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

	@Autowired
	private Userrepo userrepo;
	@Override
	public CustomsUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=this.userrepo.findByEmail(username);
		CustomsUserDetails userdetails=new CustomsUserDetails(user);
		return userdetails;
	}
	public User registeruser(Registerform form) {
	   User user=User.builder().username(form.getName()).email(form.getEmail()).password(encoder.encode(form.getPassword())).level(0).build();
		userrepo.save(user);
		return user;
	}
	
	public User getUser(String email) {
		return userrepo.findByEmail(email);
	}
	
	public int adddata(Update data){
		User user=userrepo.findByEmail(data.getEmail());
		System.out.println(data.getEmail());
		int levl=user.getLevel();
		user.setLevel(levl+1);
		userrepo.save(user);
		return levl;
	}
	

	public int decresedata(Update data){
		User user=userrepo.findByEmail(data.getEmail());
		System.out.println(data.getEmail());
		int levl=user.getLevel();
		user.setLevel(levl-1);
		userrepo.save(user);
		return levl;
	}
	
	public User changePassword(RequestToken ret) {
		User user=userrepo.findByEmail(ret.getEmail());
		user.setPassword(encoder.encode(ret.getPassword()));
		userrepo.save(user);
		return user;
	}
}
