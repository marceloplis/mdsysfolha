package br.com.mdsysfolha.dao.impl;

import org.hibernate.Session;

import br.com.mdsysfolha.dao.common.GenericDao;
import br.com.mdsysfolha.entity.BeneficioEntity;

public class BeneficioDao extends GenericDao<BeneficioEntity>{

	public BeneficioDao(Session session) {
		super(session);
	}
	
}
