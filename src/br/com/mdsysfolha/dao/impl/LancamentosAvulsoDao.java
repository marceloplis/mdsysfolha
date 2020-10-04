package br.com.mdsysfolha.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.mdsysfolha.dao.common.GenericDao;
import br.com.mdsysfolha.entity.LancamentosAvulsoEntity;

public class LancamentosAvulsoDao extends GenericDao<LancamentosAvulsoEntity> {

	public LancamentosAvulsoDao(Session session){
		super(session);
	}
	
	@SuppressWarnings("unchecked")
	public List<LancamentosAvulsoEntity> filtro(Date dtInicio, Date dtFim, String cpf, Integer loja){
		Criteria avs = session.createCriteria(LancamentosAvulsoEntity.class, "avulso");
		if(dtInicio != null && dtFim != null){
			Calendar cInicio = Calendar.getInstance();
			cInicio.setTime(dtInicio);
			
			Calendar cFim = Calendar.getInstance();
			cFim.setTime(dtFim);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			avs.add(Restrictions.between("data",java.sql.Date.valueOf(sdf.format(cInicio.getTime())),java.sql.Date.valueOf(sdf.format(cFim.getTime()))));
		}
		if(cpf != null && cpf.trim().length() > 0){
			avs.add(Restrictions.eq("funcionario.cpf", cpf.trim()));	
		}
		if(loja != null && loja > 0 ){
			avs.createAlias("avulso.funcionario","funcionario");
			avs.add(Restrictions.eq("funcionario.loja", loja));	
		}
		
		return avs.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<LancamentosAvulsoEntity> listaByFuncData(String cpf, int ano, int mes, String tpFolha){
		Calendar cInicio = Calendar.getInstance();
		cInicio.set(Calendar.YEAR, ano);
		cInicio.set(Calendar.MONTH, mes - 1);
		cInicio.set(Calendar.DAY_OF_MONTH, 1);
		cInicio.set(Calendar.HOUR_OF_DAY, 0);
		cInicio.set(Calendar.MINUTE, 0);
		cInicio.set(Calendar.SECOND, 0);
		
		Calendar cFim = Calendar.getInstance();
		cFim.set(Calendar.YEAR, ano);
		cFim.set(Calendar.MONTH, mes - 1);
		cFim.set(Calendar.DAY_OF_MONTH, cFim.getActualMaximum(Calendar.DAY_OF_MONTH));
		cFim.set(Calendar.HOUR_OF_DAY, 23);
		cFim.set(Calendar.MINUTE, 59);
		cFim.set(Calendar.SECOND, 59);
		
		List<LancamentosAvulsoEntity> lctos = session.createCriteria(LancamentosAvulsoEntity.class)
			.add( Restrictions.eq("funcionario.cpf", cpf))
			.add(Restrictions.between("data", cInicio.getTime(), cFim.getTime())) 
			.add( Restrictions.eq("tp_folha", tpFolha))
			.list();
		return lctos;
	}
	
	@SuppressWarnings("unchecked")
	public List<LancamentosAvulsoEntity> listaByFunc(String cpf){
		List<LancamentosAvulsoEntity> lctos = session.createCriteria(LancamentosAvulsoEntity.class)
			.add( Restrictions.eq("funcionario.cpf", cpf))
			.list();
		return lctos;
	}
		
}
