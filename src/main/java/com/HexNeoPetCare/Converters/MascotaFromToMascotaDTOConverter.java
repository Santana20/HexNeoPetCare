package com.HexNeoPetCare.Converters;

import com.HexNeoPetCare.DTOClass.MascotaDTO;
import com.HexNeoPetCare.Domain.Mascota;

public class MascotaFromToMascotaDTOConverter
{
	public Mascota convertToMascota(MascotaDTO request)
	{
		Mascota m = new Mascota();
		
		m.setNombre(request.getNombre());
		m.setEdad(request.getEdad());
		m.setPeso(request.getPeso());
		
		return m;
	}
}
