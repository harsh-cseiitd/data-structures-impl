/*
 * Copyright (C) 2016 Harsh Vardhan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.harshv.ds.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Adjacency list representation of Graph.
 */

public class SimpleWeightedGraph<V> implements WeightedGraph<V> {

	private HashMap<V, ArrayList<Neighbor<V>>> adjList ;
    private int edgeCount   = 0;
    private int vertexCount = 0;
    private boolean isDirected  = false;
    private boolean isWeighted  = true;
    private boolean isMutable   = true;
    
    /**
     * This constructor creates an empty graph. it doesn't have
     * any vertex.
     */
    public SimpleWeightedGraph(int initialCapacity, boolean directed) {
    	this.isDirected = directed;
    	this.adjList    = new HashMap<V, ArrayList<Neighbor<V>>> (initialCapacity);	
    }
    
    /**
     * This constructor creates a graph who has @param vertices
     * but doesn't have any edge.
     */
    public SimpleWeightedGraph(Collection<V> vertices, boolean directed) {
    	this.isDirected  = directed;
    	this.vertexCount = vertices.size();
    	this.adjList     = new HashMap<V, ArrayList<Neighbor<V>>> (this.vertexCount);	
    	for(V vertex : vertices) {
    		this.adjList.put(vertex, new ArrayList<Neighbor<V>>());
    	}
    }
    
    /**
     * This constructor creates a graph who has @param edges. vertex count gets incremented 
     * upon every unique vertex.
     */
    public SimpleWeightedGraph(Collection<WEdge<V>> edges, boolean directed, boolean isMutable) {
    		this.isDirected = directed;
    		this.edgeCount  = edges.size();
        	this.adjList    = new HashMap<V, ArrayList<Neighbor<V>>> (this.vertexCount);	
        	for(WEdge<V> edge : edges) {
        		addEdge(edge);
        	}
        	this.isMutable = isMutable;
    }

	@Override
	public boolean isWeighted() {
		return this.isWeighted;
	}

	@Override
	public boolean isDirected() {
		return this.isDirected;
	}

	@Override
	public boolean isMutable() {
		return this.isMutable;
	}


	@Override
	public boolean isEdgeExist(V u, V v) {
		if (isVertexExist(u) && isVertexExist(u)) {
			ArrayList<Neighbor<V>> neighbors = this.adjList.get(u);
			int vIndex = getIndexInEdgeList(neighbors, v);
			if (vIndex >= 0) {
				return true;
			}
		}	
		return false;
	}

	@Override
	public boolean isEdgeExist(Edge<V> e) {
		V src  = e.getSrcV();
		V dest = e.getDestV();
		return isEdgeExist(src, dest);
	}

	@Override
	public boolean isVertexExist(V u) {
		return (this.adjList.containsKey(u));
	}

	@Override
	public WEdge<V> getEdge(V src, V dest) {
		if (isVertexExist(src)) {
			ArrayList<Neighbor<V>> neighbors = this.adjList.get(src);
			for(Neighbor<V> neighbor: neighbors) {
				if (neighbor.getVertex().equals(dest)) {
					return new WEdge<V>(src, dest, this.isDirected, neighbor.getWeight());
				}
			}
		}
		return null;
	}

	@Override
	public V[] getAllVertex() {
		V[] vertices = null;
		this.adjList.keySet().toArray(vertices);
		return vertices;
	}

	@Override
	public List<V> getAllNeighbors(V vertex) {
		if (this.adjList.get(vertex) != null || this.adjList.get(vertex).size() > 0) {
			ArrayList<Neighbor<V>> edges = adjList.get(vertex);
			ArrayList<V> neighbors       = new ArrayList<V>(edges.size());
			for(Neighbor<V> edge : edges) {
				neighbors.add(edge.getVertex());
			}
			return neighbors;
		}
		return null;
	}

	@Override
	public int getDegree(V vertex) {
		if (this.adjList.get(vertex) != null) {
			return this.adjList.size();
		}
		return -1;
	}

	@Override
	public int getVertexCount() {
		return this.vertexCount;
	}

	@Override
	public int getEdgeCount() {
		return this.edgeCount;
	}

	@Override
	public void addEdge(Edge<V> edge) throws UnsupportedOperationException {
		if (edge instanceof WEdge) {
			addEdge(edge);
		} else {
			throw new UnsupportedOperationException("Can't add edge because edge doesn't have weight.");
		}
		
	}
	
	@Override
	public void addEdge(WEdge<V> edge) throws UnsupportedOperationException {
		if (this.isMutable == false) {
			throw new UnsupportedOperationException("Can't add edge because Graph is Immutable.");
		}

		V src  = edge.getSrcV();
		V dest = edge.getDestV();
		
		if (!isVertexExist(src)) {
			addVertex(src);
		}
		
		if (!isVertexExist(dest)) {
			addVertex(dest);
		}
		
		if (this.isDirected) {
			this.adjList.get(src).add(new Neighbor<V>(dest, edge.getWeight()));
		} else {
			this.adjList.get(src).add(new Neighbor<V>(dest, edge.getWeight()));
			this.adjList.get(dest).add(new Neighbor<V>(src, edge.getWeight()));
		}
		this.edgeCount = this.edgeCount +1;
	}

	@Override
	public void addVertex(V vertex) throws UnsupportedOperationException {
		if (this.isMutable == false) {
			throw new UnsupportedOperationException("Can't add vertex because Graph is Immutable.");
		} else if (!isVertexExist(vertex)) {
			this.adjList.put(vertex, new ArrayList<Neighbor<V>>());
			this.vertexCount = this.vertexCount +1;
		}
	}

	@Override
	public void setEdgeWeight(WEdge<V> e) throws UnsupportedOperationException {
		setEdgeWeight(e.getSrcV(), e.getDestV(), e.getWeight());
		
	}

	@Override
	public void setEdgeWeight(V src, V dest, double newWeight) throws UnsupportedOperationException {
		if (this.isMutable == false) {
			throw new UnsupportedOperationException("Can't modify edge weight because Graph is Immutable.");
		}
		if (isVertexExist(src) && isVertexExist(dest)) {
			ArrayList<Neighbor<V>> neighbors = this.adjList.get(src);
			int vIndex = getIndexInEdgeList(neighbors, src);
			if (vIndex >= 0) {
				this.adjList.get(src).get(vIndex).setWeight(newWeight);
			}
		}
	}

	@Override
	public double getEdgeWeight(V src, V dest) {
		if (isVertexExist(src) && isVertexExist(dest)) {
			ArrayList<Neighbor<V>> neighbors = this.adjList.get(src);
			int vIndex = getIndexInEdgeList(neighbors, src);
			if (vIndex >= 0) {
				return this.adjList.get(src).get(vIndex).getWeight();
			}
		}
		return 0;
	}

	private int getIndexInEdgeList(ArrayList<Neighbor<V>> edges, V dest) {
		if (edges.size() > 0) {
			int i = 0;
			while(i < edges.size()) {
				Neighbor<V> neighbor = edges.get(i);
				if (dest.equals(neighbor.getVertex())) {
					return i;
				}
			}
		}
		return -1;
	}
	
	private class Neighbor<V> {
		private V vertex;
		private double weight;
		
		public Neighbor(V dest, double w) {
			this.vertex = dest;
			this.weight = w;
		}
		
		public double getWeight() {
			return this.weight;
		}
		
		public V getVertex() {
			return this.vertex;
		}
		
		public void setWeight(double w) {
			this.weight = w;
		}
	}
}
