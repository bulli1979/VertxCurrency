package me.digi;

import java.util.HashMap;
import java.util.Map;

public enum CurrencyNames {
	INSTANZCE;
	private Map<String,String> nameMap = new HashMap<String, String>();
	private CurrencyNames(){
		nameMap.put("EUR", "Euro");
		nameMap.put("USD", "US Dollar");
		nameMap.put("JPY", "Japanischer Yen");
		nameMap.put("BGN", "Bulgarische Lew");
		nameMap.put("CZK", "Tschechische Krone");
		nameMap.put("DKK", "Dänische Krone");
		nameMap.put("GBP", "Britisches Pfund");
		nameMap.put("HUF", "Ungarischer Forint");
		nameMap.put("PLN", "Polnischer Zloty");
		nameMap.put("RON", "Rumänischer Leu");
		nameMap.put("SEK", "Schwedische Krone");
		nameMap.put("CHF", "Schweizer Franken");
		nameMap.put("NOK", "Norwegische Kronen");
		nameMap.put("HRK", "Kroatische Kuna");
		nameMap.put("USD", "US Dollar");
		nameMap.put("RUB", "Russischer Rubel");
		nameMap.put("TRY", "Türkische Lira (neu)");
		nameMap.put("AUD", "Australischer Dollar");
		nameMap.put("BRL", "Brasilianischer Real");
		nameMap.put("CAD", "Kanadischer Dollar");
		nameMap.put("CNY", "Chinesischer Renminbi / Yuan");
		nameMap.put("HKD", "Hongkong-Dollar");
		nameMap.put("IDR", "Indonesische Rupiah");
		nameMap.put("ILS", "Schekel (Israel)");
		nameMap.put("INR", "Indische Rupie");
		nameMap.put("KRW", "Südkoreanischer Won");
		nameMap.put("MXN", "Mexikanischer Peso");
		nameMap.put("MYR", "Malaysischer Ringgit");
		nameMap.put("NZD", "Neuseeländischer Dollar");
		nameMap.put("PHP", "Phillipinischer Peso");
		nameMap.put("SGD", "Singapur-Dollar");
		nameMap.put("THB", "Thailändischer Baht");
		nameMap.put("ZAR", "Südafrikanischer Rand");
	}	
	public Map<String,String> getNameMap(){
		return nameMap;
	}
	public String getName(String key){
		return nameMap.get(key);
	}
}