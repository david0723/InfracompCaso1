

import java.util.concurrent.LinkedBlockingQueue;




public class Buffer 
{

	public LinkedBlockingQueue<Mensaje> mensajes;

	public Buffer(int n)
	{
		mensajes = new LinkedBlockingQueue<Mensaje>(n);
	}


	public void entrarEnvioCliente(Mensaje m) 
	{
		boolean b;
		synchronized (mensajes) 
		{
			System.out.println(Thread.currentThread().getName()+" - Envio mensaje: test");
			b = mensajes.offer(m);
		}
			while(b)
			{
				System.out.println(Thread.currentThread().getName()+" - Envio mensaje: yield");
				Thread.yield();
				
				synchronized (mensajes) 
				{
					b = mensajes.offer(m);
				}
			}
		

		try 
		{
			synchronized (m) 
			{
				m.wait();
			}
			
		} 
		catch (InterruptedException e) {e.printStackTrace();}
		synchronized (this)
		{
			notifyAll();
		}
		
	}

	public void entrarRecibirServidor()
	{
		Mensaje m;
		synchronized (mensajes) 
		{
			while (mensajes.isEmpty())
			{
				System.out.println(Thread.currentThread().getName()+" - Recepcion mensaje: espera");
				try 
				{
					synchronized (this)
					{
						wait();
					}
					
				} 
				catch (InterruptedException e) {e.printStackTrace();}
			}
			System.out.println(Thread.currentThread().getName()+" - Recepcion mensaje: remove");
			m =mensajes.remove();
			
		}

		synchronized (m)
		{
			System.out.println(Thread.currentThread().getName()+" - Recepcion mensaje: going to notify");
			m.notify();
			System.out.println(Thread.currentThread().getName()+" - Recepcion mensaje: notified");
		}
		

		
	}


	public void aDumir() throws InterruptedException
	{
		wait();
	}





		 //#consultas por cliente, # clientes,# servidores estan en arcihvo de texto. son parametros

}
