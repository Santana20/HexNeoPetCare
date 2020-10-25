package com.HexNeoPetCare.Ports.Secondary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.HexNeoPetCare.Domain.VacunaMascota;

public interface VacunaMascotaRepositorio extends JpaRepository<VacunaMascota, Long>
{
	@Query( "Select r from VacunaMascota r where r.idVacunaMascota = :idVacunaMascota" )
	VacunaMascota encontrarVacunaMascotaporId( @Param("idVacunaMascota") Long idVacunaMascota );
	
	@Query( "Select r from VacunaMascota r where r.mascota.idMascota = :idMascota" )
	List<VacunaMascota> listarVacunasdeMascota( @Param("idMascota") Long idMascota );
}