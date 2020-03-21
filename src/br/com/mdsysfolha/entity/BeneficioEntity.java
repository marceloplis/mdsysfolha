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
@Table(name="tbbeneficios")
@SequenceGenerator(name = "seq_beneficio", sequenceName = "seq_beneficio", allocationSize = 1)
public class BeneficioEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BeneficioEntity() {}
	
	public BeneficioEntity(Long id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_beneficio")
	@Column(name="benef_sequencial")
	private Long id;
	
	@Column(name="benef_descricao")
	private String descricao;
	
	@Column(name="benef_tipo_valor")
	private String tipo_valor;
	
	@Column(name="benef_ordem_calculo")
	private Integer ordem;
	
	@Column(name="benef_base_calculo")
	private String base_calculo;
	
	@Column(name="benef_altera_base_calculo")
	private String altera_base_calculo;
	
	@OneToMany(targetEntity=CargoBeneficioEntity.class, fetch = FetchType.LAZY, mappedBy = "pk.beneficio", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private Set<CargoBeneficioEntity> cargoBeneficios = new HashSet<CargoBeneficioEntity>(0);

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

	public String getAltera_base_calculo() {
		return altera_base_calculo;
	}
	
	public void setAltera_base_calculo(String altera_base_calculo) {
		this.altera_base_calculo = altera_base_calculo;
	}

	public Set<CargoBeneficioEntity> getCargoBeneficios() {
		return cargoBeneficios;
	}

	public void setCargoBeneficios(Set<CargoBeneficioEntity> cargoBeneficios) {
		this.cargoBeneficios = cargoBeneficios;
	}
	
	
}
