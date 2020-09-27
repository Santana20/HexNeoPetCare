package com.HexNeoPetCare.Ports.Secondary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.HexNeoPetCare.Domain.Vacuna;

public interface VacunaRepositorio extends JpaRepository<Vacuna, Long> 
{
	@Query( "Select v from Vacuna v where v.idVacuna = :idVacuna" )
	Vacuna encontrarVacunaporId( @Param("idVacuna") Long idVacuna );
	
	@Query( "Select v from Vacuna v where v.tipomascota.idTipo = :idTipoMascota" )
	List<Vacuna> listarVacunaporTipo( @Param("idTipoMascota") Long idTipoMascota );
}
