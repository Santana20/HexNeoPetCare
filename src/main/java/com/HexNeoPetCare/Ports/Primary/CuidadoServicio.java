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
	public Cuidado registrarCuidado(Cuidado cuidado)
	{
		return RepositorioCuidado.save(cuidado);
	}
	
	//OBTENER CUIDADO
	public Cuidado obtenerCuidado(Long cod) throws Exception
	{
		Cuidado c = RepositorioCuidado.encontrarCuidadoporId(cod);
		if ( c == null ) throw new Exception( "Cuidado no encontrada." );
		return c;
	}
	
	//ELIMINAR CUIDADO
	public Long eliminarCuidado(Long codigo) throws Exception
	{
		Cuidado c = obtenerCuidado(codigo);
		
		RepositorioCuidado.delete(c);
		return codigo;
	}
	
	//LISTAR TODOS LOS CUIDADOS
	public List<Cuidado> listarCuidados()
	{
		return RepositorioCuidado.findAll();
	}
	

}