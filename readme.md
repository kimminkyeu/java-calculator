1. Gradle build system
   - https://docs.gradle.org/current/userguide/userguide.html
     - java 뿐 아니라 c++도 빌드 가능 하다.
     - 커뮤니티 매우 크다.
     - bash에서 설치해서 사용 가능 (Intelij 안쓰고 가능)
     
2. gradlew (= gradle wrapper)
   - https://docs.gradle.org/current/userguide/gradle_wrapper.html
   
   gradle wrapper 생성하기
    - https://www.baeldung.com/gradle-wrapper
   ```bash
   gradle wrapper # ./gradlew 및 필요 설정 생성
   ```
   
   생성된 gradle wrapper 버전 변경시 반영하는 법
   ```bash
   ./gradlew --version
   ./gradlew wrapper  # gradle 버전을 바꿨다면 wrapper 다시 생성해야 한다.
   ```
   
   모든 task 목록 확인하기
   ```bash
   ./gradlew tasks 
   # intellij 우측 gradle 패널에서도 실행 가능한 task를 확인할 수 있다.
   ```

   task 실행하기
    ```bash
   ./gradlew build # main과 test를 각각 빌드
   ./gradlew clean
   ```

   task 일부 제외 하기
   ```bash
   ./gradlew clean build --exclude-task 태스크명
   ./gradlew clean build --exclude-task test
   ```
   
3. `build.gradle`에서 Build Dependencies 정의 하기
   https://docs.gradle.org/current/userguide/declaring_dependencies.html
    - testRuntimeOnly, implementation 등에 대한 설명은 위 메뉴얼을 볼 것.
   ```bash
    # 추가 예정 입니다.
    ```
   
4. `Custom Task` 정의 하기
   - https://docs.gradle.org/current/userguide/implementing_custom_tasks.html
   - https://www.baeldung.com/gradle-custom-task
   ```bash
    tasks.register(Zip, "packageApp") {
      # ...
    }
    ```

4. Build and Run `Test` task with `gradlew`
   - https://docs.gradle.org/current/userguide/java_testing.html
    ```bash
    # 전체 test task 실행
    ./gradlew test 
    # 원하는 패키지만 test task 실행
    ./gradlew test --tests "myTest.StringTest"   
    ```
   
    cmd에서 직접 test task 실행하기
    먼저 아래의 패키지를 의존성으로 명시한다.
    ```bash
     dependencies {
         testImplementation 'org.assertj:assertj-core:3.22.0'
         testImplementation 'org.junit.jupiter:junit-jupiter:5.8.2'
     }
    ```
    아마 `./gradlew test` 실행하면 빌드만 되고 아무것도 안뜰 것이다.
    그건 테스트를 통과해서 그렇다. 상세 사항을 보고 싶으면 아래와 같이 설정한다.
    ```bash
    test {
        useJUnitPlatform()
        testLogging {
            events "started", "skipped", "passed", "failed"
            showStandardStreams true
        }
    }
    ```
    다시 `.gradlew clean test`를 실행하면, 빌드되면서 테스트 결과가 함께 나온다.
    ```bash
    kyeu@kyeu-thinkpad  ~/goinfree/kernel360/java-calculator   main ±✚  ./gradlew clean test
    > Task :clean
    > Task :compileJava
    > Task :processResources NO-SOURCE
    > Task :classes
    > Task :compileTestJava
    > Task :processTestResources NO-SOURCE
    > Task :testClasses

    > Task :test
   
    test example > test2 - substring test STARTED
    test example > test2 - substring test PASSED
    test example > test1 - split test STARTED
    test example > test1 - split test PASSED
    test example > test3 - string index out of bounds exception test STARTED
    test example > test3 - string index out of bounds exception test PASSED
   
    BUILD SUCCESSFUL in 1s
    4 actionable tasks: 4 executed
   ``` 
    근데 궁금한건... 그럼 컴파일 타임 테스팅인가? 
   