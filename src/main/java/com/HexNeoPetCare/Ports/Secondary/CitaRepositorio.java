package com.HexNeoPetCare.Ports.Secondary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.HexNeoPetCare.Domain.Cita;

public interface CitaRepositorio extends JpaRepository<Cita, Long> 
{

	@Query( "Select c from Cita c where c.idCita = :cod" )
	Cita encontrarCitaporId( @Param("cod") Long cod );
	
	@Query( "Select c from Cita c where c.mascota.idMascota = :idMascota" )
	List<Cita> obtenerCitaporidMascota(@Param("idMascota") Long idMascota);
	
	@Query( "Select c from Cita c where c.veterinario.idVeterinario = :idVeterinario" )
	List<Cita> obtenerCitaporidVeterinario(@Param("idVeterinario") Long idVeterinario);
	
}

