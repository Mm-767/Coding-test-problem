# 1주차: 시간 복잡도 & 그리디(Greedy)

## 이번 주 목표

- 문제의 입력 크기를 보고 "이 풀이가 시간 안에 돌아갈까?"를 코드 짜기 전에 판단한다.
- 그리디로 풀 수 있는 문제인지 알아보고, 왜 그 선택이 맞는지 한 문장으로 설명한다.

## 이번 주 문제 (프로그래머스 Lv1)

위에서부터 쉬운 순서다. 풀이는 `Week01_Greedy/<문제명>/<깃허브아이디>.java` 로 PR 제출 ([제출 가이드](../CONTRIBUTING.md)).

| # | 문제 | 난이도 | 폴더명 |
| --- | --- | --- | --- |
| 1 | [예산](https://school.programmers.co.kr/learn/courses/30/lessons/12982) | Lv1 | `예산` |
| 2 | [과일 장수](https://school.programmers.co.kr/learn/courses/30/lessons/135808) | Lv1 | `과일 장수` |
| 3 | [덧칠하기](https://school.programmers.co.kr/learn/courses/30/lessons/161989) | Lv1 | `덧칠하기` |
| 4 | [체육복](https://school.programmers.co.kr/learn/courses/30/lessons/42862) | Lv1 | `체육복` |

<details>
<summary>막혔을 때 볼 힌트 (먼저 30분은 혼자 고민해 보기)</summary>

- **예산**: 최대한 많은 부서에 주려면 어떤 부서부터 주는 게 유리할까?
- **과일 장수**: 상자 가격은 가장 낮은 점수로 정해진다. 점수가 비슷한 사과끼리 묶으려면 먼저 무엇을 해야 할까? `score` 길이가 최대 1,000,000이라는 점도 확인하기.
- **덧칠하기**: 아직 안 칠한 가장 왼쪽 칸을 칠해야 한다면, 롤러를 어디에 대는 게 가장 이득일까?
- **체육복**: 여벌을 가져왔는데 도난도 당한 학생은 어떻게 처리해야 할까?

</details>

---

## 1. 시간 복잡도

**입력 크기 N이 커질 때 연산 횟수가 얼마나 빨리 늘어나는지**를 나타낸 것이다. 가장 크게 늘어나는 항만 남겨 빅오(Big-O)로 쓴다.

```java
for (int x : arr) {          // N번 → O(N)
    ...
}

for (int x : arr) {          // N × N번 → O(N²)
    for (int y : arr) {
        ...
    }
}

Arrays.sort(arr);            // O(N log N)
```

### 입력 크기로 허용되는 복잡도 가늠하기

Java는 대략 **1초에 1억 번** 안팎의 단순 연산을 한다고 잡는다. 문제의 제한사항에서 N의 최댓값을 보고 아래 표로 먼저 거른다.

| N의 최댓값 | 안전한 복잡도 |
| --- | --- |
| 약 500 | O(N³) |
| 약 2,000 | O(N²) |
| 약 100,000 ~ 1,000,000 | O(N log N) |
| 약 10,000,000 | O(N) |

예) 과일 장수는 `score` 길이가 최대 1,000,000이다. 모든 쌍을 비교하는 O(N²)은 10¹²번이라 불가능하고, 정렬 O(N log N)은 가능하다.

### Java 연산별 복잡도 (자주 틀리는 것)

| 연산 | 복잡도 | 비고 |
| --- | --- | --- |
| `list.add(x)`, `list.get(i)` | O(1) | `ArrayList` 맨 뒤 추가, 인덱스 접근 |
| `list.add(0, x)`, `list.remove(0)` | **O(N)** | 맨 앞에 넣고 빼면 뒤 원소를 전부 민다 |
| `list.contains(x)`, `list.indexOf(x)` | **O(N)** | 처음부터 하나씩 찾는다 |
| `set.contains(x)`, `map.get(k)`, `map.containsKey(k)` | O(1) | `HashSet`, `HashMap`. 자주 찾을 거면 이쪽으로 |
| `Arrays.sort(a)`, `Collections.sort(list)` | O(N log N) | |
| `a.length`, `list.size()` | O(1) | |

---

## 2. 그리디(Greedy, 탐욕법)

**매 순간 지금 가장 좋아 보이는 것을 고르고, 한 번 고른 것은 되돌리지 않는** 방법이다.

### 항상 맞지는 않는다

동전이 500원, 400원, 100원짜리가 있고 800원을 가장 적은 개수로 거슬러야 한다고 하자.

- 그리디 (큰 동전부터): 500 + 100 + 100 + 100 → **4개**
- 정답: 400 + 400 → **2개**

실제 동전(500, 100, 50, 10)에서는 그리디가 맞는다. 큰 단위가 항상 작은 단위의 배수라서 큰 동전 하나를 작은 동전 여러 개로 바꿔서 더 적어질 일이 없기 때문이다.

그래서 그리디 문제의 핵심은 **"지금의 최선을 고르면 전체도 최선이 된다"는 것을 납득하는 것**이다. 이 조건을 탐욕 선택 속성(greedy choice property)이라고 부른다.

### 그리디 문제 푸는 순서

1. **기준 정하기**: "가장 작은 것부터?", "가장 큰 것부터?", "가장 왼쪽부터?"
2. **기준대로 정렬하기**: 그리디 문제의 대부분은 정렬로 시작한다.
3. **앞에서부터 하나씩 고르기**
4. **반례 찾아보기**: 위 동전 예시처럼 이 기준이 틀리는 입력이 있는지 손으로 한두 개 만들어 본다.

PR에는 **3번에서 왜 그 기준이 맞는지**를 한 문장으로 적는다.

---

## 3. 자주 쓰는 Java 문법

프로그래머스는 입력을 직접 받지 않는다. `solution` 메서드의 **매개변수로 입력이 들어오고, 답을 `return`** 하면 된다. `Arrays`, `HashSet` 등을 쓰려면 맨 위에 `import java.util.*;` 를 직접 적는다.

```java
import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        // ...
        return answer;
    }
}
```

| 하고 싶은 것 | 코드 |
| --- | --- |
| 오름차순 정렬 | `Arrays.sort(a);` |
| 내림차순 정렬 (`int[]`) | 오름차순 정렬 후 뒤에서부터 읽기: `for (int i = a.length - 1; i >= 0; i--)` |
| 내림차순 정렬 (`Integer[]`) | `Arrays.sort(b, Collections.reverseOrder());` |
| `int[]` → `Integer[]` | `Integer[] b = Arrays.stream(a).boxed().toArray(Integer[]::new);` |
| 기준을 정해 정렬 (2차원 배열) | `Arrays.sort(p, (x, y) -> Integer.compare(x[1], y[1]));` |
| 리스트 정렬 | `Collections.sort(list);` / `list.sort(Comparator.reverseOrder());` |
| 최솟값 / 최댓값 | `Math.min(x, y)`, `Math.max(x, y)` |
| 배열 합 | `Arrays.stream(a).sum()` |
| 몫 / 나머지 | `a / b`, `a % b` (정수끼리 나누면 소수점 버림: `7 / 2 == 3`) |
| k칸씩 건너뛰기 | `for (int i = start; i < n; i += k)` |
| 빠르게 포함 여부 확인 | `Set<Integer> s = new HashSet<>();` → `s.add(x);`, `s.contains(x)` |
| 교집합 / 차집합 | `s.retainAll(t);` / `s.removeAll(t);` (`s` 자체가 바뀐다) |

### Java에서 자주 틀리는 것

- **`int` 오버플로**: `int` 최댓값은 약 21억(2,147,483,647)이다. 20억 + 20억은 음수가 된다. 곱하거나 많이 더할 때는 `long answer = 0;`, `(long) a * b` 처럼 `long` 으로 계산한다. `Arrays.stream(a).sum()` 도 `int` 라서 넘칠 수 있으니 `Arrays.stream(a).asLongStream().sum()` 을 쓴다.
- **정렬 기준을 `x[1] - y[1]` 로 쓰기**: 값이 크면 뺄셈이 넘쳐 순서가 뒤집힌다. `Integer.compare(x[1], y[1])` 을 쓴다.
- **`Integer` 끼리 `==` 비교**: `Integer` 128 == 128 은 `false` 다 (-128 ~ 127 만 같은 객체로 캐시됨). `Integer` 끼리는 `.equals()` 로 비교한다.
