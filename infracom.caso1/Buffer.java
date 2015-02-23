import java.util.ArrayList;


public class Buffer {


	//ths piden siempre los mensajes al buffer. el programa se termina cuando ya no tengan mas que pedir
	// ths responden con el numero aumentado
	public ArrayList<Mensaje> mensajes;

	public int nClientes;
	public int nServidores;

	public Buffer()
	{
		mensajes = new ArrayList<Mensaje>();
		nClientes = 0;
		nServidores = 0;
	}


	//Cliente son redactores, 
	public synchronized void entrarEnvioCliente()
	{
		while( nClientes!=0 || nServidores!=0)
		{
			try 
			{
				wait();
			} catch (InterruptedException e) {
				System.out.println("Error esperando, al enviar mensaje");
			}
			nClientes++;
		}
	}


	public synchronized void envioCliente(Mensaje mes)
	{

	}

	public synchronized void salirEnvioCliente()
	{
		nClientes--;
		notifyAll();
	}


	//Servidor lector
	public synchronized void entrarRecibirServidor()
	{
		while( nClientes!=0)
		{
			try 
			{
				wait();
			} catch (InterruptedException e) {
				System.out.println("Error esperando, al recibir mensaje");
			}
			nServidores++;
		}
	}


	public synchronized void recibirServidor()
	{

	}

	public synchronized void salirRecibirServidor()
	{
		nServidores--;
		if(nServidores == 0)
			notify();
	}


	/**
	 * 
	 */
	 public static void main()
	 {
		 //#consultas por cliente, # clientes,# servidores estan en arcihvo de texto. son parametros



	 }

}
