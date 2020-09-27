package com.HexNeoPetCare.Ports.Secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.HexNeoPetCare.Domain.TipoMascota;

public interface TipoMascotaRepositorio extends JpaRepository<TipoMascota, Long>
{
	@Query( "Select v from TipoMascota v where v.idTipo = :idTipo" )
	TipoMascota encontrarTipoMascotaporId( @Param("idTipo") Long idTipo );
}
