package com.HexNeoPetCare.Adapters.Primary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.HexNeoPetCare.Converters.VacunaMascotaFromToVacunaMascotaDTOConverter;
import com.HexNeoPetCare.DTOClass.VacunaMascotaDTO;
import com.HexNeoPetCare.Domain.VacunaMascota;
import com.HexNeoPetCare.Ports.Primary.VacunaMascotaServicio;


@RestController
@RequestMapping("/api")
public class RestVacunaMascota
{
	@Autowired
	private VacunaMascotaServicio servicioVacunaMascota;
	
	private VacunaMascotaFromToVacunaMascotaDTOConverter converterVacunaMascota = new VacunaMascotaFromToVacunaMascotaDTOConverter();
	
	//REGISTRAR VACUNA DE UNA MASCOTA
	@PostMapping("/vacunamascota/registrarVacunaMascota/{idMascota}")	
	public void registrarVacunaMascota(@PathVariable(value = "idMascota") Long idMascota, @RequestBody VacunaMascotaDTO requestvm)
	{
		VacunaMascota vm = null;
		Long idVacuna = null;
		try
        {
			vm = converterVacunaMascota.convertToVacunaMascota(requestvm);
			idVacuna = requestvm.getIdVacuna();
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
	}

	//ELIMINAR VACUNA DE UNA MASCOTA
	@DeleteMapping("/vacunamascota/eliminarVacunadeMascota/{idVacunaMascota}")
	public void eliminarVacunadeMascota(@PathVariable("idVacunaMascota") Long idVacunaMascota) {
		try
		{
			servicioVacunaMascota.eliminarVacunadeMascota(idVacunaMascota);
		}
		catch (Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo eliminar la vacuna de la mascota.");
		}
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

