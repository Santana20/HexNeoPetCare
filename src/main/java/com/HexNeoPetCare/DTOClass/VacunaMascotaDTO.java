package com.HexNeoPetCare.DTOClass;

import java.util.Date;

public class VacunaMascotaDTO
{
	private Long idVacunaMascota;
	
	private Date fechaRegistro;
	private Date fechaVacunaRealizada;
	private boolean status;
	private Long idVacuna;
	private Long idMascota;
	
	public Long getIdVacunaMascota() {
		return idVacunaMascota;
	}
	public void setIdVacunaMascota(Long idVacunaMascota) {
		this.idVacunaMascota = idVacunaMascota;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Date getFechaVacunaRealizada() {
		return fechaVacunaRealizada;
	}
	public void setFechaVacunaRealizada(Date fechaVacunaRealizada) {
		this.fechaVacunaRealizada = fechaVacunaRealizada;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Long getIdVacuna() {
		return idVacuna;
	}
	public void setIdVacuna(Long idVacuna) {
		this.idVacuna = idVacuna;
	}
	public Long getIdMascota() {
		return idMascota;
	}
	public void setIdMascota(Long idMascota) {
		this.idMascota = idMascota;
	}
	
}
