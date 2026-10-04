import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] memo = new int[n + 2];
        Arrays.fill(memo,1);

        for(int l : lost){
            memo[l]--;
        }
        for(int r : reserve){
            memo[r]++;
        }

        for(int i = 1; i <=n ; i++){
            if(memo[i] == 0){
                if(memo[i - 1] == 2){
                    memo[i - 1] --;
                    memo[i] ++;
                }else if(memo[i + 1] == 2){
                    memo[i + 1] --;
                    memo[i] ++;
                }
            }
        }

        int answer = 0;
        for(int j = 1; j <= n ; j++){
            if(memo[j] >= 1)answer++;
        }
        return answer;
    }
}
