
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
		try 
		{
			buffer.entrarRecibirServidor();
		} 
		catch (InterruptedException e){e.printStackTrace();}
	}
	
	//varios threads para leer
	//comentarios maricas!!

}
