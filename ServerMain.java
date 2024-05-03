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
import java.util.Arrays;

public class ServerMain {

    public static int[] findNearestPointToCentroid(double[][] points, double[][] centroids) {
        int[] nearestIndices = new int[centroids.length];
        
        for (int i = 0; i < centroids.length; i++) {
            double minDistance = Double.MAX_VALUE;
            int nearestIndex = -1;
            
            for (int j = 0; j < points.length; j++) {
                double distance = calculateDistance(points[j], centroids[i]);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestIndex = j;
                }
            }
            
            nearestIndices[i] = nearestIndex;
        }
        
        return nearestIndices;
    }


    public static double calculateDistance(double[] point1, double[] point2) {
        double sum = 0.0;
        for (int i = 0; i < point1.length; i++) {
            sum += Math.pow(point1[i] - point2[i], 2);
        }
        return Math.sqrt(sum);
    }

    //******************************************methodes for the kmedoides */
    // Method to assign each point to a cluster based on the distances
    public static int[] assignClusters(double[][][] clientResults, int numDataPoints) {
        int[] clusters = new int[numDataPoints]; // Store the cluster index for each data point
        System.out.println(clientResults[1].length);
        int numpoint=0 ;
        for (int i = 0; i < clientResults.length; i++) {
            

            // Find the cluster with the minimum distance for each point
            for (int j = 0; j < clientResults[i].length; j++) {
                double minDistance = Double.MAX_VALUE;
                int clusterIndex = -1; // Initialize cluster index
                for (int k=0;k<clientResults[0][0].length;k++){
                    double distance=clientResults[i][j][k];
                    if (distance < minDistance) {
                        minDistance = distance;
                        clusterIndex = k; // Update cluster index
                    }
                }
                clusters[numpoint] = clusterIndex;
                numpoint++ ;
                // double distance = clientResults[i][j][0]; // Assuming the first column stores distances
            }
            
        }
        return clusters;
    }


    public static void main(String args[]) {
        
        try {
            
             // Get the RMI registry
            // Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // // Create an array to hold the results from each client
            // double[][] totalResults = null;

            // // Divide the points into chunks
            // double[][] points = {
            //     {1, 2},
            //     {3, 4},
            //     {5, 6},
            //     {2, 2},
            //     {3, 4},
            //     {5, 6},
            //     {7, 8},
            //     {1, 1},
            // };
            
            // // Generate some random centroids
            // double[][] centroids = {
            //     {2, 2},
            //     {4, 4}
            // };

            
            // // Get the number of clients
            // int numClients = 3; // Change this according to the number of clients

            // // Calculate chunk size
            // int chunkSize = points.length / numClients;

            // // Create an array to hold the results from each client
            // totalResults = new double[points.length][2];


            // int maxIterations = 10; // Change this as needed
            // int iterations = 0;

            // boolean centroidsChanged = true;
            // while (centroidsChanged && iterations < maxIterations) {
            //     centroidsChanged = false;

            //     // Send each chunk to a client
            //     for (int i = 0; i < numClients; i++) {
            //         // Lookup the client
            //         ClientInter client = (ClientInter) registry.lookup("client" + (i + 1));

            //         // Calculate start and end index of the chunk
            //         int startIndex = i * chunkSize;
            //         int endIndex = Math.min(startIndex + chunkSize, points.length);

            //         // Extract the chunk of points
            //         double[][] chunk = new double[endIndex - startIndex][];
            //         System.arraycopy(points, startIndex, chunk, 0, endIndex - startIndex);

            //         // Process the chunk and store the results
            //         double[][] chunkResults = client.processChunk(chunk, centroids);
            //         System.arraycopy(chunkResults, 0, totalResults, startIndex, chunkResults.length);
            //     }

            //     // Update centroids based on the totalResults
            //     for (int i = 0; i < centroids.length; i++) {
            //         double sumX = 0;
            //         double sumY = 0;
            //         int count = 0;
            //         for (int j = 0; j < totalResults.length; j++) {
            //             if (totalResults[j][0] == centroids[i][0] && totalResults[j][1] == centroids[i][1]) {
            //                 sumX += points[j][0];
            //                 sumY += points[j][1];
            //                 count++;
            //             }
            //         }
            //         double newX = count > 0 ? sumX / count : centroids[i][0];
            //         double newY = count > 0 ? sumY / count : centroids[i][1];
            //         if (newX != centroids[i][0] || newY != centroids[i][1]) {
            //             centroidsChanged = true;
            //             centroids[i][0] = newX;
            //             centroids[i][1] = newY;
            //         }
            //     }

            //     // Print the updated centroids
            //     System.out.println("************** Updated Centroids after Iteration " + (iterations + 1) + ": ****************");
            //     for (int i = 0; i < centroids.length; i++) {
            //         System.out.println("Centroid " + (i + 1) + ": (" + centroids[i][0] + ", " + centroids[i][1] + ")");
            //     }

            //     iterations++;
            // }

            // // Print if the centroids converge or reached max iterations
            // if (!centroidsChanged) {
            //     System.out.println("Centroids converged after " + iterations + " iterations.");
            // } else {
            //     System.out.println("Max iterations reached (" + maxIterations + ").");
            // }


            // // After the k-means algorithm, find the nearest point to each centroid
            // int[] finalNearestIndices = findNearestPointToCentroid(points, centroids);
            
            // // Print the nearest points to centroids
            // System.out.println("************** Nearest Points to Centroids: ****************");
            // for (int i = 0; i < centroids.length; i++) {
            //     int nearestIndex = finalNearestIndices[i];
            //     System.out.println("Centroid " + (i + 1) + " is closest to point (" + points[nearestIndex][0] + ", " + points[nearestIndex][1] + ")");
            // }

            //*****************************************************the begining of the kmedoides algorithme **************************
            // Get the RMI registry
            



            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Divide the points into chunks
            double[][] points = {
                {2.0, 6.0},
                {3.0, 4.0},
                {1.0, 1.0},
                {7.0, 4.0},
                {8.0, 5.0},
                {9.0, 5.0},
                // {6.0, 4.0},
                // {7.0, 3.0},
                // {7.0, 4.0},
                // {8.0, 5.0}
            };

            // Generate some random medoids
            double[][] medoids = {
                {3.0, 4.0},
                {7.0, 4.0}
            };

            // Get the number of clients
            int numClients = 3;

            // Calculate chunk size
            int chunkSize = points.length / numClients;

            // Create an array to hold the results from each client
            double[][][] clientResults = new double[numClients][][];


            int maxIterations = 10;
            boolean converged = false;
            int test ;
            // Send each chunk to a client
            for (int iteration = 0; iteration < maxIterations && !converged; iteration++) {
                
                for (int i = 0; i < numClients; i++) {
                    // Lookup the client
                    ClientInter client = (ClientInter) registry.lookup("client" + (i + 1));
    
                    // Calculate start and end index of the chunk
                    int startIndex = i * chunkSize;
                    int endIndex = Math.min(startIndex + chunkSize, points.length);
    
                    // Extract the chunk of points
                    double[][] chunk = Arrays.copyOfRange(points, startIndex, endIndex);
    
                    // Calculate Manhattan distances for the chunk
                    double[][] distances = client.calculateManhattanDistance(chunk, medoids);
    
                    // Store the results
                    clientResults[i] = distances;
                }

                System.out.println("************** Results from Clients: ****************");
                for (int i = 0; i < numClients; i++) {
                    System.out.println("Results from Client " + (i + 1) + ":");
                    for (int j = 0; j < clientResults[i].length; j++) {
                        System.out.print("Point " + (j + 1) + ": ");
                        for (int k = 0; k < clientResults[i][j].length; k++) {
                            System.out.print(clientResults[i][j][k] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println();
                }

                //***************************results from clusters assignement *************************************** */
                System.out.println("***************************results from clusters assignement********************");
                // Assign clusters to points
                int[] clusters = assignClusters(clientResults,points.length);
                for (int z=0;z<clusters.length;z++){
                    System.out.println("la point "+z+" appartiet au cluster "+clusters[z]);
                }

                double[][][] clientResultsCost = new double[numClients][][];

                 // ********************************Send each chunk to a client to calculate the cost ***************************
                for (int i = 0; i < numClients; i++) {
                    ClientInter client = (ClientInter) registry.lookup("client" + (i + 1));
                    int startIndex = i * chunkSize;
                    int endIndex = Math.min(startIndex + chunkSize, points.length);
                    double[][] chunk = Arrays.copyOfRange(points, startIndex, endIndex);
                    clientResultsCost[i] = client.calculateCost(chunk, points,clusters,chunkSize,i); // it allows to calculate the cost from each point to the points in the same cluster 
                }

                System.out.println("************** Results from Clients on the costs operation : ****************");
                for (int i = 0; i < numClients; i++) {
                    System.out.println("Results from Client " + (i + 1) + ":");
                    for (int j = 0; j < clientResultsCost[i].length; j++) {
                        System.out.print("Point " + (j + 1) + ": ");
                        for (int k = 0; k < clientResultsCost[i][j].length; k++) {
                            System.out.print(clientResultsCost[i][j][k] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println();
                }


                //**********************************find the points with smallest cost in each cluster ****************************************
                // Initialize an array to store the index of the point with the smallest cost for each cluster
                int[] minCostPointIndex = new int[medoids.length];

                // Initialize an array to store the minimum cost for each cluster
                double[] minCostPerCluster = new double[medoids.length];

                // Initialize the arrays
                Arrays.fill(minCostPointIndex, -1); // -1 indicates no point found yet
                Arrays.fill(minCostPerCluster, Double.MAX_VALUE); // Initialize to a very large value

                // Iterate over clientResultsCost to find the point with the smallest cost for each cluster
                for (int i = 0; i < clientResultsCost.length; i++) {
                    for (int j = 0; j < clientResultsCost[i].length; j++) {
                        double[] result = clientResultsCost[i][j];
                        int pointIndex = (int) (result[0])+chunkSize*i; // Get the index of the point
                        double cost = result[1]; // Get the cost

                        // Check if the cost is smaller than the minimum cost for the cluster
                        if (cost < minCostPerCluster[clusters[pointIndex]]) {
                            minCostPerCluster[clusters[pointIndex]] = cost; // Update the minimum cost
                            minCostPointIndex[clusters[pointIndex]] = pointIndex; // Update the point index
                        }
                    }
                } 

                // Print the values of minCostPointIndex
                System.out.println("************** Min Cost Point Index per Cluster: ****************");
                for (int i = 0; i < minCostPointIndex.length; i++) {
                    System.out.println("Cluster " + (i + 1) + ": Point " + minCostPointIndex[i]);
                }

                // Print the values of minCostPerCluster
                System.out.println("************** Min Cost per Cluster: ****************");
                for (int i = 0; i < minCostPerCluster.length; i++) {
                    System.out.println("Cluster " + (i + 1) + ": " + minCostPerCluster[i]);
                }

                //******************************************print medoids before update  **********************/
                System.out.println("********************medoids before update ***************");
                for(int i=0;i<medoids.length;i++){
                    System.out.println("medoid "+i);
                    for(int j=0;j<medoids[0].length;j++){
                        System.out.println(medoids[i][0]+"  "+medoids[i][1]);
                    }
                    
                }

                //update the medoides points 
                test=0 ;
                for (int i=0;i<medoids.length;i++){
                    if(medoids[i]==(points[minCostPointIndex[i]])){
                        test++ ;
                    }
                    medoids[i]=points[minCostPointIndex[i]] ;
                }
                if(test==medoids.length){
                    System.out.println("the algorithm converged ");
                    converged=true ;
                }

                System.out.println("********************medoids after update ***************");
                for(int i=0;i<medoids.length;i++){
                    System.out.println("medoid "+i);
                    for(int j=0;j<medoids[0].length;j++){
                        System.out.println(medoids[i][0]+"  "+medoids[i][1]);
                    }
                    
                }

            }
            


            // // Print the results
            

            

           
            // System.out.println("the first length "+clientResultsCost[0][0].length);

            

           

            // // Accumulate the costs for each cluster
            // double[] totalCosts = new double[medoids.length];
            // for (double[][] result : clientResultsCost) {
            //     for (double[] cost : result) {
            //         totalCosts[(int) cost[0]] += cost[1];
            //     }
            // }

            // Print the total costs
            // System.out.println("************** Total Costs: ****************");
            // for (int i = 0; i < totalCosts.length; i++) {
            //     System.out.println("Cluster " + i + ": " + totalCosts[i]);
            // }
            // // Print the cluster assignments
            // // System.out.println("************** Cluster Assignments: ****************");
            // for (int i = 0; i < points.length; i++) {
            //     System.out.println("Point " + (i + 1) + " is assigned to Cluster " + (clusters[i] + 1));
            // }
            // // System.out.println("le nombre de points est "+points.length);

           

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