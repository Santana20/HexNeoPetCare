package com.HexNeoPetCare.Ports.Secondary;

import com.HexNeoPetCare.Domain.TipoMascota;
import com.HexNeoPetCare.Domain.Vacuna;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestVacunaRepositorio {

    @Autowired
    private VacunaRepositorio objTest;

    @Autowired
    private TipoMascotaRepositorio tipoMascotaRepositorio;

    private final TipoMascota tipoMascota = new TipoMascota("Perro");
    private Long idVacunaLast;
    private Long idTipoMascotaLast;

    @BeforeEach
    void setup()
    {
        //CONFIG
        idTipoMascotaLast = tipoMascotaRepositorio.save(tipoMascota).getIdTipo();
        idVacunaLast = objTest.save(new Vacuna("Contra Parasitos", tipoMascota)).getIdVacuna();
    }

    @Test
    @Order(1)
    void testInsertarVacuna()
    {
        //DADO
        Vacuna vacuna = new Vacuna("Contra Parasitos", tipoMascota);

        //CUANDO
        int nBefore = objTest.findAll().size();
        Vacuna vacunaSaved = objTest.save(vacuna);
        int nAfter = objTest.findAll().size();

        //ENTONCES
        assertNotNull(vacunaSaved);
        assertEquals(nAfter, nBefore + 1);
    }

    @Test
    @Order(2)
    void testlistarMascotas()
    {
        //DADO

        //CUANDO
        List<Vacuna> vacunas = objTest.findAll();

        //ENTONCES
        assertNotNull(vacunas);
        assertTrue(vacunas.size() > 0);
    }

    @Test
    @Order(3)
    void testEncontrarVacunaporId()
    {
        //DADO
        Long idVacuna = idVacunaLast;

        //CUANDO
        Vacuna vacunaTest = objTest.encontrarVacunaporId(idVacuna);

        //ENTONCES
        assertNotNull(vacunaTest);
        assertEquals(vacunaTest.getIdVacuna(), idVacuna);
    }

    @Test
    @Order(4)
    void testActualizarVacuna()
    {
        //DADO
        Long idVacuna = idVacunaLast;
        Vacuna vacunaTest = objTest.encontrarVacunaporId(idVacuna);
        String nuevoNombreVacuna = "Desparasitacion";
        vacunaTest.setNombrevacuna(nuevoNombreVacuna);

        //CUANDO
        int nBeforeTest = objTest.findAll().size();
        objTest.save(vacunaTest);
        int nAfterTest = objTest.findAll().size();
        Vacuna afterTestVacuna = objTest.encontrarVacunaporId(idVacuna);

        //ENTONCES
        assertEquals(vacunaTest, afterTestVacuna);
        assertEquals(nBeforeTest, nAfterTest);
        assertEquals(afterTestVacuna.getNombrevacuna(), nuevoNombreVacuna);
    }

    @Test
    @Order(5)
    void testListarVacunaporTipo()
    {
        //DADO
        Long idTipoMascota = idTipoMascotaLast;

        //CUANDO
        List<Vacuna> vacunasPorTipoMascota = objTest.listarVacunaporTipo(idTipoMascota);

        //ENTONCES
        assertNotNull(vacunasPorTipoMascota);
        assertTrue(vacunasPorTipoMascota.size() > 0);
    }

    @Test
    @Order(6)
    void testEliminarVacuna()
    {
        //DADO
        Long idVacuna = idVacunaLast;
        Vacuna vacunaToDelete = objTest.encontrarVacunaporId(idVacuna);

        //CUANDO
        int nBeforeTest = objTest.findAll().size();
        boolean existeMascotaBefore = objTest.existsById(idVacuna);

        objTest.delete(vacunaToDelete);

        int nAfterTest = objTest.findAll().size();
        boolean existeMascotaAfter = objTest.existsById(idVacuna);

        //ENTONCES

        assertEquals(nAfterTest, nBeforeTest - 1);
        assertTrue(existeMascotaBefore);
        assertFalse(existeMascotaAfter);
    }
}