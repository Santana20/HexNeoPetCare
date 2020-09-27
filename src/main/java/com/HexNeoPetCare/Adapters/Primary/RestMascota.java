package com.HexNeoPetCare.Adapters.Primary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.HexNeoPetCare.Converters.MascotaFromToMascotaDTOConverter;
import com.HexNeoPetCare.DTOClass.MascotaDTO;
import com.HexNeoPetCare.Domain.Mascota;
import com.HexNeoPetCare.Ports.Primary.MascotaServicio;


@RestController
@RequestMapping("/api")
public class RestMascota
{
	@Autowired
	private MascotaServicio servicioMascota;
	
	private MascotaFromToMascotaDTOConverter convertermascota = new MascotaFromToMascotaDTOConverter();
	
	//REGISTRAR MASCOTA
	@PostMapping("/mascota/registrarMascota/{codUsuario}")
	public void registrarMascota(@PathVariable(value = "codUsuario") Long codUsuario, @RequestBody MascotaDTO mascota)
	{
		Mascota m = null;
		Long idtipoMascota = null;
		try
		{	
			m = convertermascota.convertToMascota(mascota);
			idtipoMascota = mascota.getIdtipomascota();
			
			servicioMascota.registrarMascota(codUsuario, idtipoMascota, m);
		}
		catch (Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo registrar a la mascota."+e.getMessage());
		}
		return;
	}
	
	//ACTUALIZAR MASCOTA
	@PutMapping("/mascota/actualizarMascota/{idMascota}")
	public void actualizarMascota(@PathVariable(value = "idMascota") Long idMascota, @RequestBody MascotaDTO mascota)
	{
		try
		{
			
			Mascota m = convertermascota.convertToMascota(mascota);
			Long idtipoMascota = mascota.getIdtipomascota();
			servicioMascota.actualizarMascota(idMascota, idtipoMascota, m);
		}
		catch (Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
		return;
	}
	
	//ELIMINAR MASCOTA
	@DeleteMapping("/mascota/eliminarMascota/{codigo}")
	public void eliminarMascota(@PathVariable(value = "codigo") Long codigo)
	{
		try
		{
			servicioMascota.eliminarMascota(codigo);
		}
		catch (Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo eliminar a la mascota.");
		}
		return;
	}
	
	//LISTAR TODAS LAS MASCOTAS
	@GetMapping("/mascota/listarMascotas")
	public List<Mascota> listarMascotas()
	{
		List<Mascota> lsMascota = null;
		try
		{
			lsMascota = servicioMascota.listarMascotas();
		}
		catch (Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo listar a las mascotas.");
		}
		
		return lsMascota;
	}
	
	//LISTAR MASCOTAS POR USUARIO
	@GetMapping("/mascota/listarMascotasporUsuario/{idUsuario}")
	public List<Mascota> listarMascotasporUsuario(@PathVariable(value = "idUsuario") Long idUsuario)
	{
		List<Mascota> lsMascota = null;
		try
		{
			lsMascota = servicioMascota.listarMascotasporUsuario(idUsuario);
		}
		catch (Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo listar a las mascotas del usuario.");
		}
		
		return lsMascota;
	}
}
