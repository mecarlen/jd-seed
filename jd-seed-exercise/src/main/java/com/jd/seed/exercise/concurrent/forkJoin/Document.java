package com.jd.seed.exercise.concurrent.forkJoin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 * 文档
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月18日 下午4:37:52
 */
public class Document {
	private List<String> lines;

	public Document(List<String> lines) {
		this.lines = lines;
	}

	public List<String> getLines() {
		return lines;
	}

	public static Document fromFile(File file) throws FileNotFoundException, IOException {
		List<String> lines = new LinkedList<String>();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line = reader.readLine();
			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}
		}
		return new Document(lines);
	}

}
