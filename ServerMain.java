// import java.rmi.NotBoundException;
// import java.rmi.RemoteException;
// import java.rmi.registry.LocateRegistry;
// import java.rmi.registry.Registry;
// import java.rmi.server.UnicastRemoteObject;

// public class ServerMain {
//     public static void main(String args[]) {
//         ComputeService csi = new ComputeServiceImpl();
//         try{
//             ComputeService csi_stub = (ComputeService) UnicastRemoteObject.exportObject(csi, 0);
//             Registry registry = LocateRegistry.createRegistry(1099);
//             registry.rebind("ComputeService", csi_stub);



//             ClientInter client = (ClientInter) registry.lookup("ClientInter");

//             int res = client.summer(10,10);

//             System.out.println("res  of server = " + res);
            
//         }
//         catch(RemoteException e){
//             System.out.println("RemoteException at client");            
//         }
//         catch(NotBoundException e){
//             System.out.println("NotBoundException");
//         }
//     }
// }






import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerMain {
    public static void main(String args[]) {
        
        try {
            
             Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            

            // Call the summer method on the client
            // ************************************** useful **********************
            // for (int i=0;i<2;i++){
            //     ClientInter client = (ClientInter) registry.lookup("client"+(i+1));
            //     int res = client.summer(10, 10);
            //     System.out.println("Result from Client " + (i + 1) + ": " + res);

            // // Print the result
            //     System.out.println("res  of server = " + res);
            // }
            // ************************************** useful **********************
            ClientInter client = (ClientInter) registry.lookup("client1");
            
            double[][] resultsArray ;
            
            double[][] points = {
                {1.0, 2.0},
                {3.0, 4.0},
                {5.0, 6.0}
        };

        // Generate some random centroids
        double[][] centroids = {
                {2.0, 2.0},
                {4.0, 4.0}
        };

        // Process the chunk of points
        resultsArray=client.processChunk(points, centroids);
        System.out.println("**************Results:******************");
        for (int i = 0; i < resultsArray.length; i++) {
            System.out.println("Point " + (i + 1) + " is closest to centroid (" + resultsArray[i][0] + ", " + resultsArray[i][1] + ")");
        }


        } catch (RemoteException e) {
            System.out.println("RemoteException at server");
            e.printStackTrace();
        } catch (NotBoundException e) {
            System.out.println("NotBoundException");
            e.printStackTrace();
        }
    }



    // // @Override
    // public void processChunk(double[][] points, double[][] centroids)  {
    //     // Dummy implementation for testing
    //     // System.out.println("Received chunk of points for processing:");
    //     // for (int i = 0; i < points.length; i++) {
    //     //     System.out.println("Point " + (i + 1) + ": (" + points[i][0] + ", " + points[i][1] + ")");
    //     // }
    //     // System.out.println("Processing chunk...");
    //     // Simulate processing by sending each point to processPoint method
    //     // for (ClientInter client : clients) {
    //         client.processChunk(points, centroids);
    //     // }
    // }

    // Test method to simulate sending a chunk of points to a client
    private void testProcessChunk() {
        try {
            // Create a client
            ClientInter client = new ClientImpl("client1");

            // Generate some random points
            double[][] points = {
                    {1.0, 2.0},
                    {3.0, 4.0},
                    {5.0, 6.0}
            };

            // Generate some random centroids
            double[][] centroids = {
                    {2.0, 2.0},
                    {4.0, 4.0}
            };

            // Process the chunk of points
            client.processChunk(points, centroids);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}









// Export the ComputeServiceImpl object as a remote object
            // ComputeService csi_stub = (ComputeService) UnicastRemoteObject.exportObject(csi, 0);

            // Create a registry on port 1099
            // Registry registry = LocateRegistry.createRegistry(1098);

// Rebind the ComputeService object to the registry
            // registry.rebind("ComputeService", csi_stub);

            // Look up the ClientInter object from the registry
            
// ComputeService csi = new ComputeServiceImpl();