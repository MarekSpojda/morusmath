package morusmath;

import java.math.BigInteger;

/**
 * <b>Converter</b><br>
 * <br>
 * <i>public class Converter</i><br>
 * <br>
 * Class for converting between unit systems.
 */

@SuppressWarnings("unused")
public class Converter {
    private final int precision;

    /**
     * <b>Converter</b><br>
     * <br>
     * <i>public Converter(int precision)</i><br>
     * <br>
     * Returns instance of Converter and sets given precision.
     *
     * @param precision precision to be set.
     */
    public Converter(int precision) {
        this.precision = precision;
    }

    /**
     * <b>binToDec</b><br>
     * <br>
     * <i>public String binToDec(String bin)</i><br>
     * <br>
     * Converts binary number to decimal number.
     *
     * @param bin binary number from which decimal number would be calculated.
     * @return decimal version of number.
     */
    public String binToDec(String bin) {
        Calculator calculator = new Calculator(0);
        String sum = "0";
        char[] chars = bin.toCharArray();
        int modifier = chars.length - 1;
        for (int i = 0; i < chars.length; i++) {
            String a = "" + chars[i];
            if (a.equals("1")) {
                String power = calculator.power("2", (modifier - i));
                sum = calculator.add(sum, power);
            }
        }
        return sum;
    }

    /**
     * <b>binToHex</b><br>
     * <br>
     * <i>public String binToHex(String bin)</i><br>
     * <br>
     * Converts binary number to hexadecimal number.
     *
     * @param bin binary number from which hexadecimal number would be calculated.
     * @return hexadecimal version of number.
     */
    public String binToHex(String bin) {
        Calculator calculator = new Calculator(0);
        String dec = "0";
        char[] chars = bin.toCharArray();
        int modifier = chars.length - 1;
        for (int i = 0; i < chars.length; i++) {
            String temp =
                    calculator.multiply(("" + chars[i]), calculator.power("2", (modifier - i)));
            dec = calculator.add(dec, temp);
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
            dec = calculator.divide(dec, "16");
        }
        return result.toString();
    }

    /**
     * <b>decToBin</b><br>
     * <br>
     * <i>public String decToBin(String dec)</i><br>
     * <br>
     * Converts decimal number to binary number.
     *
     * @param dec positive decimal number without decimal separator from which binary number would be calculated.
     * @return binary version of number.
     */
    public String decToBin(String dec) {
        if (dec.contains("-") || dec.contains(".")) {
            throw new NumberFormatException(
                    "Provided decimal number is negative or contains decimal separator.");
        }
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


    /**
     * <b>decToHex</b><br>
     * <br>
     * <i>public String decToHex(String dec)</i><br>
     * <br>
     * Converts decimal number to hexadecimal number.
     *
     * @param dec positive decimal number without decimal separator from which hexadecimal number would be calculated.
     * @return hexadecimal version of number.
     */
    public String decToHex(String dec) {
        if (dec.contains("-") || dec.contains(".")) {
            throw new NumberFormatException(
                    "Provided decimal number is negative or contains decimal separator.");
        }
        Calculator calculator = new Calculator(0);
        StringBuilder result = new StringBuilder();
        while (true) {
            String modifier = calculator.reminder(dec, "16");
            //noinspection IfCanBeSwitch
            if (modifier.equals("10")) {
                modifier = "A";
            } else if (modifier.equals("11")) {
                modifier = "B";
            } else if (modifier.equals("12")) {
                modifier = "C";
            } else if (modifier.equals("13")) {
                modifier = "D";
            } else if (modifier.equals("14")) {
                modifier = "E";
            } else if (modifier.equals("15")) {
                modifier = "F";
            }
            result.insert(0, modifier);
            if (calculator.biggerEqualSmaller("16", dec) < 0) {
                break;
            }
            String reminder = calculator.reminder(dec, "16");
            dec = calculator.subtract(dec, reminder);
            dec = calculator.divide(dec, "16");
        }
        return result.toString();
    }

    /**
     * <b>getPrecision</b><br>
     * <br>
     * <i>public int getPrecision()</i><br>
     * <br>
     * Gets this Converter instance precision.
     *
     * @return precision of this Converter instance.
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * <b>gradToRadian</b><br>
     * <br>
     * <i>public String gradToRadian(String grad)</i><br>
     * <br>
     * Converts grad value to radian value.
     *
     * @param grad grad angle from which radian angle would be calculated.
     * @return radian angle.
     */
    public String gradToRadian(String grad) {
        Calculator calculator = new Calculator(this.precision);
        String nominator = calculator.multiply(grad, Calculator.PI);
        return calculator.divide(nominator, "200");
    }

    /**
     * <b>hexToBin</b><br>
     * <br>
     * <i>public String hexToBin(String hex)</i><br>
     * <br>
     * Converts hexadecimal number to binary number.
     *
     * @param hex hexadecimal number from which binary number would be calculated.
     * @return binary version of number.
     */
    public String hexToBin(String hex) {
        Calculator calculator = new Calculator(0);
        String dec = "0";
        String[] hexTab = hex.split("");
        for (int i = 0; i < hexTab.length; i++) {
            if (hexTab[i].equalsIgnoreCase("A")) {
                hexTab[i] = "10";
            } else if (hexTab[i].equalsIgnoreCase("B")) {
                hexTab[i] = "11";
            } else if (hexTab[i].equalsIgnoreCase("C")) {
                hexTab[i] = "12";
            } else if (hexTab[i].equalsIgnoreCase("D")) {
                hexTab[i] = "13";
            } else if (hexTab[i].equalsIgnoreCase("E")) {
                hexTab[i] = "14";
            } else if (hexTab[i].equalsIgnoreCase("F")) {
                hexTab[i] = "15";
            }
        }
        int modifier = hexTab.length - 1;
        for (int i = 0; i < hexTab.length; i++) {
            String power = calculator.power("16", modifier - i);
            String toAdd = calculator.multiply(hexTab[i], power);
            dec = calculator.add(dec, toAdd);
        }

        StringBuilder result = new StringBuilder();
        while (true) {
            String reminder = calculator.reminder(dec, "2");
            result.insert(0, reminder);
            if (calculator.biggerEqualSmaller("2", dec) < 0) {
                break;
            }
            dec = calculator.subtract(dec, reminder);
            dec = calculator.divide(dec, "2");
        }
        return result.toString();
    }

    /**
     * <b>hexToDec</b><br>
     * <br>
     * <i>public String hexToDec(String hex)</i><br>
     * <br>
     * Converts hexadecimal number to decimal number.
     *
     * @param hex hexadecimal number from which decimal number would be calculated.
     * @return decimal version of number.
     */
    public String hexToDec(String hex) {
        Calculator calculator = new Calculator(0);
        String result = "0";
        String[] hexTab = hex.split("");
        for (int i = 0; i < hexTab.length; i++) {
            if (hexTab[i].equalsIgnoreCase("A")) {
                hexTab[i] = "10";
            } else if (hexTab[i].equalsIgnoreCase("B")) {
                hexTab[i] = "11";
            } else if (hexTab[i].equalsIgnoreCase("C")) {
                hexTab[i] = "12";
            } else if (hexTab[i].equalsIgnoreCase("D")) {
                hexTab[i] = "13";
            } else if (hexTab[i].equalsIgnoreCase("E")) {
                hexTab[i] = "14";
            } else if (hexTab[i].equalsIgnoreCase("F")) {
                hexTab[i] = "15";
            }
        }
        int modifier = hexTab.length - 1;
        for (int i = 0; i < hexTab.length; i++) {
            String power = calculator.power("16", modifier - i);
            String toAdd = calculator.multiply(hexTab[i], power);
            result = calculator.add(result, toAdd);
        }
        return result;
    }

    /**
     * <b>radianToGrad</b><br>
     * <br>
     * <i>public String radianToGrad(String radian)</i><br>
     * <br>
     * Converts radian value to grad value.
     *
     * @param radian radian angle from which grad angle would be calculated.
     * @return grad angle.
     */
    public String radianToGrad(String radian) {
        Calculator calculator = new Calculator(this.precision);
        String nominator = calculator.multiply(radian, "200");
        return calculator.divide(nominator, Calculator.PI);
    }
}