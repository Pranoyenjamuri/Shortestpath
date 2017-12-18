package com.pranoy.lowcostpath;

public class GridUtils {
    public static final GridCreator EXAMPLE_GRID_1 = new GridCreator(new int[][]{
            {3, 4, 1, 2, 8, 6},
            {6, 1, 8, 2, 7, 4},
            {5, 9, 3, 9, 9, 5},
            {8, 4, 1, 3, 2, 6},
            {3, 7, 2, 8, 6, 4}
    });
    public static final GridCreator EXAMPLE_GRID_2 = new GridCreator(new int[][]{
            {3, 4, 1, 2, 8, 6},
            {6, 1, 8, 2, 7, 4},
            {5, 9, 3, 9, 9, 5},
            {8, 4, 1, 3, 2, 6},
            {3, 7, 2, 1, 2, 3}
    });
    public static final GridCreator EXAMPLE_GRID_3 = new GridCreator(new int[][]{
            {19, 10, 19, 10, 19},
            {21, 23, 20, 19, 12},
            {20, 12, 20, 11, 10}
    });

    public static final GridCreator EXAMPLE_GRID_4 = new GridCreator(new int[][]{
            {5, 8, 5, 3, 5}
    });
    public static final GridCreator SAMPLE_GRID_5 = new GridCreator(new int[][]{
            {5},
            {8},
            {5},
            {3},
            {5}
    });

    public static final GridCreator SAMPLE_GRID_6 = new GridCreator(new String[][]{

            {"5", "4", "H"},
            {"8", "M", "7"},
            {"5", "7", "5"}
    });

    public static final GridCreator SAMPLE_GRID_7 = new GridCreator(new int[][]{
            {}
    });
    public static final GridCreator SAMPLE_GRID_8 = new GridCreator(new int[][]{
            {69, 10, 19, 10, 19},
            {51, 23, 20, 19, 12},
            {60, 12, 20, 11, 10}
    });
    public static final GridCreator SAMPLE_GRID_9 = new GridCreator(new int[][]{
            {60, 3, 3, 6},
            {6, 3, 7, 9},
            {5, 6, 8, 3}
    });
    public static final GridCreator SAMPLE_GRID_10 = new GridCreator(new int[][]{

            {6, 3, -5, 9},
            {-5, 2, 4, 10},
            {3, -2, 6, 10},
            {6, -1, -2, 10}
    });
    public static final GridCreator SAMPLE_GRID_11 = new GridCreator(new int[][]{

            {51, 51},
            {0, 51},
            {51, 51},
            {5, 5}
    });
    public static final GridCreator SAMPLE_GRID_12 = new GridCreator(new int[][]{

            {51, 51, 51},
            {0, 51, 51},
            {51, 51, 51},
            {5, 5, 51}
    });
    public static final GridCreator SAMPLE_GRID_13 = new GridCreator(new int[][]{

            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
    });


    public static int[][] gridArrayFromString(String input) {
        if (input != null) {
            String[] rows = input.split("\n");
            String[] firstColumns = rows[0].split("\\s+");
            int[][] output = new int[rows.length][firstColumns.length];

            try {
                for (int row = 0; row < rows.length; row++) {
                    String[] columns = rows[row].split("\\s+");
                    for (int column = 0; column < columns.length; column++) {
                        if (column < output[0].length) {
                            output[row][column] = Integer.valueOf(columns[column]);
                        }
                    }
                }

                return output;
            } catch (NumberFormatException nfe) {
                return new int[0][0];
            }
        } else {
            return new int[0][0];
        }
    }

}
