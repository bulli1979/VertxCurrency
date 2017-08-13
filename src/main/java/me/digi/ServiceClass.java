package me.digi;

import java.math.BigDecimal;
import java.util.Map.Entry;
import business.Currency;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class ServiceClass {
	public static JsonArray getAllCurrencys() {
		JsonArray json = new JsonArray();
		for(Entry<String, Currency> entry : CurrencyData.INSTANZCE.getCurrencyMap().entrySet()){
			JsonObject cur = new JsonObject();
			cur.put("code", entry.getValue().getCode());
			cur.put("name", entry.getValue().getName());
			cur.put("course", String.valueOf(entry.getValue().getCourse()));
			json.add(cur);
		}
		return json;
	}
	
	public static JsonObject calculateCourse(String from,String to,String value){
		JsonObject json = new JsonObject();
		BigDecimal fromNumber = CurrencyData.INSTANZCE.getCurrencyMap().get(from).getCourse();
		BigDecimal toNumber = CurrencyData.INSTANZCE.getCurrencyMap().get(to).getCourse();
		BigDecimal valueNumber = new BigDecimal(value);
		BigDecimal result  = NumberCalculation.change(fromNumber, toNumber, valueNumber);
		BigDecimal relation = NumberCalculation.getRelation(fromNumber, toNumber);
		json.put("result",result.toString());
		json.put("relation", relation.toString());
		return json;
	}
}
