package com.HexNeoPetCare.Adapters.Primary;

import com.HexNeoPetCare.Domain.Usuario;
import com.HexNeoPetCare.Ports.Primary.UsuarioServicio;
import com.HexNeoPetCare.Ports.Secondary.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class RestUsuario
{
    @Autowired
    private UsuarioServicio usuarioServicio;
    private UsuarioRepositorio usuarioRepositorio;

    //REGISTRAR USUARIO
    @PostMapping("/registrarUsuario")
    public void registrarUsuario(@RequestBody Usuario usuario)
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
    @GetMapping("/obtenerUsuario/{codUsuario}")
    public Usuario obtenerUsuario(@PathVariable(value = "codUsuario") Long codUsuario)
    {
        Usuario u;
        try
        {
            u = usuarioServicio.obtenerUsuario(codUsuario);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo obtener al usuario " + codUsuario.toString());
        }
        return u;
    }

    //ACTUALIZAR USUARIO
    @PutMapping("/actualizarUsuario/{idUsuario}")
    public void actualizarUsuario(@PathVariable(value = "idUsuario") Long idUsuario, @RequestBody Usuario usuario)
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

    //LOGIN

    @PostMapping("/login")
    public void login(@RequestBody String correo, @RequestBody String password) {
        try
        {
            usuarioServicio.validarUsuario(correo, password);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datos ingresados incorrectos");
        }
    }

}
