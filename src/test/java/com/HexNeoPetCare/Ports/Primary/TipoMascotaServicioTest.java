package com.HexNeoPetCare.Ports.Primary;

import com.HexNeoPetCare.Domain.TipoMascota;
import com.HexNeoPetCare.Ports.Secondary.TipoMascotaRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class TipoMascotaServicioTest {

    @MockBean
    private TipoMascotaRepositorio repositorioTipoMascota;

    @InjectMocks
    private TipoMascotaServicio tipoMascotaServicio;

    private final TipoMascota tipoMascota = new TipoMascota("Perro");

    @Test
    public void registrarTipoMascota() throws Exception {

        Mockito.when(repositorioTipoMascota
                .encontrarTipoMascotaPorNombre(tipoMascota.getNombreTipo()))
                .thenReturn(null);
        Mockito.when(repositorioTipoMascota.save(tipoMascota))
                .thenReturn(this.tipoMascota);

        TipoMascota result = this.tipoMascotaServicio.registrarTipoMascota(this.tipoMascota);

        assertNotNull(result);
        assertEquals(this.tipoMascota, result);
    }

    @Test
    public void obtenerTipoMascotaPorId() throws Exception {
        Long codTipoMascota = Long.valueOf(1);

        Mockito.when(repositorioTipoMascota
                .encontrarTipoMascotaporId(codTipoMascota))
                .thenReturn(this.tipoMascota);

        TipoMascota result = this.tipoMascotaServicio.obtenerTipoMascotaPorId(codTipoMascota);

        assertNotNull(result);
        assertEquals(this.tipoMascota, result);
    }

    @Test
    public void listarTipoMascotas() {
        List<TipoMascota> tipoMascotaList = new ArrayList<>();
        tipoMascotaList.add(this.tipoMascota);

        Mockito.when(repositorioTipoMascota.findAll())
                .thenReturn(tipoMascotaList);

        List<TipoMascota> result = this.tipoMascotaServicio.listarTipoMascotas();

        assertNotNull(result);
        assertEquals(tipoMascotaList, result);
    }
}