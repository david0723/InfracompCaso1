

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
			b = mensajes.offer(m);
		}
			while(!b)
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
				System.out.println(Thread.currentThread().getName()+" - Sleep");
				m.wait();
				System.out.println(Thread.currentThread().getName()+" - Wake up");
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
		boolean b;
		synchronized (mensajes) 
		{
			b= mensajes.isEmpty();
		}
		while (b)
		{
			System.out.println(Thread.currentThread().getName()+" - espera");
			try 
			{
				synchronized (this)
				{
					System.out.println(Thread.currentThread().getName()+" - Sleep");
					wait();
					System.out.println(Thread.currentThread().getName()+" - Wake up");
				}
				
			} 
			catch (InterruptedException e) {e.printStackTrace();}
			synchronized (mensajes) 
			{
				b= mensajes.isEmpty();
			}
		}
		
		m =mensajes.remove();
		System.out.println(Thread.currentThread().getName()+" - remove, msn:" + m.getMsn()+1);

		synchronized (m)
		{
			System.out.println(Thread.currentThread().getName()+" - Recepcion mensaje: going to notify");
			m.notify();
			System.out.println(Thread.currentThread().getName()+" - Recepcion mensaje: notified");
		}
		

		
	}







		 //#consultas por cliente, # clientes,# servidores estan en arcihvo de texto. son parametros

}
