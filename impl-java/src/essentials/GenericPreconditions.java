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

package essentials;

public class GenericPreconditions {

	public static boolean isElementNull(Object element) {
		return isTrue(element == null);
	}

	public static void raiseExceptionIfNull(Object element, String errorMsg) {
		if (element == null) {
			throw new NullPointerException(errorMsg);
		}
	}

	public static boolean isTrue(boolean condition) {
		if (condition) {
			return true;
		}
		return false;
	}

	public static void raiseExceptionIfTrue(boolean condition, String errorMsg) {
		if (condition) {
			throw new UnsupportedOperationException(errorMsg);
		}
	}

	public static boolean isFalse(boolean condition) {
		if (condition) {
			return false;
		}
		return true;
	}
	
	public static void raiseExceptionIfFalse(boolean condition, String errorMsg) {
		if (!condition) {
			throw new UnsupportedOperationException(errorMsg);
		}
	}
}
