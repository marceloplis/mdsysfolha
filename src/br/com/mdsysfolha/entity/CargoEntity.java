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
@Table(name="tbcargos")
@SequenceGenerator(name = "seq_cargo", sequenceName = "seq_cargo", allocationSize = 1)
public class CargoEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CargoEntity(){}
	
	public CargoEntity(Long id){
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_cargo")
	@Column(name="carg_sequencial")
	private Long id;
	
	@Column(name="carg_descricao")
	private String descricao;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="pk.cargo", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private Set<CargoBeneficioEntity> cargoBeneficios = new HashSet<CargoBeneficioEntity>(0);
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="pk.cargo", cascade=CascadeType.REMOVE, orphanRemoval=true)
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

	public Set<CargoBeneficioEntity> getCargoBeneficios() {
		return cargoBeneficios;
	}

	public void setCargoBeneficios(Set<CargoBeneficioEntity> cargoBeneficios) {
		this.cargoBeneficios = cargoBeneficios;
	}
	
	public Set<CargoDescontoEntity> getCargoDescontos() {
		return cargoDescontos;
	}

	public void setCargoDescontos(Set<CargoDescontoEntity> cargoDescontos) {
		this.cargoDescontos = cargoDescontos;
	}
	
		
}
