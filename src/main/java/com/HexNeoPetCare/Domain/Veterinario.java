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
@Table(name ="Veterinario")
public class Veterinario 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVeterinario;
	
	private String nombre;
	private String apellido;
	private String direccion_consultorio;
	private String correo;
	private String celular;
	private String username;
	private String password;
	
	@OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Horario> horarios;
	
	@OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Cita> citas;


	public Veterinario() {
	}

	public Veterinario(String nombre, String apellido, String direccion_consultorio, String correo, String celular, String username,
					   String password) {
		this.nombre = nombre;
		this.apellido= apellido;
		this.direccion_consultorio = direccion_consultorio;
		this.correo = correo;
		this.celular = celular;
		this.username = username;
		this.password = password;
	}
	
	public Long getIdVeterinario() {
		return idVeterinario;
	}
	public void setIdVeterinario(Long idVeterinario) {
		this.idVeterinario = idVeterinario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDireccion_consultorio() {
		return direccion_consultorio;
	}
	public void setDireccion_consultorio(String direccion_consultorio) {
		this.direccion_consultorio = direccion_consultorio;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Horario> getHorarios() {
		return horarios;
	}
	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}
	public List<Cita> getCitas() {
		return citas;
	}
	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}
	

}