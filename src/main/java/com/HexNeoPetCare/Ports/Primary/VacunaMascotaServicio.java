package com.HexNeoPetCare.Ports.Primary;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HexNeoPetCare.Domain.Mascota;
import com.HexNeoPetCare.Domain.Vacuna;
import com.HexNeoPetCare.Domain.VacunaMascota;
import com.HexNeoPetCare.Ports.Secondary.MascotaRepositorio;
import com.HexNeoPetCare.Ports.Secondary.VacunaMascotaRepositorio;


@Service
public class VacunaMascotaServicio 
{
	@Autowired
	private VacunaMascotaRepositorio repositorioVacunaMascota;
	
	@Autowired
	private MascotaRepositorio repositorioMascota;
	
	@Autowired
	private VacunaServicio servicioVacuna;
	
	//REGISTRAR VACUNA DE UNA MASCOTA
	public VacunaMascota registrarVacunaMascota(Long idMascota, Long idVacuna, VacunaMascota vm) throws Exception
	{
		if (vm.getFechaRegistro() == null || idVacuna == null) throw new Exception("No se llenaron todo los datos.");

		Mascota m = repositorioMascota.encontrarMascotaporId(idMascota);
		if ( m == null ) throw new Exception("Mascota no encontrada.");
		Vacuna v = servicioVacuna.obtenerVacuna(idVacuna);
		
		if ( m.getTipomascota().getIdTipo() != v.getTipomascota().getIdTipo() )
			throw new Exception("El tipo de la vacuna y la mascota no son iguales");
		vm.setMascota(m);
		vm.setVacuna(v);
		return repositorioVacunaMascota.save(vm);
	}
	
	//OBTENER VACUNA DE LA MASCOTA
	private VacunaMascota obtenerVacunaMascota(Long cod) throws Exception
	{
		VacunaMascota vm = repositorioVacunaMascota.encontrarVacunaMascotaporId(cod);
		if ( vm == null ) throw new Exception( "Vacuna de la mascota no encontrada." );
		return vm;
	}
	
	//ACTUALIZAR ESTADO DE LA VACUNA DE LA MASCOTA
	public VacunaMascota actualizarEstadoVacunaMascota(Long idVacunaMascota) throws Exception
	{
		VacunaMascota vm = obtenerVacunaMascota(idVacunaMascota);
		if (vm.isStatus()) throw new Exception("Ya fue actualizado el estado de la vacuna de la mascota.");
		
		vm.setStatus(true);
		vm.setFechaVacunaRealizada(new Date());

		return repositorioVacunaMascota.save(vm);
	}
	
	//LISTAR VACUNAS DE UNA MASCOTA
	public List<VacunaMascota> listarVacunasdeMascota(Long idMascota)
	{
		return repositorioVacunaMascota.listarVacunasdeMascota(idMascota);
	}
}
