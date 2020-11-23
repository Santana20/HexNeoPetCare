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
@Table(name ="RegistroCuidado")
public class RegistroCuidado 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRegistroCuidado;
	
	@ManyToOne
	@JoinColumn(name = "Mascota")
	@JsonIgnore
	private Mascota mascota;

	@ManyToOne
	@JoinColumn(name = "Cuidado")
	@JsonIgnore
	
	private Cuidado cuidado;
	
	private Date fechaRegistro;
	private Date fechaRealizado;
	private boolean status; 
	
	public RegistroCuidado() {
	
	}
	
	public RegistroCuidado(Date fechaRegistro, Date fechaRealizado, boolean status, Mascota mascota, Cuidado cuidado) {
		this.fechaRegistro = fechaRegistro;
		this.fechaRealizado = fechaRealizado;
		this.status = status;
		this.mascota = mascota;
		this.cuidado = cuidado;
	}


	public Mascota getMascota() {
		return mascota;
	}
	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}
	public Cuidado getCuidado() {
		return cuidado;
	}
	public void setCuidado(Cuidado cuidado) {
		this.cuidado = cuidado;
	}
	
	public Long getIdRegistroCuidado() {
		return idRegistroCuidado;
	}
	public void setIdRegistroCuidado(Long idRegistroCuidado) {
		this.idRegistroCuidado = idRegistroCuidado;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Date getFechaRealizado() {
		return fechaRealizado;
	}
	public void setFechaRealizado(Date fechaRealizado) {
		this.fechaRealizado = fechaRealizado;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}