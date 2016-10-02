package org.harshv.ds.graphs;

public class WEdge<V> extends Edge <V> {

	private double weight = 0.0;

	public WEdge() {
		super();
		this.weight = 0.0;
	}
	
	public WEdge(V src, V dest, boolean isDirected, double w) {
		super(src, dest, isDirected);
		this.weight = w;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public void setWeight(double w) {
		this.weight = w;
	}
}
	
