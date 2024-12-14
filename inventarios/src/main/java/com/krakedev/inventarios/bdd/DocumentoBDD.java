package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.krakedev.inventarios.entidades.TipoDocumento;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class DocumentoBDD {
	public void crear(TipoDocumento tipoDocumento) throws KrakeDevException {
		Connection conn = null;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO public.tipo_documentos(");
		sb.append(" codigo, descripcion)");
		sb.append(" VALUES (?, ?)");
		String sql = sb.toString();
		try {
			conn = ConexionBDD.obtenerConexion();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, tipoDocumento.getCodigo());
			ps.setString(2, tipoDocumento.getDescripcion());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al guardar el tipo de documento");
		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

	}
}
