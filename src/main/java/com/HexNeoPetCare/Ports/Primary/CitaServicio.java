package com.HexNeoPetCare.Ports.Primary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HexNeoPetCare.Domain.Cita;
import com.HexNeoPetCare.Domain.Mascota;
import com.HexNeoPetCare.Domain.Veterinario;
import com.HexNeoPetCare.Ports.Secondary.CitaRepositorio;
import com.HexNeoPetCare.Ports.Secondary.MascotaRepositorio;
import com.HexNeoPetCare.Ports.Secondary.VeterinarioRepositorio;

@Service
public class CitaServicio
{
	@Autowired
	private CitaRepositorio RepositorioCita;
	@Autowired
	private VeterinarioRepositorio RepositorioVeterinario;
	@Autowired
	private MascotaRepositorio RepositorioMascota;
	
	//REGISTRAR CITA
	public void registrarCita(Cita cita, Long idMascota, Long idVeterinario) throws Exception
	{
		Veterinario v = RepositorioVeterinario.encontrarVeterinarioporId(idVeterinario);
		if ( v == null ) throw new Exception("Veterinario no encontrado.");
		cita.setVeterinario(v);
		
		Mascota m = RepositorioMascota.encontrarMascotaporId(idMascota);
		if(m == null) throw new Exception("Mascota no encontrada");
		cita.setMascota(m);
		
		RepositorioCita.save(cita);
	}
	
	//OBTENER CITA
	public Cita obtenerCita(Long cod) throws Exception
	{
		Cita c = RepositorioCita.encontrarCitaporId(cod);
		if ( c == null ) throw new Exception( "Cita no encontrada." );
		return c;
	}
	
	//ACTUALIZAR CITA
	public void actualizarCita(Cita cita) throws Exception
	{
		Cita c = obtenerCita(cita.getIdCita());
		
		if ( cita.getFecha() != null ) c.setFecha(cita.getFecha());
		if ( cita.getHora() != null ) c.setHora(cita.getHora());
		if ( cita.getEstado() != null ) c.setEstado(cita.getEstado());

		if ( cita.getVeterinario() != null )
		{
			c.setVeterinario(cita.getVeterinario());
		}
		
		if ( cita.getMascota() != null )
		{
			c.setMascota(cita.getMascota());
		}
		
		RepositorioCita.save(c);
		return;
	}
	
	//ELIMINAR CITA
	public void eliminarCita(Long codigo) throws Exception
	{
		Cita c = obtenerCita(codigo);
		
		RepositorioCita.delete(c);
	}
	
	//LISTAR TODAS LAS CITAS
	public List<Cita> listarCitas()
	{
		return RepositorioCita.findAll();
	}
	
	//LISTAR CITAS POR MASCOTA
	public List<Cita> listarCitasporMascota(Long idMascota)
	{
		return RepositorioCita.obtenerCitaporidMascota(idMascota);
	}

	//LISTAR CITAS POR VETERINARIO
	public List<Cita> listarCitasporVeterinario(Long idMascota)
	{
		return RepositorioCita.obtenerCitaporidMascota(idMascota);
	}

}