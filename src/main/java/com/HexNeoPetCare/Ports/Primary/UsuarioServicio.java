package com.HexNeoPetCare.Ports.Primary;

import com.HexNeoPetCare.Domain.Usuario;
import com.HexNeoPetCare.Ports.Secondary.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioServicio 
{
	@Autowired
	private UsuarioRepositorio RepositorioUsuario;

	//REGISTRAR USUARIO
	public void registrarUsuario(Usuario usuario) throws Exception {
		if (usuario.getNombre() == null || usuario.getApellido() == null || usuario.getDireccion() == null || usuario.getCorreo() == null ||
			usuario.getCelular() == null || usuario.getUsername() == null || usuario.getPassword() == null) throw new Exception("No se ingresaron todos los datos.");
		RepositorioUsuario.save(usuario);
	}

	//OBTENER USUARIO
	public Usuario obtenerUsuario(Long cod) throws Exception
	{
		Usuario u = RepositorioUsuario.encontrarUsuarioporId(cod);
		if ( u == null ) throw new Exception( "Usuario no encontrado." );
		return u;
	}

	//ACTUALIZAR USUARIO
	public void actualizarUsuario(Long idUsuario, Usuario usuario) throws Exception
	{
		Usuario u = obtenerUsuario(idUsuario);

		if (usuario.getNombre() != null) u.setNombre(usuario.getNombre());
		if (usuario.getApellido() != null) u.setApellido(usuario.getApellido());
		if (usuario.getDireccion() != null) u.setDireccion(usuario.getDireccion());
		if (usuario.getCorreo() != null) u.setCorreo(usuario.getCorreo());
		if (usuario.getCelular() != null) u.setCelular(usuario.getCelular());
		if (usuario.getUsername() != null) u.setUsername(usuario.getUsername());
		if (usuario.getPassword() != null) u.setPassword(usuario.getPassword());

		RepositorioUsuario.save(u);
	}

	//ELIMINAR USUARIO
	public void eliminarVeterinario(Long codUsuario) throws Exception
	{
		Usuario u = obtenerUsuario(codUsuario);

		RepositorioUsuario.delete(u);
	}

	//LISTAR TODOS LOS VETERINARIOS
	public List<Usuario> listarUsuarios()
	{
		return RepositorioUsuario.findAll();
	}
}
