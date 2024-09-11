package com.rays.pro4.Bean;

public final class PortfolioBean extends BaseBean {

	private String portfolioName;
	private Integer Amount;
	private String level;
	private String Strategy;

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public Integer getAmount() {
		return Amount;
	}

	public void setAmount(Integer amount) {
		Amount = amount;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getStrategy() {
		return Strategy;
	}

	public void setStrategy(String strategy) {
		Strategy = strategy;
	}

	@Override
	public String getkey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

}
