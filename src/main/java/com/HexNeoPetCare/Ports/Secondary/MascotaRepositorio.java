package com.HexNeoPetCare.Ports.Secondary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.HexNeoPetCare.Domain.Mascota;

public interface MascotaRepositorio extends JpaRepository<Mascota, Long> 
{
	@Query( "Select m from Mascota m where m.idMascota = :cod" )
	Mascota encontrarMascotaporId( @Param("cod") Long cod );
	
	@Query( "Select m from Mascota m where m.usuario.idUsuario = :idUsuario" )
	List<Mascota> obtenerMascotaporidUsuario(@Param("idUsuario") Long idUsuario);
}

