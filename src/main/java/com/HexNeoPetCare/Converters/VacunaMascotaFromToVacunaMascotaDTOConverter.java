package com.HexNeoPetCare.Converters;

import com.HexNeoPetCare.DTOClass.VacunaMascotaDTO;
import com.HexNeoPetCare.Domain.VacunaMascota;

public class VacunaMascotaFromToVacunaMascotaDTOConverter
{
	public VacunaMascota convertToVacunaMascota(VacunaMascotaDTO request)
	{
		VacunaMascota vm = new VacunaMascota();
		
		vm.setFechaRegistro(request.getFechaRegistro());
		vm.setFechaVacunaRealizada(request.getFechaVacunaRealizada());
		vm.setStatus(request.isStatus());
		
		return vm;
	}
}
