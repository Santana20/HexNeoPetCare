package com.HexNeoPetCare.Ports.Secondary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.HexNeoPetCare.Domain.RegistroCuidado;

public interface RegistroCuidadoRepositorio extends JpaRepository<RegistroCuidado, Long> 
{
	@Query( "Select r from RegistroCuidado r where r.idRegistroCuidado = :idRegistroCuidado" )
	RegistroCuidado encontrarRegistroCuidadoporId( @Param("idRegistroCuidado") Long idRegistroCuidado );

	@Query( "Select r from RegistroCuidado r where r.mascota.idMascota = :idMascota" )
	List<RegistroCuidado> listarCuidadoMascota( @Param("idMascota") Long idMascota );
}
