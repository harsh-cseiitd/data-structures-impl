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
import java.util.List;

public class SimpleWGraphImpl implements SimpleWGraph {
	private final boolean IS_WEIGHTED  = true;
	private final int DEFAULT_SIZE     = 16;
    private boolean isDirected         = false;
    private int edgeCount              = 0;
    private int vertexCount            = 0;
    private ArrayList<LinkedList<WEdge>> adjList;


    public SimpleWGraphImpl() {
    	this.adjList = new ArrayList<LinkedList<WEdge>>(DEFAULT_SIZE);
    	for (int i = 0 ; i < DEFAULT_SIZE ; i++) {
			this.adjList.add(new LinkedList<WEdge>());
		}
    	this.vertexCount = this.adjList.size();
    }


    public SimpleWGraphImpl(int vertices, boolean isDirected) {
    	this.adjList     = new ArrayList<LinkedList<WEdge>>(vertices);
    	this.isDirected  = isDirected;
    	for (int i = 0 ; i < vertices ; i++) {
			this.adjList.add(new LinkedList<WEdge>());
		}
    	this.vertexCount = this.adjList.size();
    }

    @Override
	public boolean isDirected() {
		return this.isDirected;
	}

	@Override
	public boolean isWeighted() {
		return IS_WEIGHTED;
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
	public boolean edgeExist(Edge edge) {
		if (edge instanceof WEdge) {
			edgeExist((WEdge) edge);
		}
		return false;
	}

	@Override
	public boolean edgeExist(WEdge edge) {
		return edgeExist(edge.getSrc(), edge.getDest());
	}

	@Override
	public boolean edgeExist(int src, int dest) {
		if (null == getEdge(src, dest)) {
			return false;
		}
		return true;
	}

	@Override
	public double getWeight(int src, int dest) {
		WEdge edge = getEdge(src, dest);
		if (edge != null) {
			return edge.getWeight();
		}
		return Double.NaN;
	}

	@Override
	public WEdge getEdge(int src, int dest) {
		if (isValidVertex(src) && isValidVertex(dest)) {
			LinkedList<WEdge> subList = this.adjList.get(src);
			for (WEdge edge : subList) {
				if (edge.getDest() == dest) {
					return edge;
				}
			}
		}
		return null;
	}


	@Override
	public int getOutDegree(int v) {
		if (isValidVertex(v)) {
			return this.adjList.get(v).size();
		}
		return 0;
	}

	@Override
	public Iterable<WEdge> getAllEdges(int v) {
		if (isValidVertex(v)) {
			return this.adjList.get(v);
		}
		return null;
	}


	@Override
	public Iterable<Integer> getAllNeighbors(int v) {
		Iterable<WEdge> iter = getAllEdges(v);
		List<Integer> list = new ArrayList<Integer>();
		if (iter != null) {
			for (WEdge edge : iter) {
				list.add(edge.getDest());
			}
		}
		return list;
	}


	public void addEdge(int src, int dest, double weight) {
		int max = dest > src ? dest : src;
		if (!isValidVertex(max)) {
			addVertices(this.adjList.size(), max);
			
		}
		LinkedList<WEdge> srcAdjList = this.adjList.get(src);
		srcAdjList.add(new WEdgeImpl(src, dest, weight, isDirected()));
		this.adjList.set(src, srcAdjList);
		if (!isDirected()) {
			LinkedList<WEdge> destAdjList = this.adjList.get(dest);
			destAdjList.add(new WEdgeImpl(dest, src, weight, isDirected()));
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
			this.adjList.add(new LinkedList<WEdge>());
		}
		this.vertexCount = this.adjList.size();
	}
}
