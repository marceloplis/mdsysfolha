package br.com.mdsysfolha.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Embeddable
public class FuncionarioLctosExtraId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE })
	private FuncionarioEntity funcionario;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE })
	private LancamentosExtraEntity extra;

	public FuncionarioEntity getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioEntity funcionario) {
		this.funcionario = funcionario;
	}

	public LancamentosExtraEntity getExtra() {
		return extra;
	}

	public void setExtra(LancamentosExtraEntity extra) {
		this.extra = extra;
	}
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FuncionarioLctosExtraId that = (FuncionarioLctosExtraId) o;

        if (funcionario != null ? !funcionario.equals(that.funcionario) : that.funcionario != null) return false;
        if (extra != null ? !extra.equals(that.extra) : that.extra != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (funcionario != null ? funcionario.hashCode() : 0);
        result = 31 * result + (extra != null ? extra.hashCode() : 0);
        return result;
    }
	
}
