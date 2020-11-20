package com.HexNeoPetCare.Adapters.Primary;

import com.HexNeoPetCare.Domain.TipoMascota;
import com.HexNeoPetCare.Ports.Primary.TipoMascotaServicio;
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

@WebMvcTest(restTipoMascota.class)
public class restTipoMascotaTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TipoMascotaServicio tipoMascotaServicio;

    private final TipoMascota tipoMascota = new TipoMascota("Perro");

    @Test
    public void registrarTipoMascota() throws Exception {

        Mockito.when(tipoMascotaServicio
                .registrarTipoMascota(tipoMascota))
                .thenReturn(this.tipoMascota);
        String uri = "/api/tipomascota/registrarTipoMascota";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapToJson(this.tipoMascota))
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void listarTipoMascotas() throws Exception {
        List<TipoMascota> tipoMascotaList = new ArrayList<>();
        tipoMascotaList.add(this.tipoMascota);
        tipoMascotaList.add(new TipoMascota("Gato"));

        Mockito.when(tipoMascotaServicio.listarTipoMascotas())
                .thenReturn(tipoMascotaList);
        String uri = "/api/tipomascota/listarTipoMascotas";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc
                .perform(requestBuilder)
                .andExpect(status().is(200))
                .andReturn();

        String expectedJson = this.mapToJson(tipoMascotaList);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);

    }

    private String mapToJson(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }
}