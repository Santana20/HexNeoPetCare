package com.HexNeoPetCare.Ports.Primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HexNeoPetCare.Domain.TipoMascota;
import com.HexNeoPetCare.Ports.Secondary.TipoMascotaRepositorio;


@Service
public class TipoMascotaServicio
{
	@Autowired
	private TipoMascotaRepositorio RepositorioTipoMascota;
	
	//OBTENER TIPO MASCOTA
	public TipoMascota obtenerTipoMascota(Long cod) throws Exception
	{
		TipoMascota tm = RepositorioTipoMascota.encontrarTipoMascotaporId(cod);
		if (tm == null) throw new Exception("Tipo de mascota no encontrado.");
		
		return tm;
	}
}
