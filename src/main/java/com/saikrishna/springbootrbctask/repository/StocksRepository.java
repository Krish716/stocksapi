package com.saikrishna.springbootrbctask.repository;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.saikrishna.springbootrbctask.entity.Stocks;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, Long> {

	Page<Stocks> findByStock(String stockTicker, Pageable page);

	@Query(value = """
						INSERT INTO stocks.tbl_stocks (quarter,stock,date,open,high,low,close,volume,percent_change_price,percent_change_volume_over_last_wk,previous_weeks_volume,next_weeks_open,next_weeks_close,percent_change_next_weeks_price,days_to_next_dividend,percent_return_next_dividend)
			VALUES (:quarter,:stock,:date,:open,:high,:low,:close,:volume,:percent_change_price,:percent_change_volume_over_last_wk,:previous_weeks_volume,:next_weeks_open,:next_weeks_close,:percent_change_next_weeks_price,:days_to_next_dividend,:percent_return_next_dividend);

						""", nativeQuery = true)
	@Modifying
	@Transactional
	int addStock(@Param("quarter") Long quarter, @Param("stock") String stock, @Param("date") Date date, @Param("open") BigDecimal open,
			@Param("high") BigDecimal high, @Param("low") BigDecimal low, @Param("close") BigDecimal close, @Param("volume") BigDecimal volume,
			@Param("percent_change_price") BigDecimal percent_change_price, @Param("percent_change_volume_over_last_wk") BigDecimal percent_change_volume_over_last_wk,
			@Param("previous_weeks_volume") BigDecimal previous_weeks_volume, @Param("next_weeks_open") BigDecimal next_weeks_open,
			@Param("next_weeks_close") BigDecimal next_weeks_close, @Param("percent_change_next_weeks_price") BigDecimal percent_change_next_weeks_price,
			@Param("days_to_next_dividend") BigDecimal days_to_next_dividend, @Param("percent_return_next_dividend") BigDecimal percent_return_next_dividend);
}
