import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ClientImpl implements  ClientInter{

    private String name;

    public ClientImpl(String name) {
        this.name = name;
    }


    @Override
    public int summer(int n, int m){
        return n + m;
    }

    @Override
    public void processPoint(double[] point, double[][] centroids) throws RemoteException {
        // Calculate the nearest centroid for the given point
        double nearestDistance = Double.MAX_VALUE;
        double[] nearestCentroid = null;

        // Calculate Euclidean distance to find the nearest centroid
        for (double[] centroid : centroids) {
            double distance = calculateDistance(point, centroid);
            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestCentroid = centroid;
            }
        }
        System.out.println("Point (" + point[0] + ", " + point[1] + ") is closest to centroid (" +
                nearestCentroid[0] + ", " + nearestCentroid[1] + ")");
    }

    @Override
    public double[][] processChunk(double[][] points, double[][] centroids) throws RemoteException {
        // // Process each point in the chunk
        // for (double[] point : points) {
        //     processPoint(point, centroids);
        // }

        // Process the chunk and return results
        List<double[]> results = new ArrayList<>();
        for (double[] point : points) {
            double[] nearestCentroid = findNearestCentroid(point, centroids);
            results.add(nearestCentroid);
        }

        // Convert results list to array
        double[][] resultsArray = new double[results.size()][];
        for (int i = 0; i < results.size(); i++) {
            resultsArray[i] = results.get(i);
        }

        return resultsArray;

    }

    private double[] findNearestCentroid(double[] point, double[][] centroids) {
        double minDistance = Double.MAX_VALUE;
        double[] nearestCentroid = null;

        for (double[] centroid : centroids) {
            double distance = calculateDistance(point, centroid);
            if (distance < minDistance) {
                minDistance = distance;
                nearestCentroid = centroid;
            }
        }
        return nearestCentroid;
    }

    
    private double calculateDistance(double[] point, double[] centroid) {
        // Calculate Euclidean distance between point and centroid
        double sum = 0;
        for (int i = 0; i < point.length; i++) {
            sum += Math.pow(point[i] - centroid[i], 2);
        }
        return Math.sqrt(sum);
    }


    public String getName() {
        return name;
    }
    
}



// import java.rmi.RemoteException;

// public class ClientImpl implements ClientInter {

//     @Override
//     public int summ(int n, int m)  {
//         return n + m;
//     }
// }

