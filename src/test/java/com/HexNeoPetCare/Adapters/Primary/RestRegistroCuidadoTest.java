package com.HexNeoPetCare.Adapters.Primary;

import com.HexNeoPetCare.DTOClass.RegistroCuidadoDTO;
import com.HexNeoPetCare.Domain.RegistroCuidado;
import com.HexNeoPetCare.Ports.Primary.RegistroCuidadoServicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestRegistroCuidado.class)
public class RestRegistroCuidadoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegistroCuidadoServicio registroCuidadoServicio;

    private final RegistroCuidado registroCuidado = new RegistroCuidado(new Date(), null, false, null, null);

    @Test
    public void registrarCuidado() throws Exception {
        Long idMascota = Long.valueOf(1);
        Long idCuidado = Long.valueOf(1);
        RegistroCuidadoDTO request = new RegistroCuidadoDTO();
        request.setFechaRegistro(this.registroCuidado.getFechaRegistro());
        request.setFechaRealizado(this.registroCuidado.getFechaRealizado());
        request.setStatus(this.registroCuidado.isStatus());
        request.setIdCuidado(idCuidado);
        request.setIdMascota(null);

        Mockito.when(registroCuidadoServicio
                .registrarRegistroCuidado(idMascota, idCuidado, this.registroCuidado))
                .thenReturn(this.registroCuidado);
        String uri = "/api/registroCuidado/registrarCuidado/" +idMascota.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapToJson(request))
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void mostrarRegistroCuidado() throws Exception {
        Long idRegistroCuidado = Long.valueOf(1);

        Mockito.when(registroCuidadoServicio.obtenerRegistroCuidado(idRegistroCuidado))
                .thenReturn(this.registroCuidado);
        String uri = "/api/registroCuidado/" +idRegistroCuidado.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc
                .perform(requestBuilder)
                .andExpect(status().is(200))
                .andReturn();

        String expectedJson = this.mapToJson(this.registroCuidado);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

    @Test
    public void actualizarRegistroCuidado() throws Exception {
        Long idRegistroCuidado = Long.valueOf(1);

        Mockito.when(registroCuidadoServicio.actualizarEstadoRegistroCuidado(idRegistroCuidado))
                .thenReturn(this.registroCuidado);
        String uri = "/api/registroCuidado/actualizar/" + idRegistroCuidado.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(uri)
                .accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void eliminarRegistroCuidado() throws Exception {
        Long idRegistroCuidado = Long.valueOf(1);

        Mockito.when(registroCuidadoServicio.eliminarRegistroCuidado(idRegistroCuidado)).thenReturn(idRegistroCuidado);
        String uri = "/api/registroCuidado/eliminar/" + idRegistroCuidado.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(uri)
                .accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void mostrarRegistroCuidadoListaMascota() throws Exception {
        Long idMascota = Long.valueOf(1);
        List<RegistroCuidado> registroCuidados = new ArrayList<>();


        Mockito.when(registroCuidadoServicio.listarRegistroCuidadoMascota(idMascota))
                .thenReturn(registroCuidados);
        String uri = "/api/registroCuidado/mostrarListaMascota/" + idMascota.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc
                .perform(requestBuilder)
                .andExpect(status().is(200))
                .andReturn();

        String expectedJson = this.mapToJson(registroCuidados);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);


    }

    private String mapToJson(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }
}