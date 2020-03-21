package br.com.mdsysfolha.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Embeddable
public class CargoBeneficioId implements Serializable {

	private static final long serialVersionUID = 1L;
	 
	@ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE })
	private CargoEntity cargo;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE })
    private BeneficioEntity beneficio;
        
	public CargoEntity getCargo() {
		return cargo;
	}
	public void setCargo(CargoEntity cargo) {
		this.cargo = cargo;
	}
	
	
	public BeneficioEntity getBeneficio() {
		return beneficio;
	}
	public void setBeneficio(BeneficioEntity beneficio) {
		this.beneficio = beneficio;
	}
     
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CargoBeneficioId that = (CargoBeneficioId) o;

        if (cargo != null ? !cargo.equals(that.cargo) : that.cargo != null) return false;
        if (beneficio != null ? !beneficio.equals(that.beneficio) : that.beneficio != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (cargo != null ? cargo.hashCode() : 0);
        result = 31 * result + (beneficio != null ? beneficio.hashCode() : 0);
        return result;
    }
    
	
}
