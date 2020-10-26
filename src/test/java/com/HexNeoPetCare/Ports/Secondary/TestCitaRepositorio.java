package com.HexNeoPetCare.Ports.Secondary;

import com.HexNeoPetCare.Domain.Cita;
import com.HexNeoPetCare.Domain.TipoMascota;
import com.HexNeoPetCare.Domain.Usuario;
import com.HexNeoPetCare.Domain.Mascota;
import com.HexNeoPetCare.Domain.Veterinario;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestCitaRepositorio {

    @Autowired
    private CitaRepositorio objtest_repo;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private TipoMascotaRepositorio tipoMascotaRepositorio;
    
    @Autowired
    private MascotaRepositorio mascotaRepositorio;
    
    @Autowired
    private VeterinarioRepositorio veterinarioRepositorio;

    private final Usuario usuario = new Usuario("prueba", "prueba", "prueba", "prueba@gmail.com", "123456897", "prueba", "prueba");
    private final TipoMascota tipoMascota = new TipoMascota("Perro");
    private final Mascota mascota = new Mascota("Rocky", 5, 10.5, usuario, tipoMascota);
    private final Veterinario veterinario = new Veterinario("prueba", "prueba", "prueba", "prueba@gmail.com", "123456897", "prueba", "prueba");
    
    private Long idMascotaAux;
    private Long idVeterinarioAux;
    
    private Long idCitaLast;
    
    
	@SuppressWarnings("deprecation")
	private Date fecha = new Date( 2020,  10,  30,  16,  0,  0);
	
    
    @BeforeEach
    void setup()
    {
        //CONFIG¨
    	
        usuarioRepositorio.save(usuario);
    	tipoMascotaRepositorio.save(tipoMascota);
        idMascotaAux = mascotaRepositorio.save(mascota).getIdMascota();
        idVeterinarioAux = veterinarioRepositorio.save(veterinario).getIdVeterinario();
        
        idCitaLast =  objtest_repo.save(new Cita(fecha, false, veterinario, mascota)).getIdCita();
    
    }

    @Test
    @Order(1)
    void testInsertarCita() {

        //DADO
        @SuppressWarnings("deprecation")
		Date fecha = new Date( 2020,  11,  5,  16,  0,  0);
    	Cita cita= new Cita(fecha, false, veterinario, mascota);


        //CUANDO
        int nBefore = objtest_repo.findAll().size();
        Cita citaSaved = objtest_repo.save(cita);
        int nAfter = objtest_repo.findAll().size();

        //ENTONCES
        assertNotNull(citaSaved);
        assertEquals(nAfter, nBefore + 1);
    }

    @Test
    @Order(2)
    void testlistarCitas()
    {
        //DADO

        //CUANDO
        List<Cita> citas = objtest_repo.findAll();

        //ENTONCES
        assertNotNull(citas);
        assertTrue(citas.size() > 0);
    }

    @Test
    @Order(3)
    void testEncontrarCitaporId()
    {
        //DADO
        Long idCita = idCitaLast;

        //CUANDO
        Cita citatest = objtest_repo.encontrarCitaporId(idCita);

        //ENTONCES
        assertNotNull(citatest);
        assertEquals(citatest.getIdCita(), idCita);
    }

    @Test
    @Order(4)
    void testActualizarCita()
    {
        //DADO
        Long idCita = idCitaLast;
        Cita citatest = objtest_repo.encontrarCitaporId(idCita);
        
        @SuppressWarnings("deprecation")
		Date nuevaFecha= new Date( 2020,  11,  10,  16,  0,  0);
        citatest.setFecha(nuevaFecha);

        //CUANDO
        int nBeforeTest = objtest_repo.findAll().size();
        objtest_repo.save(citatest);
        int nAfterTest = objtest_repo.findAll().size();
        Cita afterTestCita = objtest_repo.encontrarCitaporId(idCita);

        //ENTONCES
        assertEquals(citatest, afterTestCita);
        assertEquals(nBeforeTest, nAfterTest);
        assertEquals(afterTestCita.getFecha(), nuevaFecha);
    }

    @Test
    @Order(5)
    void cambiarEstadoCita()
    {
    	//DADO
    	 Long idCita = idCitaLast;
    	 Cita citatest = objtest_repo.encontrarCitaporId(idCita);
         
         boolean nuevoEstado= true ;
         
         citatest.setEstado(nuevoEstado);

         //CUANDO
         int nBeforeTest = objtest_repo.findAll().size();
         objtest_repo.save(citatest);
         int nAfterTest = objtest_repo.findAll().size();
         Cita afterTestCita = objtest_repo.encontrarCitaporId(idCita);

         //ENTONCES
         assertEquals(citatest, afterTestCita);
         assertEquals(nBeforeTest, nAfterTest);
         assertEquals(afterTestCita.getEstado(), nuevoEstado);
    }

    @Test
    @Order(6)
    void testEliminarCita()
    {
        //DADO
        Long idCita = idCitaLast;

        //CUANDO
        boolean existeCitaBefore = objtest_repo.existsById(idCita);
        objtest_repo.deleteById(idCita);
        boolean existeCitaAfter = objtest_repo.existsById(idCita);

        //ENTONCES
        assertTrue(existeCitaBefore);
        assertFalse(existeCitaAfter);
    }
    
    @Test
    @Order(7)
    void testObtenerCitaporidVeterinario()
    {
        //Dado
        Long codVeterinario = idVeterinarioAux;

        //Cuando
        List<Cita> citas= objtest_repo.obtenerCitaporidVeterinario(codVeterinario);

        //Entonces
        assertNotNull(citas);
        assertTrue(citas.size() > 0);
    }
    
    @Test
    @Order(8)
    void testObtenerCitaporidMascota()
    {
        //Dado
        Long codMascota= idMascotaAux;

        //Cuando
        List<Cita> citas = objtest_repo.obtenerCitaporidMascota(codMascota);

        //Entonces
        assertNotNull(citas);
        assertTrue(citas.size() > 0);
    }
}