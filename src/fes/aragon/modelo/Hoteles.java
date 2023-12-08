package fes.aragon.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Hoteles {
	private static Hoteles instancia=new Hoteles();
	private ObservableList<Hotel> grupoHoteles=FXCollections.observableArrayList();
	private boolean modificarHotel=false;
	private int indice=-1;
	private int indiceHabitacion=-1;
	private Hoteles() {
		/*Hotel h=new Hotel();
		h.setNombre("Demo");
		h.setDireccion("Siempre Viva");
		h.setCorreo("demo@demo.mx");
		h.setTelefono("3423421");
		Gerente g=new Gerente();
		g.setNombre("G1");
		g.setApellidoPaterno("Demos");
		g.setApellidoMaterno("ADF");
		g.setCorreo("d@d.com");
		g.setRfc("ASDASDAA");
		g.setTelefono("343424242");
		h.setGerente(g);
		Habitacion hab=new Habitacion();
		hab.setNumero("A2");
		hab.setCosto(3242.34f);
		hab.setRefrigerador(true);
		hab.setTipo("Mixto");
		h.getHabitaciones().add(hab);
		this.grupoHoteles.add(h);*/
	}

	public static Hoteles getInstancia() {
		return instancia;
	}

	public boolean isModificarHotel() {
		return modificarHotel;
	}

	public void setModificarHotel(boolean modificarHotel) {
		this.modificarHotel = modificarHotel;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public int getIndiceHabitacion() {
		return indiceHabitacion;
	}

	public void setIndiceHabitacion(int indiceHabitacion) {
		this.indiceHabitacion = indiceHabitacion;
	}

	public ObservableList<Hotel> getGrupoHoteles() {
		return grupoHoteles;
	}

	public void setGrupoHoteles(ObservableList<Hotel> grupoHoteles) {
		this.grupoHoteles = grupoHoteles;
	}
	
	
	
}
