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

import com.HexNeoPetCare.Converters.RegistroCuidadoFromToRegistroCuidadoDTOConverter;
import com.HexNeoPetCare.DTOClass.RegistroCuidadoDTO;
import com.HexNeoPetCare.Domain.RegistroCuidado;
import com.HexNeoPetCare.Ports.Primary.RegistroCuidadoServicio;


@RestController
@RequestMapping ("/api")
public class RestRegistroCuidado {

	@Autowired(required= true)
	private RegistroCuidadoServicio registroCuidadoServicio;
	
	private RegistroCuidadoFromToRegistroCuidadoDTOConverter converterRegistroCuidado = new RegistroCuidadoFromToRegistroCuidadoDTOConverter();
	
	//1) Registrar Registro Cuidado
	@PostMapping("/registroCuidado/registrarCuidado/{idMascota}")
	public void registrarCuidado(@PathVariable(value="idMascota") Long idMascota, @RequestBody RegistroCuidadoDTO request) 
	{
		RegistroCuidado regCuidado = null;
		Long idCuidado = null;
		try
		{
			regCuidado = converterRegistroCuidado.converterToRegistroCuidado(request);
			idCuidado = request.getIdCuidado();
			registroCuidadoServicio.registrarRegistroCuidado(idMascota, idCuidado, regCuidado);
		}
		catch (Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo registrar."+e.getMessage());
        }
	}
	
	//2) Mostrar Registro de Cuidado
	
	@GetMapping("/registroCuidado/{idRegistroCuidado}")
	public RegistroCuidado mostrarRegistroCuidado(@PathVariable(value = "idRegistroCuidado") Long idRegistroCuidado) throws Exception {
		return registroCuidadoServicio.obtenerRegistroCuidado(idRegistroCuidado);
	}
	
	//3) Actualizar estado del Cuidado de la mascota.
	
    @PutMapping("/registroCuidado/actualizar/{idRegistroCuidado}")
    public void actualizarRegistroCuidado(@PathVariable(value = "idRegistroCuidado") Long idRegistroCuidado)
    {
    	
        try
        {
        	registroCuidadoServicio.actualizarEstadoRegistroCuidado(idRegistroCuidado);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo actualizar el cuidado."+e.getMessage());
        }
        return;
        
    }
	
	//4) Eliminar Cuidado
	
	@DeleteMapping("/registroCuidado/eliminar/{idRegistroCuidado}")
    public void eliminarRegistroCuidado(@PathVariable(value = "idRegistroCuidado") Long idRegistroCuidado)
	{
		try
		{
			registroCuidadoServicio.eliminarRegistroCuidado(idRegistroCuidado);
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo actualizar el cuidado."+e.getMessage());
		}
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudieron listar."+e.getMessage());
        }
        return IsRegCuidado;
	}
	

}
