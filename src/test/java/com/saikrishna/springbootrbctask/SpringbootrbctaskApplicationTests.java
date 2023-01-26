package com.saikrishna.springbootrbctask;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;


import org.json.simple.JSONObject;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saikrishna.springbootrbctask.entity.Stocks;
import com.saikrishna.springbootrbctask.repository.StocksRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class SpringbootrbctaskApplicationTests {

	@Autowired
	StocksRepository stocksRepo;
	
	@Test
	@Order(1)
	public void saveRecord() throws JsonMappingException, JsonProcessingException {
		JSONObject json = new JSONObject();
		long millis=System.currentTimeMillis();  
	    java.sql.Date date=new java.sql.Date(millis);  
		
		json.put("date", date.toString());
		json.put("quarter", "1");
		json.put("stock", "test");
		json.put("open", "16.71");  
		json.put("high","16.71");
		json.put("low", "15.64");
		json.put("close", "15.97");
		json.put("volume", "242963398");
		json.put("percentChangePrice", "-4.42849");
		json.put("percentChangeVolumeOverLastWk", "1.380223028");
		json.put("previousWeeksVolume", "239655616");
		json.put("nextWeeksOpen", "16.19");
		json.put("nextWeeksClose", "15.79");
		json.put("percentChangeNextWeeksPrice", "-2.47066");
		json.put("daysToNextDividend", "19");
		json.put("percentReturnNextDividend", "0.187852");

		Stocks s  = new ObjectMapper().readValue(json.toJSONString(), Stocks.class);
		stocksRepo.save(s);
		assertNotNull(stocksRepo.findByStock("test", null).get());
	}

	@Test
	@Order(2)
	public void readStockByFilter() {
		Page<Stocks> list = stocksRepo.findByStock("test", null);
		assertThat(list).size().isGreaterThan(0);
	}
	
}
