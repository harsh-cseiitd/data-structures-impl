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

/** Direction of Edge will be determined by the underline graph. So 
 *  It can't be set externally.
 *  @author harshv
 */

public class EdgeImpl implements Edge {
	
	private int src;
	private int dest;
	private boolean directed;

	public EdgeImpl() {
		this.src  = -1;
		this.dest = -1;
		this.directed = false;
	}
	
	public EdgeImpl(int src, int dest, boolean isDirected) {
		this.src  = src;
		this.dest = dest;
		this.directed = isDirected;
	}
	
	@Override
	public boolean isDirected() {
		return this.directed;
	}
	
	@Override
	public int getSrc() {
		return this.src;
	}
	
	@Override
	public int getDest() {
		return this.dest;
	}
}
