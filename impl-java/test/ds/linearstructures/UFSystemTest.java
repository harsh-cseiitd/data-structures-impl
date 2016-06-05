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

import static org.junit.Assert.*;

import org.junit.Test;

public class UFSystemTest {

	@Test
	public void testInit() {
		int N = 10;
		UFSystem system = new UFSystem(N);
		for(int i = 0; i < N ; i++) {
			assert(system.findComponent(i) == i);
		}
	}
	
	@Test
	public void testUnion() {
		int N = 10;
		UFSystem system = new UFSystem(N);
		for(int i = 1; i < N ; i++) {
			system.union(i-1, i);
		}
		for(int i = 0; i < N ; i++) {
			assert(system.findComponent(i-1) == system.findComponent(i-1));
		}
	}

}
