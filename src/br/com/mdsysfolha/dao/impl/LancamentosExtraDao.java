package br.com.mdsysfolha.dao.impl;

import org.hibernate.Session;

import br.com.mdsysfolha.dao.common.GenericDao;
import br.com.mdsysfolha.entity.LancamentosExtraEntity;

public class LancamentosExtraDao extends GenericDao<LancamentosExtraEntity>{

	public LancamentosExtraDao(Session session) {
		super(session);
	}
	
}
