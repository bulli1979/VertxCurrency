package me.digi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import business.Currency;

public enum CurrencyData {
	INSTANZCE;
	private Map<String,Currency> currencyMap = null;
	private CurrencyData(){}
	private Date date;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private boolean chkDate() {
		if(this.date != null){
			Date now = new Date();
			if(!sdf.format(now).equals(sdf.format(date))){
				return true;
			}
		}
		return false;
	}

	private void fillMap(){
		CurrencyImportService.importXML();
	}

	public Map<String, Currency> getCurrencyMap() {
		if(currencyMap == null || chkDate()){
			fillMap();
		}
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
