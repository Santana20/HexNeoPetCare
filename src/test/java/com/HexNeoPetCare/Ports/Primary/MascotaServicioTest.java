package com.HexNeoPetCare.Ports.Primary;

import com.HexNeoPetCare.Domain.Mascota;
import com.HexNeoPetCare.Domain.TipoMascota;
import com.HexNeoPetCare.Domain.Usuario;
import com.HexNeoPetCare.Ports.Secondary.MascotaRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class MascotaServicioTest {

    @MockBean
    private MascotaRepositorio repositorioMascota;

    @MockBean
    private UsuarioServicio servicioUsuario;

    @MockBean
    private TipoMascotaServicio servicioTipoMascota;

    @InjectMocks
    private MascotaServicio mascotaServicio;

    private final Mascota mascota = new Mascota("Rocky", 8, 5.6, null, null);
    private final Usuario usuario = new Usuario("Sebastian", "Contreras", "Jr. Ayacucho 458",
            "sebastian@prueba.com", "999999999", "sebastian", "123456");
    private final TipoMascota tipoMascota = new TipoMascota("Perro");
    private final List<Mascota> mascotaList = new ArrayList<>();

    @Test
    void registrarMascota() throws Exception {
        Long codUsuario = Long.valueOf(1);
        Long idtipomascota = Long.valueOf(1);

        Mockito.when(servicioUsuario.obtenerUsuario(codUsuario)).thenReturn(this.usuario);
        Mockito.when(servicioTipoMascota.obtenerTipoMascota(idtipomascota)).thenReturn(this.tipoMascota);
        Mockito.when(repositorioMascota.encontrarMascotaporNombreYUsuario(codUsuario, this.mascota.getNombre())).thenReturn(null);
        Mockito.when(repositorioMascota.save(this.mascota)).thenReturn(this.mascota);
        
        Mascota result = this.mascotaServicio.registrarMascota(codUsuario, idtipomascota, this.mascota);

        assertNotNull(result);
        assertEquals(result, this.mascota);
    }

    @Test
    void obtenerMascota() throws Exception {
        Long codMascota = Long.valueOf(1);

        Mockito.when(repositorioMascota.encontrarMascotaporId(codMascota)).thenReturn(this.mascota);

        Mascota result = this.mascotaServicio.obtenerMascota(codMascota);

        assertNotNull(result);
        assertEquals(result, this.mascota);
    }

    @Test
    void actualizarMascota() throws Exception {
        Long codMascota = Long.valueOf(1);
        Long idTipoMascota = Long.valueOf(1);
        Mascota paramMascota = new Mascota("Felipe", 8, 5.6, null, null);


        Mockito.when(repositorioMascota.encontrarMascotaporId(codMascota)).thenReturn(this.mascota);
        Mockito.when(servicioTipoMascota.obtenerTipoMascota(idTipoMascota)).thenReturn(this.tipoMascota);
        Mockito.when(repositorioMascota.save(this.mascota)).thenReturn(this.mascota);

        Mascota result = this.mascotaServicio.actualizarMascota(codMascota, null, paramMascota);

        assertNotNull(result);
        assertEquals(result, this.mascota);
    }

    @Test
    void eliminarMascota() {

    }

    @Test
    void listarMascotas() {
        this.mascotaList.add(this.mascota);

        Mockito.when(repositorioMascota.findAll()).thenReturn(this.mascotaList);

        List<Mascota> listaresult = this.mascotaServicio.listarMascotas();

        assertNotNull(listaresult);
        assertEquals(listaresult, this.mascotaList);
    }

    @Test
    void listarMascotasporUsuario() {
        Long idUsuario = Long.valueOf(1);
        this.mascotaList.add(this.mascota);

        Mockito.when(repositorioMascota.obtenerMascotasporidUsuario(idUsuario)).thenReturn(this.mascotaList);

        List<Mascota> listaresult = this.mascotaServicio.listarMascotasporUsuario(idUsuario);

        assertNotNull(listaresult);
        assertEquals(listaresult, this.mascotaList);
        assertTrue(listaresult.size() > 0);
    }
}