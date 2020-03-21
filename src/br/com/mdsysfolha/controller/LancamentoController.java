package br.com.mdsysfolha.controller;

import br.com.mdsysfolha.dao.common.FactoryDao;
import br.com.mdsysfolha.entity.FuncionarioEntity;
import br.com.mdsysfolha.entity.LancamentosAvulsoEntity;

public class LancamentoController {

	
	private final FactoryDao factoryDao;
	
	public LancamentoController(){
		this.factoryDao = new FactoryDao();
	}
	
	public LancamentosAvulsoEntity addByFile(LancamentosAvulsoEntity lcto){
		
		FuncionarioEntity funcionario = this.factoryDao.getFuncionarioDao().buscaByCPF(lcto.getCpf());
		lcto.setFuncionario(funcionario);
		
		this.factoryDao.getLancamentoAvulsoDao().adiciona(lcto);
		
		return lcto;
		
	}	
	
}
