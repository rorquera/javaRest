package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.krakedev.inventarios.entidades.Categoria;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.entidades.UnidadMedida;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProductoBDD {
	public void actualizar(Producto producto) throws KrakeDevException {
		Connection conn = null;
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE PUBLIC.PRODUCTOS");
		sb.append(" SET");
		sb.append(" NOMBRE = ?,");
		sb.append(" UDM = ?,");
		sb.append(" PRECIO_VENTA = ?,");
		sb.append(" TIENE_IVA = ?,");
		sb.append(" COSTE = ?,");
		sb.append(" CATEGORIA = ?");
		sb.append(" WHERE");
		sb.append(" CODIGO = ?");
		String sql = sb.toString();
		try {
			conn = ConexionBDD.obtenerConexion();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, producto.getNombre());
			ps.setString(2, producto.getUdm().getNombre());
			ps.setBigDecimal(3, producto.getPrecioVenta());
			ps.setBoolean(4, producto.getTieneIva());
			ps.setBigDecimal(5, producto.getCoste());
			ps.setInt(6, producto.getCategoria().getCodigo());
			ps.setInt(7, producto.getCodigo());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al actualizar el producto");
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

	public Producto buscarProducto(int identificador) throws KrakeDevException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UnidadMedida unidadMedida = null;
		Categoria categoria = null;
		Producto producto = new Producto();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append(" NOMBRE,");
		sb.append(" UDM,");
		sb.append(" CAST(PRECIO_VENTA AS DECIMAL(5, 4)),");
		sb.append(" TIENE_IVA,");
		sb.append(" CAST(COSTE AS DECIMAL(5, 4)),");
		sb.append(" CATEGORIA,");
		sb.append(" STOCK");
		sb.append(" FROM");
		sb.append(" PUBLIC.PRODUCTOS");
		sb.append(" WHERE");
		sb.append(" CODIGO = ?");
		String sql = sb.toString();
		try {
			conn = ConexionBDD.obtenerConexion();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, identificador);
			rs = ps.executeQuery();

			while (rs.next()) {
				String nombre = rs.getString("nombre");
				unidadMedida = new UnidadMedida();
				unidadMedida.setNombre(rs.getString("udm"));
				BigDecimal precioVenta = rs.getBigDecimal("precio_venta");
				Boolean tieneIva = rs.getBoolean("tiene_iva");
				BigDecimal coste = rs.getBigDecimal("coste");
				categoria = new Categoria();
				categoria.setCodigo(rs.getInt("categoria"));
				int stock = rs.getInt("stock");
				producto.setNombre(nombre);
				producto.setUdm(unidadMedida);
				producto.setPrecioVenta(precioVenta);
				producto.setTieneIva(tieneIva);
				producto.setCoste(coste);
				producto.setCategoria(categoria);
				producto.setStock(stock);
			}
		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al consultar el producto: " + e.getMessage());
		}
		return producto;
	}
}
