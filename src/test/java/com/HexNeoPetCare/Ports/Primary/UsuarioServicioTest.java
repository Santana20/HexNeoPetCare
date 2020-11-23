package com.HexNeoPetCare.Ports.Primary;

import com.HexNeoPetCare.Domain.RegistroCuidado;
import com.HexNeoPetCare.Domain.Usuario;
import com.HexNeoPetCare.Ports.Secondary.UsuarioRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UsuarioServicioTest {

    @MockBean
    private UsuarioRepositorio RepositorioUsuario;

    @InjectMocks
    private UsuarioServicio usuarioServicio;

    private final Usuario usuario = new Usuario("Sebastian", "Contreras", "Jr. Ayacucho 458",
            "sebastian@prueba.com", "999999999", "sebastian", "123456");

    @Test
    public void registrarUsuario() throws Exception {

        Mockito.when(RepositorioUsuario.encontrarUsuarioporCorreo(this.usuario.getCorreo())).thenReturn(null);
        Mockito.when(RepositorioUsuario.save(this.usuario)).thenReturn(this.usuario);

        Usuario result = this.usuarioServicio.registrarUsuario(this.usuario);
        assertNotNull(result);
        assertEquals(this.usuario, result);
    }

    @Test
    public void noRegistrarUsuarioConDatosNulos() {
        Usuario paramUsuario = new Usuario();

        Mockito.when(RepositorioUsuario.encontrarUsuarioporCorreo(this.usuario.getCorreo())).thenReturn(null);
        Mockito.when(RepositorioUsuario.save(this.usuario)).thenReturn(this.usuario);

        Throwable exception = assertThrows(Exception.class,
                () -> {
                    this.usuarioServicio.registrarUsuario(paramUsuario);
                });

        assertEquals("No se ingresaron todos los datos.", exception.getMessage());
    }

    @Test
    public void noRegistrarUsuarioConCorreoRepetido() {

        Mockito.when(RepositorioUsuario.encontrarUsuarioporCorreo(this.usuario.getCorreo())).thenReturn(new Usuario());
        Mockito.when(RepositorioUsuario.save(this.usuario)).thenReturn(this.usuario);

        Throwable exception = assertThrows(Exception.class,
                () -> {
                    this.usuarioServicio.registrarUsuario(this.usuario);
                });

        assertEquals("El correo ya se ha registrado.", exception.getMessage());
    }

    @Test
    public void obtenerUsuario() throws Exception {
        Long codUsuario = Long.valueOf(1);

        Mockito.when(RepositorioUsuario.encontrarUsuarioporId(codUsuario)).thenReturn(this.usuario);

        Usuario result = this.usuarioServicio.obtenerUsuario(codUsuario);
        assertNotNull(result);
        assertEquals(this.usuario, result);
    }

    @Test
    public void noEncontrarUsuarioEnObtenerUsuario() throws Exception {
        Long codUsuario = Long.valueOf(1);

        Mockito.when(RepositorioUsuario.encontrarUsuarioporId(codUsuario)).thenReturn(null);

        Throwable exception = assertThrows(Exception.class,
                () -> {
                    this.usuarioServicio.obtenerUsuario(codUsuario);
                });

        assertEquals("Usuario no encontrado.", exception.getMessage());
    }

    @Test
    public void actualizarUsuario() throws Exception {
        Long idUsuario = Long.valueOf(1);
        Usuario paramUsuario = new Usuario("Javier", "prueba", "prueba", "prueba@correo.com", "11111111", "prueba", "prueba");

        Mockito.when(RepositorioUsuario.encontrarUsuarioporId(idUsuario)).thenReturn(this.usuario);
        Mockito.when(RepositorioUsuario.save(this.usuario)).thenReturn(this.usuario);

        Usuario result = this.usuarioServicio.actualizarUsuario(idUsuario, paramUsuario);

        assertNotNull(result);
        assertEquals(this.usuario, result);
        assertEquals(result.getNombre(), paramUsuario.getNombre());
    }

    @Test
    public void loginUsuario() throws Exception {
        String correoUsuario = this.usuario.getCorreo();
        String passwordUsuario = this.usuario.getPassword();

        Mockito.when(RepositorioUsuario
                .encontrarUsuarioporCorreo(correoUsuario))
                .thenReturn(this.usuario);

        Boolean result = this.usuarioServicio.validarUsuario(correoUsuario, passwordUsuario);

        assertTrue(result);
    }

    @Test
    public void badLoginUsuario() throws Exception {
        String correoUsuario = this.usuario.getCorreo();
        String passwordUsuario = "12312541";

        Mockito.when(RepositorioUsuario
                .encontrarUsuarioporCorreo(correoUsuario))
                .thenReturn(this.usuario);

        Boolean result = this.usuarioServicio.validarUsuario(correoUsuario, passwordUsuario);

        assertFalse(result);
    }


}