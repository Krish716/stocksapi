package com.saikrishna.springbootrbctask.stockservice;
import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.saikrishna.springbootrbctask.entity.Stocks;
public interface StocksService {

	List<Stocks> getAllStocks();
	
	List<Stocks> readByStockTicker(String stockTicker, Pageable page);
	
	List<Stocks> readByFile(String fileURL,Pageable page)throws FileNotFoundException;
	int saveStock(Stocks stock);
	
	@Modifying
    @Transactional
	String uploadStocksinFile(MultipartFile file) throws Exception;
	
	@Modifying
	@Transactional
	Stocks uploadMissingStockbyFile(MultipartFile file) throws Exception; 
}
