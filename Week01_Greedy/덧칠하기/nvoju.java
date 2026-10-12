class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int end = 0;

        for (int wall : section) {
            if (wall > end) {
                answer++;
                end = wall + m - 1;
            }
        }

        return answer;
    }
}
