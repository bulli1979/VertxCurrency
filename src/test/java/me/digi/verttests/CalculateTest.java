package me.digi.verttests;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

import me.digi.NumberCalculation;
public class CalculateTest {
	@Test
	public void calculateTest(){
		BigDecimal from = new BigDecimal(1);
		BigDecimal to = new BigDecimal(1.2);
		BigDecimal testOne =  round(NumberCalculation.change(from, to, new BigDecimal(1)),1);
		assertTrue(round(new BigDecimal(1.2),1).equals(testOne));
		from = new BigDecimal(0.8);
		to = new BigDecimal(7.1);
		BigDecimal testTwo = round(NumberCalculation.change(from, to, new BigDecimal(1)),3);
		assertEquals(round(new BigDecimal(8.875),3),testTwo);
		from = new BigDecimal(0.001);
		to = new BigDecimal(1000);
		BigDecimal testThree = NumberCalculation.change(from, to, new BigDecimal(1)).setScale(0,RoundingMode.HALF_UP);
		assertEquals(round(new BigDecimal(1000000),0), testThree);
	}
	
	private BigDecimal round(BigDecimal n,int numbers){
		BigDecimal ret = n.setScale(numbers, RoundingMode.HALF_UP);
		return ret;
	}
}
