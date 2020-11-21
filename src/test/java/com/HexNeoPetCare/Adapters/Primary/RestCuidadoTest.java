package com.HexNeoPetCare.Adapters.Primary;

import com.HexNeoPetCare.Domain.Cuidado;
import com.HexNeoPetCare.Ports.Primary.CuidadoServicio;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestCuidado.class)
public class RestCuidadoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CuidadoServicio cuidadoServicio;

    private final Cuidado cuidado = new Cuidado("Alimentacion");

    @Test
    public void registrarCuidado() throws Exception {

        Mockito.when(cuidadoServicio.registrarCuidado(cuidado)).thenReturn(this.cuidado);
        String uri = "/api/cuidado/registrar";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapToJson(this.cuidado))
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void mostrarCuidado() throws Exception {
        Long idCuidado = Long.valueOf(1);

        Mockito.when(cuidadoServicio.obtenerCuidado(idCuidado)).thenReturn(this.cuidado);
        String uri = "/api/cuidado/" + idCuidado.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc
                .perform(requestBuilder)
                .andExpect(status().is(200))
                .andReturn();

        String expectedJson = this.mapToJson(this.cuidado);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

    @Test
    public void eliminarCuidado() throws Exception {
        Long idCuidado = Long.valueOf(1);

        Mockito.when(cuidadoServicio.eliminarCuidado(idCuidado)).thenReturn(idCuidado);
        String uri = "/api/cuidado/eliminar/" + idCuidado.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(uri);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void mostrarCuidadoLista() throws Exception {
        List<Cuidado> cuidadoList = new ArrayList<>();
        cuidadoList.add(this.cuidado);

        Mockito.when(cuidadoServicio.listarCuidados()).thenReturn(cuidadoList);
        String uri = "/api/cuidado/mostrarLista";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc
                .perform(requestBuilder)
                .andExpect(status().is(200))
                .andReturn();

        String expectedJson = this.mapToJson(cuidadoList);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }
}