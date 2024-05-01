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
            
             // Get the RMI registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Create an array to hold the results from each client
            double[][] totalResults = null;

            // Divide the points into chunks
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

            // Get the number of clients
            int numClients = 3; // Change this according to the number of clients

            // Calculate chunk size
            int chunkSize = points.length / numClients;

            // Create an array to hold the results from each client
            totalResults = new double[points.length][2];

            // Send each chunk to a client
            for (int i = 0; i < numClients; i++) {
                // Lookup the client
                ClientInter client = (ClientInter) registry.lookup("client" + (i + 1));

                // Calculate start and end index of the chunk
                int startIndex = i * chunkSize;
                int endIndex = Math.min(startIndex + chunkSize, points.length);

                // Extract the chunk of points
                double[][] chunk = new double[endIndex - startIndex][];
                System.arraycopy(points, startIndex, chunk, 0, endIndex - startIndex);

                // Process the chunk and store the results
                double[][] chunkResults = client.processChunk(chunk, centroids);
                System.arraycopy(chunkResults, 0, totalResults, startIndex, chunkResults.length);
            }

            // Print the total results
            System.out.println("************** Total Results: ****************");
            for (int i = 0; i < totalResults.length; i++) {
                System.out.println("Point " + (i + 1) + " is closest to centroid (" + totalResults[i][0] + ", " + totalResults[i][1] + ")");
            }



        } catch (RemoteException e) {
            System.out.println("RemoteException at server");
            e.printStackTrace();
        } catch (NotBoundException e) {
            System.out.println("NotBoundException");
            e.printStackTrace();
        }
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



// Export the ComputeServiceImpl object as a remote object
            // ComputeService csi_stub = (ComputeService) UnicastRemoteObject.exportObject(csi, 0);

            // Create a registry on port 1099
            // Registry registry = LocateRegistry.createRegistry(1098);

// Rebind the ComputeService object to the registry
            // registry.rebind("ComputeService", csi_stub);

            // Look up the ClientInter object from the registry
            
// ComputeService csi = new ComputeServiceImpl();




// / Call the summer method on the client
            // ************************************** useful **********************
            // for (int i=0;i<2;i++){
            //     ClientInter client = (ClientInter) registry.lookup("client"+(i+1));
            //     int res = client.summer(10, 10);
            //     System.out.println("Result from Client " + (i + 1) + ": " + res);

            // // Print the result
            //     System.out.println("res  of server = " + res);
            // }
            // ************************************** useful **********************



                // // Test method to simulate sending a chunk of points to a client
    // private void testProcessChunk() {
    //     try {
    //         // Create a client
    //         ClientInter client = new ClientImpl("client1");

    //         // Generate some random points
    //         double[][] points = {
    //                 {1.0, 2.0},
    //                 {3.0, 4.0},
    //                 {5.0, 6.0}
    //         };

    //         // Generate some random centroids
    //         double[][] centroids = {
    //                 {2.0, 2.0},
    //                 {4.0, 4.0}
    //         };

    //         // Process the chunk of points
    //         client.processChunk(points, centroids);
    //     } catch (RemoteException e) {
    //         e.printStackTrace();
    //     }
    // }