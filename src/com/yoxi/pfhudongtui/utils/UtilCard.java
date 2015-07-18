package com.yoxi.pfhudongtui.utils;

public class UtilCard {
	  public static void main(String[] args) {
	        String card = "6212264100011198646";
	        System.out.println("      card: " + card);
	        System.out.println("check code: " + getBankCardCheckCode(card));
	        System.out.println("是否为银行卡:"+checkBankCard(card));
	    }
	    
	    /**
	     * 校验银行卡卡号
	     * @param cardId
	     * @return
	     */
	    public static boolean checkBankCard(String cardId) {
	             char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
	             if(bit == 'N'){
	                 return false;
	             }
	             return cardId.charAt(cardId.length() - 1) == bit;
	    }
	    
	    /**
	     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
	     * @param nonCheckCodeCardId
	     * @return
	     */
	    public static char getBankCardCheckCode(String nonCheckCodeCardId){
	        if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
	                || !nonCheckCodeCardId.matches("\\d+")) {
	            //如果传的不是数据返回N
	            return 'N';
	        }
	        char[] chs = nonCheckCodeCardId.trim().toCharArray();
	        int luhmSum = 0;
	        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
	            int k = chs[i] - '0';
	            if(j % 2 == 0) {
	                k *= 2;
	                k = k / 10 + k % 10;
	            }
	            luhmSum += k;           
	        }
	        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
	    }
}
