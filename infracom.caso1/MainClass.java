import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;


public class MainClass 
{

	public static void main(String[] args) 
	{
		//#consultas por cliente, # clientes,# servidores estan en arcihvo de texto. son parametros

		ArrayList<Thread> clientes = new ArrayList<Thread>();
		ArrayList<Thread> servidores = new ArrayList<Thread>();
		Buffer buffer = new Buffer(50);
		
		Properties datos = new Properties();
		InputStream in=null;
		int nser = 0;
		int nclientes = 0;
		
		try
		{
		
			in =  new FileInputStream("docs/datos.properties");
			datos.load(in);
			
			nser = Integer.parseInt(datos.getProperty("servidores"));
			nclientes = Integer.parseInt(datos.getProperty("clientes"));

		
		}
		catch(IOException  e)
		{
			
		}
		finally
		{
			if (in != null) 
			{
	            try {
	                in.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
			
			System.out.println("Se tienen "+nclientes+" clientes y "+nser+" servidores");

		}
		
		for (int i = 0; i!=nclientes;i++)
		{
			int nmen = ((int) (Math.random()*50));
			
			 clientes.add(new Thread(new Cliente(nmen, buffer), "Cliente "+i));
			 System.out.println("Cliente "+i+" creado con "+nmen+" mensajes para enviar.");
		}
		
		for(int i = 0; i!=nser;i++)
		{
			 servidores.add(new Thread(new Servidor(buffer), "Servidor "+i));
		}	 
		
		for (int i = 0; i!=nclientes;i++)
		{
			clientes.get(i).start();
		}	
		
		for(int i = 0; i!=nser;i++)
		{	
			servidores.get(i).start();
		}
		
		
		

		
		

	}

}
