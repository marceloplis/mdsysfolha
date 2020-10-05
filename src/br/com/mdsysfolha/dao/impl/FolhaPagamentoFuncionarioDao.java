package br.com.mdsysfolha.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.mdsysfolha.dao.common.GenericDao;
import br.com.mdsysfolha.entity.FolhaPagamentoFuncionarioEntity;
import br.com.mdsysfolha.entity.FuncionarioEntity;

public class FolhaPagamentoFuncionarioDao extends GenericDao<FolhaPagamentoFuncionarioEntity>{

	public FolhaPagamentoFuncionarioDao(Session session) {
		super(session);
	}
	
	public void deleteByFolhaPagamento(Long idFolha){
		String hql = "delete from FolhaPagamentoFuncionarioEntity where folhaPagamento.id= :idFolha";
		session.createQuery(hql).setLong("idFolha", idFolha).executeUpdate();
	}
	
	public FolhaPagamentoFuncionarioEntity buscaPorFolhaFuncionario(FuncionarioEntity func, Long idFolha){
		Criteria folhaFunc = session.createCriteria(FolhaPagamentoFuncionarioEntity.class, "folha");
		folhaFunc.createAlias("folha.folhaPagamento","folhaPagamento");
		folhaFunc.createAlias("folha.funcionario","funcionario");
		folhaFunc.add(Restrictions.eq("folhaPagamento.id", idFolha));
		folhaFunc.add(Restrictions.eq("funcionario", func));
		return (FolhaPagamentoFuncionarioEntity) folhaFunc.uniqueResult();		
	}
	
	@SuppressWarnings("unchecked")
	public List<FolhaPagamentoFuncionarioEntity> listarFolhaFuncionarioPorFolha(Date dtInicio, Date dtFim, Long idFolha, Integer loja, String cpf){
		
		Criteria folhaFunc = session.createCriteria(FolhaPagamentoFuncionarioEntity.class, "folha");
		folhaFunc.createAlias("folha.folhaPagamento","folhaPagamento");
		if(dtInicio != null && dtFim != null){
			Calendar cInicio = Calendar.getInstance();
			cInicio.setTime(dtInicio);
			
			Calendar cFim = Calendar.getInstance();
			cFim.setTime(dtFim);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			folhaFunc.add(Restrictions.between("folhaPagamento.data_criacao",java.sql.Date.valueOf(sdf.format(cInicio.getTime())),java.sql.Date.valueOf(sdf.format(cFim.getTime()))));
		}
		if(idFolha != null && idFolha > 0 ){
			folhaFunc.add(Restrictions.eq("folhaPagamento.id", idFolha));
		}
		if((loja != null && loja > 0) || (cpf != null && cpf.trim().length() > 0)){
			folhaFunc.createAlias("folha.funcionario","funcionario");
			if(loja != null && loja > 0)
				folhaFunc.add(Restrictions.eq("funcionario.loja", loja));
			if(cpf != null && cpf.trim().length() > 0)
				folhaFunc.add(Restrictions.eq("funcionario.cpf", cpf));	
		}
		
		return folhaFunc.list();
		
	}
	
}
