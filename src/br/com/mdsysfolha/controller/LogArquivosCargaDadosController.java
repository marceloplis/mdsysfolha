package br.com.mdsysfolha.controller;

import br.com.mdsysfolha.dao.common.FactoryDao;
import br.com.mdsysfolha.entity.LogArquivosCargaDadosEntity;

public class LogArquivosCargaDadosController {

	private final FactoryDao factoryDao;
	
	public LogArquivosCargaDadosController(){
		this.factoryDao = new FactoryDao();
	}
	
	public LogArquivosCargaDadosEntity gravar(LogArquivosCargaDadosEntity log) throws Exception{		
		
		if(log.getId() == null || log.getId() == 0){	
			this.factoryDao.getLogArquivosCargaDadosDao().adiciona(log);
		}else{				
			this.factoryDao.getLogArquivosCargaDadosDao().altera(log);
		}
		
		return log;
	}
	
	public LogArquivosCargaDadosEntity getByName(String nm_arquivo){
		return this.factoryDao.getLogArquivosCargaDadosDao().getByName(nm_arquivo);
	}
	
}
