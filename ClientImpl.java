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
    public double[][] processChunk(double[][] points, double[][] centroids) throws RemoteException {
        // // Process each point in the chunk
       
        

        List<double[]> results = new ArrayList<>();
        for (double[] point : points) {
            System.out.println(point[0]+"****"+point[1]);
            double[] nearestCentroid = findNearestCentroid(point, centroids);
            System.out.println(nearestCentroid[0]+"--->"+nearestCentroid[1]);
            results.add(nearestCentroid);
        }
        System.out.println("***********************************");

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


    // ************************************************** the code used for kmedoides ***************************
    // Method to calculate Manhattan distance between a chunk of points and medoids
    @Override
    public double[][] calculateManhattanDistance(double[][] points, double[][] medoids)  {
        double[][] distances = new double[points.length][medoids.length];

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < medoids.length; j++) {
                distances[i][j] = calculateManhattanDistance(points[i], medoids[j]);
            }
        }

        return distances;
    }

    // Method to calculate Manhattan distance between two points
    @Override
    public double calculateManhattanDistance(double[] point1, double[] point2) {
        double distance = 0;
        for (int i = 0; i < point1.length; i++) {
            distance += Math.abs(point1[i] - point2[i]);
        }
        return distance;
    }

    @Override
    public double[][] calculateCost(double[][] chunk, double[][] points, int[] clusters,int chunkSize,int k ){
        List<double[]> costs = new ArrayList<>();
        double cost;
        for (int i = 0; i < chunk.length; i++) {
            cost=0 ;
            for (int j=0;j<points.length;j++){
                if (clusters[k*chunkSize+i] == clusters[j]) { 
                    // System.out.println(clusters[i] == clusters[j]);
                    // System.out.println("the point1 is "+chunk[i][0]+" "+chunk[i][1]+" the point 2 is "+points[j][0]+" "+points[j][1]+" the manhatan distane is "+calculateManhattanDistance(chunk[i], points[j]));
                     cost += calculateManhattanDistance(chunk[i], points[j]);
                }
            }
            // System.out.println("the value cost of the point "+i+" is : "+cost);
            costs.add(new double[] { i, cost });
           
        }
        return costs.toArray(new double[0][]);
    }
    // @Override
    // public double calculateDistance(double[] p1, double[] p2) {
    //     double distance = 0;
    //     for (int i = 0; i < p1.length; i++) {
    //         distance += Math.abs(p1[i] - p2[i]);
    //     }
    //     return distance;
    // }

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

