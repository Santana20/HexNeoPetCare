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

import com.HexNeoPetCare.Domain.Horario;
import com.HexNeoPetCare.Ports.Primary.HorarioServicio;


@RestController
@RequestMapping ("/api")
public class RestHorario {

	@Autowired(required= true)
	private HorarioServicio horarioServicio;
	
	//1) Registrar Veterinario
	@PostMapping("/horario/registrar/{idVeterinario}")
	public void registrarHorario(@RequestBody Horario horario, @PathVariable (value="idVeterinario") Long idVeterinario) {
		 try		 {
			 horarioServicio.registrarHorario(horario, idVeterinario); 
		 			}
		 
	        catch (Exception e)		 {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo registrar el horario.");
	        }
	}
	
	//2) Mostrar Horario
	
	@GetMapping("/horario/{idHorario}")
	public Horario mostrarHorario(@PathVariable(value = "idHorario") Long idHorario) throws Exception {
		return horarioServicio.obtenerHorario(idHorario);
	}
	
	//3) Actualizar Horario
	
    @PutMapping("/horario/actualizar")
    public void actualizarHorario(@RequestBody Horario horario)
    {
        try        {
            horarioServicio.actualizarHorario(horario);
        }
        catch (Exception e)        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo actualizar el horario.");
        }

        return;
    }
	
	//4) Eliminar Veterinario
	
	@DeleteMapping("/horario/eliminar/{idHorario}")
    public void eliminarHorario(@PathVariable(value = "idHorario") Long idHorario) throws Exception {

         horarioServicio.eliminarHorario(idHorario);
    }
	
	//5) Mostrar Lista de Horarios
	
	@GetMapping("/horario/mostrarLista")
	public List<Horario> mostrarHorarioLista() {
        List<Horario> IsHorario = null;
        try
        {
            IsHorario = horarioServicio.listarHorarios();
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudieron listar los veterinarios..");
        }
        return IsHorario;
	}
	

}