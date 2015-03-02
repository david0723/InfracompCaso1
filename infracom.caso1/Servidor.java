
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
			System.out.println(Thread.currentThread().getName()+" - Recibiendo mensaje ");
			buffer.recibirServidor();
		}
	}
	
	//varios threads para leer
	//comentarios maricas!!

}
