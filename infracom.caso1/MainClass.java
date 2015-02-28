import java.util.ArrayList;


public class MainClass 
{

	public static void main(String[] args) 
	{
		
		ArrayList<Thread> clientes = new ArrayList<Thread>();
		ArrayList<Thread> servidores = new ArrayList<Thread>();
		Buffer buffer = new Buffer(50);
		
		for (int i = 0; i!=10;i++)
		{
			 clientes.add(new Thread(new Cliente(12, buffer), "Cliente "+i));
			 servidores.add(new Thread(new Servidor(buffer), "Servidor "+i));
		}
		
		for (int i = 0; i!=10;i++)
		{

			clientes.get(i).start();
			servidores.get(i).start();
		}

		
		

	}

}
