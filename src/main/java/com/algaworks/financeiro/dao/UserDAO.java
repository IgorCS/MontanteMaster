package com.algaworks.financeiro.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.algaworks.financeiro.entities.User;
@Service
@Controller
@Scope("request")
public interface UserDAO {
	List<User> findAll();

	void save(User user);

	void update(User user);

	void remove(User user);

	User getById(Long id);
	
	User login(String username, String password);
}
