package com.krakedev.inventarios.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.krakedev.inventarios.entidades.CabeceraPedido;
import com.krakedev.inventarios.entidades.Categoria;
import com.krakedev.inventarios.entidades.CategoriaUnidadMedida;
import com.krakedev.inventarios.entidades.DetallePedido;
import com.krakedev.inventarios.entidades.Producto;
import com.krakedev.inventarios.entidades.Proveedor;
import com.krakedev.inventarios.entidades.TipoDocumento;
import com.krakedev.inventarios.entidades.UnidadMedida;
import com.krakedev.inventarios.excepciones.KrakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProveedoresBDD {
	public List<Proveedor> buscar(String subcadena) throws KrakeDevException {
		List<Proveedor> proveedores = new ArrayList<Proveedor>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Proveedor proveedor = null;
		TipoDocumento tipoDocumento = new TipoDocumento();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append("	PRO.IDENTIFICADOR,");
		sb.append("	PRO.TIPO_DOCUMENTO,");
		sb.append("	TD.DESCRIPCION,");
		sb.append("	PRO.NOMBRE,");
		sb.append("	PRO.TELEFONO,");
		sb.append("	PRO.CORREO,");
		sb.append("	PRO.DIRECCION");
		sb.append(" FROM");
		sb.append("	PUBLIC.PROVEEDORES PRO");
		sb.append("	INNER JOIN PUBLIC.TIPO_DOCUMENTOS TD ON PRO.TIPO_DOCUMENTO = TD.CODIGO");
		sb.append(" WHERE");
		sb.append("	UPPER(NOMBRE) LIKE ?");
		String sql = sb.toString();
		try {
			conn = ConexionBDD.obtenerConexion();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + subcadena.toUpperCase() + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				String identificador = rs.getString("identificador");
				tipoDocumento.setCodigo(rs.getString("tipo_documento"));
				tipoDocumento.setDescripcion(rs.getString("descripcion"));
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");
				proveedor = new Proveedor();
				proveedor.setIdentificador(identificador);
				proveedor.setTipoDocumento(tipoDocumento);
				proveedor.setNombre(nombre);
				proveedor.setTelefono(telefono);
				proveedor.setCorreo(correo);
				proveedor.setDireccion(direccion);
				proveedores.add(proveedor);
			}
		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al consultar. Detalle: " + e.getMessage());
		}
		return proveedores;
	}

	public List<TipoDocumento> buscarTiposDocumento() throws KrakeDevException {
		List<TipoDocumento> tipoDocumentos = new ArrayList<TipoDocumento>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TipoDocumento tipoDocumento = null;
		String sql = "SELECT codigo, descripcion FROM public.tipo_documentos";
		try {
			conn = ConexionBDD.obtenerConexion();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String codigo = rs.getString("codigo");
				String descripcion = rs.getString("descripcion");
				tipoDocumento = new TipoDocumento();
				tipoDocumento.setCodigo(codigo);
				tipoDocumento.setDescripcion(descripcion);
				tipoDocumentos.add(tipoDocumento);
			}
		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al consultar. Detalle: " + e.getMessage());
		}
		return tipoDocumentos;
	}

	public void crear(Proveedor proveedor) throws KrakeDevException {
		Connection conn = null;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO public.proveedores(");
		sb.append(" identificador, tipo_documento, nombre, telefono, correo, direccion)");
		sb.append(" VALUES (?, ?, ?, ?, ?, ?)");
		String sql = sb.toString();
		try {
			conn = ConexionBDD.obtenerConexion();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, proveedor.getIdentificador());
			ps.setString(2, proveedor.getTipoDocumento().getCodigo());
			ps.setString(3, proveedor.getNombre());
			ps.setString(4, proveedor.getTelefono());
			ps.setString(5, proveedor.getCorreo());
			ps.setString(6, proveedor.getDireccion());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al guardar el proveedor");
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

	public List<Producto> buscarProducto(String subcadena) throws KrakeDevException {
		List<Producto> productos = new ArrayList<Producto>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Producto producto;
		Categoria categoria;
		UnidadMedida unidadMedida;
		CategoriaUnidadMedida categoriaUnidadMedida;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append(" PRO.CODIGO AS CODIGO_PRODUCTO,");
		sb.append(" PRO.NOMBRE AS NOMBRE_PRODUCTO,");
		sb.append(" UDM.NOMBRE AS NOMBRE_UDM,");
		sb.append(" UDM.DESCRIPCION,");
		sb.append(" CUM.CODIGO AS CODIGO_CUM,");
		sb.append(" CUM.NOMBRE AS NOMBRE_CUM,");
		sb.append(" CAST(PRO.PRECIO_VENTA AS DECIMAL(6, 2)),");
		sb.append(" PRO.TIENE_IVA,");
		sb.append(" CAST(PRO.COSTE AS DECIMAL(5, 4)),");
		sb.append(" CAT.CODIGO AS CODIGO_CATEGORIA,");
		sb.append(" CAT.NOMBRE AS NOMBRE_CATEGORIA,");
		sb.append(" PRO.STOCK");
		sb.append(" FROM");
		sb.append(" PRODUCTOS PRO");
		sb.append(" INNER JOIN UNIDADES_MEDIDA UDM ON UDM.NOMBRE = PRO.UDM");
		sb.append(" INNER JOIN CATEGORIAS CAT ON CAT.CODIGO = PRO.CATEGORIA");
		sb.append(" INNER JOIN CATEGORIAS_UNIDAD_MEDIDA CUM ON CUM.CODIGO = UDM.CATEGORIA_UDM");
		sb.append(" WHERE");
		sb.append(" UPPER(PRO.NOMBRE) LIKE ?");
		String sql = sb.toString();
		try {
			conn = ConexionBDD.obtenerConexion();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + subcadena.toUpperCase() + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				int codigoProducto = rs.getInt("CODIGO_PRODUCTO");
				String nombreProducto = rs.getString("NOMBRE_PRODUCTO");

				unidadMedida = new UnidadMedida();
				unidadMedida.setNombre(rs.getString("NOMBRE_UDM"));
				unidadMedida.setDescripcion(rs.getString("DESCRIPCION"));

				BigDecimal precioVenta = rs.getBigDecimal("PRECIO_VENTA");
				Boolean tieneIva = rs.getBoolean("TIENE_IVA");
				BigDecimal coste = rs.getBigDecimal("COSTE");

				categoriaUnidadMedida = new CategoriaUnidadMedida();
				categoriaUnidadMedida.setCodigo(rs.getString("CODIGO_CUM"));
				categoriaUnidadMedida.setNombre(rs.getString("NOMBRE_CUM"));
				unidadMedida.setCategoriaUdm(categoriaUnidadMedida);

				categoria = new Categoria();
				categoria.setCodigo(rs.getInt("CODIGO_CATEGORIA"));
				categoria.setNombre(rs.getString("NOMBRE_CATEGORIA"));

				int stock = rs.getInt("STOCK");

				producto = new Producto();
				producto.setCategoria(categoria);
				producto.setCodigo(codigoProducto);
				producto.setNombre(nombreProducto);
				producto.setUdm(unidadMedida);
				producto.setPrecioVenta(precioVenta);
				producto.setTieneIva(tieneIva);
				producto.setCoste(coste);
				producto.setCategoria(categoria);
				producto.setStock(stock);

				productos.add(producto);
			}
		} catch (KrakeDevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al consultar. Detalle: " + e.getMessage());
		}
		return productos;
	}

	public void crearProducto(Producto producto) throws KrakeDevException {
		Connection conn = null;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO");
		sb.append(" PUBLIC.PRODUCTOS (");
		sb.append(" NOMBRE,");
		sb.append(" UDM,");
		sb.append(" PRECIO_VENTA,");
		sb.append(" TIENE_IVA,");
		sb.append(" COSTE,");
		sb.append(" CATEGORIA,");
		sb.append(" STOCK");
		sb.append(" )");
		sb.append(" VALUES");
		sb.append(" (?, ?, ?, ?, ?, ?, ?)");
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
			ps.setInt(7, producto.getStock());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al guardar el producto");
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

	public void crearPedido(CabeceraPedido cabeceraPedido) throws KrakeDevException {
		Connection conn = null;
		ResultSet rsClaves = null;
		int codigoCabecera = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO");
		sb.append(" PUBLIC.CABECERA_PEDIDO (PROVEEDOR, FECHA, ESTADO)");
		sb.append(" VALUES");
		sb.append(" (?, ?, ?)");

		String sql = sb.toString();
		
		StringBuilder sbd = new StringBuilder();
		sbd.append(" INSERT INTO");
		sbd.append(" PUBLIC.DETALLE_PEDIDO (");
		sbd.append(" CABECERA_PEDIDO,");
		sbd.append(" PRODUCTO,");
		sbd.append(" CANTIDAD_SOLICITADA,");
		sbd.append(" SUBTOTAL,");
		sbd.append(" CANTIDAD_RECIBIDA");
		sbd.append(" )");
		sbd.append(" VALUES");
		sbd.append(" (?, ?, ?, ?, ?)");
		
		String sqlDet = sbd.toString();

		try {
			conn = ConexionBDD.obtenerConexion();
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// INSERTAR CABECERA

			Date fechaActual = new Date();
			java.sql.Date fechaSQL = new java.sql.Date(fechaActual.getTime());

			ps.setString(1, cabeceraPedido.getProveedor().getIdentificador());
			ps.setDate(2, fechaSQL);
			ps.setString(3, "S");

			ps.executeUpdate();

			rsClaves = ps.getGeneratedKeys();
			if (rsClaves.next()) {
				codigoCabecera = rsClaves.getInt(1);
			}

			System.out.println("CODIGO GENERADO >>>> " + codigoCabecera);
			
			// INSERTAR DETALLES

			PreparedStatement psDetalle = conn.prepareStatement(sqlDet);
			List<DetallePedido> detalles = cabeceraPedido.getDetallePedido();

			for (DetallePedido detallePedido : detalles) {
				psDetalle.setInt(1, codigoCabecera);
				psDetalle.setInt(2, detallePedido.getProducto().getCodigo());
				psDetalle.setInt(3, detallePedido.getCantidadSolicitada());
				BigDecimal precioVenta = detallePedido.getProducto().getPrecioVenta();
				BigDecimal cantidadSolicitada = new BigDecimal(detallePedido.getCantidadSolicitada());
				BigDecimal subTotal = precioVenta.multiply(cantidadSolicitada);
				psDetalle.setBigDecimal(4, subTotal);
				psDetalle.setInt(5, detallePedido.getCantidadRecibida());
				psDetalle.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakeDevException("Error al guardar el pedido");
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
