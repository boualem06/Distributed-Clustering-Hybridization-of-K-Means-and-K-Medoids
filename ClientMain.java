// import java.rmi.NotBoundException;
// import java.rmi.RemoteException;
// import java.rmi.registry.LocateRegistry;
// import java.rmi.registry.Registry;
// import java.rmi.server.UnicastRemoteObject;

// public class ClientMain {
//     public static void main(String args[]){

//         ClientInter cf=new ClientImpl()   ;
//         try{
//             ClientInter cf_stub = (ClientInter) UnicastRemoteObject.exportObject(cf, 0);   
//             Registry registry = LocateRegistry.getRegistry("localhost", 1099);
//             registry.rebind("ClientInter", cf_stub);


//             // Registry registry = LocateRegistry.getRegistry();
//             ComputeService server = (ComputeService) registry.lookup("ComputeService");

//             int res = server.summer(5,4);

//             System.out.println("res = " + res);
//         }
//         catch(RemoteException e){
//             System.out.println("RemoteException at server");            
//         }
//         catch(NotBoundException e){
//             System.out.println("NotBoundException");
//         }
//     }


// }


// // public static void main(String args[]){
// //     ComputeService csi = new ComputeServiceImpl();
// //     try{
// //         ComputeService csi_stub = (ComputeService) UnicastRemoteObject.exportObject(csi, 0);
// //         Registry registry = LocateRegistry.createRegistry(1099);
// //         registry.rebind("ComputeService", csi_stub);
        
// //     }
// //     catch(RemoteException e){
// //         System.out.println("RemoteException");
// //     }
// // }




import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ClientMain {
    public static void main(String args[]) {

        try {
            
            Registry registry ;

            //getting the name of the client 
            String customName = args.length > 0 ? args[0] : "Client";

            // Create the RMI registry only for the first client
            if (args.length > 1 && args[1].equals("createRegistry")) {
                registry = LocateRegistry.createRegistry(1099);
                System.out.println("RMI registry created");
            }

            // Get the registry if its not the first client 
            else{
                 registry = LocateRegistry.getRegistry("localhost", 1099);
            }
            
            // Create an instance of the client implementation
            ClientInter cf = new ClientImpl(customName);

            // Export the client implementation as a remote object
            ClientInter cf_stub = (ClientInter) UnicastRemoteObject.exportObject(cf, 0);
            
            // Rebind the client implementation to the registry
            registry.rebind(customName, cf_stub);
            System.out.println(cf.getName() + " ready");

            
        } catch (RemoteException e) {
            System.out.println("RemoteException at client");
            e.printStackTrace();
        } 
       
    }
}

 // Registry registry = LocateRegistry.createRegistry(1099);
  // catch (NotBoundException e) {
        //     System.out.println("NotBoundException");
        //     e.printStackTrace();
        // }

// Look up the ComputeService object from the registry
            // ComputeService server = (ComputeService) registry.lookup("ComputeService");

            // // Call the summer method on the server
            // int res = server.summer(5, 4);

            // // Print the result
            // System.out.println("res = " + res);