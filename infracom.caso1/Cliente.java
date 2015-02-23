
public class Cliente {
	
	public int cantidad;
	
	public Mensaje[] mensajes;
	
	public Cliente(int cantidad)
	{
		this.cantidad = cantidad;
		mensajes = new Mensaje[cantidad];
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Mensaje[] getMensajes() {
		return mensajes;
	}

	public void setMensajes(Mensaje[] mensajes) {
		this.mensajes = mensajes;
	}
	
	

}
