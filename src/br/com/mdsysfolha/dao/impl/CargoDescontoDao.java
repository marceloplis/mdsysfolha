package br.com.mdsysfolha.dao.impl;

import org.hibernate.Session;

import br.com.mdsysfolha.dao.common.GenericDao;
import br.com.mdsysfolha.entity.CargoDescontoEntity;

public class CargoDescontoDao extends GenericDao<CargoDescontoEntity>{

	public CargoDescontoDao(Session session) {
		super(session);
	}

}
