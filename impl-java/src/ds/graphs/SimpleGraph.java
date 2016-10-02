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
 * Adjacency list representation of Undirected Graph.
 */

public class SimpleGraph<V> implements Graph<V> {

	private HashMap<V, ArrayList<V>> adjList ;
    private int edgeCount   = 0;
    private int vertexCount = 0;
    private boolean isDirected  = false;
    private boolean isWeighted  = false;
    private boolean isMutable   = true;
    
    /**
     * This constructor creates an empty graph. it doesn't have
     * any vertex.
     */
    public SimpleGraph(int initialCapacity, boolean directed) {
    	this.isDirected = directed;
    	this.adjList    = new HashMap<V, ArrayList<V>> (initialCapacity);	
    }
    
    /**
     * This constructor creates a graph who has @param vertices
     * but doesn't have any edge.
     */
    public SimpleGraph(Collection<V> vertices, boolean directed) {
    	this.isDirected  = directed;
    	this.vertexCount = vertices.size();
    	this.adjList     = new HashMap<V, ArrayList<V>> (this.vertexCount);	
    	for(V vertex : vertices) {
    		this.adjList.put(vertex, new ArrayList<V>());
    	}
    }
    
    /**
     * This constructor creates a graph who has @param edges. vertex count gets incremented 
     * upon every unique vertex.
     */
    public SimpleGraph(Collection<Edge<V>> edges, boolean directed, boolean isMutable) {
    		this.isDirected = directed;
    		this.edgeCount  = edges.size();
        	this.adjList    = new HashMap<V, ArrayList<V>> (this.vertexCount);	
        	for(Edge<V> edge : edges) {
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
			ArrayList<V> edges = this.adjList.get(u);
			if (edges.indexOf(v) >= 0) {
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
	public Edge<V> getEdge(V src, V dest) {
		if (isEdgeExist(src, dest)) {
			return new Edge<V>(src, dest, this.isDirected);
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
			ArrayList<V> edges = adjList.get(vertex);
			return edges;
		}
		return null ;
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
		if (this.isMutable == false) {
			throw new UnsupportedOperationException("Can't add edge because Graph is Immutable.");
		}

		if (!isVertexExist(edge.getSrcV())) {
			addVertex(edge.getSrcV());
		}
		
		if (!isVertexExist(edge.getDestV())) {
			addVertex(edge.getDestV());
		}
		
		if (this.isDirected) {
			this.adjList.get(edge.getSrcV()).add(edge.getDestV());
		} else {
			this.adjList.get(edge.getSrcV()).add(edge.getDestV());
			this.adjList.get(edge.getDestV()).add(edge.getSrcV());
		}
		this.edgeCount = this.edgeCount +1;
	}

	@Override
	public void addVertex(V vertex) throws UnsupportedOperationException {
		if (this.isMutable == false) {
			throw new UnsupportedOperationException("Can't add vertex because Graph is Immutable.");
		} else if (!isVertexExist(vertex)) {
			this.adjList.put(vertex, new ArrayList<V>());
			this.vertexCount = this.vertexCount +1;
		}
	}

}
