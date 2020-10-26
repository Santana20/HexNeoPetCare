package com.HexNeoPetCare.Ports.Secondary;

import com.HexNeoPetCare.Domain.TipoMascota;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestTipoMascotaRepositorio
{
    @Autowired
    private TipoMascotaRepositorio objTest_repo;

    private Long idTipoMascotaLast;

    @BeforeEach
    public void setup() throws Exception
    {
        TipoMascota tipoMascota = new TipoMascota("Perro");
        TipoMascota saved = objTest_repo.save(tipoMascota);

        idTipoMascotaLast = saved.getIdTipo();
    }

    @Test
    @Order(1)
    void testInsertarTipoMascota()
    {
        //DADO
        TipoMascota tipoMascota = new TipoMascota("Gato");

        //CUANDO
        int nBefore = objTest_repo.findAll().size();
        TipoMascota tipoMascotaSaved = objTest_repo.save(tipoMascota);
        int nAfter = objTest_repo.findAll().size();

        //ENTONCES
        assertNotNull(tipoMascotaSaved);
        assertEquals(nAfter, nBefore + 1);
    }

    @Test
    @Order(2)
    void testListarTipoMascotas()
    {
        //DADO

        //CUANDO
        List<TipoMascota> tipoMascotas = objTest_repo.findAll();

        //ENTONCES
        assertNotNull(tipoMascotas);
        assertTrue(tipoMascotas.size() > 0);
    }

    @Test
    @Order(3)
    void testEncontrarTipoMascotaporId()
    {
        //DADO
        Long idTipoMascota = idTipoMascotaLast;
        String nombreTipoMascota = "Perro";

        //CUANDO
        TipoMascota tipoMascotaTest = objTest_repo.encontrarTipoMascotaporId(idTipoMascota);

        //ENTONCES
        assertNotNull(tipoMascotaTest);
        assertEquals(tipoMascotaTest.getIdTipo(), idTipoMascota);
        assertEquals(tipoMascotaTest.getNombreTipo(), nombreTipoMascota);
    }

    @Test
    @Order(4)
    void testActualizarTipoMascota()
    {
        //DADO
        Long idTipoMascota = idTipoMascotaLast;
        TipoMascota tipoMascotaTest = objTest_repo.encontrarTipoMascotaporId(idTipoMascota);
        String nuevoNombre = "Roedor";
        tipoMascotaTest.setNombreTipo(nuevoNombre);

        //CUANDO
        int nBeforeTest = objTest_repo.findAll().size();
        objTest_repo.save(tipoMascotaTest);
        int nAfterTest = objTest_repo.findAll().size();
        TipoMascota afterTestTipoMascota = objTest_repo.encontrarTipoMascotaporId(idTipoMascota);

        //ENTONCES
        assertEquals(tipoMascotaTest, afterTestTipoMascota);
        assertEquals(nBeforeTest, nAfterTest);
        assertEquals(tipoMascotaTest.getNombreTipo(), nuevoNombre);
    }

    @Test
    @Order(5)
    void testEliminarTipoMascota()
    {
        //DADO
        Long idTipoMascota = idTipoMascotaLast;
        TipoMascota tipoMascotaToDelete = objTest_repo.encontrarTipoMascotaporId(idTipoMascota);

        //CUANDO
        int nBeforeTest = objTest_repo.findAll().size();
        boolean existeMascotaBefore = objTest_repo.existsById(idTipoMascota);

        objTest_repo.delete(tipoMascotaToDelete);

        int nAfterTest = objTest_repo.findAll().size();
        boolean existeMascotaAfter = objTest_repo.existsById(idTipoMascota);

        //ENTONCES

        assertEquals(nAfterTest, nBeforeTest - 1);
        assertTrue(existeMascotaBefore);
        assertFalse(existeMascotaAfter);
    }
}