package br.com.mdsysfolha.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.mdsysfolha.dao.common.GenericDao;
import br.com.mdsysfolha.entity.FolhaPagamentoEntity;

public class FolhaPagamentoDao extends GenericDao<FolhaPagamentoEntity>{

	public FolhaPagamentoDao(Session session) {
		super(session);
	}
	
	@SuppressWarnings("unchecked")
	public List<FolhaPagamentoEntity> filtro(Date dtInicio, Date dtFim, String status){
		Criteria folha = session.createCriteria(FolhaPagamentoEntity.class, "avulso");
		if(dtInicio != null && dtFim != null){
			Calendar cInicio = Calendar.getInstance();
			cInicio.setTime(dtInicio);
			
			Calendar cFim = Calendar.getInstance();
			cFim.setTime(dtFim);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			folha.add(Restrictions.between("data_criacao",java.sql.Date.valueOf(sdf.format(cInicio.getTime())),java.sql.Date.valueOf(sdf.format(cFim.getTime()))));
		}
		if(status != null && status.trim().length() > 0){
			folha.add(Restrictions.eq("status", status.trim()));	
		}
		
		
		return folha.list();
	}
	
}
