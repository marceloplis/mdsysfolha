package br.com.mdsysfolha.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

public class CargoDescontoId implements Serializable {

	private static final long serialVersionUID = 1L;
	 
	@ManyToOne(fetch = FetchType.LAZY, cascade={ CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE })
    private CargoEntity cargo;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={ CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE })
    private DescontoEntity desconto;
    
    
	public CargoEntity getCargo() {
		return cargo;
	}
	public void setCargo(CargoEntity cargo) {
		this.cargo = cargo;
	}	
	
	public DescontoEntity getDesconto() {
		return desconto;
	}
	public void setDesconto(DescontoEntity desconto) {
		this.desconto = desconto;
	}
     
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CargoDescontoId that = (CargoDescontoId) o;

        if (cargo != null ? !cargo.equals(that.cargo) : that.cargo != null) return false;
        if (desconto != null ? !desconto.equals(that.desconto) : that.desconto != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (cargo != null ? cargo.hashCode() : 0);
        result = 31 * result + (desconto != null ? desconto.hashCode() : 0);
        return result;
    }
    

}
