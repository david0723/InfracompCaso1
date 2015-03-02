

import java.util.concurrent.LinkedBlockingQueue;




public class Buffer 
{

	public LinkedBlockingQueue<Mensaje> mensajes;

	public Buffer(int n)
	{
		mensajes = new LinkedBlockingQueue<Mensaje>(n);
	}


	public void envioCliente(Mensaje m) 
	{
		boolean b;
		synchronized (mensajes) 
		{
			b = mensajes.offer(m);
		}
		
		while(!b)
		{
			System.out.println(Thread.currentThread().getName()+" - Mensaje no ingresado aun: procesador cedido");
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
			System.out.println(Thread.currentThread().getName()+" - Mensaje enviado correctamente. Notificando");

		}
		
	}

	public void recibirServidor()
	{
		Mensaje m;
		boolean b;
		synchronized (mensajes) 
		{
			b= mensajes.isEmpty();
		}
		while (b)
		{
			System.out.println(Thread.currentThread().getName()+" - La cola esta vacia. Esperando");
			try 
			{
				synchronized (this)
				{
					//System.out.println(Thread.currentThread().getName()+" - Sleep");
					wait();
					//System.out.println(Thread.currentThread().getName()+" - Wake up");
				}
				
			} 
			catch (InterruptedException e) {e.printStackTrace();}
			
			synchronized (mensajes) 
			{
				b= mensajes.isEmpty();
			}
		}
		
		m = mensajes.remove();
		int resp = m.getMsn()+1;
		System.out.println(Thread.currentThread().getName()+" - Mensaje respondido: " + resp);

		synchronized (m)
		{
			//System.out.println(Thread.currentThread().getName()+" - Recepcion mensaje: going to notify");
			m.notify();
			System.out.println(Thread.currentThread().getName()+" - Mensaje respondido correctamente. Notificando");
		}
		

		
	}








}
