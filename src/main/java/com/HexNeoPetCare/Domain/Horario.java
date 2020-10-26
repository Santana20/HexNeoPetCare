package com.HexNeoPetCare.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="Horario")
public class Horario 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idHorario;
	
	private String dia;
	private String hora;
	private String nota;
	
	@ManyToOne
	@JoinColumn(name = "Veterinario")
	@JsonIgnore
	private Veterinario veterinario;

	public Horario() {
	}

	public Horario(String dia, String hora, String nota, Veterinario veterinario) {
		this.dia = dia;
		this.hora = hora;
		this.nota = nota;
		this.veterinario = veterinario;
	}
	
	public Long getIdHorario() {
		return idHorario;
	}
	public void setIdHorario(Long idHorario) {
		this.idHorario = idHorario;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public Veterinario getVeterinario() {
		return veterinario;
	}
	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}


}