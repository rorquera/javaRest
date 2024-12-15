package com.krakedev.pronosticos.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.krakedev.pronosticos.entidades.Equipo;
import com.krakedev.pronosticos.excepciones.KrakeDevException;
import com.krakedev.pronosticos.utils.ConexionBDD;

public class EquipoBDD {
	public static void crear(Equipo equipo) throws KrakeDevException {
		Connection conn = null;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO public.t_equipo(");
		sb.append(" codigo, nombre)");
		sb.append(" VALUES (?, ?)");
		String sql = sb.toString();
		try {
			conn = ConexionBDD.obtenerConexion();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, equipo.getCodigo());
			ps.setString(2, equipo.getNombre());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al guardar el equipo");
		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
