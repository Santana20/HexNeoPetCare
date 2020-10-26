package com.HexNeoPetCare.Ports.Secondary;

import com.HexNeoPetCare.Domain.Cuidado;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestCuidadoRepositorio
{
    @Autowired
    private CuidadoRepositorio objTest_repo;
    
    private Long idCuidadoLast;

    @BeforeEach
    public void setup() throws Exception
    {
        Cuidado cuidado= new Cuidado("Baño");
        Cuidado saved = objTest_repo.save(cuidado);
        idCuidadoLast = saved.getIdCuidado();
        
    }

    @Test
    @Order(1)
    void testInsertarCuidado()
    {
        //DADO
        Cuidado cuidado = new Cuidado("Comida");

        //CUANDO
        int nBefore = objTest_repo.findAll().size();
        Cuidado cuidadoSaved = objTest_repo.save(cuidado);
        int nAfter = objTest_repo.findAll().size();

        //ENTONCES
        assertNotNull(cuidadoSaved);
        assertEquals(nAfter, nBefore + 1);
    }

    @Test
    @Order(2)
    void testListarCuidados()
    {
        //DADO

        //CUANDO
        List<Cuidado> cuidados= objTest_repo.findAll();

        //ENTONCES
        assertNotNull(cuidados);
        assertTrue(cuidados.size() > 0);
    }

    @Test
    @Order(3)
    void testEncontrarCuidadoporId()
    {
        //DADO
        Long idCuidado = idCuidadoLast;
        String nombreCuidado = "Baño";

        //CUANDO
        Cuidado cuidadoTest = objTest_repo.encontrarCuidadoporId(idCuidado);

        //ENTONCES
        assertNotNull(cuidadoTest);
        assertEquals(cuidadoTest.getIdCuidado(), idCuidado);
        assertEquals(cuidadoTest.getNombre(), nombreCuidado);
    }

    @Test
    @Order(4)
    void testActualizarCuidado()
    {
        //DADO
        Long idCuidado = idCuidadoLast;
        Cuidado cuidadoTest = objTest_repo.encontrarCuidadoporId(idCuidado);
        String nuevoNombre = "Corte de pelo";
        cuidadoTest.setNombre(nuevoNombre);

        //CUANDO
        int nBeforeTest = objTest_repo.findAll().size();
        objTest_repo.save(cuidadoTest);
        int nAfterTest = objTest_repo.findAll().size();
        Cuidado afterTestCuidado = objTest_repo.encontrarCuidadoporId(idCuidado);

        //ENTONCES
        assertEquals(cuidadoTest, afterTestCuidado);
        assertEquals(nBeforeTest, nAfterTest);
        assertEquals(cuidadoTest.getNombre(), nuevoNombre);
    }

    @Test
    @Order(5)
    void testEliminarCuidado()
    {
        //DADO
        Long idCuidado = idCuidadoLast;
        Cuidado cuidadoToDelete = objTest_repo.encontrarCuidadoporId(idCuidado);

        //CUANDO
        int nBeforeTest = objTest_repo.findAll().size();
        boolean existeCuidadoBefore = objTest_repo.existsById(idCuidado);

        objTest_repo.delete(cuidadoToDelete);

        int nAfterTest = objTest_repo.findAll().size();
        boolean existeCuidadoAfter = objTest_repo.existsById(idCuidado);

        //ENTONCES

        assertEquals(nAfterTest, nBeforeTest - 1);
        assertTrue(existeCuidadoBefore);
        assertFalse(existeCuidadoAfter);
    }
}