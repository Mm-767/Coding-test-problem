import java.util.Arrays;

class Solution {
    public int solution(int[] d, int budget) {
        Arrays.sort(d);
        int answer = 0;

        for (int money : d) {
            if (money > budget) {
                break;
            }

            budget -= money;
            answer++;
        }

        return answer;
    }
}
