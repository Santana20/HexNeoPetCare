package com.HexNeoPetCare.Ports.Secondary;

import com.HexNeoPetCare.Domain.Horario;
import com.HexNeoPetCare.Domain.Veterinario;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestHorarioRepositorio {

    @Autowired
    private HorarioRepositorio objtest_repo;

    @Autowired
    private VeterinarioRepositorio veterinarioRepositorio;


    private final Veterinario veterinario= new Veterinario("prueba", "prueba", "prueba", "prueba@gmail.com", "123456897", "prueba", "prueba");
    private Long idHorarioLast;
    
    private Long idVeterinarioAux;

    @BeforeEach
    void setup()
    {
        //CONFIG
        idVeterinarioAux = veterinarioRepositorio.save(veterinario).getIdVeterinario();
        idHorarioLast =  objtest_repo.save(new Horario("prueba", "prueba", "prueba", veterinario)).getIdHorario();
    }

    @Test
    @Order(1)
    void testInsertarHorario() {

        //DADO
        Horario horario= new Horario("Martes", "prueba", "prueba", veterinario);


        //CUANDO
        int nBefore = objtest_repo.findAll().size();
        Horario horarioSaved = objtest_repo.save(horario);
        int nAfter = objtest_repo.findAll().size();

        //ENTONCES
        assertNotNull(horarioSaved);
        assertEquals(nAfter, nBefore + 1);
    }

    @Test
    @Order(2)
    void testlistarHorarios()
    {
        //DADO

        //CUANDO
        List<Horario> horarios= objtest_repo.findAll();

        //ENTONCES
        assertNotNull(horarios);
        assertTrue(horarios.size() > 0);
    }

    @Test
    @Order(3)
    void testEncontrarHorarioporId()
    {
        //DADO
        Long idHorario = idHorarioLast;

        //CUANDO
        Horario horariotest = objtest_repo.encontrarHorarioporId(idHorario);

        //ENTONCES
        assertNotNull(horariotest);
        assertEquals(horariotest.getIdHorario(), idHorario);
    }

    @Test
    @Order(4)
    void testActualizarHorario()
    {
        //DADO
        Long idHorario= idHorarioLast;
        Horario horariotest = objtest_repo.encontrarHorarioporId(idHorario);
        String nuevoDia= "Viernes";
        horariotest.setDia(nuevoDia);

        //CUANDO
        int nBeforeTest = objtest_repo.findAll().size();
        objtest_repo.save(horariotest);
        int nAfterTest = objtest_repo.findAll().size();
        Horario afterTestHorario= objtest_repo.encontrarHorarioporId(idHorario);

        //ENTONCES
        assertEquals(horariotest, afterTestHorario);
        assertEquals(nBeforeTest, nAfterTest);
        assertEquals(afterTestHorario.getDia(), nuevoDia);
    }


    @Test
    @Order(5)
    void testObtenerHorarioporidVeterinario()
    {
        //Dado
        Long codVeterinario = idVeterinarioAux;

        //Cuando
        List<Horario> horarios= objtest_repo.obtenerHorarioporidVeterinario(codVeterinario);

        //Entonces
        assertNotNull(horarios);
        assertTrue(horarios.size() > 0);
    }

    @Test
    @Order(6)
    void testEliminarHorario()
    {
        //DADO
        Long codHorario = idHorarioLast;

        //CUANDO
        boolean existeHorarioBefore = objtest_repo.existsById(codHorario);
        objtest_repo.deleteById(codHorario);
        boolean existeHorarioAfter = objtest_repo.existsById(codHorario);

        //ENTONCES
        assertTrue(existeHorarioBefore);
        assertFalse(existeHorarioAfter);
    }
}