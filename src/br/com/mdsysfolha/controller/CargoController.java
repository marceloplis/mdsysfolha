package br.com.mdsysfolha.controller;

import java.util.List;

import br.com.mdsysfolha.dao.common.FactoryDao;
import br.com.mdsysfolha.entity.CargoBeneficioEntity;
import br.com.mdsysfolha.entity.CargoDescontoEntity;
import br.com.mdsysfolha.entity.CargoEntity;

public class CargoController {

	private final FactoryDao factoryDao;
	
	public CargoController(){
		this.factoryDao = new FactoryDao();
	}
	
	public List<CargoEntity> listTodos(){		
		return this.factoryDao.getCargoDao().listaTudo();
	}
	
	public CargoEntity gravar(CargoEntity cargo) throws Exception{		
		
		if(cargo.getId() == null || cargo.getId() == 0){				
			this.factoryDao.getCargoDao().adiciona(cargo);
		}else{				
			this.factoryDao.getCargoDao().altera(cargo);
		}
			
		return cargo;
	}
	
	public CargoEntity buscarById(Long id){		
		CargoEntity cargo = this.factoryDao.getCargoDao().procura(id);
		
		return cargo;
	}
	
	
	public CargoBeneficioEntity gravarBeneficio(CargoBeneficioEntity cb) throws Exception{		
		
		this.factoryDao.getCargoBeneficioDao().SaveOrUpdate(cb);
					
		return cb;
	}
	
	public CargoDescontoEntity gravarDesconto(CargoDescontoEntity cd) throws Exception{		
		
		this.factoryDao.getCargoDescontoDao().SaveOrUpdate(cd);
					
		return cd;
	}
	
	public void excluir(Long id){
		CargoEntity cargo = new CargoEntity();
		cargo.setId(id);		
		cargo.setCargoBeneficios(null);
		cargo.setCargoDescontos(null);
		this.factoryDao.getCargoDao().remove(cargo);
	}
	
	public void excluirBeneficio(CargoBeneficioEntity cb){
		this.factoryDao.getCargoBeneficioDao().remove(cb);
	}
	
	public void excluirDesconto(CargoDescontoEntity cd){
		this.factoryDao.getCargoDescontoDao().remove(cd);
	}
	
}
