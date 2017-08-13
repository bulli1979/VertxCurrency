package me.digi;

import java.sql.Date;
import java.util.Map;
import business.Currency;

public class CurrencyDataold {
	public CurrencyDataold INSTANCE;
	private Map<String,Currency> currencyMap;
	private CurrencyDataold(){}
	private Date date;
	public CurrencyDataold getInstanze(){
		if(currencyMap == null){
			fillMap();
		}
		return INSTANCE;
	}
	
	private void fillMap(){
		
	}

	public Map<String, Currency> getCurrencyMap() {
		return currencyMap;
	}

	public void setCurrencyMap(Map<String, Currency> currencyMap) {
		this.currencyMap = currencyMap;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
