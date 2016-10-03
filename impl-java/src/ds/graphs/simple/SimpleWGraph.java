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

public interface SimpleWGraph extends SimpleGraph {

	public boolean edgeExist(WEdge edge);

	public WEdge getEdge(int src, int dest);
	
	public double getWeight(int src, int dest);

	/**
	 * In case of Undirected Graph, every edge is bi-directional.
	 * Following method returns all incident edges on a vertex.
	 * @param vertex
	 * @return array of Edges
	 */
	 
	public Iterable<Integer> getAllNeighbors(int vertex);
	
	public Iterable<WEdge> getAllEdges(int vertex);
	
}
