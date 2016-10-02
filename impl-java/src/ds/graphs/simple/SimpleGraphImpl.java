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

package ds.graphs.simple;

import java.util.ArrayList;
import java.util.LinkedList;

/** 
 * This graph implementation assumes vertex numbers 0 to V-1.
 * Graph can be created as Directed or undirected however this
 * property can't be modified later.
 * @author harshv
 *
 */

public class SimpleGraphImpl implements SimpleGraph {
	private final boolean IS_WEIGHTED  = false;
	private final int DEFAULT_SIZE     = 0;
    private boolean isDirected         = false;
    private int edgeCount              = 0;
    private int vertexCount            = 0;
    private ArrayList<LinkedList<Integer>> adjList ;


    public SimpleGraphImpl() {
    	this.adjList = new ArrayList<LinkedList<Integer>>(DEFAULT_SIZE);
    	for (int i = 0 ; i < DEFAULT_SIZE ; i++) {
			this.adjList.add(new LinkedList<Integer>());
		}
    }

    public SimpleGraphImpl(int vertices, boolean isDirected) {
    	this.adjList     = new ArrayList<LinkedList<Integer>>(vertices);
    	this.isDirected  = isDirected;
    	this.vertexCount = vertices;
    	for (int i = 0 ; i < vertices ; i++) {
			this.adjList.add(new LinkedList<Integer>());
		}
    }

	@Override
	public boolean isDirected() {
		return this.isDirected;
	}

	@Override
	public int getVertexCount() {
		return this.vertexCount ;
	}

	@Override
	public int getEdgeCount() {
		return this.edgeCount;
	}


	@Override
	public boolean edgeExist(int src, int dest) {
		if (isValidVertex(src) && isValidVertex(dest)) {
			LinkedList<Integer> subList = this.adjList.get(src);
			for (Integer item : subList) {
				if (item.intValue() == dest) {
					return true;
				}
			}
		}
		return false;
	}


	public Edge getEdge(int src, int dest) {
		if (isValidVertex(src) && isValidVertex(dest)) {
			LinkedList<Integer> subList = this.adjList.get(src);
			for (Integer item : subList) {
				if (item.intValue() == dest) {
					return new EdgeImpl(src, dest, this.isDirected);
				}
			}
		}
		return null;
	}

	@Override
	public boolean edgeExist(Edge edge) {
		return edgeExist(edge.getSrc(), edge.getSrc());
	}

	@Override
	public int getOutDegree(int v) {
		if (isValidVertex(v)) {
			return this.adjList.get(v).size();
		}
		return 0;
	}

	@Override
	public boolean isWeighted() {
		return IS_WEIGHTED;
	}

	@Override
	public Iterable<Integer> getAllNeighbors(int v) {
		if (isValidVertex(v)) {
			return this.adjList.get(v);
		}
		return null;
	}


	public void addEdge(int src, int dest) {
		int max = dest > src ? dest : src;
		if (!isValidVertex(max)) {
			addVertices(this.adjList.size(), max);
			
		}
		LinkedList<Integer> srcAdjList = this.adjList.get(src);
		srcAdjList.add(dest);
		this.adjList.set(src, srcAdjList);
		if (!isDirected()) {
			LinkedList<Integer> destAdjList = this.adjList.get(dest);
			destAdjList.add(src);
			this.adjList.set(dest, destAdjList);
		}
		this.edgeCount = this.edgeCount + 1;
	}

	private boolean isValidVertex(int v) {
		if (v >= 0 && v < this.adjList.size()) {
			return true;
		}
		return false;
	}

	private void addVertices(int start, int end) {
		for (int i = start ; i <= end ; i++) {
			this.adjList.add(new LinkedList<Integer>());
		}
		System.out.println(String.format("INFO: added vertices from %d to %d", start, end));
		this.vertexCount = this.adjList.size();
	}

}
