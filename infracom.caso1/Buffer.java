
import java.util.concurrent.LinkedBlockingQueue;




public class Buffer 
{

	
	public LinkedBlockingQueue<Mensaje> mensajes;

	public Buffer(int n)
	{
		mensajes = new LinkedBlockingQueue<Mensaje>(n);
	}


	public synchronized void entrarEnvioCliente(Mensaje m) throws InterruptedException
	{
		while(!mensajes.add(m))
		{
			Thread.yield();
		}
		
		m.wait();
		notifyAll();
	}

	public synchronized void entrarRecibirServidor() throws InterruptedException
	{
		while (mensajes.isEmpty())
		{
			aDumir();
		}
		mensajes.remove().notify();

		
	}


	public void aDumir() throws InterruptedException
	{
		wait();
	}





		 //#consultas por cliente, # clientes,# servidores estan en arcihvo de texto. son parametros

}
