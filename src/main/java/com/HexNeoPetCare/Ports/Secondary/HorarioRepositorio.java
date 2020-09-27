package com.HexNeoPetCare.Ports.Secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.HexNeoPetCare.Domain.Horario;

public interface HorarioRepositorio extends JpaRepository<Horario, Long> 
{

	@Query( "Select h from Horario h where h.idHorario = :cod" )
	Horario encontrarHorarioporId( @Param("cod") Long cod );
	
}
