package com.pranoy.lowcostpath;

import android.text.TextUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Pranoy on 12/16/2017.
 */

public class GridCreator {
    int[][] values;
    String[][] stringvalues;

    GridCreator(int[][] values) {
        if (values.length >= 0 && values.length <= 10) {
            if (values[0].length >= 0 && values[0].length <= 100) {
                this.values = values;
            } else {
                throw new IllegalArgumentException("Between one and one hundred columns of values are expected");
            }
        } else {
            throw new IllegalArgumentException("Between one and ten rows of values are expected");
        }
    }


    GridCreator(String[][] values) {
        if (values.length >= 0 && values.length <= 10) {
            if (values[0].length >= 0 && values[0].length <= 100) {
                this.stringvalues = values;
            } else {
                throw new IllegalArgumentException("Between one and one hundred columns of values are expected");
            }
        } else {
            throw new IllegalArgumentException("Between one and ten rows of values are expected");
        }
    }


    public int getValueForRowAndColumn(int row, int column) {
        return this.values[row - 1][column - 1];
    }

    public int getRowCount() {
        return this.values.length;
    }

    public int getColumnCount() {
        return this.values[0].length;
    }

    public List<Integer> getRowsAdjacentTo(int rowNumber) {
        Set<Integer> adjacentRows = new HashSet();
        if (this.isValidRowNumber(rowNumber)) {
            adjacentRows.add(Integer.valueOf(rowNumber));
            adjacentRows.add(Integer.valueOf(this.getRowAbove(rowNumber)));
            adjacentRows.add(Integer.valueOf(this.getRowBelow(rowNumber)));
        }

        return new ArrayList(adjacentRows);
    }

    public boolean containsStringValues() {

        if (stringvalues != null)
            return true;
        return false;
    }

    public String asDelimitedString(String delimiter) {
        StringBuilder builder = new StringBuilder();

        for (int row = 0; row < this.values.length; ++row) {
            for (int column = 0; column < this.values[row].length; ++column) {
                builder.append(this.values[row][column]);
                if (column < this.values[row].length - 1) {
                    builder.append(delimiter);
                }
            }

            if (row < this.values.length - 1) {
                builder.append("\n");
            }
        }

        return builder.toString();
    }

    private boolean isValidRowNumber(int rowNumber) {
        return rowNumber > 0 && rowNumber <= this.values.length;
    }

    private int getRowAbove(int rowNumber) {
        int potentialRowNumber = rowNumber - 1;
        return potentialRowNumber < 1 ? this.values.length : potentialRowNumber;
    }

    private int getRowBelow(int rowNumber) {
        int potentialRowNumber = rowNumber + 1;
        return potentialRowNumber > this.values.length ? 1 : potentialRowNumber;
    }

    public boolean isValidValues() {
        if (stringvalues == null) {
            return false;
        }
        int[][] tempvalues = new int[stringvalues.length][stringvalues[0].length];

        for (int i = 0; i < stringvalues.length; i++) {
            for (int j = 0; j < stringvalues[0].length; j++) {
                try {
                    tempvalues[i][j] = Integer.parseInt(stringvalues[i][j]);
                } catch (Exception ex) {
                    return false;
                }
            }
        }
        this.values= tempvalues;


        return true;
    }
}

