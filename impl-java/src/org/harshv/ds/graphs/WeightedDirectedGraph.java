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

public interface WeightedDirectedGraph<V, E> extends DirectedGraph<V, E> {
	
	public static double DEFAULT_EDGE_WEIGHT = 1.0;
	
	public void setEdgeWeight(E e, double weight);
	
	public void setEdgeWeight(V src, V dest, double weight);

	// It returns DEFAULT_EDGE_WEIGHT in case of default settings applies
	// to  actual edge weight in graph.
	public double getEdgeWeight(E e);
	
	public double getEdgeWeight(V src, V dest);

}
