# SikuliX_AutoTest - GUI 자동 테스트 프로젝트

![SikuliX_Demo](https://github.com/user-attachments/assets/36db68bd-2e80-46ed-ae18-bf0b8a55366b)

이 프로젝트는 **주어진 시스템**(Software1,2)의 GUI 테스트 자동화를 위한 것입니다.</br> 
시스템은 **Java**(Jar)를 사용하며, **SikuliX**를 통해 테스트가 진행됩니다.

> ※ 본 프로젝트는 **S/W품질관리및테스팅** 과목의 일환으로 진행된 프로젝트입니다.

---

## 📌 테스트 대상 방법

시스템의 주요 테스트 방법은 다음과 같습니다:
1. 사용자 정의 또는 기본 유사도로 이미지를 클릭하는 방법
2. 화면에 이미지가 있는지 테스트하는 방법 
3. 주어진 위치에 텍스트를 쓰는 방법
4. 텍스트를 클립 보드에 복사하고 비교하는 방법
5. 화면을 마우스 오른쪽 버튼으로 클릭하는 방법

---

## 🧪 테스트 자동화 요구사항

본 프로젝트는 **시스템 사양**에 따라 자동화 테스트 도구 **SikuliX**를 사용하여 테스트를 진행하며,</br> 다음과 같은 시스템 사양을 포함하고 있습니다:

### 시스템 사양
<details>
<summary>접기/펼치기</summary>

#### Software-1 시스템 사양
- **Tab 1 - Koala 탭**
  - 탭으로 돌아올 때 **코알라 사진**은 동일해야 합니다.  
- **Tab 2 - Text editor 탭**
  - 입력한 **텍스트**는 탭으로 돌아올 때 동일해야 합니다.
  - **HTML 편집기**는 아래의 이미지에서 보여지는 요소들이 포함되어야 합니다.
    > ![Software1_elements](https://github.com/user-attachments/assets/d0eab5d0-a686-40d1-897e-e7a949fa3689)
  - 선택된 **텍스트 서식** 설정은 탭으로 돌아올 때 그대로 유지되어야 합니다. 
- **Tab 3 - Lorem Ipsum 탭**
  - **텍스트 길이**는 탭으로 돌아올 때 동일해야 합니다.
  - **텍스트 영역**에는 우클릭 시 활성화되는 **컨텍스트 메뉴**가 있어야 합니다.
- **Tab 4 - Color picker 탭**
  - **Color picker 탭**은 접을 수 있어야 합니다.
  - 선택된 **Color 코드**가 탭으로 돌아올 때 동일해야 합니다. (기본 색상: **흰색**)

---

#### Software-2 시스템 사양

- **Tab 1 - Koala 탭**
  - 탭으로 돌아올 때 **코알라 사진**은 동일해야 합니다.  
- **Tab 2 - Text editor 탭**
  - 입력한 **텍스트**는 탭으로 돌아올 때 동일해야 합니다.
  - **HTML 편집기**는 아래의 이미지에서 보여지는 버튼들이 포함되어야 합니다.
    > ![Software2_button](https://github.com/user-attachments/assets/30ce295a-db7e-4cde-9624-fdaa65fbaa82)
  - 선택된 **텍스트 서식** 설정은 탭으로 돌아올 때 그대로 유지되어야 합니다. 
- **Tab 3 - Lorem Ipsum 탭**
  - **텍스트 길이**는 탭으로 돌아올 때 동일해야 합니다.
  - **텍스트 영역**에는 우클릭 시 활성화되는 **컨텍스트 메뉴**가 있어야 합니다.
- **Tab 4 - Color picker 탭**
  - **Color picker 탭**은 접을 수 있어야 합니다.
  - 선택된 **Color 코드**가 탭으로 돌아올 때 동일해야 합니다. (기본 색상: **흰색**)
- **Tab 5 - Progress 탭 (새로운 기능)**
  - 로더 바는 탭으로 돌아올 때 무한 상태(앞뒤로 이동)로 유지되어야 합니다.
</details>

---

## 코드 리뷰

### Autotest.java
### 1. 🚀 주요 메서드 `run()`
- 전체 테스트를 수행하는 메서드.
- 소프트웨어 실행: `openSoftware()`
- `iteration` 값에 따라 테스트 범위 결정:
  - `iteration == 1 or 2`: `tab1` ~ `tab4` 테스트
  - `iteration == 3`: `tab1` ~ `tab5` 테스트
- 테스트 종료 후 PDF 리포트 생성: `ColumnDataTypesReport.build()`

### 2. 🧪 탭별 테스트 메서드
#### 📁 `tab1()` - Koala 이미지 유지 테스트
- `koalaTab` 클릭 후 이미지 존재 여부 확인
- 다른 탭으로 전환 후 다시 돌아왔을 때 이미지가 유지되는지 확인

#### ✏️ `tab2()` - Text editor 테스트
- 텍스트 입력 → 탭 전환 → 입력 텍스트 일치 여부 검사
- HTML 에디터 요소 존재 여부 확인
- 텍스트 서식 및 폰트 유지 여부 확인

#### 📄 `tab3()` - Lorem Ipsum 텍스트 테스트
- 고정된 Lorem Ipsum 텍스트 복사 후 비교
- 우클릭 시 컨텍스트 메뉴가 표시되는지 확인

#### 🎨 `tab4()` - Color picker UI 테스트
- Color picker 접힘/펼침 기능 확인
- 기본 색상이 흰색인지 확인

#### ⏳ `tab5()` - 로딩 Progress 테스트
- 무한 로딩 애니메이션이 화면에 나타나는지 확인

### 3. 💻 `openSoftware()`메서드
- `sikuli.openJavaJar(jarVersion)` 호출을 통해 Java JAR 파일 실행

### 4. 📊 테스트 결과 리포트
- 결과 기록 형식:  
  `reportFile.add("PASSED"/"FAILED", "Tab 번호", "상세 메시지")`
- 실행 종료 후 PDF로 출력:  
  `Report{iteration}.pdf`
---
### SikuliSteps.java
### 🔧 주요 필드 및 구성
| 필드 | 설명 |
|------|------|
| `public Process p` | 실행 중인 자바 애플리케이션 프로세스를 추적하는 객체 |
| `public Screen screen = new Screen();` | SikuliX의 화면 객체. UI 요소 탐지 및 조작에 사용 |

### 주요 메서드 설명
#### ✅ `boolean click(String pictureUrl)`
- 지정한 이미지(`pictureUrl`)를 화면에서 찾아 클릭
- 기본 유사도(similarity) 0.85 사용

#### ✅ `boolean click(String pictureUrl, Double similarity)`
- 주어진 유사도로 이미지 클릭 시도
- 예외 발생 시 `false` 반환

#### ✅ `boolean click(String pictureUrl, Double similarity, int retries)`
- 이미지 클릭을 `retries` 횟수만큼 재시도 (1초 간격)
- 실패 시 로그 출력

#### 🔠 `boolean write(String pictureUrl, String text)`
- `pictureUrl` 위치 클릭 → 더블 클릭 → `text` 입력
- 입력 성공 여부 반환

#### 📋 `boolean compareTextToClipboards(String textToCompare)`
- 현재 포커스된 영역에서 `Ctrl+A`, `Ctrl+C` 실행 후 클립보드 내용과 `textToCompare` 비교
- 동일하면 `true`, 다르면 `false`

#### 🖱️ `boolean verifyIfExists(String pictureUrl)`
- 이미지 존재 여부 확인 (`exists(...) != null`)

#### 🔁 `boolean verifyIfExistsReTried(String pictureUrl, int retries)`
- 지정한 `retries` 횟수만큼 반복적으로 이미지 존재 여부 확인

#### 🖱️ `void rightClick()`
- 현재 마우스 위치에서 우클릭 수행
- 이후 모든 키 해제 (`keyUp()`)

#### ⚙️ `void openJavaJar(int version)`
- 지정된 버전의 `.jar` 파일 실행
- 실행 후 5초 대기 → 실행 성공 여부를 로그로 출력

#### ❌ `void exitJava()`
- 실행 중인 자바 프로세스를 강제 종료

---

## 테스트 결과

테스트 결과는 아래와 같습니다.</br>
**시스템 사양**에 따라 `Autotest`클래스 작성, `Report` 3개를 제작하였습니다.

|Report1|Report2|Report3|
|-----|-----|-----|
| [![Report1](https://github.com/user-attachments/assets/e612bdfe-647b-4d0b-a4ba-61eed359fc37)](https://github.com/CodeyCraft/AutoTest/blob/master/Sikuli_Autotest/Report1.pdf)|[![Report2](https://github.com/user-attachments/assets/db9da878-d731-4378-9e35-db5ac8917574)](https://github.com/CodeyCraft/AutoTest/blob/master/Sikuli_Autotest/Report2.pdf)|[![Report3](https://github.com/user-attachments/assets/d9277b18-c2fe-4f49-a56d-d911f6fda274)](https://github.com/CodeyCraft/AutoTest/blob/master/Sikuli_Autotest/Report3.pdf)|

## 🛠️ 발견된 오류
다음과 같은 테스트 케이스 오류가 발견되었습니다:

1. **Koala Tab 오류**  
![Koala_Error](https://github.com/user-attachments/assets/6679402a-8b2a-4c9c-b749-0e9ef762a547)
  - 탭을 전환 뒤, 코알라 그림이 반전된다.

2. **Text editor Tab 오류**  
![Text editor_Error](https://github.com/user-attachments/assets/0f1d06c0-f1ae-46df-8a5b-353c1e5111a7)
  - 탭을 전환 뒤, 텍스트는 기울임 꼴로 표시되고 숫자 "1"이 추가된다.
  
3. **Lorem Ipsum Tab 오류**   
![Lorem Ipsum_Error](https://github.com/user-attachments/assets/a6205003-890f-49ed-82d8-c7e5753b9c0d)
  - 탭을 전환 뒤, 텍스트 마지막에 공백이 추가된다.

4. **Color picker Tab 오류**   
![Color Picker_Error](https://github.com/user-attachments/assets/787ddc6b-c7d9-4914-920b-51ed23436e7d)
  - 탭을 전환 뒤, 색상 코드가 전환된다.
