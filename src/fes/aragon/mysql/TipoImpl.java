package fes.aragon.mysql;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fes.aragon.interfaz.IBaseDatos;
import fes.aragon.modelo.Tipo;

public class TipoImpl <E> implements IBaseDatos<E>{

	@Override
	public ArrayList<E> consulta() throws Exception {
		// TODO Auto-generated method stub
		String query = "select * from tipos";
		ArrayList<E> datos = new ArrayList<>();
		Statement solicitud = Conexion.getInstancia().getCnn().createStatement();
		ResultSet resultado = solicitud.executeQuery(query);
		if (!resultado.next()) {
			System.out.println("Sin datos");
		} else {
			do {
				Tipo tipo=new Tipo();
				tipo.setId(resultado.getInt(1));
				tipo.setTipo(resultado.getString(2));
				datos.add((E) tipo);
			} while (resultado.next());
		}
		solicitud.close();
		resultado.close();
		return datos;
	}

	@Override
	public ArrayList<E> buscar(String patron) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertar(E obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificar(E obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E consulta(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
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
