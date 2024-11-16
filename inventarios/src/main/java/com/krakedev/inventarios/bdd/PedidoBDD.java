package com.krakedev.inventarios.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.krakedev.inventarios.entidades.CabeceraPedido;
import com.krakedev.inventarios.entidades.DetallePedido;
import com.krakedev.inventarios.entidades.EstadoPedido;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class PedidoBDD {
	public List<CabeceraPedido> buscar(String identificador) throws KrakeDevException {
		List<CabeceraPedido> pedidos = new ArrayList<CabeceraPedido>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Proveedor proveedor = null;
		CabeceraPedido pedido = null;
		DetallePedido detalle = null;
		Producto producto = null;
		EstadoPedido estadoPedido = null;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append(" CAB.NUMERO,");
		sb.append(" CAB.PROVEEDOR,");
		sb.append(" CAB.FECHA,");
		sb.append(" CAB.ESTADO,");
		sb.append(" DET.CODIGO,");
		sb.append(" DET.PRODUCTO,");
		sb.append(" DET.CANTIDAD_SOLICITADA,");
		sb.append(" CAST(DET.SUBTOTAL AS DECIMAL(6, 2)),");
		sb.append(" DET.CANTIDAD_RECIBIDA");
		sb.append(" FROM");
		sb.append(" CABECERA_PEDIDO CAB");
		sb.append(" JOIN DETALLE_PEDIDO DET ON CAB.NUMERO = DET.CABECERA_PEDIDO");
		sb.append(" WHERE");
		sb.append(" CAB.PROVEEDOR = ?");
		String sql = sb.toString();
		try {
			conn = ConexionBDD.obtenerConexion();
			ps = conn.prepareStatement(sql);
			ps.setString(1, identificador);
			rs = ps.executeQuery();

			while (rs.next()) {
				int numero = rs.getInt("numero");
				proveedor = new Proveedor();
				proveedor.setIdentificador(rs.getString("proveedor"));
				Date fecha = rs.getDate("fecha");
				String estado = rs.getString("estado");
				detalle = new DetallePedido();
				detalle.setCodigo(rs.getInt("codigo"));
				producto = new Producto();
				producto.setCodigo(rs.getInt("producto"));
				detalle.setProducto(producto);
				detalle.setCantidadSolicitada(rs.getInt("cantidad_solicitada"));
				detalle.setSubtotal(rs.getBigDecimal("subtotal"));
				detalle.setCantidadRecibida(rs.getInt("cantidad_recibida"));
				List<DetallePedido> detalles = new ArrayList<DetallePedido>();
				detalles.add(detalle);
				pedido = new CabeceraPedido();
				pedido.setNumero(numero);
				pedido.setProveedor(proveedor);
				pedido.setFecha(fecha);
				estadoPedido = new EstadoPedido();
				estadoPedido.setCodigo(estado);
				pedido.setEstado(estadoPedido);
				pedido.setDetallePedido(detalles);
				pedidos.add(pedido);
			}
		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al consultar el pedido: " + e.getMessage());
		}
		return pedidos;
	}
}
