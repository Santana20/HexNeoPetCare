package com.HexNeoPetCare.Adapters.Primary;

import com.HexNeoPetCare.DTOClass.VacunaMascotaDTO;
import com.HexNeoPetCare.Domain.Mascota;
import com.HexNeoPetCare.Domain.VacunaMascota;
import com.HexNeoPetCare.Ports.Primary.VacunaMascotaServicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestVacunaMascota.class)
public class RestVacunaMascotaTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VacunaMascotaServicio servicioVacunaMascota;

    private final VacunaMascota vacunaMascota = new VacunaMascota(new Date(), null, false, null, null);

    @Test
    public void registrarVacunaMascota() throws Exception {
        Long idMascota = Long.valueOf(1);
        Long idVacuna = Long.valueOf(1);

        VacunaMascotaDTO requestvm = new VacunaMascotaDTO();
        requestvm.setFechaRegistro(this.vacunaMascota.getFechaRegistro());
        requestvm.setFechaVacunaRealizada(this.vacunaMascota.getFechaVacunaRealizada());
        requestvm.setStatus(this.vacunaMascota.isStatus());
        requestvm.setIdVacuna(idVacuna);
        requestvm.setIdMascota(null);

        Mockito.when(servicioVacunaMascota.registrarVacunaMascota(Mockito.anyLong(), Mockito.anyLong(),
                Mockito.any(VacunaMascota.class)))
                .thenReturn(this.vacunaMascota);
        String uri = "/api/vacunamascota/registrarVacunaMascota/" + idMascota.toString();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapToJson(requestvm))
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }

    @Test
    public void actualizarEstadoVacunaMascota() {
    }

    @Test
    public void listarVacunasdeMascota() {
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(object);
    }
}