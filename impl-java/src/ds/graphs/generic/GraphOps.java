package org.harshv.ds.graphs;

public class GraphOps<V> {

		public boolean isConnected(Graph<V> g) {
			return false;
		}
		
		public boolean isCyclic(Graph<V> g) {
			return false;
		}
		
		public int[] getShortestDistances(Graph<V> g, V start) {
			return new int[5];
		}
		
		public Graph<V> getAcyclicGraph(Graph<V> g) {
			return g;
		}
		
		public WeightedGraph<V>  getMST(WeightedGraph<V> g) {
			return g;
		}
		
		public int[] getShortestDistances(WeightedGraph<V> g) {
			return new int[5];
		}
		
		public Graph<V> readGraph(String filename) {
			return new SimpleGraph<V>(10, false);
		}
		
		public Graph<V> readWeightedGraph(String filename) {
			return new SimpleWeightedGraph<V>(10);
		}
}
