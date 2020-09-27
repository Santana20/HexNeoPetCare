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

import com.HexNeoPetCare.Domain.Veterinario;
import com.HexNeoPetCare.Ports.Primary.VeterinarioServicio;


@RestController
@RequestMapping ("/api")
public class RestVeterinario {

	@Autowired(required= true)
	private VeterinarioServicio veterinarioServicio;
	
	//1) Registrar Veterinario
	@PostMapping("/veterinario/registrar")
	public void registrarVeterinario(@RequestBody Veterinario veterinario) {
		 try		 {
			 veterinarioServicio.registrarVeterinario(veterinario);        
			 }
		 
	        catch (Exception e)		 {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo registrar al veterinario.");
	        }
	}
	
	//2) Mostrar Veterinario
	
	@GetMapping("/veterinario/{idVeterinario}")
	public Veterinario mostrarVeterinario(@PathVariable(value = "idVeterinario") Long idVeterinario) throws Exception {
		return veterinarioServicio.obtenerVeterinario(idVeterinario);
	}
	
	//3) Actualizar Veterinario
	
    @PutMapping("/veterinario/actualizar")
    public void actualizarVeterinario(@RequestBody Veterinario veterinario)
    {
        try        {
            veterinarioServicio.actualizarVeterinario(veterinario);
        }
        catch (Exception e)        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo actualizar el veterinario.");
        }

        return;
    }
	
	//4) Eliminar Veterinario
	
	@DeleteMapping("/veterinario/eliminar/{idVeterinario}")
    public void eliminarVeterinario(@PathVariable(value = "idVeterinario") Long idVeterinario) throws Exception {

         veterinarioServicio.eliminarVeterinario(idVeterinario);
    }
	
	//5) Mostrar Lista de Veterinarios
	
	@GetMapping("/veterinario/mostrarLista")
	public List<Veterinario> mostrarVeterinarioLista() {
        List<Veterinario> IsVeterinario = null;
        try
        {
            IsVeterinario = veterinarioServicio.listarVeterinarios();
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudieron listar los veterinarios..");
        }
        return IsVeterinario;
	}
	

}