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

import com.HexNeoPetCare.Domain.RegistroCuidado;
import com.HexNeoPetCare.Ports.Primary.RegistroCuidadoServicio;


@RestController
@RequestMapping ("/api")
public class RestRegistroCuidado {

	@Autowired(required= true)
	private RegistroCuidadoServicio registroCuidadoServicio;
	
	//1) Registrar Registro Cuidado
	@PostMapping("/registroCuidado/registrar/{idMascota}/{idCuidado}")
	public void registrarCuidado(@RequestBody RegistroCuidado regCuidado,@PathVariable(value="idMascota") Long idMascota, @PathVariable(value="idCuidado") Long idCuidado) {
		 try		 {
			 registroCuidadoServicio.registrarRegistroCuidado(idMascota, idCuidado, regCuidado);       
			 }
		 
	        catch (Exception e)		 {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo registrar.");
	        }
	}
	
	//2) Mostrar Registro de Cuidado
	
	@GetMapping("/registroCuidado/{idRegistroCuidado}")
	public RegistroCuidado mostrarRegistroCuidado(@PathVariable(value = "idRegistroCuidado") Long idRegistroCuidado) throws Exception {
		return registroCuidadoServicio.obtenerRegistroCuidado(idRegistroCuidado);
	}
	
	//3) Actualizar Cuidado
	
    @PutMapping("/registroCuidado/actualizar")
    public void actualizarRegistroCuidado(@RequestBody RegistroCuidado regCuidado)
    {
        try        {
        	registroCuidadoServicio.actualizarRegistroCuidado(regCuidado);
        }
        catch (Exception e)        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo actualizar el cuidado.");
        }
        return;
        
    }
	
	//4) Eliminar Cuidado
	
	@DeleteMapping("/registroCuidado/eliminar/{idRegistroCuidado}")
    public void eliminarRegistroCuidado(@PathVariable(value = "idRegistroCuidado") Long idRegistroCuidado) throws Exception {

		registroCuidadoServicio.eliminarRegistroCuidado(idRegistroCuidado);
	}
	
	//5) Mostrar Lista de Cuidados por Mascota
	
	@GetMapping("/registroCuidado/mostrarListaMascota/{idMascota}")
	public List<RegistroCuidado> mostrarRegistroCuidadoListaMascota(@PathVariable(value = "idMascota") Long idMascota) {
        List<RegistroCuidado> IsRegCuidado = null;
        try
        {
        	IsRegCuidado = registroCuidadoServicio.listarRegistroCuidadoMascota(idMascota);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudieron listar.");
        }
        return IsRegCuidado;
	}
	

}
