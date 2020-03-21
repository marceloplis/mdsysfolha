package br.com.mdsysfolha.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.mdsysfolha.dao.common.GenericDao;
import br.com.mdsysfolha.entity.FuncionarioEntity;
import br.com.mdsysfolha.util.Utils;

public class FuncionarioDao extends GenericDao<FuncionarioEntity> {

	public FuncionarioDao(Session session){
		super(session);
	}	
	
	public FuncionarioEntity buscaByCPF(String cpf){
		FuncionarioEntity func = (FuncionarioEntity) session.createCriteria(FuncionarioEntity.class)
				.add(Restrictions.eq("cpf", Utils.lpadS(cpf.trim(),"0",11))).uniqueResult();
		return func;
	}
	
	public FuncionarioEntity buscaByCPFAtivo(String cpf){
		FuncionarioEntity func = (FuncionarioEntity) session.createCriteria(FuncionarioEntity.class)
				.add(Restrictions.eq("cpf", cpf.trim()))
				.add(Restrictions.eq("fl_ativo", "S"))
		.uniqueResult();
		return func;
	}
	
	@SuppressWarnings("unchecked")
	public List<FuncionarioEntity> listaTodos(){
		Criteria funcs = session.createCriteria(FuncionarioEntity.class);
		funcs.addOrder(Order.asc("nome"));
		return funcs.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<FuncionarioEntity> listaTodosAtivos(){
		Criteria funcs = session.createCriteria(FuncionarioEntity.class);
		funcs.add(Restrictions.eq("fl_ativo", "S"));
		funcs.addOrder(Order.asc("nome"));
		return funcs.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<FuncionarioEntity> filtro(int loja, String flAtivo, String cpf, String nome, int cargo){
		Criteria funcs = session.createCriteria(FuncionarioEntity.class);
		if(nome != null && nome.trim().length() > 0)
			funcs.add(Restrictions.ilike("nome", "%" + nome.trim().toLowerCase() + "%"));
		if(loja > 0)
			funcs.add(Restrictions.eq("loja", loja));
		if(flAtivo != null && flAtivo.trim().length() > 0)
			funcs.add(Restrictions.eq("fl_ativo", flAtivo.trim()));
		if(cpf != null && cpf.trim().replaceAll("[.-]", "").length() > 0)
			funcs.add(Restrictions.eq("cpf", cpf.trim().replaceAll("[.-]", "")));
		if(cargo > 0)
			funcs.add(Restrictions.eq("cargo.id", new Long(cargo)));
		if(cargo < 0)
			funcs.add(Restrictions.isNull("cargo"));
		
		funcs.addOrder(Order.asc("nome"));	
		return funcs.list();
	}
		
}
