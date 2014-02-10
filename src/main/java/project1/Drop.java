package project1;

import java.util.Dictionary;

public class Drop{
	private List unclaimed;
	
	private Dictionary<K, V> claimed;
	
	private Dictionary outputs;
	
	//private int nProducers;
	
	public Drop()
	{
		unclaimed = new ArrayList<Job>();
		
		claimed = new Hashtable<String, Job>();
		
		outputs = new Hashtable<String, Output>();
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
        
        if (!(j instanceof SubJob))
        {
        	Output out = new Output(j);
        	outputs.put(j.getId(), out);
        }
        
        notifyAll();
	}
	
	public synchronized Job claim()
	{
		while ((this.unclaimed.size() == 0)&&(!claimed.isEmpty())) {
            try { 
                wait(1000);
                if (!claimed.isEmpty())
                {
                	Set<String> keys = claimed.keySet();
                	int ind = ThreadLocalRandom.current().nextInt(0, keys.size());
                	String key = keys[ind];
                	return claimed.get(key);
                }
                wait(1000);
                return NULL;
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
		Output out = outputs.get(j.getId());
		if (out == NULL)
			return;
		
		out.insertData(j, data);
	}
	
	public synchronized int[][] getData(Job j)
	{
		Output out = outputs.get(j.getId());
		if (out == NULL)
			return;
			
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
