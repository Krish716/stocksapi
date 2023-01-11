package com.saikrishna.springbootrbctask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.saikrishna.springbootrbctask.entity.Stocks;
import com.saikrishna.springbootrbctask.message.Message;
import com.saikrishna.springbootrbctask.service.fileservice.FileStorageService;
import com.saikrishna.springbootrbctask.stockservice.StocksService;

@RestController
public class StockController {

	@Autowired
	private FileStorageService storageService;
	@Autowired
	private StocksService stockService;
	
	
	@PostMapping("/bulk-insert")
	public ResponseEntity<Message> uploadBulkData(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			storageService.store(file);
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			String msg = stockService.uploadStocksinFile(file);
			return ResponseEntity.status(HttpStatus.OK).body(new Message(message+"----"+msg));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Message(message + e));
		}
	}
	
	@GetMapping("/")
	public List<Stocks> getStocksByFilter(@RequestParam String stockTicker,  Pageable page) {
		return stockService.readByStockTicker(stockTicker, page);
	}
	
	@PostMapping("/")
	public ResponseEntity<Message> uploadMissingDataFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			storageService.store(file);
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			Stocks msg = stockService.uploadMissingStockbyFile(file);
			return ResponseEntity.status(HttpStatus.OK).body(new Message(message+"----"+msg.getStock()));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Message(message + e));
		}
	}
}
