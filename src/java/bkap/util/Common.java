/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Random;

/**
 *
 * @author panth
 */
public class Common {
    // chuyển từ kiểu float sang định dạng tiền Việt

    public static String formatNumber(float number) {
        if (number < 1000) {
            return String.valueOf(number);
        }

        try {
            NumberFormat formatter = new DecimalFormat("###,###");
            String resp = formatter.format(number);
            resp = resp.replace(",", "."); //Nếu cần định dạng kiểu dấu phẩy (,)
            //thí dụ: 1,000,000, thì trong hàm formatNumber bỏ dòng này:
            return resp;
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * chuyển từ chuỗi có định dạng tiền ex: 1.000.000 sang float -> 1000000
     *
     * @param price
     * @return
     */
    public static String formatMoney(float price) {
        Locale locale = new Locale("vi", "VN");
        Currency currency = Currency.getInstance("VND");

        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance(locale);
        dfs.setCurrency(currency);
        
        NumberFormat numberformat = NumberFormat.getCurrencyInstance(locale);
        numberformat.setCurrency(currency);
        return numberformat.format(price);
    }

    public static float convertToFloat(String number) {
        float result = 0;
        return Float.parseFloat(number.replaceAll("[.]", ""));
    }

    public static int runDom() {
        return new Random().nextInt(9999 - 1000) + 1000;
    }
}
