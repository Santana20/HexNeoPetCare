package com.HexNeoPetCare.Ports.Primary;

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
    public void obtenerUsuario() throws Exception {
        Long codUsuario = Long.valueOf(1);

        Mockito.when(RepositorioUsuario.encontrarUsuarioporId(codUsuario)).thenReturn(this.usuario);

        Usuario result = this.usuarioServicio.obtenerUsuario(codUsuario);
        assertNotNull(result);
        assertEquals(this.usuario, result);
    }

    @Test
    public void actualizarUsuario() throws Exception {
        Long idUsuario = Long.valueOf(1);
        Usuario paramUsuario = new Usuario("Javier", null, null, null, null, null, null);

        Mockito.when(RepositorioUsuario.encontrarUsuarioporId(idUsuario)).thenReturn(this.usuario);
        Mockito.when(RepositorioUsuario.save(this.usuario)).thenReturn(this.usuario);

        Usuario result = this.usuarioServicio.actualizarUsuario(idUsuario, paramUsuario);
        assertNotNull(result);
        assertEquals(this.usuario, result);
        assertEquals(result.getNombre(), paramUsuario.getNombre());
    }
}