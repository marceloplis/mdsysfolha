package br.com.mdsysfolha.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.mdsysfolha.dao.common.GenericDao;
import br.com.mdsysfolha.entity.FuncionarioEntity;
import br.com.mdsysfolha.entity.LancamentosFolhaAvulsoEntity;
import br.com.mdsysfolha.enums.TipoLancamentoEnum;

public class LancamentosFolhaAvulsoDao extends GenericDao<LancamentosFolhaAvulsoEntity>{

	public LancamentosFolhaAvulsoDao(Session session){
		super(session);
	}
	
	@SuppressWarnings("unchecked")
	public List<LancamentosFolhaAvulsoEntity> listaBeneficiosPorFuncionario(FuncionarioEntity func, Long idFolha){
		Criteria benefs = session.createCriteria(LancamentosFolhaAvulsoEntity.class, "folha");
		benefs.createAlias("folha.folhaPagamento","folhaPagamento");
		benefs.createAlias("folha.avulso","avulso");
		benefs.add(Restrictions.eq("folhaPagamento.id", idFolha));
		benefs.add(Restrictions.eq("avulso.funcionario", func));
		benefs.add(Restrictions.eq("avulso.tipo", TipoLancamentoEnum.CREDITO.getValue()));
		return benefs.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<LancamentosFolhaAvulsoEntity> listaDescontosPorFuncionario(FuncionarioEntity func, Long idFolha){
		Criteria descs = session.createCriteria(LancamentosFolhaAvulsoEntity.class, "folha");
		descs.createAlias("folha.folhaPagamento","folhaPagamento");
		descs.createAlias("folha.avulso","avulso");
		descs.add(Restrictions.eq("folhaPagamento.id", idFolha));
		descs.add(Restrictions.eq("avulso.funcionario", func));
		descs.add(Restrictions.eq("avulso.tipo", TipoLancamentoEnum.DEBITO.getValue()));
		return descs.list();
	}
	
	public void deleteByFolhaPagamento(Long idFolha){
		String hql = "delete from LancamentosFolhaAvulsoEntity where folhaPagamento.id= :idFolha";
		session.createQuery(hql).setLong("idFolha", idFolha).executeUpdate();
	}
	
}
