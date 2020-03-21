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
@Table(name = "tbfuncionarioextra")
@AssociationOverrides({
		@AssociationOverride(name = "pk.funcionario", 
			joinColumns = @JoinColumn(name = "func_cpf")),
		@AssociationOverride(name = "pk.extra", 
			joinColumns = @JoinColumn(name = "ext_sequencial")) })
public class FuncionarioLctosExtraEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FuncionarioLctosExtraId pk = new FuncionarioLctosExtraId();
	
	@Transient
	private FuncionarioEntity funcionario;
	
	@Transient
	private LancamentosExtraEntity extra;
	
	@Column(name="fnext_valor")
	private Double valor;
	
	@Transient
	private String valorParse;

	public FuncionarioLctosExtraId getPk() {
		return pk;
	}

	public void setPk(FuncionarioLctosExtraId pk) {
		this.pk = pk;
	}

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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public String getValorParse(){
		if(getValor() != null)
			valorParse = Utils.doubleToMoeda(getValor());
		return valorParse;
	}
	
	public void setValorParse(String string) {
		valorParse = string;
		if((valorParse != null) && (!valorParse.equals("")))
			setValor(Utils.MoedaStrToDouble(valorParse));
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		FuncionarioLctosExtraEntity that = (FuncionarioLctosExtraEntity) o;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
	
}
