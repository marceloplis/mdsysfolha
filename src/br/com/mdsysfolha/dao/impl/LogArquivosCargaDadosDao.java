package br.com.mdsysfolha.dao.impl;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.mdsysfolha.dao.common.GenericDao;
import br.com.mdsysfolha.entity.LogArquivosCargaDadosEntity;

public class LogArquivosCargaDadosDao extends GenericDao<LogArquivosCargaDadosEntity>{

	public LogArquivosCargaDadosDao(Session session) {
		super(session);
	}
	
	public LogArquivosCargaDadosEntity getByName(String nm_arquivo){
		LogArquivosCargaDadosEntity arq = (LogArquivosCargaDadosEntity) session.createCriteria(LogArquivosCargaDadosEntity.class)
				.add(Restrictions.eq("nome", nm_arquivo.trim())).uniqueResult();
		return arq;
	}

}
