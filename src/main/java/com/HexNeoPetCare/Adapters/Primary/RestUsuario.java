package com.HexNeoPetCare.Adapters.Primary;

import com.HexNeoPetCare.Domain.Usuario;
import com.HexNeoPetCare.Ports.Primary.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestUsuario
{
    @Autowired
    private UsuarioServicio usuarioServicio;

    //REGISTRAR USUARIO
    public void registrarUsuario(Usuario usuario)
    {
        try {
            usuarioServicio.registrarUsuario(usuario);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo registrar al usuario.");
        }
    }

    //OBTENER USUARIO
    public Usuario obtenerUsuario(Long cod)
    {
        Usuario u;
        try
        {
            u = usuarioServicio.obtenerUsuario(cod);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo obtener al usuario " + cod.toString());
        }
        return u;
    }

    //ACTUALIZAR USUARIO
    public void actualizarUsuario(Long idUsuario, Usuario usuario)
    {
        try
        {
            usuarioServicio.actualizarUsuario(idUsuario, usuario);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo actualizar al usuario " + idUsuario.toString());
        }
    }

    //ELIMINAR USUARIO
    public void eliminarVeterinario(Long codUsuario)
    {
        try
        {
            usuarioServicio.eliminarVeterinario(codUsuario);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se pudo eliminar al usuario " + codUsuario.toString());
        }
    }

    //LISTAR TODOS LOS VETERINARIOS
    public List<Usuario> listarUsuarios()
    {
        List<Usuario> lsUsuarios;
        try
        {
            lsUsuarios = usuarioServicio.listarUsuarios();
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo listar a los usuarios.");
        }
        return lsUsuarios;
    }
}
