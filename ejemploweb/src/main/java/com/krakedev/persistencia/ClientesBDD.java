package com.krakedev.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.krakedev.entidades.Cliente;
import com.krakedev.excepciones.KrakeDevException;
import com.krakedev.utils.ConexionBDD;

public class ClientesBDD {
	public void insertar(Cliente cliente) throws KrakeDevException {
		Connection conn = null;
		String sql = "INSERT INTO public.clientes(cedula, nombre, numerohijos) VALUES (?, ?, ?)";
		try {
			conn = ConexionBDD.obtenerConexion();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cliente.getCedula());
			ps.setString(2, cliente.getNombre());
			ps.setInt(3, cliente.getNumeroHijos());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al insertar el cliente");
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

	public void actualizar(Cliente cliente) throws KrakeDevException {
		Connection conn = null;
		String sql = "UPDATE public.clientes SET nombre=?, numerohijos=? WHERE cedula=?";
		try {
			conn = ConexionBDD.obtenerConexion();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cliente.getNombre());
			ps.setInt(2, cliente.getNumeroHijos());
			ps.setString(3, cliente.getCedula());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al actualizar el cliente");
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

	public List<Cliente> recuperarTodos() throws KrakeDevException {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cliente cliente = null;
		String sql = "SELECT cedula, nombre, numerohijos FROM public.clientes";
		try {
			conn = ConexionBDD.obtenerConexion();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String cedula = rs.getString("cedula");
				String nombre = rs.getString("nombre");
				int numeroHijos = rs.getInt("numerohijos");
				cliente = new Cliente(cedula, nombre, numeroHijos);
				clientes.add(cliente);
			}
		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al consultar. Detalle: " + e.getMessage());
		}
		return clientes;
	}

	public Cliente buscarPorPk(String cedula) throws KrakeDevException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cliente cliente = null;
		String sql = "SELECT cedula, nombre, numerohijos FROM public.clientes WHERE cedula=?";
		try {
			conn = ConexionBDD.obtenerConexion();
			ps = conn.prepareStatement(sql);
			ps.setString(1, cedula);
			rs = ps.executeQuery();

			if (rs.next()) {
				String nombre = rs.getString("nombre");
				int numeroHijos = rs.getInt("numerohijos");
				cliente = new Cliente(cedula, nombre, numeroHijos);
			}
		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al consultar. Detalle: " + e.getMessage());
		}
		return cliente;
	}

	public List<Cliente> buscarPorHijos(int numeroHijos) throws KrakeDevException {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cliente cliente = null;
		String sql = "SELECT cedula, nombre, numerohijos FROM public.clientes WHERE numerohijos >= ?";
		try {
			conn = ConexionBDD.obtenerConexion();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, numeroHijos);
			rs = ps.executeQuery();

			while (rs.next()) {
				String cedula = rs.getString("cedula");
				String nombre = rs.getString("nombre");
				int numHijos = rs.getInt("numerohijos");
				cliente = new Cliente(cedula, nombre, numHijos);
				clientes.add(cliente);
			}
		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al consultar. Detalle: " + e.getMessage());
		}
		return clientes;
	}
}
