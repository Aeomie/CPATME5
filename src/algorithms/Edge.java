package algorithms;

import java.awt.Point;

public class Edge implements Comparable<Edge> {

	public  Point p;
	public Point q;
	public  double dist;

	public Edge(Point u, Point v) {
		this.p = u;
		this.q = v;
		this.dist = u.distance(v);
	}

	public Edge(Point u, Point v, double dist) {
		this.p = u;
		this.q = v;
		this.dist = dist;
	}
 protected double distance(){ return p.distance(q); }
	public Point getP() {
		return p;
	}

	public Point getQ() {
		return q;
	}

	public double getDist() {
		return dist;
	}

	@Override
	public int compareTo(Edge e) {
		if (this.dist > e.getDist())
			return 1;
		else if (this.dist < e.getDist())
			return -1;
		else
			return 0;
	}

}