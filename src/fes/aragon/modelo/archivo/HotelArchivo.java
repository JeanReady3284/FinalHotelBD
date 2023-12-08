package fes.aragon.modelo.archivo;

import java.io.Serializable;
import java.util.ArrayList;

import fes.aragon.modelo.Gerente;
import fes.aragon.modelo.Habitacion;

public class HotelArchivo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Gerente gerente=new Gerente();
	private ArrayList<Habitacion> habitaciones=new ArrayList<>();
	private String nombre;
	private String direccion;
	private String correo;
	private String telefono;
	
	private static HotelArchivo instancia=new HotelArchivo();
	public HotelArchivo() {
		
	}
	public static HotelArchivo getHotel() {
		return instancia;
	}
	public Gerente getGerente() {
		return gerente;
	}
	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}
	
	public ArrayList<Habitacion> getHabitaciones() {
		return habitaciones;
	}
	
	public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	@Override
	public String toString() {
		return "Hotel [gerente=" + gerente + ", habitaciones=" + habitaciones + ", nombre=" + nombre + ", direccion="
				+ direccion + ", correo=" + correo + ", telefono=" + telefono + "]";
	}
	
	/*public int getIndiceHabitacion() {
		if(indiceHabitacion<this.habitaciones.length) {
			return indiceHabitacion; 
		}else {
			indiceHabitacion=this.habitaciones.length-1;
			return indiceHabitacion;
		}
			
	}

	public void incrementoIndice() {
		if(indiceHabitacion<this.habitaciones.length) {
			indiceHabitacion++;
		}else {
			indiceHabitacion=this.habitaciones.length-1;			
		}
	}*/
	
	
	
	
}
