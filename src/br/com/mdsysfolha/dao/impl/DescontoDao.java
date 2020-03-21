package br.com.mdsysfolha.dao.impl;

import org.hibernate.Session;

import br.com.mdsysfolha.dao.common.GenericDao;
import br.com.mdsysfolha.entity.DescontoEntity;

public class DescontoDao extends GenericDao<DescontoEntity>{

	public DescontoDao(Session session) {
		super(session);
	}
	

}
