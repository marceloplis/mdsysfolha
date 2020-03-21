package br.com.mdsysfolha.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.mdsysfolha.dao.common.GenericDao;
import br.com.mdsysfolha.entity.UsuarioEntity;

public class UsuarioDao extends GenericDao<UsuarioEntity> {

	public UsuarioDao(Session session){
		super(session);
	}
	
	public UsuarioEntity buscaByUsername(String username){
		UsuarioEntity usuario = (UsuarioEntity) session.createCriteria(UsuarioEntity.class)
				.add(Restrictions.eq("username", username.trim())).uniqueResult();
		return usuario;
	}

	public UsuarioEntity login(String username, String password){
		UsuarioEntity usuario = (UsuarioEntity) session.createCriteria(UsuarioEntity.class)
				.add(Restrictions.and(Restrictions.eq("username", username.trim()), Restrictions.eq("senha", password.trim()))).uniqueResult();
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioEntity> busca(String termo){
		List<UsuarioEntity> usuarios = session.createCriteria(UsuarioEntity.class)
			.add( Restrictions.ilike("nome"	 , "%" + termo.toLowerCase() + "%")).list();
		return usuarios;
	}

	
}
