package com.krakedev.evaluacion.persistencia;

import java.sql.Connection;

import com.krakedev.evaluacion.excepciones.KrakeException;
import com.krakedev.evaluacion.utils.ConexionBDD;

public class TestConexionBDD {

	public void probarConexion() {
		try {
			Connection con = ConexionBDD.obtenerConexion();
			if (con != null) {
				System.out.println("Conexión exitosa");
			} else {
				System.out.println("Error al obtener Conexión");
			}
		} catch (KrakeException e) {
			e.printStackTrace();
		}

	}
}
