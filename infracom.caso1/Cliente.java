
public class Cliente implements Runnable
{
	
	public int cantidad;
	
	public Mensaje[] mensajes;
	
	private Buffer buffer;
	
	/**
	 * 
	 * @param cantidad de mensajes que va a enviar
	 * @param buffer pasado por parametro a donde va a enviar los mensajes
	 */
	public Cliente(int cantidad, Buffer buffer)
	{
		this.buffer=buffer;
		this.cantidad = cantidad;
		mensajes = new Mensaje[cantidad];
		for ( int i = 0; i < cantidad; i++)
		{
			mensajes[i] = new Mensaje((int) (Math.random()*100));
			//mensajes[i] = new Mensaje((int) i);
		}
		
	}

	public int getCantidad() 
	{
		return cantidad;
	}

	public void setCantidad(int cantidad) 
	{
		this.cantidad = cantidad;
	}

	public Mensaje[] getMensajes() 
	{
		return mensajes;
	}

	public void setMensajes(Mensaje[] mensajes) 
	{
		this.mensajes = mensajes;
	}

	@Override
	/**
	 * Envia todos los mensajes con los que fue creado.
	 */
	public void run() 
	{
		for (int i = 0; i<cantidad;i++)
		{
			System.out.println(Thread.currentThread().getName()+" - Envio mensaje: "+ mensajes[i].getMsn());

			buffer.envioCliente(mensajes[i]);
		}
		
		

		
	}
	
	

}
