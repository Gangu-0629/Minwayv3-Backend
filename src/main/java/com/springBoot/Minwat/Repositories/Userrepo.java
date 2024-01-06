package com.springBoot.Minwat.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springBoot.Minwat.Models.User;
import java.util.List;


@Repository
public interface Userrepo extends JpaRepository<User, Long>{

	public User findByEmail(String email);
}
