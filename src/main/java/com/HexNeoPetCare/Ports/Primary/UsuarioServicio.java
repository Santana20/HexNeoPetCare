package com.HexNeoPetCare.Ports.Primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HexNeoPetCare.Domain.Usuario;
import com.HexNeoPetCare.Ports.Secondary.UsuarioRepositorio;


@Service
public class UsuarioServicio 
{
	@Autowired
	private UsuarioRepositorio RepositorioUsuario;
	
	//OBTENER USUARIO
	public Usuario obtenerUsuario(Long cod) throws Exception
	{
		Usuario u = RepositorioUsuario.encontrarUsuarioporId(cod);
		if ( u == null ) throw new Exception( "Usuario no encontrado." );
		return u;
	}
}
