package com.HexNeoPetCare.Converters;

import com.HexNeoPetCare.DTOClass.RegistroCuidadoDTO;
import com.HexNeoPetCare.Domain.RegistroCuidado;

public class RegistroCuidadoFromToRegistroCuidadoDTOConverter
{
	public RegistroCuidado converterToRegistroCuidado(RegistroCuidadoDTO request)
	{
		RegistroCuidado rc = new RegistroCuidado();
		
		rc.setFechaRegistro(request.getFechaRegistro());
		rc.setFechaRealizado(request.getFechaRealizado());
		rc.setStatus(request.isStatus());
		return rc;
	}
}
