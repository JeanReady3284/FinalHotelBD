package fes.aragon.controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fes.aragon.modelo.Habitacion;
import fes.aragon.modelo.Hotel;
import fes.aragon.modelo.Hoteles;
import fes.aragon.modelo.Tipo;
import fes.aragon.modelo.TipoError;
import fes.aragon.mysql.HabitacionesImpl;
import fes.aragon.mysql.TipoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class HabitacionController extends BaseController implements Initializable {
	private Hotel hotel;
	private String mensajes = "";
	@FXML
	private Button btnAceptar;

	@FXML
	private Button btnCancelar;

	@FXML
	private ChoiceBox<Tipo> chcTipo;

	@FXML
	private CheckBox chkRegrigerador;

	@FXML
	private TextField txtCosto;

	@FXML
	private TextField txtNumero;

	public HabitacionController() {

	}

	@FXML
	void cancelarHabitacion(ActionEvent event) {
		this.cerrarVentana(btnCancelar);
	}

	@FXML
	void nuevaHabitacion(ActionEvent event) {
		if (this.verificar()) {
			Habitacion hab = new Habitacion();
			hab.setNumero(this.txtNumero.getText());
			hab.setCosto(Float.valueOf(this.txtCosto.getText()));
			hab.setRefrigerador(this.chkRegrigerador.isSelected());
			hab.setTipo(this.chcTipo.getValue());
			if (Hoteles.getInstancia().isModificarHotel() && Hoteles.getInstancia().getIndiceHabitacion() != -1) {
				Habitacion tmp = hotel.getHabitaciones().get(Hoteles.getInstancia().getIndiceHabitacion());
				hab.setIdHotel(tmp.getIdHotel());
				hab.setId(tmp.getId());
				hotel.getHabitaciones().set(Hoteles.getInstancia().getIndiceHabitacion(), hab);
				// llamar a la base de datos
				HabitacionesImpl<Habitacion> cnn = new HabitacionesImpl<>();
				try {
					/*Si la habitacion ya habia sido creada se modifica en la BD directamente,
					 caso contrario modifica en la aplicacion y espera a que se cree hotel
					 para insertar en la BD */
					if (hab.getId() != null) {
						cnn.modificar(hab);
					} else {
						hotel.getHabitaciones().set(Hoteles.getInstancia().getIndiceHabitacion(), hab);
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					this.ventanaEmergente("Mensaje", "Error en la aplicación", "Consultar al administrador");
				}

			} else {

				/* Añade nuevas habitaciones a la lista, se inserta a la BD hasta crear hotel */
				hotel.getHabitaciones().add(hab);

			}
			this.cerrarVentana(btnAceptar);
		} else {
			this.ventanaEmergente("Mensaje", "Datos no correctos", this.mensajes);
			this.mensajes = "";
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TipoImpl<Tipo> cnn = new TipoImpl<>();
		try {
			ArrayList<Tipo> tipo = cnn.consulta();
			Tipo tmpTipo = new Tipo();
			tmpTipo.setId(0);
			tmpTipo.setTipo("Selecciona un tipo");
			this.chcTipo.getItems().add(tmpTipo);
			for (Tipo tipo2 : tipo) {
				this.chcTipo.getItems().add(tipo2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.ventanaEmergente("Mensaje", "Error en la aplicación", "Consultar al administrador");
		}
		this.chcTipo.getSelectionModel().select(0);
		this.verificarEntrada(txtCosto, TipoError.NUMEROS);
		this.verificarEntrada(txtNumero, TipoError.PALABRAS);
		if (Hoteles.getInstancia().isModificarHotel()) {
			this.hotel = Hoteles.getInstancia().getGrupoHoteles().get(Hoteles.getInstancia().getIndice());
			int indice = Hoteles.getInstancia().getIndiceHabitacion();
			Habitacion hab = null;
			if (indice == -1) {
				hab = new Habitacion();
				hab.setNumero("Nueva habitación");
			} else {
				hab = hotel.getHabitaciones().get(Hoteles.getInstancia().getIndiceHabitacion());
			}
			this.txtNumero.setText(hab.getNumero());
			this.txtCosto.setText(String.valueOf(hab.getCosto()));
			this.chkRegrigerador.setSelected(hab.isRefrigerador());
			this.chcTipo.setValue(hab.getTipo());
		} else {
			hotel = Hoteles.getInstancia().getGrupoHoteles().get(Hoteles.getInstancia().getGrupoHoteles().size() - 1);
		}
	}

	private boolean verificar() {
		boolean valido = true;
		if ((this.txtNumero.getText() == null) || (this.txtNumero != null && this.txtNumero.getText().isEmpty())) {
			this.mensajes += "El número de la habitación no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtNumero.getText() == null) || (this.txtNumero != null && !this.txtNumero.getText().isEmpty())) {
			if (!this.habitacionValido) {
				this.mensajes += "El número de la habitación no es valido, no se permiten espacios\n";
				valido = false;
			}
		}

		if ((this.txtCosto.getText() == null) || (this.txtCosto != null && this.txtCosto.getText().isEmpty())) {
			this.mensajes += "El costo de la habitación no es valido, es vacio\n";
			valido = false;
		}
		if ((this.txtCosto.getText() == null) || (this.txtCosto != null && !this.txtCosto.getText().isEmpty())) {
			try {
				if (!this.costoValido) {
					throw new NumberFormatException();
				}
				Float.parseFloat(this.txtCosto.getText());
			} catch (NumberFormatException ex) {
				this.mensajes += "El costo no es valido,debe contener decimales\n";
				valido = false;
			}
		}
		if (this.chcTipo.getSelectionModel().getSelectedIndex() == 0
				|| this.chcTipo.getSelectionModel().getSelectedIndex() == -1) {
			this.mensajes += "Selecciones un tipo de habitación\n";
			valido = false;
		}
		return valido;
	}

}
