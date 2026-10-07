import java.util.Arrays;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] clothes = new int[n + 2];
        Arrays.fill(clothes, 1);
        for (int student : lost) {
            clothes[student]--;
        }
        for (int student : reserve) {
            clothes[student]++;
        }

        int answer = 0;
        for (int student = 1; student <= n; student++) {
            if (clothes[student] == 0) {
                if (clothes[student - 1] == 2) {
                    clothes[student - 1]--;
                    clothes[student]++;
                } else if (clothes[student + 1] == 2) {
                    clothes[student + 1]--;
                    clothes[student]++;
                }
            }
            if (clothes[student] >= 1) {
                answer++;
            }
        }
        return answer;
    }
}
