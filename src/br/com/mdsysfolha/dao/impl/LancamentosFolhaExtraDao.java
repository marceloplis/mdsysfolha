package br.com.mdsysfolha.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.mdsysfolha.dao.common.GenericDao;
import br.com.mdsysfolha.entity.FuncionarioEntity;
import br.com.mdsysfolha.entity.LancamentosFolhaExtraEntity;

public class LancamentosFolhaExtraDao extends GenericDao<LancamentosFolhaExtraEntity>{

	public LancamentosFolhaExtraDao(Session session){
		super(session);
	}
	
	@SuppressWarnings("unchecked")
	public List<LancamentosFolhaExtraEntity> listaExtrasPorFuncionario(FuncionarioEntity func, Long idFolha){
		Criteria benefs = session.createCriteria(LancamentosFolhaExtraEntity.class, "folha");
		benefs.createAlias("folha.folhaPagamento","folhaPagamento");
		benefs.createAlias("folha.funcionario","funcionario");
		benefs.add(Restrictions.eq("folhaPagamento.id", idFolha));
		benefs.add(Restrictions.eq("funcionario", func));
		return benefs.list();
	}
	
	public void deleteByFolhaPagamento(Long idFolha){
		String hql = "delete from LancamentosFolhaExtraEntity where folhaPagamento.id= :idFolha";
		session.createQuery(hql).setLong("idFolha", idFolha).executeUpdate();
	}
	
}
