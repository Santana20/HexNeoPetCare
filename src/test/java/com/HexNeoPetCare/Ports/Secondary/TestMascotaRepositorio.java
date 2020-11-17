package com.HexNeoPetCare.Ports.Secondary;

import com.HexNeoPetCare.Domain.Mascota;
import com.HexNeoPetCare.Domain.TipoMascota;
import com.HexNeoPetCare.Domain.Usuario;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestMascotaRepositorio {

    @Autowired
    private MascotaRepositorio objtest_repo;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private TipoMascotaRepositorio tipoMascotaRepositorio;

    private final Usuario usuario = new Usuario("prueba", "prueba", "prueba", "prueba@gmail.com", "123456897", "prueba", "prueba");
    private final TipoMascota tipoMascota = new TipoMascota("Perro");
    private Long idMascotaLast;
    private final String nombreMascotaLast = "Rocky";
    private Long idUsuarioAux;

    @BeforeEach
    void setup()
    {
        //CONFIG
        idUsuarioAux = usuarioRepositorio.save(usuario).getIdUsuario();
        tipoMascotaRepositorio.save(tipoMascota);
        idMascotaLast =  objtest_repo.save(new Mascota(nombreMascotaLast, 5, 10.5, usuario, tipoMascota)).getIdMascota();
    }

    @Test
    @Order(1)
    void testInsertarMascota() {

        //DADO
        Mascota mascota = new Mascota("Firulais", 5, 10.5, usuario, tipoMascota);


        //CUANDO
        int nBefore = objtest_repo.findAll().size();
        Mascota mascotaSaved = objtest_repo.save(mascota);
        int nAfter = objtest_repo.findAll().size();

        //ENTONCES
        assertNotNull(mascotaSaved);
        assertEquals(nAfter, nBefore + 1);
    }

    @Test
    @Order(2)
    void testlistarMascotas()
    {
        //DADO

        //CUANDO
        List<Mascota> mascotas = objtest_repo.findAll();

        //ENTONCES
        assertNotNull(mascotas);
        assertTrue(mascotas.size() > 0);
    }

    @Test
    @Order(3)
    void testEncontrarMascotaporId()
    {
        //DADO
        Long idMascota = idMascotaLast;

        //CUANDO
        Mascota mascotatest = objtest_repo.encontrarMascotaporId(idMascota);

        //ENTONCES
        assertNotNull(mascotatest);
        assertEquals(mascotatest.getIdMascota(), idMascota);
    }

    @Test
    @Order(4)
    void testActualizarMascota()
    {
        //DADO
        Long idMascota = idMascotaLast;
        Mascota mascotatest = objtest_repo.encontrarMascotaporId(idMascota);
        String nuevoNombre = "Tom";
        mascotatest.setNombre(nuevoNombre);

        //CUANDO
        int nBeforeTest = objtest_repo.findAll().size();
        objtest_repo.save(mascotatest);
        int nAfterTest = objtest_repo.findAll().size();
        Mascota afterTestMascota = objtest_repo.encontrarMascotaporId(idMascota);

        //ENTONCES
        assertEquals(mascotatest, afterTestMascota);
        assertEquals(nBeforeTest, nAfterTest);
        assertEquals(afterTestMascota.getNombre(), nuevoNombre);
    }


    @Test
    @Order(5)
    void testObtenerMascotaporidUsuario()
    {
        //Dado
        Long codUsuario = idUsuarioAux;

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
        Long codUsuario = idUsuarioAux;
        String nombreMascota = nombreMascotaLast;

        //Cuando
        Mascota mascota = objtest_repo.encontrarMascotaporNombreYUsuario(codUsuario, nombreMascota);

        //Entonces
        assertNotNull(mascota);
    }

    @Test
    @Order(7)
    void testEliminarMascota()
    {
        //DADO
        Long codMascota = idMascotaLast;

        //CUANDO
        boolean existeMascotaBefore = objtest_repo.existsById(codMascota);
        objtest_repo.deleteById(codMascota);
        boolean existeMascotaAfter = objtest_repo.existsById(codMascota);

        //ENTONCES
        assertTrue(existeMascotaBefore);
        assertFalse(existeMascotaAfter);
    }
}