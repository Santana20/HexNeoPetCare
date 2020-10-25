package com.HexNeoPetCare.Ports.Primary;

import com.HexNeoPetCare.Domain.Mascota;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class TestMascotaServicio {
    @Autowired
    private MascotaServicio objTestMascotaServicio;

    @Test
    @Rollback(value = false)
    void registrarMascota()
    {
        //DADO
        Long codUsuario = (long)1;
        Long idtipomascota = (long)1;
        Mascota m = new Mascota("Marshal", 2, 6.5, null, null);

        //CUANDO
        try {
            objTestMascotaServicio.registrarMascota(codUsuario, idtipomascota, m);
        }
        catch (Exception x)
        {
            System.out.println(x.getMessage());
        }

    }

    @Test
    void obtenerMascota()
    {

    }

    @Test
    void actualizarMascota()
    {

    }

    @Test
    void eliminarMascota() {
    }

    @Test
    void listarMascotas() {
    }

    @Test
    void listarMascotasporUsuario() {
    }
}