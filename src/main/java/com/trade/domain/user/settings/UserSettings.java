package com.trade.domain.user.settings;

import com.trade.common.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "user_settings")
public class UserSettings {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "exchange_rate")
	private BigDecimal exchangeRate;
	@Enumerated(value = STRING)
	@Column(name = "currency")
	private Currency currency;
	@Column
	private double percent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}
}
