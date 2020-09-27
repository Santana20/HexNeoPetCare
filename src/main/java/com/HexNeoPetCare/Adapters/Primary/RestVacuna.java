package com.HexNeoPetCare.Adapters.Primary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.HexNeoPetCare.Domain.Vacuna;
import com.HexNeoPetCare.Ports.Primary.VacunaServicio;


@RestController
@RequestMapping("/api")
public class RestVacuna
{
	@Autowired
	private VacunaServicio servicioVacuna;
	
	//REGISTRO DE VACUNA
	@PostMapping("/vacuna/registrarVacuna/{idTipoMascota}")
	public void registrarVacuna(@PathVariable(value = "idTipoMascota") Long idTipoMascota, @RequestBody Vacuna vacuna)
	{
		try
		{
			servicioVacuna.registrarVacuna(idTipoMascota, vacuna);
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		return;
	}
	
	//ELIMINAR VACUNA
	@DeleteMapping("/vacuna/eliminarVacuna/{idVacuna}")
	public void eliminarVacuna(@PathVariable(value = "idVacuna") Long idVacuna)
	{
		try
		{
			servicioVacuna.eliminarVacuna(idVacuna);
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo eliminar la vacuna.");
		}
		return;
	}
	
	//LISTAR TODAS LOS VACUNA
	@GetMapping("/vacuna/listarVacunas")
	public List<Vacuna> listarVacunas()
	{
		List<Vacuna> ls = null;
		try
		{
			ls = servicioVacuna.listarVacunas();
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo listar las vacunas.");
		}
		return ls;
	}
	
	//LISTAR VACUNAS POR TIPOMASCOTA
	@GetMapping("/vacuna/listarVacunaporTipo/{idTipoMascota}")
	public List<Vacuna> listarVacunaporTipo(@PathVariable(value = "idTipoMascota") Long idTipoMascota)
	{
		List<Vacuna> ls = null;
		try
		{
			ls = servicioVacuna.listarVacunaporTipo(idTipoMascota);
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo listar las vacunas por tipo.");
		}
		return ls;
	}
}
