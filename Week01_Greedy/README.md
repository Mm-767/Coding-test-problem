# 1주차: 시간 복잡도 & 그리디(Greedy)

## 이번 주 목표

- 문제의 입력 크기를 보고 "이 풀이가 시간 안에 돌아갈까?"를 코드 짜기 전에 판단한다.
- 그리디로 풀 수 있는 문제인지 알아보고, 왜 그 선택이 맞는지 한 문장으로 설명한다.

## 이번 주 문제 (프로그래머스 Lv1)

위에서부터 쉬운 순서다. 풀이는 `Week01_Greedy/<문제명>/<깃허브아이디>.확장자` 로 PR 제출 ([제출 가이드](../CONTRIBUTING.md)).

| # | 문제 | 난이도 | 폴더명 |
| --- | --- | --- | --- |
| 1 | [예산](https://school.programmers.co.kr/learn/courses/30/lessons/12982) | Lv1 | `예산` |
| 2 | [과일 장수](https://school.programmers.co.kr/learn/courses/30/lessons/135808) | Lv1 | `과일 장수` |
| 3 | [덧칠하기](https://school.programmers.co.kr/learn/courses/30/lessons/161989) | Lv1 | `덧칠하기` |
| 4 | [체육복](https://school.programmers.co.kr/learn/courses/30/lessons/42862) | Lv1 | `체육복` |

<details>
<summary>막혔을 때 볼 힌트 (먼저 20분은 혼자 고민해 보기)</summary>

- **예산**: 최대한 많은 부서에 주려면 어떤 부서부터 주는 게 유리할까?
- **과일 장수**: 상자 가격은 가장 낮은 점수로 정해진다. 점수가 비슷한 사과끼리 묶으려면 먼저 무엇을 해야 할까? `score` 길이가 최대 1,000,000이라는 점도 확인하기.
- **덧칠하기**: 아직 안 칠한 가장 왼쪽 칸을 칠해야 한다면, 롤러를 어디에 대는 게 가장 이득일까?
- **체육복**: 여벌을 가져왔는데 도난도 당한 학생은 어떻게 처리해야 할까?

</details>

---

## 1. 시간 복잡도

**입력 크기 N이 커질 때 연산 횟수가 얼마나 빨리 늘어나는지**를 나타낸 것이다. 가장 크게 늘어나는 항만 남겨 빅오(Big-O)로 쓴다.

```python
for x in arr:          # N번 → O(N)
    ...

for x in arr:          # N × N번 → O(N²)
    for y in arr:
        ...

arr.sort()             # O(N log N)
```

### 입력 크기로 허용되는 복잡도 가늠하기

파이썬은 대략 **1초에 1천만~2천만 번** 정도의 단순 연산을 한다고 잡는다. 문제의 제한사항에서 N의 최댓값을 보고 아래 표로 먼저 거른다.

| N의 최댓값 | 안전한 복잡도 |
| --- | --- |
| 약 500 | O(N³) |
| 약 2,000 | O(N²) |
| 약 100,000 ~ 1,000,000 | O(N log N) |
| 약 10,000,000 | O(N) |

예) 과일 장수는 `score` 길이가 최대 1,000,000이다. 모든 쌍을 비교하는 O(N²)은 10¹²번이라 불가능하고, 정렬 O(N log N)은 가능하다.

### 파이썬 연산별 복잡도 (자주 틀리는 것)

| 연산 | 복잡도 | 비고 |
| --- | --- | --- |
| `list.append(x)`, `list.pop()` | O(1) | 맨 뒤 추가/삭제 |
| `list.pop(0)`, `list.insert(0, x)` | **O(N)** | 맨 앞은 느리다 |
| `x in list`, `list.remove(x)` | **O(N)** | 처음부터 하나씩 찾는다 |
| `x in set`, `x in dict` | O(1) | 자주 찾을 거면 set으로 바꾸기 |
| `sorted(a)`, `a.sort()` | O(N log N) | |
| `len(a)` | O(1) | |

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

## 3. 자주 쓰는 문법

프로그래머스는 입력을 직접 받지 않는다. `solution` 함수의 **매개변수로 입력이 들어오고, 답을 `return`** 하면 된다.

```python
def solution(d, budget):
    answer = 0
    # ...
    return answer
```

| 하고 싶은 것 | Python | Java |
| --- | --- | --- |
| 오름차순 정렬 | `a.sort()` / `sorted(a)` | `Arrays.sort(a)` |
| 내림차순 정렬 | `a.sort(reverse=True)` | `Arrays.sort(a, Collections.reverseOrder())` (`Integer[]`만 가능) |
| 기준을 정해 정렬 | `a.sort(key=lambda x: x[1])` | `Arrays.sort(a, (x, y) -> Integer.compare(x[1], y[1]))` |
| 최솟값 / 최댓값 / 합 | `min(a)`, `max(a)`, `sum(a)` | `Math.min(x, y)`, `Arrays.stream(a).sum()` |
| 몫 / 나머지 | `a // b`, `a % b` | `a / b`, `a % b` (정수끼리) |
| k칸씩 건너뛰기 | `a[start::k]` | `for (int i = start; i < n; i += k)` |
| 빠르게 포함 여부 확인 | `s = set(a)`, `x in s` | `Set<Integer> s = new HashSet<>()`, `s.contains(x)` |
| 교집합 / 차집합 | `set(a) & set(b)`, `set(a) - set(b)` | `s.retainAll(t)`, `s.removeAll(t)` |

---

## 참고 자료

- [코딩 테스트에 자주 나오는 Big O 알고리즘 복잡도 - 알고달레](https://www.algodale.com/guides/big-o-complexities/): O(1)부터 O(N!)까지 예시 코드와 함께 정리
- [TimeComplexity - Python Wiki](https://wiki.python.org/moin/TimeComplexity) (영문): list, set, dict 연산별 공식 복잡도 표
- [탐욕 알고리즘 - 위키백과](https://ko.wikipedia.org/wiki/탐욕_알고리즘): 탐욕 선택 속성, 최적 부분 구조 정의
- [정렬 기법 - 파이썬 공식 문서](https://docs.python.org/ko/3/howto/sorting.html): `key`, `reverse`, 여러 기준 정렬
- [코딩테스트를 위한 Python 정리 - choiiis Devlog](https://choiiis.github.io/python/for-coding-test/): 자료형과 내장 함수 치트시트
