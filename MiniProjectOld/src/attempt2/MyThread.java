package attempt2;

public class MyThread implements Runnable {
	
	int ID;
	
	MyThread(int ID){
		this.ID = ID;
	}
	public int getId() {
		return this.ID;
	}
	
	@Override
	public synchronized void run() {
		System.out.println("I am thread: " + this.ID);
		
	}
	
}
