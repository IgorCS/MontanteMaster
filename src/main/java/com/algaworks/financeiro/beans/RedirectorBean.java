package com.algaworks.financeiro.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
@Scope("request")
public class RedirectorBean {
	public String userPage() {
		return "userPage";
	}

	public String adminPage() {
		return "adminPage";
	}

	public String index() {
		return "index";
	}

	public String consultaLancamentos() {
		return "consultaLancamentos";
	}

	public String successfulPage() {
		return "successfulPages";
	}
}
