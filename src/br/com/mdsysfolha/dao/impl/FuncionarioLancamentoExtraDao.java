package br.com.mdsysfolha.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.mdsysfolha.dao.common.GenericDao;
import br.com.mdsysfolha.entity.FuncionarioLctosExtraEntity;

public class FuncionarioLancamentoExtraDao extends GenericDao<FuncionarioLctosExtraEntity>{
	
	public FuncionarioLancamentoExtraDao(Session session) {
		super(session);
	}
	
	@SuppressWarnings("unchecked")
	public List<FuncionarioLctosExtraEntity> listaByFunc(String cpf){
		
		List<FuncionarioLctosExtraEntity> lctos = session.createCriteria(FuncionarioLctosExtraEntity.class)
			.add( Restrictions.eq("pk.funcionario.cpf", cpf))			
			.list();
		return lctos;
	}
	
}
