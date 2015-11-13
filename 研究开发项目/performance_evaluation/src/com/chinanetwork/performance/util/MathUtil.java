package com.chinanetwork.performance.util;

import java.math.BigDecimal;

public class MathUtil {
	
	public static float baoliu(float f, int i)
	{
		BigDecimal big = new BigDecimal(f);  
		float baoliu = big.setScale(i, BigDecimal.ROUND_HALF_UP).floatValue();
		return baoliu;
	}
	
	

}
