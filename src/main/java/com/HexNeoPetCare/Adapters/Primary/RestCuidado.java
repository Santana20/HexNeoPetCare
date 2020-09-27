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

import com.HexNeoPetCare.Domain.Cuidado;
import com.HexNeoPetCare.Ports.Primary.CuidadoServicio;


@RestController
@RequestMapping ("/api")
public class RestCuidado {

	@Autowired(required= true)
	private CuidadoServicio cuidadoServicio;
	
	//1) Registrar Cuidado
	@PostMapping("/cuidado/registrar")
	public void registrarCuidado(@RequestBody Cuidado cuidado) {
		 try		 {
			 cuidadoServicio.registrarCuidado(cuidado);        
			 }
	
	        catch (Exception e)		 {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo registrar el cuidado.");
	        }
	}
	
	//2) Mostrar Cuidado
	
	@GetMapping("/cuidado/{idCuidado}")
	public Cuidado mostrarCuidado(@PathVariable(value = "idCuidado") Long idCuidado) throws Exception {
		return cuidadoServicio.obtenerCuidado(idCuidado);
	}
	
	//3) Actualizar Cuidado
	
    @PutMapping("/cuidado/actualizar")
    public void actualizarCuidado(@RequestBody Cuidado cuidado)
    {
        try        {
            cuidadoServicio.actualizarCuidado(cuidado);
        }
        catch (Exception e)        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo actualizar el cuidado.");
        }
        return;
    }
	
	//4) Eliminar Cuidado
	
	@DeleteMapping("/cuidado/eliminar/{idCuidado}")
    public void eliminarCuidado(@PathVariable(value = "idCuidado") Long idCuidado) throws Exception {

		cuidadoServicio.eliminarCuidado(idCuidado);
	}
	
	//5) Mostrar Lista de Cuidados
	
	@GetMapping("/cuidado/mostrarLista")
	public List<Cuidado> mostrarCuidadoLista() {
        List<Cuidado> IsCuidado = null;
        try
        {
        	IsCuidado = cuidadoServicio.listarCuidados();
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudieron listar los cuidados..");
        }
        return IsCuidado;
	}
	

}
