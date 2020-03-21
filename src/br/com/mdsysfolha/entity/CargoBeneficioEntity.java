package br.com.mdsysfolha.entity;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.mdsysfolha.util.Utils;

@Entity
@Table(name = "tbcargobeneficio")
@AssociationOverrides({
		@AssociationOverride(name = "pk.cargo", 
			joinColumns = @JoinColumn(name = "carg_sequencial")),
		@AssociationOverride(name = "pk.beneficio", 
			joinColumns = @JoinColumn(name = "benef_sequencial")) })
public class CargoBeneficioEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CargoBeneficioId pk = new CargoBeneficioId();
	
	@Transient
	private CargoEntity cargo;
	
	@Transient
	private BeneficioEntity beneficio;
	
	@Column(name="cgben_valor")
	private Double valor;
	
	@Transient
	private String valorBeneficioParse;
	
	public CargoBeneficioId getPk() {
		return pk;
	}

	public void setPk(CargoBeneficioId pk) {
		this.pk = pk;
	}
		
	public CargoEntity getCargo() {
		return getPk().getCargo();
	}

	public void setCargo(CargoEntity cargo) {
		getPk().setCargo(cargo);
	}

	public BeneficioEntity getBeneficio() {
		return getPk().getBeneficio();
	}

	public void setBeneficio(BeneficioEntity beneficio) {
		getPk().setBeneficio(beneficio);
	}
	

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public String getValorBeneficioParse(){
		if(getValor() != null)
			valorBeneficioParse = Utils.doubleToMoeda(getValor());
		return valorBeneficioParse;
	}
	
	public void setValorBeneficioParse(String string) {
		valorBeneficioParse = string;
		if((valorBeneficioParse != null) && (!valorBeneficioParse.equals("")))
			setValor(Utils.MoedaStrToDouble(valorBeneficioParse));
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CargoBeneficioEntity that = (CargoBeneficioEntity) o;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
	
}
