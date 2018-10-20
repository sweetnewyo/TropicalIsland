import java.util.Arrays;

public class TropicalIsland {
    public int getWaterVolume(Integer[][] island) {
        int height = island.length;
        int width = island[0].length;
        int result = 0;
        int[] array = new int[width * height];
        if (height > 1 || width > 1) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    array[i * width + j] = island[i][j];
                }
            }
            Arrays.sort(array);
            int maxValue = array[array.length-1];

            Integer[][] water_island = new Integer[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                        water_island[i][j] = island[i][j];
                    } else {
                        water_island[i][j] = maxValue;
                    }
                }
            }

            boolean is_Changed = true;

            while (is_Changed) {
                is_Changed = false;
                for (int i = 1; i < height - 1; i++) {
                    for (int j = 1; j < width - 1; j++) {

                        int up = water_island[i - 1][j];
                        int down = water_island[i + 1][j];
                        int left = water_island[i][j - 1];
                        int right = water_island[i][j + 1];
                        int minValue = Math.min(up, Math.min(down, Math.min(left, right)));

                        if (water_island[i][j] > minValue && !water_island[i][j].equals(island[i][j])) {
                            if (minValue > island[i][j]) {
                                water_island[i][j] = minValue;
                            } else {
                                water_island[i][j] = island[i][j];
                            }
                            is_Changed = true;
                        }

                    }
                }
            }

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    result += water_island[i][j] - island[i][j];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        final Integer[][] matrix1 = {
                {5, 3, 4, 5},
                {6, 2, 1, 4},
                {3, 1, 1, 4},
                {8, 5, 4, 3}
        };

        final Integer[][] matrix2 = {
                {4, 5, 4},
                {3, 1, 5},
                {5, 4, 1}
        };

        final Integer[][] matrix3 = {
                {2, 2, 2},
                {2, 1, 2},
                {2, 1, 2},
                {2, 1, 2}
        };

        final Integer[][] matrix4 = {
                {2, 2, 2}
        };

        final Integer[][] matrix5 = {
                {6, 5, 3, 1, 8},
                {4, 2, 1, 9, 5},
                {3, 6, 5, 4, 6},
                {8, 2, 1, 1, 9},
                {7, 4, 2, 3, 8},
        };

        TropicalIsland island1 = new TropicalIsland();

        System.out.println(island1.getWaterVolume(matrix1));
        System.out.println(island1.getWaterVolume(matrix2));
        System.out.println(island1.getWaterVolume(matrix3));
        System.out.println(island1.getWaterVolume(matrix4));
        System.out.println(island1.getWaterVolume(matrix5));
    }

}
