package fes.aragon.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fes.aragon.interfaz.IBaseDatos;
import fes.aragon.modelo.Habitacion;
import fes.aragon.modelo.Tipo;

public class HabitacionesImpl<E> implements IBaseDatos<E> {

	@Override
	public ArrayList<E> consulta() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<E> buscar(String patron) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertar(E obj) throws Exception {
		// TODO Auto-generated method stub
		String query="insert into habitaciones(numero,costo,refrigerador,id_tps,id_htl) values(?,?,?,?,?)";
		PreparedStatement solicitud = Conexion.getInstancia().getCnn().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		Habitacion dato= (Habitacion) obj;
		solicitud.setString(1, dato.getNumero());
		solicitud.setFloat(2, dato.getCosto());
		solicitud.setBoolean(3, dato.isRefrigerador());
		solicitud.setInt(4, dato.getTipo().getId());
		solicitud.setInt(5, dato.getIdHotel());
		solicitud.executeUpdate();
		ResultSet resultado = solicitud.getGeneratedKeys();
		if (resultado.next()) {
			dato.setId(resultado.getInt(1));
		}
		solicitud.close();
		resultado.close();
	}

	@Override
	public void modificar(E obj) throws Exception {
		// TODO Auto-generated method stub
		String query="update habitaciones set numero=?,costo=? "
				+ ",refrigerador=?,id_tps=? where"
				+ " id_htl=? and id_hbt=?";
		PreparedStatement solicitud=Conexion.getInstancia().getCnn().prepareStatement(query);
		Habitacion dato=(Habitacion)obj;		
		solicitud.setString(1,dato.getNumero());
		solicitud.setFloat(2, dato.getCosto());
		solicitud.setBoolean(3, dato.isRefrigerador());
		solicitud.setInt(4, dato.getTipo().getId());
		solicitud.setInt(5, dato.getIdHotel());
		solicitud.setInt(6, dato.getId());
		solicitud.executeUpdate();		
		solicitud.close();

	}

	@Override
	public E consulta(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Integer id) throws Exception {
		// TODO Auto-generated method stub
		String query="delete from habitaciones where id_htl=?";
		PreparedStatement solicitud=Conexion.getInstancia().getCnn().prepareStatement(query);
		solicitud.setInt(1, id);
		solicitud.executeUpdate();
		solicitud.close();
	}

	@Override
	public void cerrar() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarProc(Integer id) throws Exception {
		// TODO Auto-generated method stub

	}

	public ArrayList<E> buscarIdHotel(Integer id) throws Exception {
		String query = "select a.id_hbt,a.numero,a.costo,a.refrigerador,a.id_htl,b.id_tps,b.tipo"
				+ " from habitaciones a,tipos b where id_htl=" + id + " and a.id_tps=b.id_tps";
		ArrayList<E> datos = new ArrayList<>();
		Statement solicitud = Conexion.getInstancia().getCnn().createStatement();
		ResultSet resultado = solicitud.executeQuery(query);
		if (!resultado.next()) {
			System.out.println("Sin datos");
		} else {
			do {
				Habitacion hb = new Habitacion();
				hb.setId(resultado.getInt(1));
				hb.setNumero(resultado.getString(2));
				hb.setCosto(resultado.getFloat(3));
				hb.setRefrigerador(resultado.getBoolean(4));
				Tipo tmpTipo= new Tipo();
				tmpTipo.setId(resultado.getInt(6));
				tmpTipo.setTipo(resultado.getString(7));
				hb.setTipo(tmpTipo);
				hb.setIdHotel(id);
				datos.add((E) hb);
			} while (resultado.next());
		}
		solicitud.close();
		resultado.close();
		return datos;
	}

}
