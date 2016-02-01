package org.harshv.ds.graphs;

public class Edge <V> {
	private V srcVertex;
	private V destVertex;
	private boolean directed;

	public Edge() {
		this.srcVertex  = null;
		this.destVertex = null;
		this.directed   = false;
	}
	
	public Edge(V src, V dest, boolean isDirected) {
		this.srcVertex  = src;
		this.destVertex = dest;
		this.directed   = isDirected;
	}
	
	public boolean isDirected() {
		return this.directed;
	}
	
	public V getSrcV() {
		return this.srcVertex;
	}
	
	public V getDestV() {
		return this.destVertex;
	}
}
