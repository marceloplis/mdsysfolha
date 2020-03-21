package br.com.mdsysfolha.dao.impl;

import org.hibernate.Session;

import br.com.mdsysfolha.dao.common.GenericDao;
import br.com.mdsysfolha.entity.CargoBeneficioEntity;

public class CargoBeneficioDao extends GenericDao<CargoBeneficioEntity>{

	public CargoBeneficioDao(Session session) {
		super(session);
	}	

}
