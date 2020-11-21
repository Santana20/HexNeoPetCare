package com.HexNeoPetCare.Ports.Primary;

import com.HexNeoPetCare.Domain.Cuidado;
import com.HexNeoPetCare.Ports.Secondary.CuidadoRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoPostProcessor;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CuidadoServicioTest {

    @MockBean
    private CuidadoRepositorio RepositorioCuidado;

    @InjectMocks
    private CuidadoServicio cuidadoServicio;

    private final Cuidado cuidado = new Cuidado("Alimentacion");

    @Test
    public void registrarCuidado() {
        Mockito.when(RepositorioCuidado.save(cuidado)).thenReturn(this.cuidado);

        Cuidado result = this.cuidadoServicio.registrarCuidado(this.cuidado);

        assertNotNull(result);
        assertEquals(this.cuidado, result);
    }

    @Test
    public void obtenerCuidado() throws Exception {
        Long codCuidado = Long.valueOf(1);

        Mockito.when(RepositorioCuidado
                .encontrarCuidadoporId(codCuidado))
                .thenReturn(this.cuidado);

        Cuidado result = this.cuidadoServicio.obtenerCuidado(codCuidado);

        assertNotNull(result);
        assertEquals(this.cuidado, result);
    }

    @Test
    public void noEncontradoCuidadoAlObtenerCuidado() {
        Long codCuidado = Long.valueOf(1);

        Mockito.when(RepositorioCuidado
                .encontrarCuidadoporId(codCuidado))
                .thenReturn(null);

        Throwable exception = assertThrows(Exception.class,
                () -> {
                    this.cuidadoServicio.obtenerCuidado(codCuidado);
                });

        assertEquals("Cuidado no encontrada.", exception.getMessage());
    }

    @Test
    public void eliminarCuidado() throws Exception {
        Long codCuidado = Long.valueOf(1);

        Mockito.when(RepositorioCuidado
                .encontrarCuidadoporId(codCuidado))
                .thenReturn(this.cuidado);

        Long result = this.cuidadoServicio.eliminarCuidado(codCuidado);

        assertNotNull(result);
        assertEquals(codCuidado, result);
    }

    @Test
    public void listarCuidados() {
        List<Cuidado> cuidadoList = new ArrayList<>();
        cuidadoList.add(this.cuidado);

        Mockito.when(RepositorioCuidado.findAll()).thenReturn(cuidadoList);

        List<Cuidado> result = this.cuidadoServicio.listarCuidados();

        assertNotNull(result);
        assertEquals(cuidadoList, result);
    }
}