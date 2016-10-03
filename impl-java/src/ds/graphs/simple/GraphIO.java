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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GraphIO {
	
	/**
	 * File format is following:
	 * vertices=
	 * edges=
	 * directed=
	 * weighted=false
	 * srcvertex1 destvertex1
	 * srcvertex2 destvertex2
	 * srcvertex3 destvertex3
	 * @param filename
	 * @return
	 */

	public static SimpleGraph readSimpleGraphFromFile(String filename) {
		BufferedReader fileHandle = null;
		System.out.println("INFO: Reading Simple graph from file: " + filename);
		try {
			fileHandle = new BufferedReader(new FileReader(new File(filename)));
		} catch (FileNotFoundException e) {
			System.out.println(String.format("ERROR: file not found: %s exception: %s", filename, e.getMessage()));
			return null;
		}

		int vertices       = Integer.parseInt(readProperty(fileHandle, "vertices"));
		int edges          = Integer.parseInt(readProperty(fileHandle, "edges"));
		boolean isDirected = Boolean.getBoolean(readProperty(fileHandle, "directed"));
		boolean isWeighted = Boolean.getBoolean(readProperty(fileHandle, "weighted"));
		
		System.out.println(String.format("INFO: started reading graph from file: %s", filename));
		System.out.println(String.format("INFO: vertices:%d  edges: %d", vertices, edges));
		System.out.println(String.format("INFO: directed:%s  weighted: %s", isDirected, isWeighted));

		SimpleGraphImpl graph  = new SimpleGraphImpl(vertices, isDirected);
		readGraphEdges(fileHandle, graph);
		return graph;
	}

	
	private static String readProperty(BufferedReader fileHandle, String key) {
		String line  = "";
		try {
			if ((line = fileHandle.readLine()) != null) {
				String[] tokens = line.split("=");
				if (tokens[0].trim().equals(key)) {
					return tokens[1].trim();
				} else {
					System.out.println(String.format("ERROR: key %s not found at proper place", key));
					
				}
			}
		} catch (IOException e) {
			System.out.println(String.format("ERROR: exception in reading key %s , : %s", key, e.getMessage()));
		}
		return "";
	}
	
	private static void readGraphEdges(BufferedReader fileHandle, SimpleGraphImpl graph) {
		String line;
		try {
			while ((line = fileHandle.readLine()) != null) {
				String[] tokens = line.split(" ");
				int src  = Integer.parseInt(tokens[0].trim());
				int dest = Integer.parseInt(tokens[1].trim());
				graph.addEdge(src, dest);
				System.out.println(String.format("INFO: Added edge from %d to %d", src, dest));
			}
		} catch (NumberFormatException | IOException e) {
			System.out.println(String.format("ERROR: exception in reading vertices %s ", e.getMessage()));
		}
	}
	
	public static void writeSimpleGraphToFile(SimpleGraph graph, String filename, boolean append) {
		
	}
}
