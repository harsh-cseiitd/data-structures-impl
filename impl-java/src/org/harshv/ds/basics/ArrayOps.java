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

package org.harshv.ds.basics;

public class ArrayOps {

		public static <V extends Comparable> int binarySrearch(V[] array, V searchItem) {
			int startIndex  = 0;
			int endIndex    = array.length -1;
			int resultIndex = -1;
			
			while (startIndex <= endIndex) {
				if (startIndex == endIndex) {
					//System.out.println("start: " + startIndex + " and end: " + endIndex + " are equal ");
					if (array[startIndex].compareTo(searchItem) == 0) {
						//System.out.println("Element found at: " + startIndex);
						resultIndex = startIndex;
					} 
					break;
				}

				int midIndex = (startIndex + endIndex) / 2;
				if (array[midIndex].compareTo(searchItem)== 0) {
					resultIndex = midIndex;
					break;
				} else if (array[midIndex].compareTo(searchItem) > 0) {
					endIndex    = midIndex - 1;
				} else {
					startIndex  = midIndex + 1;
				}
			}
			return resultIndex;
		}
}
