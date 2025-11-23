package com.lonkachu;

public class NumberUtils
{
    static final private float DOT_SCALE_AJUSTMENT = 0.9f;
    final private static String[] SUFFIXES = {"", "K", "M", "B"};

    public static String abberivate(int count)
    {
        String sign = count < 0 ? "-" : "";
        count = Math.abs(count);

        if (count < 10_000)
            return sign + count;

        int powerOf1000 = (int) Math.log10(count) / 3;
        double mantissa = count / Math.pow(1000, powerOf1000);
        int roundFactor = mantissa < 10 ? 100 : (mantissa < 100 ? 10 : 1);
        mantissa = Math.floor(mantissa * roundFactor);
        mantissa /= roundFactor;

        return sign + Double.toString(mantissa).replaceAll(".0$", "") + SUFFIXES[powerOf1000];
    }

    private static float getScaleByLength(String string)
    {
        if (string.length() <= 2)
            return 1.0f;
        if (string.length() == 3)
            return 0.95f;
        return 0.7f;
    }

    public static float getScale(String string)
    {
        boolean containsDot = string.contains(".");
        string = string.replace(".", "");
        float baseScale = getScaleByLength(string);

        if (containsDot)
            return baseScale * DOT_SCALE_AJUSTMENT;
        return baseScale;
    }
}
