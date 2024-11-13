package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.krakedev.inventarios.entidades.CabeceraVenta;
import com.krakedev.inventarios.entidades.DetalleVenta;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class VentasBDD {
	public void crearVenta(CabeceraVenta cabeceraVenta) throws KrakeDevException {
		Connection conn = null;
		ResultSet rsClaves = null;
		int codigoCabecera = 0;

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO");
		sb.append(" PUBLIC.CABECERA_VENTAS (FECHA, TOTAL_SIN_IVA, IVA, TOTAL)");
		sb.append(" VALUES");
		sb.append(" (?, ?, ?, ?)");

		String sql = sb.toString();

		try {
			conn = ConexionBDD.obtenerConexion();
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			Date fechaActual = new Date();
			java.sql.Date fechaSQL = new java.sql.Date(fechaActual.getTime());

			ps.setDate(1, fechaSQL);
			ps.setBigDecimal(2, new BigDecimal(0));
			ps.setBigDecimal(3, new BigDecimal(0));
			ps.setBigDecimal(4, new BigDecimal(0));

			ps.executeUpdate();

			rsClaves = ps.getGeneratedKeys();
			if (rsClaves.next()) {
				codigoCabecera = rsClaves.getInt(1);
			}

			System.out.println("CODIGO GENERADO >>>> " + codigoCabecera);

			List<DetalleVenta> detalles = cabeceraVenta.getDetalleVentas();
			guardarDetalle(detalles, conn, codigoCabecera);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al guardar la venta");
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

	private void guardarDetalle(List<DetalleVenta> detalles, Connection conn, int codigoCabecera)
			throws SQLException, KrakeDevException {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO");
		sb.append(" PUBLIC.DETALLE_VENTAS (");
		sb.append(" CABECERA_VENTAS,");
		sb.append(" PRODUCTO,");
		sb.append(" CANTIDAD,");
		sb.append(" PRECIO_VENTA,");
		sb.append(" SUBTOTAL,");
		sb.append(" SUBTOTAL_CON_IVA");
		sb.append(" )");
		sb.append(" VALUES");
		sb.append(" (?, ?, ?, ?, ?, ?)");

		String sql = sb.toString();

		PreparedStatement psDetalle = conn.prepareStatement(sql);

		for (DetalleVenta detalle : detalles) {
			psDetalle.setInt(1, codigoCabecera);
			psDetalle.setInt(2, detalle.getProducto().getCodigo());
			psDetalle.setInt(3, detalle.getCantidad());
			BigDecimal precioVenta = detalle.getProducto().getPrecioVenta();
			psDetalle.setBigDecimal(4, precioVenta);
			BigDecimal cantidadSolicitada = new BigDecimal(detalle.getCantidad());
			BigDecimal subTotal = precioVenta.multiply(cantidadSolicitada);
			psDetalle.setBigDecimal(5, subTotal);
			BigDecimal subtotalConIva;
			if (detalle.getProducto().getTieneIva()) {
				subtotalConIva = subTotal.multiply(new BigDecimal(1.12));
			} else {
				subtotalConIva = subTotal;
			}
			psDetalle.setBigDecimal(6, subtotalConIva);
			psDetalle.executeUpdate();

			// ACTUALIZAR LA CABECERA
			actualizarCabecera(detalles, conn, codigoCabecera);

			// GUARDA EL HISTORIAL DE STOCK
			generarHistorialStock(detalle);
		}
	}

	private void actualizarCabecera(List<DetalleVenta> detalles, Connection conn, int codigoCabecera)
			throws KrakeDevException {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE PUBLIC.CABECERA_VENTAS");
		sb.append(" SET");
		sb.append(" TOTAL_SIN_IVA = ?,");
		sb.append(" IVA = ?,");
		sb.append(" TOTAL = ?");
		sb.append(" WHERE");
		sb.append(" CODIGO = ?");
		String sql = sb.toString();
		try {
			BigDecimal totalSinIva = new BigDecimal(0);
			BigDecimal iva = new BigDecimal(0);
			BigDecimal total = new BigDecimal(0);

			for (DetalleVenta detalleVenta : detalles) {
				if (detalleVenta.getProducto().getTieneIva()) {
					iva = iva.add(new BigDecimal(1.12));
				} else {
					totalSinIva = totalSinIva.add(detalleVenta.getPrecioVenta());
				}
			}
			total = totalSinIva.add(iva);

			conn = ConexionBDD.obtenerConexion();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBigDecimal(1, totalSinIva);
			ps.setBigDecimal(2, iva);
			ps.setBigDecimal(3, total);
			ps.setInt(4, codigoCabecera);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al actualizar la cabecera de venta");
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

	private void generarHistorialStock(DetalleVenta detalleVenta) throws KrakeDevException {
		Connection conn = null;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO");
		sb.append(" PUBLIC.HISTORIAL_STOCK (FECHA, REFERENCIA, PRODUCTO, CANTIDAD)");
		sb.append(" VALUES");
		sb.append(" (?, ?, ?, ?)");
		String sql = sb.toString();
		try {
			conn = ConexionBDD.obtenerConexion();
			PreparedStatement ps = conn.prepareStatement(sql);
			Date fechaActual = new Date();
			Timestamp timestamp = new Timestamp(fechaActual.getTime());

			ps.setTimestamp(1, timestamp);
			ps.setString(2, "Venta " + detalleVenta.getProducto().getCodigo());
			ps.setInt(3, detalleVenta.getProducto().getCodigo());
			ps.setInt(4, detalleVenta.getCantidad() * (-1));
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al guardar el historial de stock");
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
