package me.digi;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Mirko Eberlein
 * @version 0.1
 *
 */

/**
 * The NumberCalculation class provides the necessary basic math operation to
 * convert an initial value with specific currency to a target value with a specific currency. 
 * Furthermore it provides an operation to calculate the ratio between two currencies.
 * 
 */
public class NumberCalculation {
	public static BigDecimal change(BigDecimal from,BigDecimal to,BigDecimal value){
		return  new BigDecimal(1).divide(from,6,RoundingMode.HALF_UP).multiply(to).multiply(value);
	}
	
	public static BigDecimal getRelation(BigDecimal from,BigDecimal to){
		return new BigDecimal(1).divide(from,6,RoundingMode.HALF_UP).multiply(to);
	}
	
}
