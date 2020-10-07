package loadbalancer.observer;
import loadbalancer.util.FileProcessor;
import loadbalancer.observer.LoadBalancer;
import loadbalancer.entities.Service;
import loadbalancer.entities.Machine;
import loadbalancer.subject.Cluster;
import java.util.List;
import java.util.ArrayList;

public class ServiceManager {
		//private String key;
    
		// Information pertaining to the service.
   private String servicename;
		private String URL;
		private List<String> hostnames = new ArrayList<String>();
    Cluster cl = new Cluster(); 
    Machine m=new Machine();
		// Rest of the code.
    private static List<String> serv = new ArrayList<String>();
    //private List<String> machinegetlist = new ArrayList<String>();
    public ServiceManager() { }
    public ServiceManager(List<String> se)
    {
      setServicename(se.get(0));
      setURL(se.get(1));
      setHostnames(se.get(2));
      addServ();
    }
    public void addServ()
    {
      Service s = new Service(getServicename(),getURL());
      serv.add(getServicename());
      for(int i=0;i<getHostnames().size();i++) {
        if(cl.machineexists(getHostnames().get(i))) {
          m.assignService(servicename,getHostnames().get(i));
          System.out.println("Service"+servicename+"created on machine"+getHostnames().get(i));
          
        }
        else {
          System.out.println("Machine does not exists to add service");
        }
      }
    }
    public void addIns(List<String> se)
    {
      setServicename(se.get(0));
      if(serv.contains(getServicename())) {
        if(cl.machineexists(se.get(1))) {
          m.assignService(servicename,se.get(1));
          System.out.println("Service"+servicename+"created on machine"+se.get(1));
          
        }
        else {
          System.out.println("Machine does not exists to add instance");
        }
      }
      else {
        System.out.println("Service doesnot exists to add instance");
      }
    }
    public void removeServ(String s)
    {
      if(serv.contains(s))
      {
        cl.removeList(s);
      }
      else
      {
        System.out.println("Service does not exists to remove");
      }
    }
    
    public void removeIns(List<String> s)
    {
      if(serv.contains(s.get(0)))
      {
        cl.removeListIn(s.get(0),s.get(1));
      }
      else
      {
        System.out.println("Service does not exists to remove");
      }
    }
    
    public void setURL(String URLIn)
    {
      URL = URLIn;
    }
    public void setServicename(String servicenameIn)
    {
      servicename = servicenameIn;
    }
    private void setHostnames(String hostnamesIn)
  {
    String[] sep1 = hostnamesIn.split(",");
    for(int i=0;i<sep1.length;i++)
    {
      hostnames.add(sep1[i]);
    }
  }
  
  
    public List<String> printList()
    {
      return hostnames;
    }
  
  
    public String getURL()
    {
      return URL;
    }
    public String getServicename() 
    {
      return servicename;
    }
    public List<String> getHostnames()
  {
    return this.hostnames;
  }
}