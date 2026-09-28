# 풀이 제출 가이드

풀이는 **Pull Request(PR)** 로 제출한다. 서로 코드를 리뷰하는 것도 스터디의 일부다.

## 사전 준비

- 스터디장에게 저장소 **collaborator** 추가를 요청한다. (fork 아님)
- 추가되면 저장소를 클론한다.
  ```bash
  git clone https://github.com/Mm-767/Coding-test-problem.git
  cd Coding-test-problem
  ```

## 제출 절차

1. `main` 을 최신 상태로 맞춘다.
   ```bash
   git switch main && git pull
   ```
2. 브랜치를 만든다. 이름: `<깃허브아이디>/week03-미로탈출`
   ```bash
   git switch -c minhun/week03-미로탈출
   ```
3. 풀이 파일을 규칙에 맞는 경로에 추가한다.
   ```
   Week03_DataStructures_DFS_BFS/미로 탈출/minhun.java
   ```
   - 경로 규칙: `WeekNN_.../<문제명>/<깃허브아이디>.java`
   - 폴더가 없으면 새로 만든다. 문제명은 한글 그대로 쓴다.
   - 언어는 **Java** 로 통일한다. **한 문제당 본인 파일 1개.**
   - 프로그래머스 코드 그대로 `class Solution` 으로 올린다. `public class Solution` 으로 바꾸면 파일명과 달라 컴파일 오류가 난다.
4. 커밋한다.
   ```bash
   git add .
   git commit -m "[Week03] 미로 탈출 - minhun"
   ```
5. push 후 PR 을 만든다. (base: `main`)
   ```bash
   git push -u origin minhun/week03-미로탈출
   ```
6. PR 제목: `[Week03] 미로 탈출 - minhun`
   - 한 주에 여러 문제를 풀었다면 PR 하나에 묶어도 된다.

## 주차별 폴더

| 주차 | 폴더 |
| --- | --- |
| 1주차 | `Week01_Greedy` |
| 2주차 | `Week02_Implementation_Simulation` |
| 3주차 | `Week03_DataStructures_DFS_BFS` |
| 4주차 | `Week04_Sorting_BinarySearch` |
| 5주차 | `Week05_Dynamic_Programming` |
| 6주차 | `Week06_Shortest_Path` |
| 7주차 | `Week07_Backend_Dev_Coding_Test` |
| 8주차 | `Week08_Math_Search_Review` |

## 7주차(개발형) 주의

- API 키·토큰·비밀번호는 **절대 커밋하지 않는다.** 환경변수로 분리한다.
- 예시 응답은 실제 호출 결과 대신 목업(mock) 데이터로 남긴다.
