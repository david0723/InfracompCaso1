
public class Mensaje
{

	private int msn;
	public Mensaje(int msn)
	{
		this.setMsn(msn);
	}
	
	public void aDumir() throws InterruptedException
	{
		wait();
	}
	
	public void adespertar() throws InterruptedException
	{
		notify();
	}

	public int getMsn() {
		return msn;
	}

	public void setMsn(int msn) {
		this.msn = msn;
	}
	
}
