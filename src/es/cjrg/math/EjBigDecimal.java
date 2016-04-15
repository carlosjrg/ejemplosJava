package es.cjrg.math;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class EjBigDecimal {

	public static void main(String[] args) {
		
		BigDecimal bd = new BigDecimal(32194108.134);
		System.out.println("Valor original: " + bd);

		// Operar
		bd = bd.add(new BigDecimal(3431.48));

		// Redondear
		bd = bd.setScale(2, BigDecimal.ROUND_DOWN);
		System.out.println("Valor redondeado hacia abajo: " + bd);

		// Formatear
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		df.setGroupingUsed(false);// Separadores de Miles
		String result = df.format(bd);
		System.out.println("Valor formateado: " + result);
	}
}
