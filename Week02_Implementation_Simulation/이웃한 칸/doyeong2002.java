class Solution {
    public int solution(String[][] board, int h, int w) {
        int[] dh = {-1, 1, 0, 0};
        int[] dw = {0, 0, -1, 1};
        int count = 0;

        for (int i = 0; i < 4; i++) {
            int nh = h + dh[i];
            int nw = w + dw[i];
            if (nh >= 0 && nh < board.length && nw >= 0 && nw < board.length
                    && board[h][w].equals(board[nh][nw])) {
                count++;
            }
        }
        return count;
    }
}
