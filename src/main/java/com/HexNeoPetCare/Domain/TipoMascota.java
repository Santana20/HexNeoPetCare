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
@Table(name="TipoMascota")
public class TipoMascota
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipo;
	private String nombreTipo;
	
	@OneToMany(mappedBy = "tipomascota", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Mascota> mascotas;
	
	@OneToMany(mappedBy = "tipomascota", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Vacuna> vacunas;

	public Long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	public List<Mascota> getMascotas() {
		return mascotas;
	}

	public void setMascotas(List<Mascota> mascotas) {
		this.mascotas = mascotas;
	}

	public List<Vacuna> getVacunas() {
		return vacunas;
	}

	public void setVacunas(List<Vacuna> vacunas) {
		this.vacunas = vacunas;
	}
	
	
}
