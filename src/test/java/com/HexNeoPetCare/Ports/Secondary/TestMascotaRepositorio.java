package com.HexNeoPetCare.Ports.Secondary;

import com.HexNeoPetCare.Domain.Mascota;
import com.HexNeoPetCare.Domain.TipoMascota;
import com.HexNeoPetCare.Domain.Usuario;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestMascotaRepositorio {

    @Autowired
    private MascotaRepositorio objtest_repo;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private TipoMascotaRepositorio tipoMascotaRepositorio;

    @Test
    @Order(1)
    void testInsertarMascota()
    {
        //Dado
        Usuario usuario = usuarioRepositorio.encontrarUsuarioporId((long) 1);
        TipoMascota tipoMascota = tipoMascotaRepositorio.encontrarTipoMascotaporId((long) 1);
        Mascota mascota = new Mascota("Firulais", 5, 10.5, usuario, tipoMascota);

        //Cuando
        Mascota mascotaSaved = objtest_repo.save(mascota);

        //Entonces
        assertNotNull(mascotaSaved);
    }

    @Test
    @Order(2)
    void testlistarMascotas()
    {
        //Dado

        //Cuando
        List<Mascota> mascotas = objtest_repo.findAll();

        //Entonces
        assertNotNull(mascotas);
        assertTrue(mascotas.size() > 0);
    }

    @Test
    @Order(3)
    void testActualizarMascota()
    {
        //Dado
        int nBeforeTest = objtest_repo.findAll().size();
        Long idMascota = (long) 1;
        Mascota mascotatest = objtest_repo.encontrarMascotaporId(idMascota);
        String nuevoNombre = "Tom";
        mascotatest.setNombre(nuevoNombre);

        //Cuando
        objtest_repo.save(mascotatest);
        int nAfterTest = objtest_repo.findAll().size();
        Mascota afterTestMascota = objtest_repo.encontrarMascotaporId(idMascota);

        //Entonces
        assertEquals(mascotatest, afterTestMascota);
        assertEquals(nBeforeTest, nAfterTest);
    }

    @Test
    @Order(7)
    void testEliminarMascota()
    {
        //Dado
        Long codMascota = (long) 1;

        //Cuando
        boolean existeMascotaBefore = objtest_repo.existsById(codMascota);
        objtest_repo.deleteById(codMascota);
        boolean existeMascotaAfter = objtest_repo.existsById(codMascota);
        //Entonces

        assertTrue(existeMascotaBefore);
        assertFalse(existeMascotaAfter);
    }

    @Test
    @Order(4)
    void testEncontrarMascotaporId()
    {
        //Dado
        Long idMascota = (long) 1;

        //Cuando
        Mascota mascotatest = objtest_repo.encontrarMascotaporId(idMascota);

        //Entonces
        assertNotNull(mascotatest);
        assertEquals(mascotatest.getIdMascota(), (long) 1);
    }

    @Test
    @Order(5)
    void testObtenerMascotaporidUsuario()
    {
        //Dado
        Long codUsuario = (long) 1;

        //Cuando
        List<Mascota> mascotas = objtest_repo.obtenerMascotasporidUsuario(codUsuario);

        //Entonces
        assertNotNull(mascotas);
        assertTrue(mascotas.size() > 0);
    }

    @Test
    @Order(6)
    void testEncontrarMascotasporNombreYUsuario()
    {
        //Dado
        Long codUsuario = (long) 1;
        String nombreMascota = "Firulais";

        //Cuando
        List<Mascota> mascotas = objtest_repo.encontrarMascotasporNombreYUsuario(codUsuario, nombreMascota);

        //Entonces
        assertNotNull(mascotas);
        assertTrue(mascotas.size() > 0);
    }
}