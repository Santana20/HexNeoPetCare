package com.HexNeoPetCare.Domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Vacuna")
public class Vacuna 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVacuna;
	
	private String nombrevacuna;
	
	@OneToMany(mappedBy = "vacuna", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<VacunaMascota> registro_vacunas;
	
	@ManyToOne
	@JoinColumn(name = "TipoMascota")
	@JsonIgnore
	private TipoMascota tipomascota;

	public Vacuna() {
	}

	public Vacuna(String nombrevacuna, TipoMascota tipomascota) {
		this.nombrevacuna = nombrevacuna;
		this.tipomascota = tipomascota;
	}

	public Long getIdVacuna() {
		return idVacuna;
	}

	public void setIdVacuna(Long idVacuna) {
		this.idVacuna = idVacuna;
	}


	public String getNombrevacuna() {
		return nombrevacuna;
	}

	public void setNombrevacuna(String nombrevacuna) {
		this.nombrevacuna = nombrevacuna;
	}

	public List<VacunaMascota> getRegistro_vacunas() {
		return registro_vacunas;
	}

	public void setRegistro_vacunas(List<VacunaMascota> registro_vacunas) {
		this.registro_vacunas = registro_vacunas;
	}

	public TipoMascota getTipomascota() {
		return tipomascota;
	}

	public void setTipomascota(TipoMascota tipomascota) {
		this.tipomascota = tipomascota;
	}
	
	
}
