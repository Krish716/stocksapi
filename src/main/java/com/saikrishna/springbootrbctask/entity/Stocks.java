package com.saikrishna.springbootrbctask.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_stocks")
public class Stocks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long quarter;

	private String stock;

	private Date date;

	private BigDecimal open;

	private BigDecimal high;

	private BigDecimal low;

	private BigDecimal close;

	private BigDecimal volume;

	private BigDecimal percentChangePrice;

	private BigDecimal percentChangeVolumeOverLastWk;

	private BigDecimal previousWeeksVolume;

	private BigDecimal nextWeeksOpen;

	private BigDecimal nextWeeksClose;

	private BigDecimal percentChangeNextWeeksPrice;

	private BigDecimal daysToNextDividend;

	private BigDecimal percentReturnNextDividend;

	/*
	 * 
	 * @JoinColumn(name="user_id", nullable = false)
	 * 
	 * @OnDelete(action = OnDeleteAction.CASCADE)
	 * 
	 * @JsonIgnore private User user;
	 */

	public Stocks(Long quarter, String stock, Date date, BigDecimal open, BigDecimal high, BigDecimal low,
			BigDecimal close, BigDecimal volume, BigDecimal percentChangePrice,
			BigDecimal percentChangeVolumeOverLastWk, BigDecimal previousWeeksVolume, BigDecimal nextWeeksOpen,
			BigDecimal nextWeeksClose, BigDecimal percentChangeNextWeeksPrice, BigDecimal daysToNextDividend,
			BigDecimal percentReturnNextDividend) {
		this.quarter = quarter;
		this.stock = stock;
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.percentChangePrice = percentChangePrice;
		this.percentChangeVolumeOverLastWk = percentChangeVolumeOverLastWk;
		this.previousWeeksVolume = previousWeeksVolume;
		this.nextWeeksOpen = nextWeeksOpen;
		this.nextWeeksClose = nextWeeksClose;
		this.percentChangeNextWeeksPrice = percentChangeNextWeeksPrice;
		this.daysToNextDividend = daysToNextDividend;
		this.percentReturnNextDividend = percentReturnNextDividend;
	}

	public Long getQuarter() {
		return quarter;
	}

	public void setQuarter(Long quarter) {
		this.quarter = quarter;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public BigDecimal getOpen() {
		return open;
	}

	public void setOpen(BigDecimal open) {
		this.open = open;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getHigh() {
		return high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	public BigDecimal getLow() {
		return low;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public BigDecimal getClose() {
		return close;
	}

	public void setClose(BigDecimal close) {
		this.close = close;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public BigDecimal getPercentChangePrice() {
		return percentChangePrice;
	}

	public void setPercentChangePrice(BigDecimal percentChangePrice) {
		this.percentChangePrice = percentChangePrice;
	}

	public BigDecimal getPercentChangeVolumeOverLastWk() {
		return percentChangeVolumeOverLastWk;
	}

	public void setPercentChangeVolumeOverLastWk(BigDecimal percentChangeVolumeOverLastWk) {
		this.percentChangeVolumeOverLastWk = percentChangeVolumeOverLastWk;
	}

	public BigDecimal getPreviousWeeksVolume() {
		return previousWeeksVolume;
	}

	public void setPreviousWeeksVolume(BigDecimal previousWeeksVolume) {
		this.previousWeeksVolume = previousWeeksVolume;
	}

	public BigDecimal getNextWeeksOpen() {
		return nextWeeksOpen;
	}

	public void setNextWeeksOpen(BigDecimal nextWeeksOpen) {
		this.nextWeeksOpen = nextWeeksOpen;
	}

	public BigDecimal getNextWeeksClose() {
		return nextWeeksClose;
	}

	public void setNextWeeksClose(BigDecimal nextWeeksClose) {
		this.nextWeeksClose = nextWeeksClose;
	}

	public BigDecimal getPercentChangeNextWeeksPrice() {
		return percentChangeNextWeeksPrice;
	}

	public void setPercentChangeNextWeeksPrice(BigDecimal percentChangeNextWeeksPrice) {
		this.percentChangeNextWeeksPrice = percentChangeNextWeeksPrice;
	}

	public BigDecimal getDaysToNextDividend() {
		return daysToNextDividend;
	}

	public void setDaysToNextDividend(BigDecimal daysToNextDividend) {
		this.daysToNextDividend = daysToNextDividend;
	}

	public BigDecimal getPercentReturnNextDividend() {
		return percentReturnNextDividend;
	}

	public void setPercentReturnNextDividend(BigDecimal percentReturnNextDividend) {
		this.percentReturnNextDividend = percentReturnNextDividend;
	}

}
