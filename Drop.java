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
	
	public synchronized Job claim()
	{
		while (this.unclaimed.size() == 0) {
            try { 
                wait();
            } catch (InterruptedException e) {}
        }
        
        Job j = unclaimed.remove(0);
        String id;
        if (j instanceof SubJob)
        {
        	id = j.getSubId();
        }
        else
        {
        	id = j.getId();
        }
        
        claimed.put(id, j);
        return j;
	}
	
	public synchronized void finish(Job j)
	{
		String id;
        if (j instanceof SubJob)
        {
        	id = j.getSubId();
        }
        else
        {
        	id = j.getId();
        }
        
        claimed.remove(id);
	}
	
	public synchronized void putData(Job j, int[][] data)
	{
	
	}
	
	public synchronized register()
	{
		nProducers++;
	}
}
