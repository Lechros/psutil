# psutil
Java로 알고리즘 문제를 풀면서 자주 사용하는 기능을 클래스로 분리하여 정리해두었습니다.

## PScanner
`java.util.Scanner`와 유사한 기능을 제공합니다.
`BufferedReader`, `StringTokenizer`를 사용하는 것보다 빠릅니다.

### 예시
```java
PScanner sc = new Scanner(System.in);
int n = sc.nextInt();
```
### 생성자
> PScanner(InputStream input)

### 메소드
> boolean hasNext()

EOF 이전에 공백이 아닌 문자열이 있는지 여부를 검사합니다.
탐색 과정에서 `'\n'` 문자를 소비합니다.

> boolean hasNextInLine()

`'\n'` 문자 이전에 공백이 아닌 문자열이 있는지 여부를 검사합니다.

> String next()

공백으로 구분된 다음 단어를 반환합니다.

> char nextChar()

공백이 아닌 다음 문자를 반환합니다.

> int nextInt()

공백으로 구분된 다음 정수를 반환합니다.
숫자가 아닌 문자에 도달했을 경우 해당 문자 이전까지 소비한 결과를 반환합니다.
`int` 범위를 벗어날 경우 예외 없이 오버플로우가 발생합니다.

> long nextLong()

공백으로 구분된 다음 정수를 반환합니다.
숫자가 아닌 문자에 도달했을 경우 해당 문자 이전까지 소비한 결과를 반환합니다.
`long` 범위를 벗어날 경우 예외 없이 오버플로우가 발생합니다.

## PWriter
`System.out`과 유사한 기능을 제공합니다.
`System.out.print`, `StringBuilder`를 사용하는 것보다 빠릅니다.
`BufferedReader`의 API를 더 편리하게 변경한 래퍼 클래스입니다.

### 예시
```java
PWriter out = new PWriter(System.out);
out.println(1);
out.println(2);
```

### 생성자
> PWriter(OutputStream out)

### 메소드
> void flush()

버퍼에 저장된 내용을 출력합니다.

> void print(char | int | long | float | double | Object | String s)

내용을 출력합니다. `RuntimeException`만 발생합니다.

> void println(char | int | long | float | double | Object | String s)
 
내용과 `'\n'` 문자를 출력합니다. `RuntimeException`만 발생합니다.
인자를 전달하지 않을 경우 `'\n'` 문자만 출력합니다.

> void printf(String format, Object... args)

내용을 포맷에 맞춰 출력합니다. `String.format`을 사용합니다.

