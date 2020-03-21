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
@Table(name="tbdescontos")
@SequenceGenerator(name = "seq_desconto", sequenceName = "seq_desconto", allocationSize = 1)
public class DescontoEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DescontoEntity() {}
	
	public DescontoEntity(Long id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_desconto")
	@Column(name="desc_sequencial")
	private Long id;
	
	@Column(name="desc_descricao")
	private String descricao;
	
	@Column(name="desc_tipo_valor")
	private String tipo_valor;
	
	@Column(name="desc_ordem_calculo")
	private Integer ordem;
	
	@Column(name="desc_base_calculo")
	private String base_calculo;
	
	@OneToMany(targetEntity=CargoDescontoEntity.class, fetch = FetchType.LAZY, mappedBy = "pk.desconto", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private Set<CargoDescontoEntity> cargoDescontos = new HashSet<CargoDescontoEntity>(0);

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
	
	public Integer getOrdem() {
		return ordem;
	}
	
	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}
	
	public String getBase_calculo() {
		return base_calculo;
	}
	
	public void setBase_calculo(String base_calculo) {
		this.base_calculo = base_calculo;
	}
	
	public Set<CargoDescontoEntity> getCargoDescontos() {
		return cargoDescontos;
	}

	public void setCargoDescontos(Set<CargoDescontoEntity> cargoDescontos) {
		this.cargoDescontos = cargoDescontos;
	}	 
	
	
}
