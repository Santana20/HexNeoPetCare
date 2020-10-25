package com.HexNeoPetCare.Domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="VacunaMascota")
public class VacunaMascota 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVacunaMascota;
	
	private Date FechaRegistro;
	private Date FechaVacunaRealizada;
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name = "Mascota")
	@JsonIgnore
	private Mascota mascota;
	
	@ManyToOne
	@JoinColumn(name = "Vacuna")
	@JsonIgnore
	private Vacuna vacuna;

	public VacunaMascota(){
	}

	public VacunaMascota(Date fechaRegistro, Date fechaVacunaRealizada, boolean status, Mascota mascota, Vacuna vacuna) {
		FechaRegistro = fechaRegistro;
		FechaVacunaRealizada = fechaVacunaRealizada;
		this.status = status;
		this.mascota = mascota;
		this.vacuna = vacuna;
	}

	public Date getFechaRegistro() {
		return FechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		FechaRegistro = fechaRegistro;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public Vacuna getVacuna() {
		return vacuna;
	}

	public void setVacuna(Vacuna vacuna) {
		this.vacuna = vacuna;
	}

	public Long getIdVacunaMascota() {
		return idVacunaMascota;
	}

	public void setIdVacunaMascota(Long idVacunaMascota) {
		this.idVacunaMascota = idVacunaMascota;
	}

	public Date getFechaVacunaRealizada() {
		return FechaVacunaRealizada;
	}

	public void setFechaVacunaRealizada(Date fechaVacunaRealizada) {
		FechaVacunaRealizada = fechaVacunaRealizada;
	}
	
	
}
