package br.com.mdsysfolha.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.mdsysfolha.dao.common.GenericDao;
import br.com.mdsysfolha.entity.FuncionarioEntity;
import br.com.mdsysfolha.entity.LancamentosFolhaDescontoEntity;

public class LancamentosFolhaDescontoDao extends GenericDao<LancamentosFolhaDescontoEntity> {

	public LancamentosFolhaDescontoDao(Session session){
		super(session);
	}
	
	@SuppressWarnings("unchecked")
	public List<LancamentosFolhaDescontoEntity> listaDescontosPorFuncionario(FuncionarioEntity func, Long idFolha){
		Criteria descs = session.createCriteria(LancamentosFolhaDescontoEntity.class, "folha");
		descs.createAlias("folha.folhaPagamento","folhaPagamento");
		descs.createAlias("folha.funcionario","funcionario");
		descs.add(Restrictions.eq("folhaPagamento.id", idFolha));
		descs.add(Restrictions.eq("funcionario", func));
		return descs.list();
	}
	
	public void deleteByFolhaPagamento(Long idFolha){
		String hql = "delete from LancamentosFolhaDescontoEntity where folhaPagamento.id= :idFolha";
		session.createQuery(hql).setLong("idFolha", idFolha).executeUpdate();
	}
	
}
