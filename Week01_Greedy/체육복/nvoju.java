class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] clothes = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            clothes[i] = 1;
        }

        for (int x : lost) {
            clothes[x]--;
        }

        for (int x : reserve) {
            clothes[x]++;
        }

        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if (clothes[i] == 0) {
                if (clothes[i - 1] == 2) {
                    clothes[i - 1]--;
                    clothes[i]++;
                } else if (clothes[i + 1] == 2) {
                    clothes[i + 1]--;
                    clothes[i]++;
                }
            }

            if (clothes[i] >= 1) {
                answer++;
            }
        }

        return answer;
    }
}
