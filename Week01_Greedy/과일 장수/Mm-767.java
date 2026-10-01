import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        // Arrays.sort(score);
        // for(int i = score.length - m ; 0 <= i ; i -= m ){
        //     answer = answer + score[i] * m;
        // }
        int [] cnt = new int [k + 1];
        for (int s : score) cnt[s]++;

        int out = 0;
        for(int s = k ; 1 <= s ; s --){
            int total = cnt[s] + out;
            answer += (total/m) * s * m;
            out = total % m;
        }
        return answer;


    }
}
