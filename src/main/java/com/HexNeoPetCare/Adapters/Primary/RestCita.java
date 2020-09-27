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

import com.HexNeoPetCare.Domain.Cita;
import com.HexNeoPetCare.Ports.Primary.CitaServicio;


@RestController
@RequestMapping ("/api")
public class RestCita {

	@Autowired(required= true)
	private CitaServicio citaServicio;
	
	//1) Registrar Cuidado
	@PostMapping("/cita/registrar/{idMascota}/{idVeterinario}")
	public void registrarCita(@RequestBody Cita cita, @PathVariable(value="idMascota") Long idMascota,@PathVariable(value="idVeterinario") Long idVeterinario)  {
		 try		 {
			 citaServicio.registrarCita(cita, idMascota, idVeterinario);
			 }
		 
	        catch (Exception e)		 {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo registrar la cita.");
	        }
	}
	
	//2) Mostrar Cita
	
	@GetMapping("/cita/{idCita}")
	public Cita mostrarCita(@PathVariable(value = "idCita") Long idCita) throws Exception {
		return citaServicio.obtenerCita(idCita);
	}
	
	//3) Actualizar Cita
	
    @PutMapping("/cita/actualizar")
    public void actualizarCita(@RequestBody Cita cita)
    {
        try        {
        	citaServicio.actualizarCita(cita);
        }
        catch (Exception e)        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo actualizar la cita.");
        }
        return;
    }
	
	//4) Eliminar Cita
	
	@DeleteMapping("/cita/eliminar/{idCita}")
    public void eliminarCita(@PathVariable(value = "idCita") Long idCita) throws Exception {

		citaServicio.eliminarCita(idCita);
	}
	
	//5) Mostrar Lista de Citas
	
	@GetMapping("/cita/mostrarLista")
	public List<Cita> mostrarCitaLista() {
        List<Cita> IsCita = null;
        try
        {
            IsCita = citaServicio.listarCitas();
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudieron listar las citas.");
        }
        return IsCita;
	}
	
	//6) Mostrar Lista de Citas según Mascota
	
	@GetMapping("/cita/mostrarListaporMascota/{idMascota}")
	public List<Cita> mostrarCitaListaMascota(@PathVariable(value = "idMascota") Long idMascota) {
        List<Cita> IsCita = null;
        try
        {
            IsCita = citaServicio.listarCitasporMascota(idMascota);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudieron listar las citas.");
        }
        return IsCita;
	}
	
	//7) Mostrar Lista de Citas según Veterinario
	
	@GetMapping("/cita/mostrarListaporVeterinario/{idVeterinario}")
	public List<Cita> mostrarCitaListaVeterinario(@PathVariable(value = "idVeterinario") Long idVeterinario) {
        List<Cita> IsCita = null;
        try
        {
            IsCita = citaServicio.listarCitasporVeterinario(idVeterinario);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudieron listar las citas.");
        }
        return IsCita;
	}


	
}