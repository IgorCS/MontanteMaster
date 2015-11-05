package com.algaworks.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.financeiro.controller.Usuario;



public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public Usuarios(EntityManager manager) {
		this.manager = manager;
	}

	public Usuario porId(Long id) {
		return manager.find(Usuario.class, id);
	}
	
	public Usuario porUsername(String username) {
		return manager.find(Usuario.class, username);
	}

	public void adicionar(Usuario usuario) {
		this.manager.persist(usuario);
	}

	public Usuario guardar(Usuario usuario) {
		return this.manager.merge(usuario);
	}

	public List<Usuario> todosUsuarios() {
		TypedQuery<Usuario> query = manager.createQuery("from Usuario", Usuario.class);
		return query.getResultList();
	}
	
	

	public Usuario userLogin() {
		TypedQuery<Usuario> query = manager.createQuery("from Usuario", Usuario.class);
		return query.getSingleResult();
	}

	/*public Usuario getUsuario(String nome, String senha) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("usuarios");
		EntityManager em = factory.createEntityManager();

		try {
			Usuario usuario = (Usuario) em
					.createQuery("SELECT u from Usuario u where u.nome = :nome and u.senha = :senha")
					.setParameter("nome", nome).setParameter("senha", senha).getSingleResult();

			return usuario;
		} catch (NoResultException e) {
			return null;
		}

	}*/

}