package com.HexNeoPetCare.Ports.Primary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HexNeoPetCare.Domain.Horario;
import com.HexNeoPetCare.Domain.Veterinario;
import com.HexNeoPetCare.Ports.Secondary.HorarioRepositorio;
import com.HexNeoPetCare.Ports.Secondary.VeterinarioRepositorio;


@Service
public class HorarioServicio
{
	@Autowired
	private HorarioRepositorio RepositorioHorario;
	@Autowired
	private VeterinarioRepositorio RepositorioVeterinario;
	
	//REGISTRAR HORARIO
	public void registrarHorario(Horario horario, Long idVeterinario)  throws Exception
	{
		Veterinario v = RepositorioVeterinario.encontrarVeterinarioporId(idVeterinario);
		if ( v == null ) throw new Exception("Veterinario no encontrado.");
		horario.setVeterinario(v);
		RepositorioHorario.save(horario);
	}
	
	//OBTENER HORARIO
	public Horario obtenerHorario(Long cod) throws Exception
	{
		Horario h = RepositorioHorario.encontrarHorarioporId(cod);
		if ( h == null ) throw new Exception( "Horario no encontrado." );
		return h;
	}
	
	//ACTUALIZAR HORARIO
	public void actualizarHorario(Horario horario) throws Exception
	{
		Horario h = obtenerHorario(horario.getIdHorario());
		
		if ( horario.getDia() != null ) h.setDia(horario.getDia());
		if ( horario.getHora() != null ) h.setHora(horario.getHora());
		if ( horario.getNota() != null ) h.setNota(horario.getNota());

		RepositorioHorario.save(h);
		return;
	}
	
	//ELIMINAR HORARIO
	public void eliminarHorario(Long codigo) throws Exception
	{
		Horario h = obtenerHorario(codigo);
		
		RepositorioHorario.delete(h);
	}
	
	//LISTAR TODOS LOS HORARIOS
	public List<Horario> listarHorarios()
	{
		return RepositorioHorario.findAll();
	}
	
}
