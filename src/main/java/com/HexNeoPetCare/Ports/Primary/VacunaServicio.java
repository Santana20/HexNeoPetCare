package com.HexNeoPetCare.Ports.Primary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HexNeoPetCare.Domain.TipoMascota;
import com.HexNeoPetCare.Domain.Vacuna;
import com.HexNeoPetCare.Ports.Secondary.VacunaRepositorio;


@Service
public class VacunaServicio
{
	@Autowired
	private VacunaRepositorio RepositorioVacuna;
	
	@Autowired
	private TipoMascotaServicio servicioTipoMascota;
	
	//REGISTRO DE VACUNA
	public Vacuna registrarVacuna(Long idTipoMascota, Vacuna vacuna) throws Exception
	{
		TipoMascota tm = servicioTipoMascota.obtenerTipoMascotaPorId(idTipoMascota);
		//if (vacuna.getNombreVacuna() == null) throw new Exception("si se agarra el nombre vacuna"+vacuna.getNombreVacuna());
		vacuna.setTipomascota(tm);

		return RepositorioVacuna.save(vacuna);
	}
	
	//OBTENER VACUNA
	public Vacuna obtenerVacuna(Long cod) throws Exception
	{
		Vacuna v = RepositorioVacuna.encontrarVacunaporId(cod);
		if ( v == null ) throw new Exception( "Vacuna no encontrada." );
		return v;
	}
	
	//ELIMINAR VACUNA
	public Long eliminarVacuna(Long idVacuna) throws Exception
	{
		Vacuna c = obtenerVacuna(idVacuna);
		
		RepositorioVacuna.delete(c);
		return c.getIdVacuna();
	}
	
	//LISTAR TODAS LOS VACUNA
	public List<Vacuna> listarVacunas()
	{
		return RepositorioVacuna.findAll();
	}
	
	//LISTAR VACUNAS POR TIPOMASCOTA
	public List<Vacuna> listarVacunaporTipo(Long idTipoMascota)
	{
		return RepositorioVacuna.listarVacunaporTipo(idTipoMascota);
	}
}
