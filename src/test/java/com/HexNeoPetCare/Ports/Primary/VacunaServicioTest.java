package com.HexNeoPetCare.Ports.Primary;

import com.HexNeoPetCare.Domain.TipoMascota;
import com.HexNeoPetCare.Domain.Vacuna;
import com.HexNeoPetCare.Ports.Secondary.VacunaRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class VacunaServicioTest {

    @MockBean
    private VacunaRepositorio RepositorioVacuna;

    @MockBean
    private TipoMascotaServicio servicioTipoMascota;

    @InjectMocks
    private VacunaServicio vacunaServicio;

    private final Vacuna vacuna = new Vacuna("Primera Vacuna", null);
    private final TipoMascota tipoMascota = new TipoMascota("Perro");

    @Test
    public void registrarVacuna() throws Exception {
        Long idTipoMascota = Long.valueOf(1);

        Mockito.when(servicioTipoMascota.obtenerTipoMascota(idTipoMascota)).thenReturn(this.tipoMascota);
        Mockito.when(RepositorioVacuna.save(this.vacuna)).thenReturn(this.vacuna);

        Vacuna result = this.vacunaServicio.registrarVacuna(idTipoMascota, this.vacuna);

        assertNotNull(result);
        assertEquals(this.vacuna, result);
    }

    @Test
    public void obtenerVacuna() throws Exception {
        Long codVacuna = Long.valueOf(1);

        Mockito.when(RepositorioVacuna.encontrarVacunaporId(codVacuna)).thenReturn(this.vacuna);

        Vacuna result = this.vacunaServicio.obtenerVacuna(codVacuna);

        assertNotNull(result);
        assertEquals(this.vacuna, result);
    }

    @Test
    public void eliminarVacuna() throws Exception {
        Long idVacuna = Long.valueOf(1);
        this.vacuna.setIdVacuna(idVacuna);

        Mockito.when(RepositorioVacuna.encontrarVacunaporId(idVacuna)).thenReturn(this.vacuna);

        Long idResult = this.vacunaServicio.eliminarVacuna(idVacuna);

        assertNotNull(idResult);
        assertEquals(idVacuna, idResult);
    }

    @Test
    public void listarVacunas() {
        List<Vacuna> listaVacunas = new ArrayList<>();
        listaVacunas.add(this.vacuna);

        Mockito.when(RepositorioVacuna.findAll()).thenReturn(listaVacunas);

        List<Vacuna> result = this.vacunaServicio.listarVacunas();

        assertNotNull(result);
        assertEquals(listaVacunas, result);
    }

    @Test
    public void listarVacunaporTipo() {
        Long idTipoMascota = Long.valueOf(1);
        List<Vacuna> listaVacunas = new ArrayList<>();
        listaVacunas.add(this.vacuna);

        Mockito.when(RepositorioVacuna.listarVacunaporTipo(idTipoMascota)).thenReturn(listaVacunas);

        List<Vacuna> result = this.vacunaServicio.listarVacunaporTipo(idTipoMascota);

        assertNotNull(result);
        assertEquals(listaVacunas, result);
    }
}