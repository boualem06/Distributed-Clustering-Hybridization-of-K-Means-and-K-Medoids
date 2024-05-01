
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInter  extends Remote{
    
    int summer(int n, int m) throws RemoteException;
    String getName() throws RemoteException ;
    // Method to process a point and find the nearest centroid
    void processPoint(double[] point, double[][] centroids) throws RemoteException;
    

    // Method to process a chunk of points and find the nearest centroids for each point
    double[][] processChunk(double[][] points, double[][] centroids) throws RemoteException;

    // int[] getChunk()
}
 

// import java.rmi.Remote;
// import java.rmi.RemoteException;

// public interface ClientInter extends Remote {
//     int summ(int n, int m) throws RemoteException;
// }