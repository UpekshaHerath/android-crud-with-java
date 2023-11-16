package com.example.tha_app_204074m.utils;

public class Validator {
    public static String DecimalPointValidator(String str, int MAX_DECIMAL) {
        if (str.charAt(0) == '.') str = "0" + str;
        int max = str.length();
        String rFinal = "";
        boolean after = false;
        int i = 0,decimal = 0;
        char t;
        while (i < max) {
            t = str.charAt(i);
            if (t == '.') {
                after = true;
            } else if(after) {
                decimal++;
                if (decimal > MAX_DECIMAL)
                    return rFinal;
            }
            rFinal = rFinal + t;
            i++;
        }
        return rFinal;
    }

    public static void InputsValidator(String name,String description,String price) throws RuntimeException {
        if(name==null||name.equals(""))throw new RuntimeException("Please enter name correctly");
        if(description==null||description.equals(""))throw new RuntimeException("Please enter description correctly");
        if(price==null||price.equals(""))throw new RuntimeException("Please enter price correctly");
    }
}
