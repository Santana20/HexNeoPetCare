package com.HexNeoPetCare.Domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="Cuidado")
public class Cuidado 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCuidado;
	
	private String nombre;
	
	@OneToMany(mappedBy = "cuidado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<RegistroCuidado> registro_cuidado;

	public Cuidado() {
	}

	public Cuidado(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdCuidado() {
		return idCuidado;
	}

	public void setIdCuidado(Long idCuidado) {
		this.idCuidado = idCuidado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<RegistroCuidado> getRegistro_cuidado() {
		return registro_cuidado;
	}

	public void setRegistro_cuidado(List<RegistroCuidado> registro_cuidado) {
		this.registro_cuidado = registro_cuidado;
	}
	
	
}