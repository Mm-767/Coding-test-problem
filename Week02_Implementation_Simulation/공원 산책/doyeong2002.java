class Solution {
    public int[] solution(String[] park, String[] routes) {
        int row = 0;
        int col = 0;
        for (int i = 0; i < park.length; i++) {
            int start = park[i].indexOf('S');
            if (start >= 0) {
                row = i;
                col = start;
                break;
            }
        }

        String directions = "NSWE";
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        for (String route : routes) {
            int direction = directions.indexOf(route.charAt(0));
            int distance = Integer.parseInt(route.substring(2));
            int nextRow = row;
            int nextCol = col;
            boolean possible = true;

            for (int step = 0; step < distance; step++) {
                nextRow += dr[direction];
                nextCol += dc[direction];
                if (nextRow < 0 || nextRow >= park.length || nextCol < 0
                        || nextCol >= park[0].length() || park[nextRow].charAt(nextCol) == 'X') {
                    possible = false;
                    break;
                }
            }
            if (possible) {
                row = nextRow;
                col = nextCol;
            }
        }
        return new int[]{row, col};
    }
}
