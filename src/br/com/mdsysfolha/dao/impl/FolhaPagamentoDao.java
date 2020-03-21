package br.com.mdsysfolha.dao.impl;

import org.hibernate.Session;

import br.com.mdsysfolha.dao.common.GenericDao;
import br.com.mdsysfolha.entity.FolhaPagamentoEntity;

public class FolhaPagamentoDao extends GenericDao<FolhaPagamentoEntity>{

	public FolhaPagamentoDao(Session session) {
		super(session);
	}
	
}
