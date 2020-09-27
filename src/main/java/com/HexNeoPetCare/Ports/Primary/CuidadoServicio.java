package com.HexNeoPetCare.Ports.Primary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HexNeoPetCare.Domain.Cuidado;
import com.HexNeoPetCare.Ports.Secondary.CuidadoRepositorio;

@Service
public class CuidadoServicio
{
	@Autowired
	private CuidadoRepositorio RepositorioCuidado;
	
	//REGISTRAR CUIDADO
	public void registrarCuidado(Cuidado cita)
	{
		RepositorioCuidado.save(cita);
	}
	
	//OBTENER CUIDADO
	public Cuidado obtenerCuidado(Long cod) throws Exception
	{
		Cuidado c = RepositorioCuidado.encontrarCuidadoporId(cod);
		if ( c == null ) throw new Exception( "Cuidado no encontrada." );
		return c;
	}
	
	//ACTUALIZAR CUIDADO
	public void actualizarCuidado(Cuidado cuidado) throws Exception
	{
		Cuidado c = obtenerCuidado(cuidado.getIdCuidado());
		
		if ( cuidado.getNombre() != null ) c.setNombre(cuidado.getNombre());

		RepositorioCuidado.save(c);
		return;
	}
	
	//ELIMINAR CUIDADO
	public void eliminarCuidado(Long codigo) throws Exception
	{
		Cuidado c = obtenerCuidado(codigo);
		
		RepositorioCuidado.delete(c);
	}
	
	//LISTAR TODOS LOS CUIDADOS
	public List<Cuidado> listarCuidados()
	{
		return RepositorioCuidado.findAll();
	}
	

}