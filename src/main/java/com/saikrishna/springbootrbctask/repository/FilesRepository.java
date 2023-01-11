package com.saikrishna.springbootrbctask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saikrishna.springbootrbctask.entity.File;

public interface FilesRepository extends JpaRepository<File, String> {
	File findByName(String fileName);
}
