package com.algaworks.financeiro.service;

import com.algaworks.financeiro.entities.User;

public interface LoginService {
	
	User login(String username, String password) throws IllegalArgumentException;
}
