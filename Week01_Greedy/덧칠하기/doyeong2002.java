class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int paintedUntil = 0;

        for (int position : section) {
            if (position > paintedUntil) {
                int start = Math.min(position, n - m + 1);
                paintedUntil = start + m - 1;
                answer++;
            }
        }
        return answer;
    }
}
