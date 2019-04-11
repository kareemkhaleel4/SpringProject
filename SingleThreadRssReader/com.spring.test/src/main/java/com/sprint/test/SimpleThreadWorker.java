package com.sprint.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimpleThreadWorker implements Runnable{
	String dataDir;
	String catDir;
	String fileName;

	public SimpleThreadWorker(String dataDir, String catDir, String fileName) {
		super();
		this.dataDir = dataDir;
		this.catDir = catDir;
		this.fileName = fileName;
	}

	@Override
	public void run() {
		Path p = Paths.get(dataDir);
		if(!Files.exists(p)) {
			new File(dataDir).mkdirs();
		}
		p = Paths.get(dataDir + "\\" + catDir);
		if (!Files.exists(p)) {
			new File(dataDir + "\\" + catDir).mkdirs();
		}
		
		try {
			new File(dataDir + "\\" + catDir + "\\" + fileName + ".xml").createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
