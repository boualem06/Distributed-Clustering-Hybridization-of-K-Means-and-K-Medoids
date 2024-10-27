# Distributed Clustering Hybridization of K-Means and K-Medoids

# Overview
This project explores a distributed hybrid approach for unsupervised clustering by combining the strengths of the k-means and k-medoids algorithms. The solution leverages the speed of k-means with the robustness of k-medoids to outliers, achieving more efficient and high-quality clustering results, especially in large-scale and noisy datasets.

# Objective
The goal of this project is to address the limitations of individual clustering algorithms through a distributed hybridization of k-means and k-medoids. This hybrid approach aims to:

- Enhance clustering accuracy.
- Improve processing time through distributed computation.
- Maintain robustness against outliers.

  
# Key Features
- Clustering Algorithms: Implements both k-means and k-medoids to balance speed and accuracy.
- Distributed Framework: Utilizes distributed processing to handle large datasets efficiently.
- Hybridized Approach: Merges k-means’ computational efficiency with k-medoids’ resilience to outliers, enabling better clustering for complex data.

# Methodology
- Initialization: Randomly select initial points as cluster centers.
- Assignment: Assign each data point to the nearest cluster center.

# Optimization:
- Update the cluster representative points by calculating the total exchange cost between current and new representative objects.
- Swap representatives if a lower-cost configuration is found.
- Termination: Repeat until no further changes occur, finalizing the cluster assignments.



