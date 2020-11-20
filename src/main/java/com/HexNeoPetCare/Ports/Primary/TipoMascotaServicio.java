package com.HexNeoPetCare.Ports.Primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HexNeoPetCare.Domain.TipoMascota;
import com.HexNeoPetCare.Ports.Secondary.TipoMascotaRepositorio;

import java.util.List;


@Service
public class TipoMascotaServicio
{
	@Autowired
	private TipoMascotaRepositorio repositorioTipoMascota;

	//REGISTRAR TIPO MASCOTA
	public TipoMascota registrarTipoMascota(TipoMascota tipoMascota) throws Exception {

		if (tipoMascota.getNombreTipo() == null) throw new Exception("No se ingreso el nombre del tipo de mascota.");
		if (this.repositorioTipoMascota.encontrarTipoMascotaPorNombre(tipoMascota.getNombreTipo()) != null)
			throw new Exception("Ya existe un tipo de mascota con el mismo nombre.");

		return this.repositorioTipoMascota.save(tipoMascota);
	}

	//OBTENER TIPO MASCOTA
	public TipoMascota obtenerTipoMascotaPorId(Long cod) throws Exception
	{
		TipoMascota tm = repositorioTipoMascota.encontrarTipoMascotaporId(cod);
		if (tm == null) throw new Exception("Tipo de mascota no encontrado.");
		
		return tm;
	}

	//LISTAR TODAS LOS TIPOS DE MASCOTA
	public List<TipoMascota> listarTipoMascotas() {
		return this.repositorioTipoMascota.findAll();
	}
}
