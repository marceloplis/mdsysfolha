package br.com.mdsysfolha.controller;

import java.util.List;

import br.com.mdsysfolha.dao.common.FactoryDao;
import br.com.mdsysfolha.entity.DescontoEntity;

public class DescontoController {

	private final FactoryDao factoryDao;
	
	public DescontoController(){
		this.factoryDao = new FactoryDao();
	}
	
	public List<DescontoEntity> listTodos(){		
		return this.factoryDao.getDescontoDao().listaTudo();
	}
	
	public DescontoEntity gravar(DescontoEntity desconto) throws Exception{		
		
		if(desconto.getId() == null || desconto.getId() == 0){				
			this.factoryDao.getDescontoDao().adiciona(desconto);
		}else{				
			this.factoryDao.getDescontoDao().altera(desconto);
		}
			
		return desconto;
	}
	
	public void excluir(Long id){
		DescontoEntity desc = new DescontoEntity();
		desc.setId(id);		
		this.factoryDao.getDescontoDao().remove(desc);
	}
	
	public DescontoEntity buscarById(Long id){		
		DescontoEntity desc = this.factoryDao.getDescontoDao().procura(id);
		
		return desc;
	}
	
}
