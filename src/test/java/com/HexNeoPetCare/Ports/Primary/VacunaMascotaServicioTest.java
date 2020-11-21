package com.HexNeoPetCare.Ports.Primary;

import com.HexNeoPetCare.Domain.Mascota;
import com.HexNeoPetCare.Domain.TipoMascota;
import com.HexNeoPetCare.Domain.Vacuna;
import com.HexNeoPetCare.Domain.VacunaMascota;
import com.HexNeoPetCare.Ports.Secondary.MascotaRepositorio;
import com.HexNeoPetCare.Ports.Secondary.VacunaMascotaRepositorio;
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
public class VacunaMascotaServicioTest {

    @MockBean
    private VacunaMascotaRepositorio repositorioVacunaMascota;

    @MockBean
    private MascotaRepositorio repositorioMascota;

    @MockBean
    private VacunaServicio servicioVacuna;

    @InjectMocks
    private VacunaMascotaServicio vacunaMascotaServicio;

    private final Mascota mascota = new Mascota("Rocky", 8, 5.6, null, null);
    private final Vacuna vacuna = new Vacuna("Primera Vacuna", null);
    private final VacunaMascota vacunaMascota = new VacunaMascota(new Date(), null, false, null, null);
    private final TipoMascota tipoMascota = new TipoMascota("Perro");

    @Test
    public void registrarVacunaMascota() throws Exception {
        Long idMascota = Long.valueOf(1);
        Long idVacuna = Long.valueOf(1);
        this.tipoMascota.setIdTipo(Long.valueOf(1));
        this.mascota.setTipomascota(this.tipoMascota);
        this.vacuna.setTipomascota(this.tipoMascota);

        Mockito.when(repositorioMascota.encontrarMascotaporId(idMascota)).thenReturn(this.mascota);
        Mockito.when(servicioVacuna.obtenerVacuna(idVacuna)).thenReturn(this.vacuna);
        Mockito.when(repositorioVacunaMascota.save(this.vacunaMascota)).thenReturn(this.vacunaMascota);

        VacunaMascota result = this.vacunaMascotaServicio.registrarVacunaMascota(idVacuna, idMascota, this.vacunaMascota);

        assertNotNull(result);
        assertEquals(this.vacunaMascota, result);
        assertEquals(result.getVacuna(), this.vacuna);
        assertEquals(result.getMascota(), this.mascota);

    }

    @Test
    public void actualizarEstadoVacunaMascota() throws Exception {
        Long idVacunaMascota = Long.valueOf(1);

        Mockito.when(repositorioVacunaMascota.encontrarVacunaMascotaporId(idVacunaMascota)).thenReturn(this.vacunaMascota);
        Mockito.when(repositorioVacunaMascota.save(this.vacunaMascota)).thenReturn(this.vacunaMascota);

        VacunaMascota result = this.vacunaMascotaServicio.actualizarEstadoVacunaMascota(idVacunaMascota);

        assertNotNull(result);
        assertEquals(this.vacunaMascota, result);
        assertNotNull(result.getFechaVacunaRealizada());
        assertTrue(result.isStatus());
    }

    @Test
    public void eliminarVacunadeMascota() throws Exception {
        Long idVacunaMascota = Long.valueOf(1);

        Mockito.when(repositorioVacunaMascota
                .encontrarVacunaMascotaporId(idVacunaMascota))
                .thenReturn(this.vacunaMascota);

        Long result = this.vacunaMascotaServicio.eliminarVacunadeMascota(idVacunaMascota);

        assertNotNull(result);
        assertEquals(idVacunaMascota, result);
    }

    @Test
    public void listarVacunasdeMascota() {
        Long idMascota = Long.valueOf(1);
        List<VacunaMascota> vacunaMascotaList = new ArrayList<>();
        vacunaMascotaList.add(this.vacunaMascota);

        Mockito.when(repositorioVacunaMascota.listarVacunasdeMascota(idMascota)).thenReturn(vacunaMascotaList);

        List<VacunaMascota> result = this.vacunaMascotaServicio.listarVacunasdeMascota(idMascota);

        assertNotNull(result);
        assertEquals(vacunaMascotaList, result);
    }
}