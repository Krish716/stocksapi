package com.saikrishna.springbootrbctask.service.fileservice;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.saikrishna.springbootrbctask.entity.File;
import com.saikrishna.springbootrbctask.repository.FilesRepository;

@Service
public class FileStorageServiceImpl implements FileStorageService {

	@Autowired
	private FilesRepository filesRepository;

	@Override
	public File store(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    File FileDB = new File(fileName, file.getContentType(), file.getBytes());
	    return filesRepository.save(FileDB);
	}

	@Override
	public Stream<File> getAllFiles() {
		return filesRepository.findAll().stream();
	}

	@Override
	public File getFile(String id) {
		// TODO Auto-generated method stub
		return filesRepository.findById(id).get();
	}

	@Override
	public File getFileByName(String name) {
		// TODO Auto-generated method stub
		return filesRepository.findByName(name);
	}
	
}
