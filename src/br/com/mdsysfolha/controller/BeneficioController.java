package br.com.mdsysfolha.controller;

import java.util.List;

import br.com.mdsysfolha.dao.common.FactoryDao;
import br.com.mdsysfolha.entity.BeneficioEntity;

public class BeneficioController {

	private final FactoryDao factoryDao;
	
	public BeneficioController(){
		this.factoryDao = new FactoryDao();
	}
	
	public List<BeneficioEntity> listTodos(){		
		return this.factoryDao.getBeneficioDao().listaTudo();
	}
	
	public BeneficioEntity gravar(BeneficioEntity beneficio) throws Exception{		
		
		if(beneficio.getId() == null || beneficio.getId() == 0){				
			this.factoryDao.getBeneficioDao().adiciona(beneficio);
		}else{				
			this.factoryDao.getBeneficioDao().altera(beneficio);
		}
			
		return beneficio;
	}
	
	public void excluir(Long id){
		BeneficioEntity benef = new BeneficioEntity();
		benef.setId(id);		
		this.factoryDao.getBeneficioDao().remove(benef);
	}
	
	
	public BeneficioEntity buscarById(Long id){		
		BeneficioEntity benef = this.factoryDao.getBeneficioDao().procura(id);
		
		return benef;
	}
	
}
