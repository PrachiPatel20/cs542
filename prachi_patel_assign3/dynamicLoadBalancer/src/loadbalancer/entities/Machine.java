package loadbalancer.entities;
import loadbalancer.observer.LoadBalancer;
import loadbalancer.observer.ServiceManager;
import loadbalancer.subject.Cluster;
import loadbalancer.util.FileProcessor;
import loadbalancer.entities.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap; 

public class Machine {
		private String hostname;
		// Service name to hosted services.
		private Map<String, String> hostedServices = new HashMap<String,String>();
   Cluster c = new Cluster();
		// Rest of the code.
   public Machine() {}
   public Machine(String host) {
      setHostname(host);
    }
    public void assignService(String serviceIn, String hostnameIn){
      setHostname(hostnameIn);
      hostedServices.put(getHostname(),serviceIn);
      c.addincluster(getHostname(),serviceIn);
    }
    private void setHostname(String hostnameIn) {
      hostname = hostnameIn;
    }
    public String getHostname() {
      return hostname;
    }
    
}