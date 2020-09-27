package com.HexNeoPetCare.Ports.Secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.HexNeoPetCare.Domain.Cuidado;

public interface CuidadoRepositorio extends JpaRepository<Cuidado, Long> 
{

	@Query( "Select c from Cuidado c where c.idCuidado = :cod" )
	Cuidado encontrarCuidadoporId( @Param("cod") Long cod );
	
}

