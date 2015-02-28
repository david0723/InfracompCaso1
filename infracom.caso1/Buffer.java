
import java.util.concurrent.LinkedBlockingQueue;




public class Buffer 
{

	
	public LinkedBlockingQueue<Mensaje> mensajes;

	public Buffer(int n)
	{
		mensajes = new LinkedBlockingQueue<Mensaje>(n);
	}


	public synchronized void entrarEnvioCliente(Mensaje m) 
	{

		while(!mensajes.add(m))
		{
			System.out.println(Thread.currentThread().getName()+" - Envio mensaje: yield");
			Thread.yield();
		}

		try 
		{
			synchronized (m) 
			{
				m.wait();
			}
			
		} 
		catch (InterruptedException e) {e.printStackTrace();}
		
		notifyAll();
	}

	public synchronized void entrarRecibirServidor()
	{
		while (mensajes.isEmpty())
		{
			System.out.println(Thread.currentThread().getName()+" - Recepcion mensaje: espera");
			try 
			{
				wait();
			} 
			catch (InterruptedException e) {e.printStackTrace();}
		}
		System.out.println(Thread.currentThread().getName()+" - Recepcion mensaje: remove");
		Mensaje m =mensajes.remove();
		synchronized (m)
		{
			System.out.println(Thread.currentThread().getName()+" - Recepcion mensaje: going to notify");
			m.notify();
			System.out.println(Thread.currentThread().getName()+" - Recepcion mensaje: notified");
		}
		
		//m.notify();

		
	}


	public void aDumir() throws InterruptedException
	{
		wait();
	}





		 //#consultas por cliente, # clientes,# servidores estan en arcihvo de texto. son parametros

}
