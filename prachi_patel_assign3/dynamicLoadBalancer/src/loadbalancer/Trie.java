package loadbalancer;
import loadbalancer.observer.LoadBalancer;
import loadbalancer.observer.ServiceManager;
import loadbalancer.entities.Service;
import java.util.List;
import java.util.Map;
import java.util.HashMap; 

public class Trie {
  private Map<String, ServiceManager> smap = new HashMap<String, ServiceManager>();
  private Map<String, ServiceManager> map = new HashMap<String, ServiceManager>(); 
  
  private List<String> list_machines;
  public List<String> fetchServiceDetails(String service) {
    //Service s = new Service(service);
    //map.put(s,new ServiceManager(service));
    //list_machines = (map.get(s)).printList();
    //System.out.println("-----"+list_machines);
    return list_machines;
  }
  
  
  
  public void addServ(List<String> sd)
  {
    if(smap.containsKey(sd.get(0))) {
      System.out.println("Already exists");
    }
    else {
      smap.put(sd.get(0),new ServiceManager(sd));
    }
  }
  public void addIns(List<String> sd)
  {
    if(smap.containsKey(sd.get(0))) {
      System.out.println("Service Already exists");
    }
    else {
      System.out.println("Service doesnot exists");
    }
  }
}