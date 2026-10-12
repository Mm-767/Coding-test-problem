class Solution {
    public String solution(int[] numbers, String hand) {
        int left = 10;
        int right = 12;
        StringBuilder answer = new StringBuilder();

        for (int number : numbers) {
            int target = number == 0 ? 11 : number;
            if (number == 1 || number == 4 || number == 7) {
                answer.append('L');
                left = target;
            } else if (number == 3 || number == 6 || number == 9) {
                answer.append('R');
                right = target;
            } else {
                int leftDistance = Math.abs((left - 1) / 3 - (target - 1) / 3)
                        + Math.abs((left - 1) % 3 - (target - 1) % 3);
                int rightDistance = Math.abs((right - 1) / 3 - (target - 1) / 3)
                        + Math.abs((right - 1) % 3 - (target - 1) % 3);
                if (leftDistance < rightDistance
                        || (leftDistance == rightDistance && hand.equals("left"))) {
                    answer.append('L');
                    left = target;
                } else {
                    answer.append('R');
                    right = target;
                }
            }
        }
        return answer.toString();
    }
}
