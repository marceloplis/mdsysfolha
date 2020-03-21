package br.com.mdsysfolha.controller;

import java.util.List;

import br.com.mdsysfolha.dao.common.FactoryDao;
import br.com.mdsysfolha.entity.FuncionarioLctosExtraEntity;
import br.com.mdsysfolha.entity.LancamentosExtraEntity;

public class LancamentoExtraController {

private final FactoryDao factoryDao;
	
	public LancamentoExtraController(){
		this.factoryDao = new FactoryDao();
	}
	
	public List<LancamentosExtraEntity> listTodos(){		
		return this.factoryDao.getLancamentosExtraDao().listaTudo();
	}
	
	public LancamentosExtraEntity gravar(LancamentosExtraEntity extra) throws Exception{		
		
		if(extra.getId() == null || extra.getId() == 0){				
			this.factoryDao.getLancamentosExtraDao().adiciona(extra);
		}else{				
			this.factoryDao.getLancamentosExtraDao().altera(extra);
		}
			
		return extra;
	}
	
	public void excluir(Long id){
		LancamentosExtraEntity benef = new LancamentosExtraEntity();
		benef.setId(id);		
		this.factoryDao.getLancamentosExtraDao().remove(benef);
	}
	
	
	public List<FuncionarioLctosExtraEntity> listaByFunc(String cpf){		
		return this.factoryDao.getFuncionarioLancamentoExtraDao().listaByFunc(cpf);
	}
	
	public LancamentosExtraEntity buscarById(Long id){		
		LancamentosExtraEntity extra = this.factoryDao.getLancamentosExtraDao().procura(id);
		
		return extra;
	}
	
}
