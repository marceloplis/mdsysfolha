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
@Table(name = "tbcargodesconto")
@AssociationOverrides({
		@AssociationOverride(name = "pk.cargo", 
			joinColumns = @JoinColumn(name = "carg_sequencial")),
		@AssociationOverride(name = "pk.desconto", 
			joinColumns = @JoinColumn(name = "desc_sequencial")) })
public class CargoDescontoEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CargoDescontoId pk = new CargoDescontoId();
	
	@Transient
	private CargoEntity cargo;
	
	@Transient
	private BeneficioEntity beneficio;
	
	@Column(name="cgdes_valor")
	private Double valor;
	
	@Transient
	private String valorDescontoParse;
	
	public CargoDescontoId getPk() {
		return pk;
	}

	public void setPk(CargoDescontoId pk) {
		this.pk = pk;
	}
	
	public CargoEntity getCargo() {
		return getPk().getCargo();
	}

	public void setCargo(CargoEntity cargo) {
		getPk().setCargo(cargo);
	}

	public DescontoEntity getDesconto() {
		return getPk().getDesconto();
	}

	public void setDesconto(DescontoEntity desconto) {
		getPk().setDesconto(desconto);
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}	
	
	public String getValorDescontoParse(){
		if(getValor() != null)
			valorDescontoParse = Utils.doubleToMoeda(getValor());
		return valorDescontoParse;
	}
	
	public void setValorDescontoParse(String string) {
		valorDescontoParse = string;
		if((valorDescontoParse != null) && (!valorDescontoParse.equals("")))
			setValor(Utils.MoedaStrToDouble(valorDescontoParse));
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CargoDescontoEntity that = (CargoDescontoEntity) o;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
	
}
