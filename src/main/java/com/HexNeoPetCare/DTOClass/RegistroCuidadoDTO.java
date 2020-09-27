package com.HexNeoPetCare.DTOClass;

import java.util.Date;

public class RegistroCuidadoDTO
{
	private Long idRegistroCuidado;
	private Long idMascota;
	private Long idCuidado;
	private Date fechaRegistro;
	private Date fechaRealizado;
	private boolean status;
	
	public Long getIdRegistroCuidado() {
		return idRegistroCuidado;
	}
	public void setIdRegistroCuidado(Long idRegistroCuidado) {
		this.idRegistroCuidado = idRegistroCuidado;
	}
	public Long getIdMascota() {
		return idMascota;
	}
	public void setIdMascota(Long idMascota) {
		this.idMascota = idMascota;
	}
	public Long getIdCuidado() {
		return idCuidado;
	}
	public void setIdCuidado(Long idCuidado) {
		this.idCuidado = idCuidado;
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
