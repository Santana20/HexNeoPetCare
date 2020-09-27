package com.HexNeoPetCare.Adapters.Primary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.HexNeoPetCare.Domain.VacunaMascota;
import com.HexNeoPetCare.Ports.Primary.VacunaMascotaServicio;


@RestController
@RequestMapping("/api")
public class RestVacunaMascota
{
	@Autowired
	private VacunaMascotaServicio servicioVacunaMascota;
	
	//REGISTRAR VACUNA DE UNA MASCOTA
	@PostMapping("/vacunamascota/registrarVacunaMascota/{idMascota}/{idVacuna}")	
	public void registrarVacunaMascota(@PathVariable(value = "idMascota") Long idMascota, 
			@PathVariable(value = "idVacuna") Long idVacuna, @RequestBody VacunaMascota vm)
	{
		try
        {
			servicioVacunaMascota.registrarVacunaMascota(idMascota, idVacuna, vm);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo registrar la vacuna de la mascota.\n"+e.getMessage());
        }
		
		return;
	}
	
	//ACTUALIZAR ESTADO DE LA VACUNA DE LA MASCOTA
	@PutMapping("/vacunamascota/actualizarEstadoVacunaMascota/{idVacunaMascota}")
	public void actualizarEstadoVacunaMascota(@PathVariable(value = "idVacunaMascota") Long idVacunaMascota)
	{
		try
        {
			servicioVacunaMascota.actualizarEstadoVacunaMascota(idVacunaMascota);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo actualizar el estado la vacuna de la mascota.");
        }
		
		return;
	}
	
	//LISTAR VACUNAS DE UNA MASCOTA
	@GetMapping("/vacunamascota/listarVacunasdeMascota/{idMascota}")
	public List<VacunaMascota> listarVacunasdeMascota(@PathVariable(value = "idMascota") Long idMascota)
	{
		List<VacunaMascota> lsVM = null;
		try
        {
			lsVM = servicioVacunaMascota.listarVacunasdeMascota(idMascota);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
		
		return lsVM;
	}
}

