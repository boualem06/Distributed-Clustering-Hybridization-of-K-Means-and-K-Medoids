
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInter  extends Remote{
    
    int summer(int n, int m) throws RemoteException;
    String getName() throws RemoteException ;
    // Method to process a point and find the nearest centroid
    // void processPoint(double[] point, double[][] centroids) throws RemoteException;
    

    // Method to process a chunk of points and find the nearest centroids for each point
    double[][] processChunk(double[][] points, double[][] centroids) throws RemoteException;

    double calculateManhattanDistance(double[] point1, double[] point2) throws RemoteException;
    // int[] getChunk()
     double[][] calculateManhattanDistance(double[][] points, double[][] medoids) throws RemoteException ;

    //  double[][] calculateCost(double[][] points, int[] clusters) throws RemoteException;

    double[][] calculateCost(double[][] chunk, double[][] points, int[] clusters ,int chunkSize,int k) throws RemoteException;
     
    
    }
 

// import java.rmi.Remote;
// import java.rmi.RemoteException;

// public interface ClientInter extends Remote {
//     int summ(int n, int m) throws RemoteException;
// }