package br.com.mdsysfolha.dao.impl;

import org.hibernate.Session;

import br.com.mdsysfolha.dao.common.GenericDao;
import br.com.mdsysfolha.entity.CargoEntity;

public class CargoDao extends GenericDao<CargoEntity>{

	public CargoDao(Session session) {
		super(session);
	}

}
