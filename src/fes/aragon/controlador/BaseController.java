package fes.aragon.controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fes.aragon.modelo.Hotel;
import fes.aragon.modelo.Hoteles;
import fes.aragon.modelo.TipoError;
import fes.aragon.modelo.archivo.HotelArchivo;
import fes.aragon.mysql.HotelesImp;
import javafx.collections.FXCollections;
import javafx.css.PseudoClass;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BaseController {
	protected boolean rfcValido = true;
	protected boolean correoValido = true;
	protected boolean telefonoValido = true;
	protected boolean habitacionValido = true;
	protected boolean costoValido = true;
	/*
	 * EXPRESIONES REGULARES 0 palabras sin espacio 1 solo números 2 validar RFC 3
	 * validar correo 4 validar teléfono
	 */
	private String[] expresiones = { "(\\w+)", "(\\d+)(\\.\\d{1,2})", "(\\w){13}",
			"^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", "(\\d){10}" };

	public void nuevaVentana(String archivo) {
		try {
			Pane root = (Pane) FXMLLoader.load(getClass().getResource("/fes/aragon/fxml/" + archivo + ".fxml"));
			Scene scene = new Scene(root);
			Stage escenario = new Stage();
			escenario.setScene(scene);
			escenario.initStyle(StageStyle.UNDECORATED);
			escenario.initModality(Modality.APPLICATION_MODAL);
			// escenario.setX(Screen.getPrimary().getVisualBounds().getMaxX() + 300);
			escenario.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ventanaEmergente(String titulo, String encabezado, String contenido) {
		Alert alerta;
		alerta = new Alert(AlertType.INFORMATION);
		// alerta.setX(Screen.getPrimary().getVisualBounds().getMaxX() + 300);
		alerta.setTitle(titulo);
		alerta.setHeaderText(encabezado);
		alerta.setContentText(contenido);
		alerta.showAndWait();
	}

	public void cerrarVentana(Button boton) {
		Stage stage = (Stage) boton.getScene().getWindow();
		stage.close();
	}

	public void verificarEntrada(TextField caja, TipoError error) {
		caja.textProperty().addListener(evento -> {
			String text = caja.getText();
			if (text == null) {
				text = "";
			}
			String patron = expresiones[error.ordinal()];
			Pattern pt = Pattern.compile(patron);
			Matcher match = pt.matcher(text);
			caja.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"), !match.matches());
			if (error == TipoError.PALABRAS) {
				this.habitacionValido = match.matches();
			} else if (error == TipoError.NUMEROS) {
				this.costoValido = match.matches();
			} else if (error == TipoError.RFC) {
				this.costoValido = match.matches();
			} else if (error == TipoError.CORREO) {
				this.correoValido = match.matches();
			} else if (error == TipoError.TELEFONO) {
				this.telefonoValido = match.matches();
			}
		});
	}

	public void recogerDatos() throws Exception {
		HotelesImp<Hotel> cn = new HotelesImp<>();
		ArrayList<Hotel> datos = cn.consulta();
		for (Hotel hotel : datos) {
			Hotel objeto = new Hotel();
			objeto.setNombre(hotel.getNombre());
			objeto.setDireccion(hotel.getDireccion());
			objeto.setCorreo(hotel.getCorreo());
			objeto.setTelefono(hotel.getTelefono());
			objeto.setGerente(hotel.getGerente());
			objeto.setId(hotel.getId());
			objeto.setHabitaciones(FXCollections.observableArrayList(hotel.getHabitaciones()));
			Hoteles.getInstancia().getGrupoHoteles().add(objeto);
		}
	}

	public void guardarArchivo(Button boton) throws IOException {
		Stage stage = (Stage) boton.getScene().getWindow();
		FileChooser archivos = new FileChooser();
		archivos.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivos para Hoteles", "*.fes"));
		archivos.setTitle("Gurardar archivo de Hotel");
		archivos.setInitialDirectory(new File(System.getProperty("user.dir")));
		File ruta = archivos.showSaveDialog(stage);
		if (ruta != null) {
			FileOutputStream fo = new FileOutputStream(ruta);
			ObjectOutputStream salida = new ObjectOutputStream(fo);
			ArrayList<HotelArchivo> hoteles = new ArrayList<>();
			for (Hotel hotel : Hoteles.getInstancia().getGrupoHoteles()) {
				HotelArchivo objeto = new HotelArchivo();
				objeto.setNombre(hotel.getNombre());
				objeto.setDireccion(hotel.getDireccion());
				objeto.setCorreo(hotel.getCorreo());
				objeto.setTelefono(hotel.getTelefono());
				objeto.setGerente(hotel.getGerente());
				objeto.setHabitaciones(new ArrayList<>(hotel.getHabitaciones()));
				hoteles.add(objeto);
			}
			salida.writeObject(hoteles);
			salida.close();
			fo.close();
		}
	}

}
