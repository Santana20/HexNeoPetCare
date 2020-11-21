package com.HexNeoPetCare.Adapters.Primary;

import com.HexNeoPetCare.Converters.MascotaFromToMascotaDTOConverter;
import com.HexNeoPetCare.DTOClass.MascotaDTO;
import com.HexNeoPetCare.Domain.Mascota;
import com.HexNeoPetCare.Ports.Primary.MascotaServicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value=RestMascota.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestMascotaTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MascotaServicio servicioMascota;

    private final Mascota mascota = new Mascota("Rocky", 8, 5.6, null, null);

    @Test
    @Order(1)
    public void registrarMascota() throws Exception {
        Long codUsuario = Long.valueOf(1);
        Long idtipoMascota = Long.valueOf(1);

        MascotaDTO inputmascota = new MascotaDTO();
        inputmascota.setNombre(this.mascota.getNombre());
        inputmascota.setEdad(this.mascota.getEdad());
        inputmascota.setPeso(this.mascota.getPeso());
        inputmascota.setIdtipomascota(idtipoMascota);

        Mockito.when(servicioMascota.registrarMascota(Mockito.anyLong(), Mockito.anyLong(),
                Mockito.any(Mascota.class)))
                .thenReturn(this.mascota);
        String uri = "/api/mascota/registrarMascota/" + codUsuario.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapToJson(inputmascota))
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void registrarMascotaConDatosNulos() throws Exception {
        Long codUsuario = Long.valueOf(1);
        Long idtipoMascota = Long.valueOf(1);

        MascotaDTO inputmascota = new MascotaDTO();
        inputmascota.setIdtipomascota(idtipoMascota);

        Mockito.when(servicioMascota
                .registrarMascota(Mockito.anyLong(), Mockito.anyLong(),
                        Mockito.any(Mascota.class)))
                .thenThrow(new Exception("No se completo todos los datos o son ivalidos."));

        String uri = "/api/mascota/registrarMascota/" + codUsuario.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapToJson(inputmascota))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc
                .perform(requestBuilder)
                .andExpect(status().is(400))
                .andReturn();
        String expectedMessage = "No se pudo registrar a la mascota. No se completo todos los datos o son ivalidos.";
        String resultMessage = result.getResponse().getErrorMessage();

        assertNotNull(resultMessage);
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @Order(2)
    public void actualizarMascota() throws Exception {
        Long idMascota = Long.valueOf(1);
        MascotaDTO inputmascota = new MascotaDTO();
        inputmascota.setNombre("Firulais");
        inputmascota.setEdad(8);
        inputmascota.setPeso(5.6);

        Mockito.when(servicioMascota.actualizarMascota(Mockito.anyLong(), Mockito.anyLong(),
                Mockito.any(Mascota.class)))
                .thenReturn(this.mascota);
        String uri = "/api/mascota/actualizarMascota/" + idMascota.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(uri)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapToJson(inputmascota))
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void actualizarMascotaConNombreRepetido() throws Exception {
        Long idMascota = Long.valueOf(1);
        Long idTipoMascota = Long.valueOf(1);
        MascotaDTO inputmascota = new MascotaDTO();
        inputmascota.setIdtipomascota(idTipoMascota);

        Mockito.when(servicioMascota.actualizarMascota(Mockito.anyLong(), Mockito.anyLong(), Mockito.any(Mascota.class)))
                .thenThrow(new Exception("Una de sus mascotas ya tiene ese nombre."));

        String uri = "/api/mascota/actualizarMascota/" + idMascota.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(uri)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapToJson(inputmascota))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc
                .perform(requestBuilder)
                .andExpect(status().is(400))
                .andReturn();

        String expectedMessage = "Una de sus mascotas ya tiene ese nombre.";
        String resultMessage = result.getResponse().getErrorMessage();

        assertNotNull(resultMessage);
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @Order(3)
    public void eliminarMascota() throws Exception {
        Long codMascota = Long.valueOf(1);

        Mockito.when(servicioMascota.eliminarMascota(Mockito.anyLong()))
                .thenReturn(codMascota);
        String uri = "/api/mascota/eliminarMascota/" + codMascota.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(uri);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void noEncontrarMascotaCuandoQuieroEliminarlo() throws Exception {
        Long codMascota = Long.valueOf(1);

        Mockito.when(servicioMascota.eliminarMascota(codMascota))
                .thenThrow(new Exception("Mascota no encontrada."));
        String uri = "/api/mascota/eliminarMascota/" + codMascota.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(uri);

        MvcResult result = this.mockMvc.perform(requestBuilder)
                .andExpect(status().is(400))
                .andReturn();

        String expectedMessage = "No se pudo eliminar a la mascota.";
        String resultMessage = result.getResponse().getErrorMessage();

        assertNotNull(resultMessage);
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @Order(4)
    public void listarMascotas() throws Exception {
        List<Mascota> listaMascota = new ArrayList<>();
        listaMascota.add(this.mascota);
        listaMascota.add(new Mascota("Firulais", 4, 6.2, null, null));

        Mockito.when(servicioMascota.listarMascotas()).thenReturn(listaMascota);
        String uri = "/api/mascota/listarMascotas";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc
                .perform(requestBuilder)
                .andExpect(status().is(200))
                .andReturn();

        String expectedJson = this.mapToJson(listaMascota);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

    @Test
    @Order(5)
    public void listarMascotasporUsuario() throws Exception {
        Long idUsuario = Long.valueOf(1);

        List<Mascota> listaMascota = new ArrayList<>();
        listaMascota.add(this.mascota);
        listaMascota.add(new Mascota("Firulais", 4, 6.2, null, null));

        Mockito.when(servicioMascota.listarMascotasporUsuario(Mockito.anyLong())).thenReturn(listaMascota);
        String uri = "/api/mascota/listarMascotasporUsuario/" + idUsuario.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc
                .perform(requestBuilder)
                .andExpect(status().is(200))
                .andReturn();

        String expectedJson = this.mapToJson(listaMascota);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }
}