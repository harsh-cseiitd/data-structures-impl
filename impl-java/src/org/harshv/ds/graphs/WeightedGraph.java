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

public interface WeightedGraph<V> extends Graph<V> {
	
	public static double DEFAULT_EDGE_WEIGHT = 1.0;
	
	public double getEdgeWeight(V src, V dest);
	
	public void addEdge(WEdge<V> edge) throws UnsupportedOperationException;
	
	public void setEdgeWeight(WEdge<V> e) throws UnsupportedOperationException;
	
	public void setEdgeWeight(V src, V dest, double weight) throws UnsupportedOperationException;
	
}
