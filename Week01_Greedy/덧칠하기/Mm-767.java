import java.util.*;
class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int painted = 0;
        for (int i = 0; i < section.length; i++) {
            if (section[i] > painted) {
                painted = section[i] + m - 1;
                answer++;
            }
        }
        return answer;
    }
}
