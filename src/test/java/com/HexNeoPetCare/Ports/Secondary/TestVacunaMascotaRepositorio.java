package com.HexNeoPetCare.Ports.Secondary;

import com.HexNeoPetCare.Domain.Mascota;
import com.HexNeoPetCare.Domain.Vacuna;
import com.HexNeoPetCare.Domain.VacunaMascota;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestVacunaMascotaRepositorio {

    @Autowired
    private VacunaMascotaRepositorio objTest;

    @Autowired
    private VacunaRepositorio vacunaRepositorio;

    @Autowired
    private MascotaRepositorio mascotaRepositorio;

    private final Vacuna vacuna = new Vacuna("Desparasitacion", null);
    private final Mascota mascota = new Mascota("Rocky", 5, 10.5, null, null);
    private Long idMascotaLast;
    private Long idVacunaMascotaLast;

    @BeforeEach
    void setup() {
        vacunaRepositorio.save(vacuna);
        idMascotaLast = mascotaRepositorio.save(mascota).getIdMascota();
        idVacunaMascotaLast = objTest.save(new VacunaMascota(new Date(), null, false, mascota, vacuna)).getIdVacunaMascota();
    }

    @Test
    @Order(1)
    void testInsertarVacunaMascota() {
        //DADO
        VacunaMascota vacunaMascota = new VacunaMascota(new Date(), null, false, mascota, vacuna);

        //CUANDO
        int nBefore = objTest.findAll().size();
        VacunaMascota vacunaMascotaSaved = objTest.save(vacunaMascota);
        int nAfter = objTest.findAll().size();

        //ENTONCES
        assertNotNull(vacunaMascotaSaved);
        assertEquals(nAfter, nBefore + 1);
    }

    @Test
    @Order(2)
    void testlistarVacunaMascotas()
    {
        //DADO

        //CUANDO
        List<VacunaMascota> vacunaMascotas = objTest.findAll();

        //ENTONCES
        assertNotNull(vacunaMascotas);
        assertTrue(vacunaMascotas.size() > 0);
    }

    @Test
    @Order(3)
    void testEncontrarVacunaMascotaporId()
    {
        //DADO
        Long idVacunaMascota = idVacunaMascotaLast;

        //CUANDO
        VacunaMascota vacunaMascotaTest = objTest.encontrarVacunaMascotaporId(idVacunaMascota);

        //ENTONCES
        assertNotNull(vacunaMascotaTest);
        assertEquals(vacunaMascotaTest.getIdVacunaMascota(), idVacunaMascota);
    }

    @Test
    @Order(4)
    void testActualizarEstadoVacunaMascota()
    {
        //DADO
        Long idVacunaMascota = idVacunaMascotaLast;
        VacunaMascota vacunaMascotaTest = objTest.encontrarVacunaMascotaporId(idVacunaMascota);
        int nBeforeTest = objTest.findAll().size();

        //CUANDO
        Date fechaVacunaRealizada = new Date();
        vacunaMascotaTest.setFechaVacunaRealizada(fechaVacunaRealizada);
        vacunaMascotaTest.setStatus(true);
        objTest.save(vacunaMascotaTest);


        int nAfterTest = objTest.findAll().size();
        VacunaMascota afterTestVacunaMascota = objTest.encontrarVacunaMascotaporId(idVacunaMascota);

        //ENTONCES
        assertEquals(vacunaMascotaTest, afterTestVacunaMascota);
        assertEquals(nBeforeTest, nAfterTest);
        assertEquals(vacunaMascotaTest.getFechaVacunaRealizada(), fechaVacunaRealizada);
        assertTrue(vacunaMascotaTest.isStatus());
    }

    @Test
    @Order(5)
    void listarVacunasdeMascota()
    {
        //DADO
        Long idMascota = idMascotaLast;

        //CUANDO
        List<VacunaMascota> VacunasPorMascota = objTest.listarVacunasdeMascota(idMascota);

        //ENTONCES
        assertNotNull(VacunasPorMascota);
        assertTrue(VacunasPorMascota.size() > 0);
    }

    @Test
    @Order(6)
    void testEliminarVacunaMascota()
    {
        //DADO
        Long idVacunaMascota = idVacunaMascotaLast;
        VacunaMascota vacunaMascotaToDelete = objTest.encontrarVacunaMascotaporId(idVacunaMascota);

        //CUANDO
        int nBeforeTest = objTest.findAll().size();
        boolean existeMascotaBefore = objTest.existsById(idVacunaMascota);

        objTest.delete(vacunaMascotaToDelete);

        int nAfterTest = objTest.findAll().size();
        boolean existeMascotaAfter = objTest.existsById(idVacunaMascota);

        //ENTONCES

        assertEquals(nAfterTest, nBeforeTest - 1);
        assertTrue(existeMascotaBefore);
        assertFalse(existeMascotaAfter);
    }
}