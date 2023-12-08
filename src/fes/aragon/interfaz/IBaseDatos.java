package fes.aragon.interfaz;


import java.util.ArrayList;

public interface IBaseDatos<E> {
	public ArrayList<E> consulta() throws Exception;

	public ArrayList<E> buscar(String patron) throws Exception;

	public void insertar(E obj) throws Exception;

	public void modificar(E obj) throws Exception;

	public E consulta(Integer id) throws Exception;

	public void eliminar(Integer id) throws Exception;

	public void cerrar() throws Exception;

	public void eliminarProc(Integer id) throws Exception;

}
