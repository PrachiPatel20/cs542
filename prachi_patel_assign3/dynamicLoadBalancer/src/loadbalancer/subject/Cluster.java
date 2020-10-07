package loadbalancer.subject;
import loadbalancer.util.FileProcessor;
import loadbalancer.entities.Machine;
import loadbalancer.entities.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class Cluster {
		// Hostnames to corresponding machine instances.
		private Map<String, String> machines = new HashMap<String,String>();
    private static List<String> machinelist = new ArrayList<String>();
		private static List<String> machinegetlist = new ArrayList<String>();
    // Rest of the code.
   private String message;
   private String sendmessage;
   static Machine m;
   
    public void addMachine(String s) {
      if(machinelist.contains(s)) {
        //sendmessage = "Machine already exists";
      }
      else {
        //sendmessage = "Machine created";
        m = new Machine(s);
        machinelist.add(s);
        System.out.println("Machine created");
      }
    }
    public boolean machineexists(String m) {
      if(machinelist.contains(m)) { return true; }
      else { return false; }
    }
    public List<String> getList()
    {
      return machinelist;
    }
    public void removeList(String s)
    {
      machines.remove(s);
      System.out.println("Service "+s+" removed");
    }
    public void removeListIn(String s1,String s2)
    {
      machines.containsValue(s2);
      System.out.println("Service "+s1+" removed");
    }
    public void addincluster(String hn,String sIn)
    {
      machines.put(sIn,hn);
    }
    /*public String messagedisplay() 
    {
      message = sendmessage;
      return message;
    }*/
    public void addservmach(String servicename,List<String> machine) {
      for(int i=0;i<machine.size();i++) {
        if(machinelist.contains(machine.get(i))) {
          m.assignService(servicename,machine.get(i));
          //m.print();
          machinegetlist.add(machine.get(i));
          System.out.println("Service"+servicename+"created on machine"+machine.get(i));
        }
        else {
          System.out.println("Machine does not exists");
        }
      }
    }
    public void addservmachI(String servicename,String machine) {
        if(machinelist.contains(machine)) {
          m.assignService(servicename,machine);
          //m.print();
          System.out.println("Service"+servicename+"created on machine"+machine);
        }
        else {
          System.out.println("Machine does not exists");
        }
    }
    
    public void removeInstance(String servicename, String hostname) {
      if(machinelist.contains(hostname)) {
        machinelist.remove(hostname);
        System.out.println("Machine removed");
      }
      else {
        System.out.println(hostname+" does not exists");
      }
    }
     
    public void removeMachine(String s) {
      if(machinelist.contains(s)) {
        machinelist.remove(s);
        System.out.println("Removed");
      }
      else {
        System.out.println("Does not exists");
      }
    }
    
    public List<String> getMachineList(String service)
    {
      return machinegetlist;
    }
    
    
}