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
@Table(name="Mascota")
public class Mascota 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMascota;
	
	private String Nombre;
	private int edad;
	private double peso;
	
	@OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<RegistroCuidado> registro_cuidados;
	
	@OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Cita> citas;
	
	@OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<VacunaMascota> registro_vacunas;
	
	@ManyToOne
	@JoinColumn(name = "Usuario")
	@JsonIgnore
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "TipoMascota")
	@JsonIgnore
	private TipoMascota tipomascota;

	public Mascota() {
	}

	public Mascota(String nombre, int edad, double peso, Usuario usuario, TipoMascota tipomascota) {
		Nombre = nombre;
		this.edad = edad;
		this.peso = peso;
		this.usuario = usuario;
		this.tipomascota = tipomascota;
	}

	public Long getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(Long idMascota) {
		this.idMascota = idMascota;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public List<RegistroCuidado> getRegistro_cuidados() {
		return registro_cuidados;
	}

	public void setRegistro_cuidados(List<RegistroCuidado> registro_cuidados) {
		this.registro_cuidados = registro_cuidados;
	}

	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public List<VacunaMascota> getRegistro_vacunas() {
		return registro_vacunas;
	}

	public void setRegistro_vacunas(List<VacunaMascota> registro_vacunas) {
		this.registro_vacunas = registro_vacunas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoMascota getTipomascota() {
		return tipomascota;
	}

	public void setTipomascota(TipoMascota tipomascota) {
		this.tipomascota = tipomascota;
	}

	@Override
	public String toString() {
		return "Mascota{" +
				"idMascota=" + idMascota +
				", Nombre='" + Nombre + '\'' +
				", edad=" + edad +
				", peso=" + peso +
				'}';
	}
}
