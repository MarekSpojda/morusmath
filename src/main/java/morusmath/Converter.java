package morusmath;

import java.math.BigInteger;

@SuppressWarnings("unused")
public class Converter {
    //TODO make javadoc to Converter
    private final int precision;

    public Converter() {
        this.precision = 10;
    }

    public Converter(int precision) {
        this.precision = precision;
    }

    public String binToDec(String bin) {
        Calculator bdc = new Calculator(0);
        String sum = "0";
        char[] chars = bin.toCharArray();
        int modifier = chars.length - 1;
        for (int i = 0; i < chars.length; i++) {
            String a = "" + chars[i];
            if (a.equals("1")) {
                String power = bdc.power("2", (modifier - i));
                sum = bdc.add(sum, power);
            }
        }
        return sum;
    }

    public String binToHex(String bin) {
        Calculator bdc = new Calculator(0);
        String dec = "0";
        char[] chars = bin.toCharArray();
        int modifier = chars.length - 1;
        for (int i = 0; i < chars.length; i++) {
            String temp = bdc.multiply(("" + chars[i]), bdc.power("2", (modifier - i)));
            dec = bdc.add(dec, temp);
        }

        StringBuilder result = new StringBuilder();
        while (true) {
            BigInteger temp = new BigInteger(dec).remainder(new BigInteger("16"));
            String decModifier = "" + temp;
            if (temp.compareTo(new BigInteger("10")) == 0) {
                decModifier = "A";
            } else if (temp.compareTo(new BigInteger("11")) == 0) {
                decModifier = "B";
            } else if (temp.compareTo(new BigInteger("12")) == 0) {
                decModifier = "C";
            } else if (temp.compareTo(new BigInteger("13")) == 0) {
                decModifier = "D";
            } else if (temp.compareTo(new BigInteger("14")) == 0) {
                decModifier = "E";
            } else if (temp.compareTo(new BigInteger("15")) == 0) {
                decModifier = "F";
            }
            result.insert(0, "" + decModifier);
            if (new BigInteger(dec).compareTo(new BigInteger("16")) < 0) {
                break;
            }
            dec = bdc.divide(dec, "16");
        }
        return result.toString();
    }

    public String decToBin(String dec) {
        BigInteger bigIntDec = new BigInteger(dec);
        BigInteger temp;
        StringBuilder result = new StringBuilder();
        while (true) {
            temp = bigIntDec.remainder(new BigInteger("2"));
            String modifier = "" + temp;
            result.insert(0, "" + modifier);
            if (bigIntDec.compareTo(new BigInteger("2")) < 0) {
                break;
            }
            bigIntDec = bigIntDec.divide(new BigInteger("2"));
        }
        return result.toString();
    }

    public String decToHex(String dec) {
        int intDec = Integer.parseInt(dec);
        StringBuilder result = new StringBuilder();
        while (true) {
            int temp = intDec % 16;
            String modifier = "" + temp;
            if (temp == 10) {
                modifier = "A";
            } else if (temp == 11) {
                modifier = "B";
            } else if (temp == 12) {
                modifier = "C";
            } else if (temp == 13) {
                modifier = "D";
            } else if (temp == 14) {
                modifier = "E";
            } else if (temp == 15) {
                modifier = "F";
            }
            result.insert(0, "" + modifier);
            if (intDec < 16) {
                break;
            }
            intDec = intDec / 16;
        }
        return result.toString();
    }

    public int getPrecision() {
        return precision;
    }

    public String gradToRadian(String grad) {
        Calculator bdc = new Calculator(this.precision);
        String nominator = bdc.multiply(grad, Calculator.PI);
        return bdc.divide(nominator, "200");
    }

    public String hexToBin(String hex) {
        int dec = 0;
        String[] hexTab = hex.split("");
        for (int i = 0; i < hexTab.length; i++) {
            switch (hexTab[i]) {
                case "A" -> hexTab[i] = "10";
                case "B" -> hexTab[i] = "11";
                case "C" -> hexTab[i] = "12";
                case "D" -> hexTab[i] = "13";
                case "E" -> hexTab[i] = "14";
                case "F" -> hexTab[i] = "15";
            }
        }
        int modifier = hexTab.length - 1;
        for (int i = 0; i < hexTab.length; i++) {
            dec += Integer.parseInt(hexTab[i]) * Math.pow(16, (modifier - i));
        }

        StringBuilder result = new StringBuilder();
        while (true) {
            int temp = dec % 2;
            String decModifier = "" + temp;
            result.insert(0, "" + decModifier);
            if (dec < 2) {
                break;
            }
            dec = dec / 2;
        }
        return result.toString();
    }

    public String hexToDec(String hex) {
        int result = 0;
        String[] hexTab = hex.split("");
        for (int i = 0; i < hexTab.length; i++) {
            switch (hexTab[i]) {
                case "A" -> hexTab[i] = "10";
                case "B" -> hexTab[i] = "11";
                case "C" -> hexTab[i] = "12";
                case "D" -> hexTab[i] = "13";
                case "E" -> hexTab[i] = "14";
                case "F" -> hexTab[i] = "15";
            }
        }
        int modifier = hexTab.length - 1;
        for (int i = 0; i < hexTab.length; i++) {
            result += Integer.parseInt(hexTab[i]) * Math.pow(16, (modifier - i));
        }
        return "" + result;
    }

    public String radianToGrad(String radian) {
        Calculator bdc = new Calculator(this.precision);
        String nominator = bdc.multiply(radian, "200");
        return bdc.divide(nominator, Calculator.PI);
    }
}