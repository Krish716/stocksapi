package com.saikrishna.springbootrbctask.service.fileservice;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.saikrishna.springbootrbctask.entity.File;



public interface FileStorageService {

	File store(MultipartFile file)throws IOException;
	
	Stream<File> getAllFiles();
	
	File getFile(String id);

	File getFileByName(String name);
}
