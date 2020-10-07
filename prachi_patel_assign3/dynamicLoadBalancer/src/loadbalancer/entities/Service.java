package loadbalancer.entities;
import loadbalancer.observer.LoadBalancer;
import loadbalancer.observer.ServiceManager;
import loadbalancer.util.FileProcessor;
import java.util.List;
import java.util.ArrayList;

public class Service {
		// Service URL.
		private String URL;
		// Service name.
		private String serviceName;
		// Rest of the code.
    public Service(String s1,String s2) {
      setServiceName(s1);
      setURL(s2);
    }
    public Service(String servi) {
      setServiceName(servi);
    }
    
    private void setServiceName(String serviceNameIn) {
     serviceName = serviceNameIn;
   }
   private void setURL(String URLIn)
   {
     URL = URLIn;
   }
   
   public String getServiceName()
   {
     return this.serviceName;
   }
   public String getURL()
   {
     return this.URL;
   }
   
}