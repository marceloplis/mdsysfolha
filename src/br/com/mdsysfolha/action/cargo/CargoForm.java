package br.com.mdsysfolha.action.cargo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.mdsysfolha.action.common.FormBase;
import br.com.mdsysfolha.entity.BeneficioEntity;
import br.com.mdsysfolha.entity.CargoBeneficioEntity;
import br.com.mdsysfolha.entity.CargoDescontoEntity;
import br.com.mdsysfolha.entity.CargoEntity;
import br.com.mdsysfolha.entity.DescontoEntity;

public class CargoForm extends FormBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public CargoEntity cargo;
	
	public List<CargoEntity> listCargos;
	
	public Long beneficioId;
	
	public Long descontoId;
		
	public CargoBeneficioEntity cargoBeneficio;
	
	public CargoDescontoEntity cargoDesconto;
	
	public List<BeneficioEntity> listBeneficios;
	
	public List<DescontoEntity> listDescontos;
	
	public CargoEntity getCargo() {
		return cargo;
	}
	
	public void setCargo(CargoEntity cargo) {
		this.cargo = cargo;
	}
	
	public List<CargoEntity> getListCargos() {
		return listCargos;
	}
	
	public void setListCargos(List<CargoEntity> listCargos) {
		this.listCargos = listCargos;
	}
		
	public Long getBeneficioId() {
		return beneficioId;
	}
	
	public void setBeneficioId(Long beneficioId) {
		this.beneficioId = beneficioId;
	}
	
	public Long getDescontoId() {
		return descontoId;
	}
	
	public void setDescontoId(Long descontoId) {
		this.descontoId = descontoId;
	}	
	
	public CargoBeneficioEntity getCargoBeneficio() {
		return cargoBeneficio;
	}

	public void setCargoBeneficio(CargoBeneficioEntity cargoBeneficio) {
		this.cargoBeneficio = cargoBeneficio;
	}

	public CargoDescontoEntity getCargoDesconto() {
		return cargoDesconto;
	}

	public void setCargoDesconto(CargoDescontoEntity cargoDesconto) {
		this.cargoDesconto = cargoDesconto;
	}

	public List<BeneficioEntity> getListBeneficios() {
		return listBeneficios;
	}

	public void setListBeneficios(List<BeneficioEntity> listBeneficios) {
		this.listBeneficios = listBeneficios;
	}

	public List<DescontoEntity> getListDescontos() {
		return listDescontos;
	}

	public void setListDescontos(List<DescontoEntity> listDescontos) {
		this.listDescontos = listDescontos;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request ) {
	      ActionErrors errors = new ActionErrors();
	      
	      if( cargo.getDescricao() == null || cargo.getDescricao().length() < 1 ) {
	    	  errors.add("nome",new ActionMessage("error.required.o","nome"));
	      }
	      
	      return errors;
	}
	
}
