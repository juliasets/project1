package project1;

public class Drop{
	private List unclaimed;
	
	private Dictionary claimed;
	
	private Dictionary outputs;
	
	private int nProducers;
	
	public Drop()
	{
		unclaimed = new ArrayList<Job>();
		
		claimed = new Hashtable<String, Job>();
		
		outputs = new Hashtable<String, ***>();
	}
	
	public synchronized void put(Job j)
	{
		while (this.unclaimed.size() >= 1000) {
            try { 
                wait();
            } catch (InterruptedException e) {}
        }
        
        //allow Producers to unregister
        
        this.unclaimed.add(j);
        
        notifyAll();
	}
	
	public synchronized register()
	{
		nProducers++;
	}
}
