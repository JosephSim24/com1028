package com.films4you.req2;

/*
 * A class to test the sorting of a hashmap.
 * Not needed for the other classes to work.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortTest {

	public static void main(String[] args) {
		Film f1 = new Film(1);
		Film f2 = new Film(2);
		Film f3 = new Film(3);
		Film f4 = new Film(4);
		Film f5 = new Film(5);
		f1.setTitle("Film 1");
		f2.setTitle("Film 2");
		f3.setTitle("Film 3");
		f4.setTitle("Film 4");
		f5.setTitle("Film 5");
		
		HashMap<Film, Integer> map = new HashMap<>();
			LinkedHashMap<Film, Integer> sortedMap = new LinkedHashMap<>();
			List<Integer> values = new ArrayList<>();
			map.put(f1, 5);
			map.put(f2, 7);
			map.put(f3, 3);
			map.put(f4, 1);
			map.put(f5, 2);
			for (Map.Entry<Film, Integer> entry : map.entrySet()) {
				values.add(entry.getValue());
			}
			Collections.sort(values, Collections.reverseOrder());
			for (int num : values) {
				for (Entry<Film, Integer> entry : map.entrySet()) {
					if (entry.getValue().equals(num)) {
						sortedMap.put(entry.getKey(), num);
					}
				}
			}
			System.out.println(sortedMap);
		}

}
