package project1;

import java.util.*;

public class Drop{
	private ArrayList<Job> unclaimed;
	
	private Hashtable<String, Job> claimed;
	
	private Hashtable<String, Output> outputs;
	
	//private int nProducers;
	
	public Drop()
	{
		unclaimed = new ArrayList<Job>();
		
		claimed = new Hashtable<String, Job>();
		
		outputs = new Hashtable<String, Output>();
	}
	
	public synchronized void put(Job j)
	{
		while (this.unclaimed.size() >= 100000) {
            try { 
                wait();
            } catch (InterruptedException e) {}
        }
        
        //allow Producers to unregister
        
        this.unclaimed.add(j);
        
        if (!(j instanceof SubJob))
        {
        	Output out = new Output(j);
        	outputs.put(j.getId(), out);
        }
        
        notifyAll();
	}
	
	public synchronized Job claim()
	{
		while (this.unclaimed.size() == 0) {
            try { 
                wait(1000);
                if (this.unclaimed.size() == 0)
                {
		            if (!claimed.isEmpty())
		            {
		            	Enumeration<String> keys = claimed.keys();
		            	//int ind = ThreadLocalRandom.current() .nextInt(0, keys.size());
		            	String key = keys.nextElement();
		            	return claimed.get(key);
		            }
		            if (claimed.isEmpty())
		            {
				        wait(20000);
				        if ((this.unclaimed.size() == 0) && claimed.isEmpty())
				            return null;
		            }
                }
            } catch (InterruptedException e) {}
        }
        
        Job j = unclaimed.remove(unclaimed.size() - 1);
        
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
		Output out = outputs.get(j.getId());
		if (out == null)
			return;
		
		out.insertData(j, data);
		notifyAll();
	}
	
	public synchronized int[][] getData(Job j)
	{
		Output out = outputs.get(j.getId());
		if (out == null)
			return null;
			
		while (!(out.isFull())) {
            try { 
                wait();
            } catch (InterruptedException e) {}
        }
			
		int[][] data = out.getData();
		outputs.remove(j.getId()); //not sure if this leaves data in tact
		
		return data;
	}
	
	/*public synchronized register()
	{
		nProducers++;
	}*/
}
