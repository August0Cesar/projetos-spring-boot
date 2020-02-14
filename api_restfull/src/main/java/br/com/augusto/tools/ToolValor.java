package br.com.augusto.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ToolValor {
	/**
	 * Função que formata Double para duas casas após virgula
	 * @param d - double atual
     * @param scale - numero de casas após a virgula
	 * @return Retorna um Double com o número de casas passado como parametro
   */
public static double roudingDouble(double d, int scale){
   BigDecimal bd = new BigDecimal(d).setScale(scale, RoundingMode.HALF_EVEN);
   return bd.doubleValue();
}

}
