package loadbalancer.observer;
import loadbalancer.util.FileProcessor;
import loadbalancer.entities.Service;
import loadbalancer.subject.Cluster;
import loadbalancer.observer.ServiceManager;
import loadbalancer.Trie;

import java.util.List;
import java.util.ArrayList;

public class LoadBalancer {
		// Index to find the URL and hostname for a given service name.
		//
		// Trie is optional for under-graduate students.
		// Graduate students have to use a Trie datastructure.
		//private Trie ServiceURLAndHostnameFetcher;



		// Rest of the code.
   
   private ArrayList<String> hostnames = new ArrayList<String>();
   private String hostn;
   private String servicename;
   private String URL;
   private ArrayList<String> serv_mach = new ArrayList();
   private ArrayList<String> servicelist = new ArrayList();
   private List<String> mlist= null;
   Cluster c = new Cluster();
   Trie t= new Trie();
   ServiceManager sm = new ServiceManager();
   public void request(String s) {
     mlist = t.fetchServiceDetails(s);
     System.out.println("Processed request");
     System.out.println(mlist);
   }
   
   public void addService(List<String> s){
     t.addServ(s);
   }
   
   public void removeService(String s) {
     //System.out.println(s);
     sm.removeServ(s);
   }
   public void addInstance(List<String> s) {
     sm.addIns(s);
     /*if(servicelist.contains(s.get(0))) {
       c.addservmachI(s.get(0),s.get(1));
     }
     else {
       System.out.println(s.get(0)+" does not exists so can't add instance");
     }*/
   }
   public void removeInstance(List<String> s) {
     sm.removeIns(s);
   }
   
   private void setHostnames(String hostnamesIn)
  {
    String[] sep1 = hostnamesIn.split(",");
    for(int i=0;i<sep1.length;i++)
    {
      hostnames.add(sep1[i]);
    }
  }
  private void setHostn(String hostnIn) {
    hostn = hostnIn;
  }
  
  public ArrayList<String> getHostnames()
  {
    return this.hostnames;
  }
  public String getHostn() 
  {
    return this.hostn;
  }
}

