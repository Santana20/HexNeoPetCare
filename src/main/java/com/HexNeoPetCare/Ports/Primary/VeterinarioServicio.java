package com.HexNeoPetCare.Ports.Primary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HexNeoPetCare.Domain.Veterinario;
import com.HexNeoPetCare.Ports.Secondary.VeterinarioRepositorio;


@Service
public class VeterinarioServicio
{
	@Autowired
	private VeterinarioRepositorio RepositorioVeterinario;
	
	//REGISTRAR VETERINARIO
	public void registrarVeterinario(Veterinario veterinario){
		RepositorioVeterinario.save(veterinario);
	}
	
	//OBTENER VETERINARIO
	public Veterinario obtenerVeterinario(Long cod) throws Exception
	{
		Veterinario v = RepositorioVeterinario.encontrarVeterinarioporId(cod);
		if ( v == null ) throw new Exception( "Veterinario no encontrado." );
		return v;
	}
	
	//ACTUALIZAR VETERINARIO
	public void actualizarVeterinario(Veterinario veterinario) throws Exception
	{
		Veterinario v = obtenerVeterinario(veterinario.getIdVeterinario());
		
		if ( veterinario.getNombre() != null ) v.setNombre(veterinario.getNombre());
		if ( veterinario.getApellido() != null ) v.setApellido(veterinario.getApellido());
		if ( veterinario.getDireccion_consultorio() != null ) v.setDireccion_consultorio(veterinario.getDireccion_consultorio());
		if ( veterinario.getCorreo() != null ) v.setCorreo(veterinario.getCorreo());
		if ( veterinario.getCelular() != null ) v.setCelular(veterinario.getCelular());
		if ( veterinario.getUsername() != null ) v.setUsername(veterinario.getUsername());
		if ( veterinario.getPassword() != null ) v.setPassword(veterinario.getPassword());

		RepositorioVeterinario.save(v);
		return;
	}
	
	//ELIMINAR VETERINARIO
	public void eliminarVeterinario(Long codigo) throws Exception
	{
		Veterinario v = obtenerVeterinario(codigo);
		
		RepositorioVeterinario.delete(v);
	}
	
	//LISTAR TODOS LOS VETERINARIOS
	public List<Veterinario> listarVeterinarios()
	{
		return RepositorioVeterinario.findAll();
	}
	
}
