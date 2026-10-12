class Solution {
    public int[] solution(String[] wallpaper) {
        int minRow = wallpaper.length;
        int minCol = wallpaper[0].length();
        int maxRow = -1;
        int maxCol = -1;

        for (int row = 0; row < wallpaper.length; row++) {
            for (int col = 0; col < wallpaper[row].length(); col++) {
                if (wallpaper[row].charAt(col) == '#') {
                    minRow = Math.min(minRow, row);
                    minCol = Math.min(minCol, col);
                    maxRow = Math.max(maxRow, row);
                    maxCol = Math.max(maxCol, col);
                }
            }
        }
        return new int[]{minRow, minCol, maxRow + 1, maxCol + 1};
    }
}
