package com.jd.seed.exercise.concurrent.forkJoin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 * 文件夹
 * 
 * </pre>
 * 
 * @author mecarlen 2019年3月18日 下午4:45:17
 */
public class Folder {
	// 子文件夹
	private List<Folder> subfolders;
	// 文件
	private List<Document> documents;

	public Folder(List<Folder> subfolders, List<Document> documents) {
		this.subfolders = subfolders;
		this.documents = documents;
	}

	public List<Folder> getSubfolders() {
		return subfolders;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public static Folder loadFolderFromDirectory(File file) throws FileNotFoundException, IOException {
		List<Folder> subfolders = new LinkedList<>();
		List<Document> documents = new LinkedList<>();
		for (File entry : file.listFiles()) {
			if (entry.isDirectory()) {
				subfolders.add(Folder.loadFolderFromDirectory(entry));
			} else {
				documents.add(Document.fromFile(entry));
			}
		}
		return new Folder(subfolders, documents);
	}

}
