package br.com.mdsysfolha.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbextras")
@SequenceGenerator(name = "seq_extra", sequenceName = "seq_extra", allocationSize = 1)
public class LancamentosExtraEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LancamentosExtraEntity() {}
	
	public LancamentosExtraEntity(Long id) {
		this.id = id;
	}
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_extra")
	@Column(name="ext_sequencial")
	private Long id;	 
	 
	@Column(name="ext_descricao")
	private String descricao;
	 
	@Column(name="ext_tipo_valor")
	private String tipo_valor;
	
	@Column(name="ext_tipo_lcto")
	private String tipo_lancamento;

	@OneToMany(targetEntity=FuncionarioLctosExtraEntity.class, fetch = FetchType.LAZY, mappedBy = "pk.extra", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private Set<FuncionarioLctosExtraEntity> lancamentosExtras = new HashSet<FuncionarioLctosExtraEntity>(0);
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo_valor() {
		return tipo_valor;
	}

	public void setTipo_valor(String tipo_valor) {
		this.tipo_valor = tipo_valor;
	}
	 
	public String getTipo_lancamento() {
		return tipo_lancamento;
	}
	
	public void setTipo_lancamento(String tipo_lancamento) {
		this.tipo_lancamento = tipo_lancamento;
	}
	
	public Set<FuncionarioLctosExtraEntity> getLancamentosExtras() {
		return lancamentosExtras;
	}
	
	public void setLancamentosExtras(
			Set<FuncionarioLctosExtraEntity> lancamentosExtras) {
		this.lancamentosExtras = lancamentosExtras;
	}
	
}
