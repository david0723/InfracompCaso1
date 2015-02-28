
public class Servidor implements Runnable
{
	private Buffer buffer;
	
	public Servidor(Buffer buffer)
	{
		this.buffer = buffer;
	}

	@Override
	public void run() 
	{
		while(true)
		{
			System.out.println(Thread.currentThread().getName()+" - Recepcion mensaje: ");
			buffer.entrarRecibirServidor();
		}
	}
	
	//varios threads para leer
	//comentarios maricas!!

}
