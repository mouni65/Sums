package com.demo.problems;

//1. Scans a folder based on input,  it returns the basic path of each file 
//2. Gets the creation time, as input based on month.
//3. using pattern mm it gets the files based on month pattern
//4. add the files based on month using hashmap month as key and list of files as an array
//5. give us the size of files based on month.

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Problem2 {

	public static void main(String[] args) throws IOException {

		File file = new File("D:\\IRM");
		File files[] = file.listFiles();
		Map<String, List<File>> map = new HashMap<>();
		for (int i = 0; i < files.length; i++) {
			BasicFileAttributes attrs;
			attrs = Files.readAttributes(files[i].toPath(), BasicFileAttributes.class);
			FileTime time = attrs.creationTime();
			String pattern = "MM ";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String formatted = simpleDateFormat.format(new Date(time.toMillis()));

			if (map.containsKey(formatted)) {
				List<File> list = map.get(formatted);
				list.add(files[i]);
				map.put(formatted, list);
			} else {
				List<File> list1 = new ArrayList<>();
				list1.add(files[i]);
				map.put(formatted, list1);
			}
		}
		System.out.println(map);
		for (Map.Entry m : map.entrySet()) {
			List list = (List) m.getValue();
			System.out.println(list.size());
		}
	}

}
