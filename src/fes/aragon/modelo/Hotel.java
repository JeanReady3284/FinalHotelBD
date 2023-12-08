package fes.aragon.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Hotel {
	private Gerente gerente = new Gerente();
	private ObservableList<Habitacion> habitaciones = FXCollections.observableArrayList();
	private String nombre;
	private String direccion;
	private String correo;
	private String telefono;
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private static Hotel instancia = new Hotel();

	public Hotel() {

	}

	public static Hotel getHotel() {
		return instancia;
	}

	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}

	public ObservableList<Habitacion> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(ObservableList<Habitacion> habitaciones) {
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

	/*
	 * public int getIndiceHabitacion() {
	 * if(indiceHabitacion<this.habitaciones.length) { return indiceHabitacion;
	 * }else { indiceHabitacion=this.habitaciones.length-1; return indiceHabitacion;
	 * }
	 * 
	 * }
	 * 
	 * public void incrementoIndice() {
	 * if(indiceHabitacion<this.habitaciones.length) { indiceHabitacion++; }else {
	 * indiceHabitacion=this.habitaciones.length-1; } }
	 */

}
