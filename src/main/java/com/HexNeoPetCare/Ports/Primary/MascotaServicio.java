package com.HexNeoPetCare.Ports.Primary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HexNeoPetCare.Domain.Mascota;
import com.HexNeoPetCare.Domain.TipoMascota;
import com.HexNeoPetCare.Domain.Usuario;
import com.HexNeoPetCare.Ports.Secondary.MascotaRepositorio;


@Service
public class MascotaServicio {
    @Autowired
    private MascotaRepositorio RepositorioMascota;

    @Autowired
    private UsuarioServicio servicioUsuario;

    @Autowired
    private TipoMascotaServicio servicioTipoMascota;

    //REGISTRAR MASCOTA
    public void registrarMascota(Long codUsuario, Long idtipomascota, Mascota m) throws Exception {
        Usuario u = servicioUsuario.obtenerUsuario(codUsuario);

        TipoMascota tm = servicioTipoMascota.obtenerTipoMascota(idtipomascota);

        if (m.getNombre() == null || idtipomascota == null || m.getEdad() < 0 || m.getPeso() < 0.0)
            throw new Exception("No se completo todos los datos o son ivalidos.");

        if (RepositorioMascota.encontrarMascotasporNombreYUsuario(u.getIdUsuario(), m.getNombre()).size() > 0)
            throw new Exception("Una de sus mascotas ya tiene ese nombre.");

        m.setUsuario(u);
        m.setTipomascota(tm);
        RepositorioMascota.save(m);
    }

    //OBTENER MASCOTA
    public Mascota obtenerMascota(Long cod) throws Exception {
        Mascota m = RepositorioMascota.encontrarMascotaporId(cod);
        if (m == null) throw new Exception("Mascota no encontrada.");
        return m;
    }

    //ACTUALIZAR MASCOTA
    public void actualizarMascota(Long idMascota, Long idTipoMascota, Mascota mascota) throws Exception {

        Mascota m = obtenerMascota(idMascota);

        if (mascota.getNombre() != null) m.setNombre(mascota.getNombre());

        if (mascota.getEdad() != 0) m.setEdad(mascota.getEdad());

        if (mascota.getPeso() != 0.0) m.setPeso(mascota.getPeso());

        if (idTipoMascota != null && idTipoMascota != m.getTipomascota().getIdTipo()) {
            TipoMascota tm = servicioTipoMascota.obtenerTipoMascota(idTipoMascota);
            m.setTipomascota(tm);
        }

        RepositorioMascota.save(m);
        return;
    }

    //ELIMINAR MASCOTA
    public void eliminarMascota(Long codigo) throws Exception {
        Mascota m = obtenerMascota(codigo);

        RepositorioMascota.delete(m);
        return;
    }

    //LISTAR TODAS LAS MASCOTAS
    public List<Mascota> listarMascotas() {
        return RepositorioMascota.findAll();
    }

    //LISTAR MASCOTAS POR USUARIO
    public List<Mascota> listarMascotasporUsuario(Long idUsuario) {
        return RepositorioMascota.obtenerMascotasporidUsuario(idUsuario);
    }
}
