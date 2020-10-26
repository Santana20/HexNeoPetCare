package com.HexNeoPetCare.Ports.Secondary;

import com.HexNeoPetCare.Domain.Veterinario;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestVeterinarioRepositorio {

    @Autowired
    private VeterinarioRepositorio objtest_repo;

    private Long idVeterinarioLast;
    private final String nombreVeterinarioLast = "Julio";

    @BeforeEach
    void setup()
    {
        idVeterinarioLast =  objtest_repo.save(new Veterinario(nombreVeterinarioLast, "prueba", "prueba", "prueba@gmail.com", "123456897", "prueba", "prueba")).getIdVeterinario();
    }

    @Test
    @Order(1)
    void testInsertarVeterinario() {

        //DADO
    	Veterinario veterinario = new Veterinario("Juan", "prueba", "prueba", "prueba@gmail.com", "123456897", "prueba", "prueba");


        //CUANDO
        int nBefore = objtest_repo.findAll().size();
        Veterinario veterinarioSaved = objtest_repo.save(veterinario);
        int nAfter = objtest_repo.findAll().size();

        //ENTONCES
        assertNotNull(veterinarioSaved);
        assertEquals(nAfter, nBefore + 1);
    }

    @Test
    @Order(2)
    void testlistarMascotas()
    {
        //DADO

        //CUANDO
        List<Veterinario> veterinarios = objtest_repo.findAll();

        //ENTONCES
        assertNotNull(veterinarios);
        assertTrue(veterinarios.size() > 0);
    }

    @Test
    @Order(3)
    void testEncontrarVeterionarioporId()
    {
        //DADO
        Long idVeterinario = idVeterinarioLast;

        //CUANDO
        Veterinario veterinariotest = objtest_repo.encontrarVeterinarioporId(idVeterinario);

        //ENTONCES
        assertNotNull(veterinariotest);
        assertEquals(veterinariotest.getIdVeterinario(), idVeterinario);
    }

    @Test
    @Order(4)
    void testActualizarVeterinario()
    {
        //DADO
        Long idVeterinario = idVeterinarioLast;
        Veterinario veterinariotest = objtest_repo.encontrarVeterinarioporId(idVeterinario);
        String nuevoNombre = "Luis";
        veterinariotest.setNombre(nuevoNombre);

        //CUANDO
        int nBeforeTest = objtest_repo.findAll().size();
        objtest_repo.save(veterinariotest);
        int nAfterTest = objtest_repo.findAll().size();
        Veterinario afterTestVeterinario= objtest_repo.encontrarVeterinarioporId(idVeterinario);

        //ENTONCES
        assertEquals(veterinariotest, afterTestVeterinario);
        assertEquals(nBeforeTest, nAfterTest);
        assertEquals(afterTestVeterinario.getNombre(), nuevoNombre);
    }
    

    @Test
    @Order(5)
    void testEliminarVeterinario()
    {
        //DADO
        Long codVeterinario = idVeterinarioLast;

        //CUANDO
        boolean existeVeterinarioBefore = objtest_repo.existsById(codVeterinario);
        objtest_repo.deleteById(codVeterinario);
        boolean existeVeterinarioAfter = objtest_repo.existsById(codVeterinario);

        //ENTONCES
        assertTrue(existeVeterinarioBefore);
        assertFalse(existeVeterinarioAfter);
    }
}
