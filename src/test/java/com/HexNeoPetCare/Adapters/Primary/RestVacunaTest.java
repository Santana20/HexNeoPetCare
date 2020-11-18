package com.HexNeoPetCare.Adapters.Primary;

import com.HexNeoPetCare.Domain.Mascota;
import com.HexNeoPetCare.Domain.Vacuna;
import com.HexNeoPetCare.Ports.Primary.VacunaServicio;
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

@WebMvcTest(RestVacuna.class)
public class RestVacunaTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VacunaServicio servicioVacuna;

    private final Vacuna vacuna = new Vacuna("Primera vacuna", null);

    @Test
    public void registrarVacuna() throws Exception {
        Long idTipoMascota = Long.valueOf(1);

        Mockito.when(servicioVacuna.registrarVacuna(Mockito.anyLong(),
                Mockito.any(Vacuna.class)))
                .thenReturn(this.vacuna);
        String uri = "/api/vacuna/registrarVacuna/" + idTipoMascota.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapToJson(this.vacuna))
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void eliminarVacuna() throws Exception {
        Long idVacuna = Long.valueOf(1);

        Mockito.when(servicioVacuna.eliminarVacuna(Mockito.anyLong())).thenReturn(idVacuna);

        String uri = "/api//vacuna/eliminarVacuna/" + idVacuna.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(uri);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));

    }

    @Test
    public void listarVacunas() throws Exception {
        List<Vacuna> listaVacunas = new ArrayList<>();
        listaVacunas.add(this.vacuna);
        listaVacunas.add(new Vacuna("Segunda Vacuna", null));

        Mockito.when(servicioVacuna.listarVacunas()).thenReturn(listaVacunas);
        String uri = "/api/vacuna/listarVacunas";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc
                .perform(requestBuilder)
                .andExpect(status().is(200))
                .andReturn();

        String expectedJson = this.mapToJson(listaVacunas);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

    @Test
    public void listarVacunaporTipo() throws Exception {

        Long idTipoMascota = Long.valueOf(1);
        List<Vacuna> listaVacunas = new ArrayList<>();
        listaVacunas.add(this.vacuna);
        listaVacunas.add(new Vacuna("Segunda Vacuna", null));

        Mockito.when(servicioVacuna.listarVacunaporTipo(Mockito.anyLong())).thenReturn(listaVacunas);
        String uri = "/api/vacuna/listarVacunaporTipo/" + idTipoMascota.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc
                .perform(requestBuilder)
                .andExpect(status().is(200))
                .andReturn();

        String expectedJson = this.mapToJson(listaVacunas);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }
}