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

import java.util.List;

public interface Graph<V> {

	public boolean isWeighted();

	public boolean isDirected();

	public boolean isMutable();

	
	public boolean isVertexExist(V vertex);

	public boolean isEdgeExist(V src, V dest);

	public boolean isEdgeExist(Edge<V> edge);


	
	public int getVertexCount();
	
	public int getEdgeCount();


	public Edge<V> getEdge(V src, V dest);

	public V[] getAllVertex();
	
	/**
	 * In case of Undirected Graph, every edge is bi-directional.
	 * Following method returns all incident edges on a vertex.
	 * @param vertex
	 * @return array of Edges
	 */
	 
	public List<V> getAllNeighbors(V vertex);
	
	/**
	 * Following method returns count of all incident edges on
	 * a vertex. 
	 * @param vertex
	 * @return integer which represents count of incident edges
	 * on input vertex
	 */
	public int getDegree(V vertex);
	
	public void addEdge(Edge<V> edge) throws UnsupportedOperationException;
	
	public void addVertex(V vertex) throws UnsupportedOperationException;

}