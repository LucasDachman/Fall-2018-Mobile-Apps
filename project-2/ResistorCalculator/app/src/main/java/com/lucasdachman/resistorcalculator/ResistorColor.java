package com.lucasdachman.resistorcalculator;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class ResistorColor {

    private int color;
    private String name;
    private double value;

    private ResistorColor(int color, double value, String name) {
        this.color = color;
        this.value = value;
        this.name = name;
    }

    static ArrayList<ResistorColor> getDigit1Colors() {
        return new ArrayList<ResistorColor>() {
            {
                add(new ResistorColor(mColors.Brown, 1, "1 Brown"));
                add(new ResistorColor(mColors.Red, 2, "2 Red"));
                add(new ResistorColor(mColors.Orange, 3, "3 Orange"));
                add(new ResistorColor(mColors.Yellow, 4, "4 Yellow"));
                add(new ResistorColor(mColors.Green, 5, "5 Green"));
                add(new ResistorColor(mColors.Blue, 6, "6 Blue"));
                add(new ResistorColor(mColors.Violet, 7, "7 Violet"));
                add(new ResistorColor(mColors.Gray, 8, "8 Gray"));
                add(new ResistorColor(mColors.White, 9, "9 White"));
            }
        };
    }

    static ArrayList<ResistorColor> getDigit23Colors() {
        return new ArrayList<ResistorColor>() {
            {
                add(new ResistorColor(mColors.Black, 0, "0 Black"));
                add(new ResistorColor(mColors.Brown, 1, "1 Brown"));
                add(new ResistorColor(mColors.Red, 2, "2 Red"));
                add(new ResistorColor(mColors.Orange, 3, "3 Orange"));
                add(new ResistorColor(mColors.Yellow, 4, "4 Yellow"));
                add(new ResistorColor(mColors.Green, 5, "5 Green"));
                add(new ResistorColor(mColors.Blue, 6, "6 Blue"));
                add(new ResistorColor(mColors.Violet, 7, "7 Violet"));
                add(new ResistorColor(mColors.Gray, 8, "8 Gray"));
                add(new ResistorColor(mColors.White, 9, "9 White"));
            }
        };
    }

    static ArrayList<ResistorColor> geMultiplierColors() {
        return new ArrayList<ResistorColor>() {
            {
                add(new ResistorColor(mColors.Black, 1, "x1 Black"));
                add(new ResistorColor(mColors.Brown, 10, "x10 Brown"));
                add(new ResistorColor(mColors.Red, 100, "x100 Red"));
                add(new ResistorColor(mColors.Orange, 1000, "x1000 Orange"));
                add(new ResistorColor(mColors.Yellow, 10000, "x10000 Yellow"));
                add(new ResistorColor(mColors.Green, 100000, "x100000 Green"));
                add(new ResistorColor(mColors.Blue, 1000000, "x1000000 Blue"));
                add(new ResistorColor(mColors.Violet, 10000000, "x10000000 Violet"));
                add(new ResistorColor(mColors.Gray, 100000000, "x100000000 Gray"));
                add(new ResistorColor(mColors.White, 1000000000, "1000000000 White"));
                add(new ResistorColor(mColors.Gold, 0.1, "÷10"));
                add(new ResistorColor(mColors.Silver, 0.01, "÷100"));
            }
        };
    }

    static ArrayList<ResistorColor> geToleranceColors() {
        return new ArrayList<ResistorColor>() {
            {
                add(new ResistorColor(mColors.Brown, 0.01, "±1% Brown"));
                add(new ResistorColor(mColors.Red, 0.02, "±2% Red"));
                add(new ResistorColor(mColors.Orange, 0.03, "±3% Orange"));
                add(new ResistorColor(mColors.Yellow, 0.04, "±4% Yellow"));
                add(new ResistorColor(mColors.Green, 0.005, "±0.5% Green"));
                add(new ResistorColor(mColors.Blue, 0.025, "±0.25% Blue"));
                add(new ResistorColor(mColors.Violet, 0.010, "±0.10% Violet"));
                add(new ResistorColor(mColors.Gray, 0.0005, "±0.05% Gray"));
                add(new ResistorColor(mColors.Gold, 0.05, "±5%"));
                add(new ResistorColor(mColors.Silver, 0.10, "±10%"));
            }
        };
    }

    static ArrayList<ResistorColor> getTempcoColors() {
        return new ArrayList<ResistorColor>() {
            {
                add(new ResistorColor(mColors.Brown, 100, "100 ppm/ºC"));
                add(new ResistorColor(mColors.Red, 50, "50 ppm/ºC"));
                add(new ResistorColor(mColors.Orange, 15, "15 ppm/ºC"));
                add(new ResistorColor(mColors.Yellow, 25, "25 ppm/ºC"));
                add(new ResistorColor(mColors.Blue, 10, "10 ppm/ºC"));
                add(new ResistorColor(mColors.Violet, 5, "5 ppm/ºC"));
            }
        };
    }

    public ColorDrawable getColorDrawable() {
        return new ColorDrawable(this.color);
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private static class mColors {
        public static int Black = Color.BLACK;
        public static int Brown = 0x7D4302 ;
        public static int Red = Color.RED;
        public static int Orange = 0xff8c1a;
        public static int Yellow = Color.YELLOW;
        public static int Green = Color.GREEN;
        public static int Blue = Color.BLUE;
        public static int Violet = 0x660066;
        public static int Gray = 0x4d4d4d;
        public static int White = Color.WHITE;
        public static int Gold = 0xFFD700;
        public static int Silver = 0xC1C1C1;
    }

}
