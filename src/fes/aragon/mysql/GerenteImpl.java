package fes.aragon.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fes.aragon.interfaz.IBaseDatos;
import fes.aragon.modelo.Gerente;

public class GerenteImpl<E> implements IBaseDatos<E> {

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
		String query = "insert into gerentes(nombre,apellido_paterno,apellido_materno,rfc,correo,telefono) values(?,?,?,?,?,?)";
		PreparedStatement solicitud = Conexion.getInstancia().getCnn().prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS);
		Gerente dato = (Gerente) obj;
		solicitud.setString(1, dato.getNombre());
		solicitud.setString(2, dato.getApellidoPaterno());
		solicitud.setString(3, dato.getApellidoMaterno());
		solicitud.setString(4, dato.getRfc());
		solicitud.setString(5, dato.getCorreo());
		solicitud.setString(6, dato.getTelefono());
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
		String query = "update gerentes set nombre=?,apellido_paterno=? "
				+ ",apellido_materno=?,rfc=?,correo=?,telefono=?  where" + " id_gre=?";
		PreparedStatement solicitud = Conexion.getInstancia().getCnn().prepareStatement(query);
		Gerente dato = (Gerente) obj;
		solicitud.setString(1, dato.getNombre());
		solicitud.setString(2, dato.getApellidoPaterno());
		solicitud.setString(3, dato.getApellidoMaterno());
		solicitud.setString(4, dato.getRfc());
		solicitud.setString(5, dato.getCorreo());
		solicitud.setString(6, dato.getTelefono());
		solicitud.setInt(7, dato.getId());
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
		String query = "delete from gerentes where id_gre=?";
		PreparedStatement solicitud = Conexion.getInstancia().getCnn().prepareStatement(query);
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

}
