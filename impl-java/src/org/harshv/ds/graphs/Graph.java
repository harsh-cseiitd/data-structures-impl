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

public interface Graph<V, E> {

	public boolean isWeighted();

	public boolean isDirected();

	public boolean isMutable();
	
	public boolean isConnected();

	public boolean isEdgeExist(V u, V v);

	public boolean isEdgeExist(E e);

	public boolean isVertexExist(V u);

	public E getEdge(V src, V dest);

	// In case of Undirected Graph, boolean flag doesnt matter but 
	// for directed graphs, it returns out-degree or in-degree as
	// per this boolean flag.
	public E[] getAllOutgoingEdges(V vertex);

	public V[] getAllVertex();

	// In case of Undirected Graph, boolean flag doesnt matter but 
	// for directed graphs, it returns out-degree or in-degree as
	// per this boolean flag.
	public int getOutDegree(V vertex);
	
	public int getInDegree(V vertex);

}