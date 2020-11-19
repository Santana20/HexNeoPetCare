package com.HexNeoPetCare.Adapters.Primary;

import com.HexNeoPetCare.Domain.Usuario;
import com.HexNeoPetCare.Ports.Primary.UsuarioServicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestUsuario.class)
public class RestUsuarioTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsuarioServicio usuarioServicio;

    @Test
    public void registrarUsuario() throws Exception {
        Usuario newUsuario = new Usuario("Sebastian",
                "Contreras",
                "Jr. Ayacucho 458",
                "sebastian@prueba.com",
                "999999999",
                "sebastian",
                "123456");

        Mockito.when(usuarioServicio.registrarUsuario(newUsuario)).thenReturn(newUsuario);
        String uri = "/api/usuario/registrarUsuario";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapToJson(newUsuario))
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void obtenerUsuario() throws Exception {
        Long codUsuario = Long.valueOf(1);
        Usuario usuario = new Usuario("Sebastian",
                "Contreras",
                "Jr. Ayacucho 458",
                "sebastian@prueba.com",
                "999999999",
                "sebastian",
                "123456");

        Mockito.when(usuarioServicio.obtenerUsuario(codUsuario)).thenReturn(usuario);
        String uri = "/api/usuario/obtenerUsuario/" + codUsuario.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc
                .perform(requestBuilder)
                .andExpect(status().is(200))
                .andReturn();

        String expectedJson = this.mapToJson(usuario);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

    @Test
    public void actualizarUsuario() throws Exception {
        Long idUsuario = Long.valueOf(1);
        Usuario usuario = new Usuario("Sebastian",
                "Contreras",
                "Jr. Ayacucho 458",
                "sebastian@prueba.com",
                "999999999",
                "sebastian",
                "123456");

        Mockito.when(usuarioServicio.actualizarUsuario(idUsuario, usuario)).thenReturn(usuario);
        String uri = "/api/usuario/actualizarUsuario/" + idUsuario.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(uri)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapToJson(usuario))
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }
}