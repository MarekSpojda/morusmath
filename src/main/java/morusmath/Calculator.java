package morusmath;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * <b>Calculator</b><br>
 * <br>
 * <i>public class Calculator</i><br>
 * <br>
 * Class for math operations.
 */

@SuppressWarnings("unused")
public class Calculator {
    /**
     * Value of PI with 2000 decimal places.
     */
    public static final String PI =
            "3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808651328230664709384460955058223172535940812848111745028410270193852110555964462294895493038196442881097566593344612847564823378678316527120190914564856692346034861045432664821339360726024914127372458700660631558817488152092096282925409171536436789259036001133053054882046652138414695194151160943305727036575959195309218611738193261179310511854807446237996274956735188575272489122793818301194912983367336244065664308602139494639522473719070217986094370277053921717629317675238467481846766940513200056812714526356082778577134275778960917363717872146844090122495343014654958537105079227968925892354201995611212902196086403441815981362977477130996051870721134999999837297804995105973173281609631859502445945534690830264252230825334468503526193118817101000313783875288658753320838142061717766914730359825349042875546873115956286388235378759375195778185778053217122680661300192787661119590921642019893809525720106548586327886593615338182796823030195203530185296899577362259941389124972177528347913151557485724245415069595082953311686172785588907509838175463746493931925506040092770167113900984882401285836160356370766010471018194295559619894676783744944825537977472684710404753464620804668425906949129331367702898915210475216205696602405803815019351125338243003558764024749647326391419927260426992279678235478163600934172164121992458631503028618297455570674983850549458858692699569092721079750930295532116534498720275596023648066549911988183479775356636980742654252786255181841757467289097777279380008164706001614524919217321721477235014144197356854816136115735255213347574184946843852332390739414333454776241686251898356948556209921922218427255025425688767179049460165346680498862723279178608578438382796797668145410095388378636095068006422512520511739298489608412848862694560424196528502221066118630674427862203919494504712371378696095636437191728746776465757396241389086583264599581339047802759009";
    private int precision;

    /**
     * <b>Calculator</b><br>
     * <br>
     * <i>public Calculator(int precision)</i><br>
     * <br>
     * Returns instance of Calculator and sets given precision.
     *
     * @param precision precision to be set.
     */
    public Calculator(int precision) {
        this.precision = precision;
    }

    /**
     * <b>absolute</b><br>
     * <br>
     * <i>public String absolute(String number)</i><br>
     * <br>
     * Calculates absolute version of number.
     *
     * @param number number from which absolute version would be calculated.
     * @return absolute version of number.
     */
    public String absolute(String number) {
        return new BigDecimal(number).abs(new MathContext(this.precision))
                .setScale(this.precision, RoundingMode.HALF_EVEN).toPlainString();
    }

    /**
     * <b>add</b><br>
     * <br>
     * <i>public String add(String numberA, String numberB)</i><br>
     * <br>
     * Calculates sum of two numbers.
     *
     * @param numberA first number to be added.
     * @param numberB second number to be added.
     * @return sum of both numbers.
     */
    public String add(String numberA, String numberB) {
        return new BigDecimal(numberA).add(new BigDecimal(numberB))
                .setScale(this.precision, RoundingMode.HALF_EVEN).toPlainString();
    }

    /**
     * <b>arcCos</b><br>
     * <br>
     * <i>public String arcCos(String cosine)</i><br>
     * <br>
     * Calculates arcus cosine from given value.
     *
     * @param cosine cosine value.
     * @return arcus cosine in radians.
     */
    public String arcCos(String cosine) {
        String piBy2 = divide(Calculator.PI, "2");
        return subtract(piBy2, arcSin(cosineToSine(cosine)));
    }

    /**
     * <b>arcCtg</b><br>
     * <br>
     * <i>public String arcCtg(String cotangent)</i><br>
     * <br>
     * Calculates arcus cotangent from given value.
     *
     * @param cotangent cotangent value.
     * @return arcus cotangent in radians.
     */
    public String arcCtg(String cotangent) {
        String piBy2 = divide(Calculator.PI, "2");
        return subtract(piBy2, arcTg(cotangent));
    }

    /**
     * <b>arcSin</b><br>
     * <br>
     * <i>public String arcSin(String sine)</i><br>
     * <br>
     * Calculates arcus sine from given value.
     *
     * @param sine sine value.
     * @return arcus sine in radians.
     */
    public String arcSin(String sine) {
        BigDecimal bigDecimalSine = new BigDecimal(sine);
        BigDecimal bigDecimal1 = new BigDecimal("1");
        BigDecimal bigDecimalMinus1 = new BigDecimal("-1");
        if (bigDecimalSine.compareTo(bigDecimal1) == 0) {
            return divide(PI, "2");
        } else if (bigDecimalSine.compareTo(bigDecimalMinus1) == 0) {
            return "-" + divide(PI, "2");
        } else if (bigDecimalSine.compareTo(bigDecimalMinus1) < 0 ||
                bigDecimalSine.compareTo(bigDecimal1) > 0) {
            throw new ArithmeticException("Trying to calculate arcSin or arcCos from |x| > 1");
        }

        return arcTg(sineToTangent(sine));
    }

    /**
     * <b>arcTg</b><br>
     * <br>
     * <i>public String arcTg(String tangent)</i><br>
     * <br>
     * Calculates arcus tangent from given value.
     *
     * @param tangent tangent value.
     * @return arcus tangent in radians.
     */
    public String arcTg(String tangent) {
        BigDecimal bigX = new BigDecimal(tangent);
        String piBy4 = divide(Calculator.PI, "4");

        int inputComparedTo1 = bigX.compareTo(new BigDecimal("1"));
        int inputComparedToMinus1 = bigX.compareTo(new BigDecimal("-1"));
        if (inputComparedTo1 == 0) {
            return piBy4;
        } else if (inputComparedToMinus1 == 0) {
            return "-" + piBy4;
        }

        String result;
        if (inputComparedTo1 < 0 && inputComparedToMinus1 > 0) {
            result = "0";
            for (int i = 0; i < (this.precision + 10); i++) {
                int factor = 2 * i + 1;
                String numerator = power("-1", i);
                String denominator = "" + factor;
                String fraction = divide(numerator, denominator);
                String xPowered = power(tangent, factor);
                result = add(result, multiply(fraction, xPowered));
            }

            return new BigDecimal(result).setScale(this.precision, RoundingMode.HALF_EVEN)
                    .toPlainString();
        } else {
            String modifier = "1";
            if (inputComparedToMinus1 < 0) {
                modifier = "-1";
            }
            result = multiply(modifier, divide(Calculator.PI, "2"));
            for (int i = 0; i < (this.precision + 10); i++) {
                String numerator = power("-1", i + 1);
                int factor = 2 * i + 1;
                String xPowered = power(tangent, factor);
                String denominator = multiply("" + factor, xPowered);
                String fraction = divide(numerator, denominator);
                result = add(result, fraction);
            }
        }

        return new BigDecimal(result).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    /**
     * <b>biggerEqualSmaller</b><br>
     * <br>
     * <i>public int biggerEqualSmaller(String numberA, String numberB)</i><br>
     * <br>
     * Checks if numberB is smaller, equal to or bigger than numberA.
     *
     * @param numberA first number to be compared.
     * @param numberB second number to be compared.
     * @return 1 if numberB is greater than numberA, 0 if numbers are equal or -1 if numberB is smaller than numberA.
     */
    public int biggerEqualSmaller(String numberA, String numberB) {
        BigDecimal bigDecimalA = new BigDecimal(numberA);
        BigDecimal bigDecimalB = new BigDecimal(numberB);
        return bigDecimalB.compareTo(bigDecimalA);
    }

    /**
     * <b>cosineToCotangent</b><br>
     * <br>
     * <i>public String cosineToCotangent(String cosine)</i><br>
     * <br>
     * Converts cosine to cotangent.
     *
     * @param cosine cosine value.
     * @return cotangent.
     */
    public String cosineToCotangent(String cosine) {
        int tempPrecision = this.precision;
        this.precision = this.precision + 10;
        String cosine2 = power(cosine, 2);
        String sine2 = subtract("1", cosine2);
        String sine = squareRootOf(sine2);
        String result = divide(cosine, sine);
        this.precision = tempPrecision;

        return new BigDecimal(result).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    /**
     * <b>cosineToSine</b><br>
     * <br>
     * <i>public String cosineToSine(String cosine)</i><br>
     * <br>
     * Converts cosine to sine.
     *
     * @param cosine cosine value.
     * @return sine.
     */
    public String cosineToSine(String cosine) {
        int tempPrecision = this.precision;
        this.precision = this.precision + 10;
        String cosine2 = power(cosine, 2);
        String sine2 = subtract("1", cosine2);
        String sine = squareRootOf(sine2);
        this.precision = tempPrecision;

        return new BigDecimal(sine).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    /**
     * <b>cosineToTangent</b><br>
     * <br>
     * <i>public String cosineToTangent(String cosine)</i><br>
     * <br>
     * Converts cosine to tangent.
     *
     * @param cosine cosine value.
     * @return tangent.
     */
    public String cosineToTangent(String cosine) {
        int tempPrecision = this.precision;
        this.precision = this.precision + 10;
        String cosine2 = power(cosine, 2);
        String sine2 = subtract("1", cosine2);
        String sine = squareRootOf(sine2);
        String result = divide(sine, cosine);
        this.precision = tempPrecision;

        return new BigDecimal(result).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    /**
     * <b>cotangentToCosine</b><br>
     * <br>
     * <i>public String cotangentToCosine(String cotangent)</i><br>
     * <br>
     * Converts cotangent to cosine.
     *
     * @param cotangent cotangent value.
     * @return cosine.
     */
    public String cotangentToCosine(String cotangent) {
        int tempPrecision = this.precision;
        this.precision = this.precision + 10;
        String cotangent2 = power(cotangent, 2);
        String factor = divide(cotangent2, add("1", cotangent2));
        String result = squareRootOf(factor);
        this.precision = tempPrecision;

        return new BigDecimal(result).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    /**
     * <b>cotangentToSine</b><br>
     * <br>
     * <i>public String cotangentToSine(String cotangent)</i><br>
     * <br>
     * Converts cotangent to sine.
     *
     * @param cotangent cotangent value.
     * @return sine.
     */
    public String cotangentToSine(String cotangent) {
        int tempPrecision = this.precision;
        this.precision = this.precision + 10;
        String cotangent2 = power(cotangent, 2);
        String factor = divide("1", add("1", cotangent2));
        String result = squareRootOf(factor);
        this.precision = tempPrecision;

        return new BigDecimal(result).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    /**
     * <b>cotangentToTangent</b><br>
     * <br>
     * <i>public String cotangentToTangent(String cotangent)</i><br>
     * <br>
     * Converts cotangent to tangent.
     *
     * @param cotangent cotangent value.
     * @return tangent.
     */
    public String cotangentToTangent(String cotangent) {
        int tempPrecision = this.precision;
        this.precision = this.precision + 10;
        String result = divide("1", cotangent);
        this.precision = tempPrecision;

        return new BigDecimal(result).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    /**
     * <b>divide</b><br>
     * <br>
     * <i>public String divide(String numberA, String numberB)</i><br>
     * <br>
     * Divides numberA by numberB.
     *
     * @param numberA first number.
     * @param numberB first number.
     * @return numberA divided by numberB.
     */
    public String divide(String numberA, String numberB) {
        return new BigDecimal(numberA)
                .divide(new BigDecimal(numberB), this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    /**
     * <b>factorial</b><br>
     * <br>
     * <i>public String factorial(String number)</i><br>
     * <br>
     * Calculates factorial from number.
     *
     * @param number number to calculate factorial from.
     * @return factorial of number.
     */
    public String factorial(String number) {
        if (number.contains(".")) {
            throw new ArithmeticException("Trying to calculate factorial from decimal number");
        } else if (new BigDecimal(number).signum() == -1) {
            throw new ArithmeticException("Trying to calculate factorial from negative number");
        }

        BigDecimal result = new BigDecimal("1");
        BigDecimal assistant = new BigDecimal("1");
        while (!assistant.toString().equals(number)) {
            assistant = assistant.add(new BigDecimal("1"));
            result = result.multiply(assistant);
        }
        return result.setScale(0, RoundingMode.HALF_EVEN).toPlainString();
    }

    /**
     * <b>getPrecision</b><br>
     * <br>
     * <i>public int getPrecision()</i><br>
     * <br>
     * Returns current Calculator instance precision.
     *
     * @return current Calculator instance precision.
     */
    public int getPrecision() {
        return this.precision;
    }

    /**
     * <b>multiply</b><br>
     * <br>
     * <i>public String multiply(String numberA, String numberB)</i><br>
     * <br>
     * Multiplies two numbers.
     *
     * @param numberA first number to be multiplied.
     * @param numberB second number to be multiplied.
     * @return multiplication of given numbers.
     */
    public String multiply(String numberA, String numberB) {
        return new BigDecimal(numberA).multiply(new BigDecimal(numberB))
                .setScale(this.precision, RoundingMode.HALF_EVEN).toPlainString();
    }

    /**
     * <b>power</b><br>
     * <br>
     * <i>public String power(String number, int power)</i><br>
     * <br>
     * Calculates power of number.
     *
     * @param number number from which power will be calculated.
     * @param power  power to be used.
     * @return number raised to given power.
     */
    public String power(String number, int power) {
        if (power == 0) {
            return new BigDecimal("1").setScale(this.precision, RoundingMode.HALF_EVEN)
                    .toPlainString();
        } else if (power < 0) {
            power = power * -1;
            BigDecimal temporaryPowered = new BigDecimal(number).pow(power)
                    .setScale(this.precision + 10, RoundingMode.HALF_EVEN);
            return new BigDecimal("1")
                    .divide(temporaryPowered, this.precision, RoundingMode.HALF_EVEN)
                    .toPlainString();
        }
        return new BigDecimal(number).pow(power).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    /**
     * <b>reminder</b><br>
     * <br>
     * <i>public String reminder(String numberA, String numberB)</i><br>
     * <br>
     * Returns reminder from dividing numberA by numberB.
     *
     * @param numberA number to be divided.
     * @param numberB value by which numberA is to be divided.
     * @return reminder of action numberA/numberB.
     */
    public String reminder(String numberA, String numberB) {
        BigDecimal bigDecimalA = new BigDecimal(numberA);
        BigDecimal bigDecimalB = new BigDecimal(numberB);
        return bigDecimalA.remainder(bigDecimalB).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    /**
     * <b>rootOfDegree</b><br>
     * <br>
     * <i>public String rootOfDegree(String number, int rootDegree)</i><br>
     * <br>
     * Calculates root of number.
     *
     * @param number     number from which root will be calculated.
     * @param rootDegree root degree to be used.
     * @return number root of given degree.
     */
    public String rootOfDegree(String number, int rootDegree) {
        BigDecimal numberAsBigDecimal = new BigDecimal(number);
        boolean isInputNegative = numberAsBigDecimal.signum() == -1;
        boolean isRootDegreeNegative = rootDegree < 0;
        if (isInputNegative) {
            numberAsBigDecimal = numberAsBigDecimal.abs();
        }
        if (isRootDegreeNegative) {
            rootDegree = rootDegree * -1;
        }

        BigDecimal result = numberAsBigDecimal;
        while (result.pow(rootDegree).compareTo(numberAsBigDecimal) > 0) {
            result =
                    result.divide(new BigDecimal("2"), this.precision + 10, RoundingMode.HALF_EVEN);
        }

        BigDecimal lowerBound = result,
                upperBound = result.multiply(new BigDecimal("2")),
                center, leftCenter, rightCenter;
        while (upperBound.subtract(lowerBound).compareTo(new BigDecimal("1")) > 0) {
            center = upperBound.add(lowerBound)
                    .divide(new BigDecimal("2"), this.precision + 10, RoundingMode.HALF_EVEN);
            leftCenter = lowerBound.add(center)
                    .divide(new BigDecimal("2"), this.precision + 10, RoundingMode.HALF_EVEN);
            rightCenter = upperBound.add(center)
                    .divide(new BigDecimal("2"), this.precision + 10, RoundingMode.HALF_EVEN);
            if (leftCenter.pow(rootDegree).subtract(numberAsBigDecimal).abs()
                    .compareTo(rightCenter.pow(rootDegree).subtract(numberAsBigDecimal).abs()) <
                    1) {
                upperBound = center;
            } else {
                lowerBound = center;
            }
        }
        result = lowerBound.subtract(lowerBound.remainder(new BigDecimal("1")));

        BigDecimal modifier;
        int modifierFiller = 0;
        BigDecimal delta = new BigDecimal("0." + "0".repeat(this.precision + 9) + "1");

        while (true) {
            modifier = new BigDecimal("0." + "0".repeat(modifierFiller) + "1");
            while (result.add(modifier).pow(rootDegree).compareTo(numberAsBigDecimal) < 1) {
                result = result.add(modifier);
            }
            if (result.pow(rootDegree).compareTo(numberAsBigDecimal) == 0 ||
                    result.pow(rootDegree).subtract(result.add(modifier).pow(rootDegree)).abs()
                            .compareTo(delta)
                            < 1) {
                break;
            }
            modifierFiller++;
        }

        if (isRootDegreeNegative) {
            return new BigDecimal("1").divide(result, this.precision, RoundingMode.HALF_EVEN)
                    .toPlainString();
        }
        return result.setScale(this.precision, RoundingMode.HALF_EVEN).toPlainString();
    }

    /**
     * <b>roundNumberToGivenPrecision</b><br>
     * <br>
     * <i>public String roundNumberToGivenPrecision(String number, int customPrecision)</i><br>
     * <br>
     * Rounds number to given precision which may differ from precision of this Calculator instance.
     *
     * @param number          number to be rounded.
     * @param customPrecision precision to be used in rounding.
     * @return number rounded to given precision.
     */
    public String roundNumberToGivenPrecision(String number, int customPrecision) {
        return new BigDecimal(number).setScale(customPrecision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    /**
     * <b>sineToCosine</b><br>
     * <br>
     * <i>public String sineToCosine(String sine)</i><br>
     * <br>
     * Converts sine to cosine.
     *
     * @param sine sine value.
     * @return cosine.
     */
    public String sineToCosine(String sine) {
        int tempPrecision = this.precision;
        this.precision = this.precision + 10;
        String sine2 = power(sine, 2);
        String cosine2 = subtract("1", sine2);
        String cosine = squareRootOf(cosine2);
        this.precision = tempPrecision;

        return new BigDecimal(cosine).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    /**
     * <b>sineToCotangent</b><br>
     * <br>
     * <i>public String sineToCotangent(String sine)</i><br>
     * <br>
     * Converts sine to cotangent.
     *
     * @param sine sine value.
     * @return cotangent.
     */
    public String sineToCotangent(String sine) {
        int tempPrecision = this.precision;
        this.precision = this.precision + 10;
        String sine2 = power(sine, 2);
        String cosine2 = subtract("1", sine2);
        String cosine = squareRootOf(cosine2);
        String result = divide(cosine, sine);
        this.precision = tempPrecision;

        return new BigDecimal(result).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    /**
     * <b>sineToTangent</b><br>
     * <br>
     * <i>public String sineToTangent(String sine)</i><br>
     * <br>
     * Converts sine to tangent.
     *
     * @param sine sine value.
     * @return tangent.
     */
    public String sineToTangent(String sine) {
        int tempPrecision = this.precision;
        this.precision = this.precision + 10;
        String sine2 = power(sine, 2);
        String cosine2 = subtract("1", sine2);
        String cosine = squareRootOf(cosine2);
        String result = divide(sine, cosine);
        this.precision = tempPrecision;

        return new BigDecimal(result).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    /**
     * <b>squareRootOf</b><br>
     * <br>
     * <i>public String squareRootOf(String number)</i><br>
     * <br>
     * Calculates square root of number.
     *
     * @param number number from which square root will be calculated.
     * @return square root of number.
     */
    public String squareRootOf(String number) {
        return new BigDecimal(number).sqrt(new MathContext(this.precision))
                .setScale(this.precision, RoundingMode.HALF_EVEN).toPlainString();
    }

    /**
     * <b>subtract</b><br>
     * <br>
     * <i>public String subtract(String numberA, String numberB)</i><br>
     * <br>
     * Subtracts numberB from numberA.
     *
     * @param numberA number from which second number will be subtracted.
     * @param numberB number which will be subtracted from first number.
     * @return result of subtracting numberB from numberA.
     */
    public String subtract(String numberA, String numberB) {
        return new BigDecimal(numberA).subtract(new BigDecimal(numberB))
                .setScale(this.precision, RoundingMode.HALF_EVEN).toPlainString();
    }

    /**
     * <b>tangentToCosine</b><br>
     * <br>
     * <i>public String tangentToCosine(String tangent)</i><br>
     * <br>
     * Converts tangent to cosine.
     *
     * @param tangent tangent value.
     * @return cosine.
     */
    public String tangentToCosine(String tangent) {
        int tempPrecision = this.precision;
        this.precision = this.precision + 10;
        String tangent2 = power(tangent, 2);
        String factor = divide("1", add(tangent2, "1"));
        String result = squareRootOf(factor);
        this.precision = tempPrecision;

        return new BigDecimal(result).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    /**
     * <b>tangentToCotangent</b><br>
     * <br>
     * <i>public String tangentToCotangent(String tangent)</i><br>
     * <br>
     * Converts tangent to cotangent.
     *
     * @param tangent tangent value.
     * @return cotangent.
     */
    public String tangentToCotangent(String tangent) {
        int tempPrecision = this.precision;
        this.precision = this.precision + 10;
        String result = divide("1", tangent);
        this.precision = tempPrecision;

        return new BigDecimal(result).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }

    /**
     * <b>tangentToSine</b><br>
     * <br>
     * <i>public String tangentToSine(String tangent)</i><br>
     * <br>
     * Converts tangent to sine.
     *
     * @param tangent tangent value.
     * @return sine.
     */
    public String tangentToSine(String tangent) {
        int tempPrecision = this.precision;
        this.precision = this.precision + 10;
        String tangent2 = power(tangent, 2);
        String factor = divide(tangent2, add(tangent2, "1"));
        String result = squareRootOf(factor);
        this.precision = tempPrecision;

        return new BigDecimal(result).setScale(this.precision, RoundingMode.HALF_EVEN)
                .toPlainString();
    }
}