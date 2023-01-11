package com.saikrishna.springbootrbctask.stockservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saikrishna.springbootrbctask.entity.Stocks;
import com.saikrishna.springbootrbctask.repository.StocksRepository;
import com.saikrishna.springbootrbctask.service.fileservice.FileStorageService;

@Service
public class StocksServiceImpl implements StocksService {

	@Autowired
	private StocksRepository stocksRepo;

	@Autowired
	private FileStorageService storageService;

	@Override
	public List<Stocks> getAllStocks() {
		return stocksRepo.findAll();
	}

	@Override
	public List<Stocks> readByStockTicker(String stockTicker, Pageable page) {
		// TODO Auto-generated method stub
		return stocksRepo.findByStock(stockTicker, page).toList();
	}
	
	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Override
	public int saveStock(Stocks stock) {
		Calendar c = Calendar.getInstance();
		c.setTime(stock.getDate());
		java.util.Date temp = (java.util.Date) c.getTime();
		return stocksRepo.addStock(stock.getQuarter(), stock.getStock(), temp, stock.getOpen(), stock.getHigh(),
				stock.getLow(), stock.getClose(), stock.getVolume(), stock.getPercentChangePrice(),
				stock.getPercentChangeVolumeOverLastWk(), stock.getPreviousWeeksVolume(), stock.getNextWeeksOpen(),
				stock.getNextWeeksClose(), stock.getPercentChangeNextWeeksPrice(), stock.getDaysToNextDividend(),
				stock.getPercentReturnNextDividend());
		// return stocksRepo.save(stock);
	}

	@Override
	public List<Stocks> readByFile(String fileURL, Pageable page) throws FileNotFoundException {
		// TODO Auto-generated method stub
		FileReader fileTemp = new FileReader(new File(fileURL));
		System.out.println(fileURL);
		String temp;
		try {
			BufferedReader br = new BufferedReader(fileTemp);

			temp = br.readLine();
			while (temp != null) {
				temp = br.readLine();
				System.out.println(temp);
				continue;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Override
	public String uploadStocksinFile(MultipartFile file) throws Exception {
		com.saikrishna.springbootrbctask.entity.File fileDB = storageService.getFileByName(file.getOriginalFilename());
		String str = new String(fileDB.getData());
		String[] lines = str.split("\\r?\\n");
		String[] linesData = Arrays.copyOfRange(lines, 1, lines.length);
		Long[] quarterArr = new Long[linesData.length];
		String[] stockArr = new String[linesData.length];
		Date[] dateArr = new Date[linesData.length];
		BigDecimal[] openArr = new BigDecimal[linesData.length];
		BigDecimal[] highArr = new BigDecimal[linesData.length];
		BigDecimal[] lowArr = new BigDecimal[linesData.length];
		BigDecimal[] closeArr = new BigDecimal[linesData.length];
		BigDecimal[] volumeArr = new BigDecimal[linesData.length];
		BigDecimal[] percentChangePriceArr = new BigDecimal[linesData.length];
		BigDecimal[] percentChangeVolumeOverLastWkArr = new BigDecimal[linesData.length];
		BigDecimal[] previousWeeksVolumeArr = new BigDecimal[linesData.length];
		BigDecimal[] nextWeeksOpenArr = new BigDecimal[linesData.length];
		BigDecimal[] nextWeeksCloseArr = new BigDecimal[linesData.length];
		BigDecimal[] percentChangeNextWeeksPriceArr = new BigDecimal[linesData.length];
		BigDecimal[] daysToNextDividendArr = new BigDecimal[linesData.length];
		BigDecimal[] percentReturnNextDividendArr = new BigDecimal[linesData.length];
		String[] res;
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		symbols.setDecimalSeparator('.');
		String pattern = "#,##0.0#";
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);
		int count = 0;
		for (String line : linesData) {
			res = line.split("[,]", 0);

			quarterArr[count] = Long.parseLong(res[0]);
			stockArr[count] = res[1];
			try {
				java.util.Date utilPackageDate = new SimpleDateFormat("MM/dd/yyyy").parse(res[2]);
				// Date dateTemp = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(res[2]);
				System.out.println(utilPackageDate);
				java.sql.Date sqlPackageDate = new java.sql.Date(utilPackageDate.getTime());
				// Date dateTemp = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(res[2]);
				// String dateString2 = new SimpleDateFormat("yyyy-MM-dd").format(dateTemp);
				dateArr[count] = sqlPackageDate;
				openArr[count] = (BigDecimal) decimalFormat.parse(res[3].substring(1));
				highArr[count] = (BigDecimal) decimalFormat.parse(res[4].substring(1));
				lowArr[count] = (BigDecimal) decimalFormat.parse(res[5].substring(1));
				closeArr[count] = (BigDecimal) decimalFormat.parse(res[6].substring(1));
				if (res[7] != "" && res[7] != null) {
					volumeArr[count] = (BigDecimal) decimalFormat.parse(res[7]);
				} else
					volumeArr[count] = null;

				if (res[8] != "" && res[8] != null) {
					percentChangePriceArr[count] = (BigDecimal) decimalFormat.parse(res[8]);
				} else
					percentChangePriceArr[count] = null;

				if (res[9] != "" && res[9] != null) {
					percentChangeVolumeOverLastWkArr[count] = (BigDecimal) decimalFormat.parse(res[9]);
				} else
					percentChangeVolumeOverLastWkArr[count] = null;

				if (res[10] != "" && res[10] != null) {
					previousWeeksVolumeArr[count] = (BigDecimal) decimalFormat.parse(res[10]);
				} else
					previousWeeksVolumeArr[count] = null;

				if (res[11] != "" && res[11] != null) {
					nextWeeksOpenArr[count] = (BigDecimal) decimalFormat.parse(res[11].substring(1));
				} else
					nextWeeksOpenArr[count] = null;

				if (res[12] != "" && res[12] != null) {
					nextWeeksCloseArr[count] = (BigDecimal) decimalFormat.parse(res[12].substring(1));
				} else
					nextWeeksCloseArr[count] = null;

				if (res[13] != "" && res[13] != null) {
					percentChangeNextWeeksPriceArr[count] = (BigDecimal) decimalFormat.parse(res[13]);
				} else
					percentChangeNextWeeksPriceArr[count] = null;

				if (res[14] != "" && res[14] != null) {
					daysToNextDividendArr[count] = (BigDecimal) decimalFormat.parse(res[14]);
				} else
					daysToNextDividendArr[count] = null;

				if (res[14] != "" && res[14] != null) {
					percentReturnNextDividendArr[count] = (BigDecimal) decimalFormat.parse(res[15]);
				}
				saveStock(new Stocks(quarterArr[count], stockArr[count], dateArr[count], openArr[count], highArr[count],
						lowArr[count], closeArr[count], volumeArr[count], percentChangePriceArr[count],
						percentChangeVolumeOverLastWkArr[count], previousWeeksVolumeArr[count], nextWeeksOpenArr[count],
						nextWeeksCloseArr[count], percentChangeNextWeeksPriceArr[count], daysToNextDividendArr[count],
						percentReturnNextDividendArr[count]));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;

		}
		return "Stocks in file upload successfully!!!";

	}

	@Override
	public Stocks uploadMissingStockbyFile(MultipartFile file) throws Exception {
		com.saikrishna.springbootrbctask.entity.File fileDB = storageService.getFileByName(file.getOriginalFilename());
		String str = new String(fileDB.getData());
		
		JSONParser parser = new JSONParser();  
		JSONObject json = (JSONObject) parser.parse(str);  
		//JSONParser parser = new JSONParser();
		//Reader reader = new FileReader(file.getOriginalFilename());

		//Object jsonObj = parser.parse(reader);

		//JSONObject jsonObject = (JSONObject) jsonObj;
		//Object o  = new ObjectMapper().readValue(str, Object.class); 
		
		java.util.Date utilPackageDate = new SimpleDateFormat("MM/dd/yyyy").parse(json.get("date").toString());
		java.sql.Date sqlPackageDate = new java.sql.Date(utilPackageDate.getTime());
		
		json.put("date", this.addDays(sqlPackageDate, 1).toString());
		String open = json.get("open").toString().substring(1);
		String high = json.get("high").toString().substring(1);
		String low = json.get("low").toString().substring(1);
		String close = json.get("close").toString().substring(1);
		String nextWeeksOpen = json.get("nextWeeksOpen").toString().substring(1);
		String nextWeeksClose = json.get("nextWeeksClose").toString().substring(1);
		
		json.put("open", open);
		json.put("high", high);
		json.put("low", low);
		json.put("close", close);
		json.put("nextWeeksOpen", nextWeeksOpen);
		json.put("nextWeeksClose", nextWeeksClose);
		
		

		Stocks s  = new ObjectMapper().readValue(json.toJSONString(), Stocks.class);
		//JSONObject jsonObject = (JSONObject) o;
		//JSONParser parser = new JSONParser();  
		//JSONObject json = (JSONObject) parser.parse(str);  
		//System.out.println("JSON="+jsonObject);
		//System.out.println(s.getDate());
		stocksRepo.save(s);
		return s;
	}

	 public static Date addDays(Date date, int days) {
	        Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        c.add(Calendar.DATE, days);
	        return new Date(c.getTimeInMillis());
	    }
}
