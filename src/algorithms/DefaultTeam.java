package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DefaultTeam {

  public int[][] calculShortestPaths(ArrayList<Point> points, int edgeThreshold) {
	    int[][] paths=new int[points.size()][points.size()];
	    for (int i=0;i<paths.length;i++) for (int j=0;j<paths.length;j++) paths[i][j]=i;

	    double[][] dist=new double[points.size()][points.size()];

	    for (int i=0;i<paths.length;i++) {
	      for (int j=0;j<paths.length;j++) {
	        if (i==j) {dist[i][i]=0; continue;}
	        if (points.get(i).distance(points.get(j))<=edgeThreshold) dist[i][j]=points.get(i).distance(points.get(j));
	        else dist[i][j]=Double.POSITIVE_INFINITY;
	        
	        paths[i][j]=j;
	      }
	    }

	    for (int k=0;k<paths.length;k++) {
	      for (int i=0;i<paths.length;i++) {
	        for (int j=0;j<paths.length;j++) {
	          if (dist[i][j]>dist[i][k] + dist[k][j]){
	            dist[i][j]=dist[i][k] + dist[k][j];
	            paths[i][j]=paths[i][k];

	          }
	        }
	      }
	    }

	    return paths;
	  }

	public Tree2D calculSteiner(ArrayList<Point> points, int edgeThreshold, ArrayList<Point> hitPoints) {
		System.out.println("je suis dans CalculSteiner");
		
		// setting up the MST
		kruskal k = new kruskal();
		ArrayList<Edge> steinerEdge = k.kruskal(hitPoints);
		Tree2D steinerTree=k.edgesToTree(steinerEdge,hitPoints.get(0) );

		int[][] paths = calculShortestPaths(points, edgeThreshold);

		Tree2D newSteinerTree = Steiner(paths, steinerTree, points);

		return newSteinerTree;
	}
	private Tree2D Steiner(int[][] paths, Tree2D steinerTree, ArrayList<Point> points) {
	    Point root = steinerTree.getRoot();
	    int rootIndex = points.indexOf(root);
	    ArrayList<Tree2D> children = new ArrayList<>();

	    for (Tree2D subTree : steinerTree.getSubTrees()) {
	        Point childRoot = subTree.getRoot();
	        int childIndex = points.indexOf(childRoot);
	        int nextNodeIndex = paths[rootIndex][childIndex];
	        Point nextNode = points.get(nextNodeIndex);

	        if (nextNode.equals(childRoot)) {
	            // Point next is the normal child node
	            children.add(Steiner(paths, subTree, points));
	        } else {
	            // Point next is an intermediate node
	            Tree2D intermediateTree = new Tree2D(nextNode, new ArrayList<>(List.of(subTree)));
	            children.add(Steiner(paths, intermediateTree, points));
	        }
	    }

	    return new Tree2D(root, children);
	}
	
	

}
