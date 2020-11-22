package com.HexNeoPetCare.Ports.Secondary;

import com.HexNeoPetCare.Domain.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestRegistroCuidadoRepositorio {

    @Autowired
    private CuidadoRepositorio cuidadoRepositorio;

    @Autowired
    private RegistroCuidadoRepositorio objtest_repo;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private TipoMascotaRepositorio tipoMascotaRepositorio;

    @Autowired
    private MascotaRepositorio mascotaRepositorio;

    private final Cuidado cuidado = new Cuidado("prueba");
    private final Usuario usuario = new Usuario("prueba", "prueba", "prueba", "prueba@gmail.com", "123456897", "prueba", "prueba");
    private final TipoMascota tipoMascota = new TipoMascota("Perro");
    private final Mascota mascota = new Mascota("Rocky", 5, 10.5, usuario, tipoMascota);
    private Long idRegistroCuidadoLast;

    private Long idMascotaAux;

    @SuppressWarnings("deprecation")
    private Date fechaRegistro = new Date(2020, 10, 30, 16, 0, 0);

    @SuppressWarnings("deprecation")
    private Date fechaRealizado = new Date(2020, 11, 30, 16, 0, 0);


    @BeforeEach
    void setup() {
        //CONFIG
        cuidadoRepositorio.save(cuidado);
        usuarioRepositorio.save(usuario);
        tipoMascotaRepositorio.save(tipoMascota);
        idMascotaAux = mascotaRepositorio.save(mascota).getIdMascota();

        idRegistroCuidadoLast = objtest_repo.save(new RegistroCuidado(fechaRegistro, fechaRealizado, true, mascota, cuidado)).getIdRegistroCuidado();
    }

    @Test
    @Order(1)
    void testInsertarRegistroCuidado() {

        //DADO
        @SuppressWarnings("deprecation")
        Date fecha1 = new Date(2020, 11, 5, 16, 0, 0);

        @SuppressWarnings("deprecation")
        Date fecha2 = new Date(2020, 11, 8, 16, 0, 0);

        RegistroCuidado registroCuidado = new RegistroCuidado(fecha1, fecha2, true, mascota, cuidado);


        //CUANDO
        int nBefore = objtest_repo.findAll().size();
        RegistroCuidado registroCuidadoSaved = objtest_repo.save(registroCuidado);
        int nAfter = objtest_repo.findAll().size();

        //ENTONCES
        assertNotNull(registroCuidadoSaved);
        assertEquals(nAfter, nBefore + 1);
    }

    @Test
    @Order(2)
    void testlistarRegistros() {
        //DADO

        //CUANDO
        List<RegistroCuidado> registros = objtest_repo.findAll();

        //ENTONCES
        assertNotNull(registros);
        assertTrue(registros.size() > 0);
    }

    @Test
    @Order(3)
    void testEncontrarRegistroCuidadoporId() {
        //DADO
        Long idRegistroCuidado = idRegistroCuidadoLast;

        //CUANDO
        RegistroCuidado registrotest = objtest_repo.encontrarRegistroCuidadoporId(idRegistroCuidado);

        //ENTONCES
        assertNotNull(registrotest);
        assertEquals(registrotest.getIdRegistroCuidado(), idRegistroCuidado);
    }

    @Test
    @Order(4)
    void testActualizarRegistroCuidado() {
        //DADO
        Long idRegistroCuidado = idRegistroCuidadoLast;
        RegistroCuidado registrotest = objtest_repo.encontrarRegistroCuidadoporId(idRegistroCuidado);

        @SuppressWarnings("deprecation")
        Date fechaTest = new Date(2020, 11, 5, 16, 30, 0);

        registrotest.setFechaRegistro(fechaTest);

        //CUANDO
        int nBeforeTest = objtest_repo.findAll().size();
        objtest_repo.save(registrotest);
        int nAfterTest = objtest_repo.findAll().size();
        RegistroCuidado afterTestRegistroCuidado = objtest_repo.encontrarRegistroCuidadoporId(idRegistroCuidado);

        //ENTONCES
        assertEquals(registrotest, afterTestRegistroCuidado);
        assertEquals(nBeforeTest, nAfterTest);
        assertEquals(afterTestRegistroCuidado.getFechaRegistro(), fechaTest);
    }


    @Test
    @Order(5)
    void testObtenerRegistrosporidMascota() {
        //Dado
        Long codMascota = idMascotaAux;

        //Cuando
        List<RegistroCuidado> registros = objtest_repo.listarCuidadoMascota(codMascota);

        //Entonces
        assertNotNull(registros);
        assertTrue(registros.size() > 0);
    }


    @Test
    @Order(6)
    void testEliminarRegistroCuidado() {
        //DADO
        Long codRegistroCuidado = idRegistroCuidadoLast;

        //CUANDO
        boolean existeRegistroCuidadoBefore = objtest_repo.existsById(codRegistroCuidado);
        objtest_repo.deleteById(codRegistroCuidado);
        boolean existeRegistroCuidadoAfter = objtest_repo.existsById(codRegistroCuidado);

        //ENTONCES
        assertTrue(existeRegistroCuidadoBefore);
        assertFalse(existeRegistroCuidadoAfter);
    }

    @Test
    @Order(7)
    void testEditarRegistroCuidado() {
        //DADO
        Long idRegistroCuidado = idRegistroCuidadoLast;
        RegistroCuidado registrotest = objtest_repo.encontrarRegistroCuidadoporId(idRegistroCuidado);

        @SuppressWarnings("deprecation")
        Date nuevaFecha = new Date(2020, 11, 10, 16, 0, 0);
        registrotest.setFechaRealizado(nuevaFecha);

        //CUANDO
        int nBeforeTest = objtest_repo.findAll().size();
        objtest_repo.save(registrotest);
        int nAfterTest = objtest_repo.findAll().size();
        RegistroCuidado afterTestRegistro = objtest_repo.encontrarRegistroCuidadoporId(idRegistroCuidado);

        //ENTONCES
        assertEquals(registrotest, afterTestRegistro);
        assertEquals(nBeforeTest, nAfterTest);
        assertEquals(afterTestRegistro.getFechaRealizado(), nuevaFecha);

    }
}