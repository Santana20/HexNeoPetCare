package com.HexNeoPetCare.Ports.Primary;

import com.HexNeoPetCare.Domain.Cuidado;
import com.HexNeoPetCare.Domain.Mascota;
import com.HexNeoPetCare.Domain.RegistroCuidado;
import com.HexNeoPetCare.Ports.Secondary.CuidadoRepositorio;
import com.HexNeoPetCare.Ports.Secondary.MascotaRepositorio;
import com.HexNeoPetCare.Ports.Secondary.RegistroCuidadoRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class RegistroCuidadoServicioTest {

    @MockBean
    private RegistroCuidadoRepositorio RepositorioRegistroCuidado;

    @MockBean
    private MascotaRepositorio RepositorioMascota;

    @MockBean
    private CuidadoRepositorio RepositorioCuidado;

    @InjectMocks
    private RegistroCuidadoServicio registroCuidadoServicio;

    private final Cuidado cuidado = new Cuidado("Alimentacion");
    private final Mascota mascota = new Mascota("Rocky", 5, 6.5, null, null);
    private final RegistroCuidado registroCuidado = new RegistroCuidado(new Date(), null, false, null, null);

    @Test
    public void registrarRegistroCuidado() throws Exception {
        Long idMascota = Long.valueOf(1);
        Long idCuidado = Long.valueOf(1);

        Mockito.when(RepositorioMascota
                .encontrarMascotaporId(idMascota))
                .thenReturn(this.mascota);
        Mockito.when(RepositorioCuidado
                .encontrarCuidadoporId(idCuidado))
                .thenReturn(this.cuidado);

        Mockito.when(RepositorioRegistroCuidado
                .save(registroCuidado))
                .thenReturn(this.registroCuidado);

        RegistroCuidado result = this.registroCuidadoServicio.registrarRegistroCuidado(idMascota, idCuidado, this.registroCuidado);

        assertNotNull(result);
        assertEquals(this.registroCuidado, result);
        assertEquals(result.getCuidado(), this.cuidado);
        assertEquals(result.getMascota(), this.mascota);
    }

    @Test
    public void obtenerRegistroCuidado() throws Exception {
        Long idRegistroCuidado = Long.valueOf(1);

        Mockito.when(RepositorioRegistroCuidado
                .encontrarRegistroCuidadoporId(idRegistroCuidado))
                .thenReturn(this.registroCuidado);

        RegistroCuidado result = this.registroCuidadoServicio.obtenerRegistroCuidado(idRegistroCuidado);
        assertNotNull(result);
        assertEquals(this.registroCuidado, result);
    }

    @Test
    public void actualizarEstadoRegistroCuidado() throws Exception {
        Long idRegistroCuidado = Long.valueOf(1);

        Mockito.when(RepositorioRegistroCuidado
                .encontrarRegistroCuidadoporId(idRegistroCuidado))
                .thenReturn(this.registroCuidado);

        Mockito.when(RepositorioRegistroCuidado
                .save(registroCuidado))
                .thenReturn(this.registroCuidado);

        RegistroCuidado result = this.registroCuidadoServicio.actualizarEstadoRegistroCuidado(idRegistroCuidado);

        assertNotNull(result);
        assertEquals(this.registroCuidado, result);
        assertNotNull(result.getFechaRealizado());
        assertTrue(result.isStatus());
    }

    @Test
    public void eliminarRegistroCuidado() throws Exception {
        Long idRegistroCuidado = Long.valueOf(1);

        Mockito.when(RepositorioRegistroCuidado
                .encontrarRegistroCuidadoporId(idRegistroCuidado))
                .thenReturn(this.registroCuidado);

        Long result = this.registroCuidadoServicio.eliminarRegistroCuidado(idRegistroCuidado);

        assertNotNull(result);
        assertEquals(idRegistroCuidado, result);
    }

    @Test
    public void listarRegistroCuidadoMascota() {
        Long idMascota = Long.valueOf(1);
        List<RegistroCuidado> registroCuidadoList = new ArrayList<>();
        registroCuidadoList.add(this.registroCuidado);

        Mockito.when(RepositorioRegistroCuidado.listarCuidadoMascota(idMascota))
                .thenReturn(registroCuidadoList);

        List<RegistroCuidado> result = this.registroCuidadoServicio.listarRegistroCuidadoMascota(idMascota);

        assertNotNull(result);
        assertEquals(registroCuidadoList, result);
    }
}