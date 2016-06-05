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

package ds.linearstructures;

public class UFSystem {
	private int[] treeRoot;
	private int[] treeSize;
	
	public UFSystem(int N) {
		this.treeRoot = new int[N];
		this.treeSize = new int[N];
		for (int i = 0; i < N ; i++) {
			this.treeRoot[i] = i;
			this.treeSize[i] = 1;
		}
	}
	
	public int findComponent(int a) {
		while (a != this.treeRoot[a]) {
			this.treeRoot[a] = this.treeRoot[this.treeRoot[a]];
			a = this.treeRoot[a];
		}
		return a;
	}

	public boolean isConnected(int a, int b) {
		return (findComponent(a) == findComponent(b));
	}

	public void union(int a, int b) {
		int rootA = findComponent(a);
		int rootB = findComponent(b);
		
		/* If roots are eual then no need to do anything */
		if (rootA != rootB) {
			if (this.treeSize[rootA] < this.treeSize[rootB]) {
				this.treeRoot[rootA] = rootB;
				this.treeSize[rootB] += this.treeSize[rootA];
			} else {
				this.treeRoot[rootB] = rootA;
				this.treeSize[rootA] += this.treeSize[rootB];
			}
		}
	}
}
