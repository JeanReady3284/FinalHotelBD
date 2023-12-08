package fes.aragon.modelo;

import java.io.Serializable;

public class Habitacion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numero;
	private float costo;
	private boolean refrigerador;
	private Tipo tipo;
	private Integer id;
	private Integer idHotel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(Integer idHotel) {
		this.idHotel = idHotel;
	}

	public Habitacion() {
		// TODO Auto-generated constructor stub
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public boolean isRefrigerador() {
		return refrigerador;
	}

	public void setRefrigerador(boolean refrigerador) {
		this.refrigerador = refrigerador;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Habitacion [numero=" + numero + ", costo=" + costo + ", refrigerador=" + refrigerador + ", tipo=" + tipo
				+ "]";
	}

}
