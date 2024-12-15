package com.krakedev.pronosticos.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.krakedev.pronosticos.entidades.Equipo;
import com.krakedev.pronosticos.entidades.Partido;
import com.krakedev.pronosticos.entidades.Pronostico;
import com.krakedev.pronosticos.entidades.Usuario;
import com.krakedev.pronosticos.excepciones.KrakeDevException;
import com.krakedev.pronosticos.utils.ConexionBDD;

public class PronosticoBDD {
	public List<Pronostico> buscar(String identificador) throws KrakeDevException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Usuario usuario = null;
		Partido partido = null;
		Equipo equipoA = null;
		Equipo equipoB = null;
		Pronostico pronostico = null;
		StringBuilder sb = new StringBuilder();
		sb.append(
				"SELECT p.id, u.nombre, u.cedula, p.partido, eqa.nombre as nombreqa, eqb.nombre as nombreqb, p.marcador_eq_a, p.marcador_eq_b");
		sb.append(" FROM public.t_pronostico p");
		sb.append(" join public.t_usuario u on p.usuario=u.cedula");
		sb.append(" join public.t_partido pa on pa.id = p.partido");
		sb.append(" join public.t_equipo eqa on eqa.codigo=p.equipo_a");
		sb.append(" join public.t_equipo eqb on eqb.codigo=p.equipo_b");
		sb.append(" WHERE p.usuario=?");
		String sql = sb.toString();
		List<Pronostico> pronosticos = new ArrayList<Pronostico>();
		try {
			conn = ConexionBDD.obtenerConexion();
			ps = conn.prepareStatement(sql);
			ps.setString(1, identificador);
			rs = ps.executeQuery();

			while (rs.next()) {
				pronostico = new Pronostico();
				pronostico.setId(rs.getInt("id"));
				usuario = new Usuario();
				usuario.setNombre(rs.getString("nombre"));
				usuario.setCedula(rs.getString("cedula"));
				partido = new Partido();
				partido.setId(rs.getInt("partido"));
				pronostico.setPartido(partido);
				equipoA = new Equipo();
				equipoB = new Equipo();
				equipoA.setNombre(rs.getString("nombreqa"));
				equipoB.setNombre(rs.getString("nombreqb"));
				pronostico.setEquipoA(equipoA);
				pronostico.setEquipoB(equipoB);
				pronostico.setMarcadorEqA(rs.getInt("marcador_eq_a"));
				pronostico.setMarcadorEqB(rs.getInt("marcador_eq_b"));
				pronosticos.add(pronostico);
			}
		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al consultar el pedido: " + e.getMessage());
		}
		return pronosticos;
	}
}
