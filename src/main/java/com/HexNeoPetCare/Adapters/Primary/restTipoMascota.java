package com.HexNeoPetCare.Adapters.Primary;

import com.HexNeoPetCare.Domain.TipoMascota;
import com.HexNeoPetCare.Ports.Primary.TipoMascotaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tipomascota")
public class restTipoMascota {

    @Autowired
    private TipoMascotaServicio tipoMascotaServicio;


    //REGISTRAR TIPO MASCOTA
    @PostMapping("/registrarTipoMascota")
    public void registrarTipoMascota(TipoMascota tipoMascota) {
        try {
            this.tipoMascotaServicio.registrarTipoMascota(tipoMascota);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo registrar al tipo mascota. " + e.getMessage());
        }
    }

    //LISTAR TODAS LOS TIPOS DE MASCOTA
    @GetMapping("/listarTipoMascotas")
    public List<TipoMascota> listarTipoMascotas() {
        List<TipoMascota> tipoMascotaList;

        try {
            tipoMascotaList = this.tipoMascotaServicio.listarTipoMascotas();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo registrar al tipo mascota. " + e.getMessage());
        }

        return tipoMascotaList;
    }
}
