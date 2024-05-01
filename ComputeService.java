import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ComputeService  extends Remote{
    
    int summer(int n, int m) throws RemoteException;
    // int[] getChunk()
}
 