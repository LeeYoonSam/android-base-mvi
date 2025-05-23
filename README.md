# Android MVI Compose Base

## 🛠 기술 스택 & 오픈소스 라이브러리
- 최소 SDK 레벨 24
- Jetpack Compose 기반 + Coroutine + 비동기 Flow.
- Jetpack
  - Compose: 네이티브 UI 구축을 위한 최신 안드로이드 툴킷입니다.
  - ViewModel: UI 관련 데이터 홀더 및 라이프사이클 인식.
  - Navigation: 탐색: 화면 탐색 및 종속성 주입을 위한 Hilt Navigation Compose용.
  - Hilt: 종속성 주입.
- Retrofit2 및 OkHttp3: REST API 및 페이징 네트워크 데이터를 구성합니다.
- Coil: 안드로이드 및 컴포즈 멀티플랫폼용 이미지 로딩 라이브러리
- Baseline Profiles: 안드로이드 런타임에서 사용할 수 있는 클래스 및 메소드 사양 목록을 APK에 포함하여 앱 성능을 개선합니다.

## 🏛️ Architecture
구글의 [앱 아키텍처 가이드](https://developer.android.com/topic/architecture?hl=ko)를 따릅니다.

### UI Layer
UI 레이어는 사용자가 상호작용할 수 있는 버튼, 메뉴, 탭과 같은 UI 요소와 앱 상태를 유지하고 구성이 변경될 때 데이터를 복원하는 UI 상태 홀더인 ViewModel로 구성됩니다.

`UI 레이어 = UI 요소 + 뷰모델(UI 상태).`

상태가 아래로 흐르고 이벤트가 위로 흐르는 패턴을 단방향 데이터 흐름(UDF)이라고 합니다. 이 아키텍처는 다음과 같은 구조를 통해 문제를 명확하게 분리하고 예측 가능한 UI 동작을 촉진합니다:
- 뷰모델은 UI 상태에 대한 소스로서 애플리케이션 데이터를 렌더링에 적합한 형식으로 변환하는 역할을 합니다.
- UI 요소는 이 상태를 관찰하고 그에 따라 콘텐츠를 렌더링합니다.
- 사용자가 UI 요소와 상호 작용하면 이벤트가 뷰모델로 전송됩니다.
- 뷰모델은 이러한 이벤트를 처리하고 필요에 따라 상태를 업데이트한 다음 새 상태를 UI로 다시 전송합니다.
- 이 사이클은 상태 변경을 초래하는 모든 이벤트에 대해 반복됩니다.

화면 수준 탐색 또는 목적지의 맥락에서 뷰모델은 데이터 레이어와 상호 작용하여 데이터를 가져와 UI 상태로 변환하는 동시에 해당 상태에 영향을 미치는 사용자 중심 이벤트의 결과도 통합합니다.

### Data Layer
데이터 계층은 앱에서 특정 유형의 데이터 관리를 담당하는 저장소 클래스로 구성됩니다. 리포지토리는 하나 이상의 데이터 소스와 상호 작용할 수 있으며, 각 데이터 도메인에 대해 별도의 리포지토리를 만드는 것이 좋습니다.

리포지토리는 다음을 담당합니다:
- 앱에 노출할 데이터 노출.
- 데이터 작업의 중앙 집중화 및 조정.
- 여러 데이터 소스가 관련된 경우 충돌을 해결합니다.
- 상위 계층에서 기본 데이터 소스를 추상화합니다.
- 데이터 관리와 관련된 비즈니스 로직을 포함합니다.

## 🗂️모듈화

** 이 기본 Android** 앱은 완전히 모듈화 되었으며 다음에서 사용된 모듈화 전략에 대한 자세한 지침과 설명을 확인할 수 있습니다.
[modularization learning journey](docs/ModularizationLearningJourney.md).

## 작업 순서 결정
- 다른 부분과 디펜던시가 없는 부분부터 진행
- Data Layer -> Domain Layer -> UI Layer 순으로 진행

### 작업 순서
1. [libs.versions.toml](gradle/libs.versions.toml) 사용 할 플러그인 및 오픈소스 라이브러리 추가
2. 모듈화 진행
   - Core
     - model: api 응답 객체
     - network: 네트워크 관련 모듈
     - data: repository 인터페이스, 구현
     - navigation: compose navigation
     - designsystem: 공통으로 사용 할 디자인 시스템(테마, 색상) 모듈
   - Feature
     - 화면 단위 모듈 생성
3. app 모듈 구현
4. baselineprofile 추가
5. spotless 적용
6. build-logic 추가

## 트러블 슈팅

### build.gradle.kts 에 플러그인 선언시 오류 발생

- plugins {} 블록:
  - 역할: 현재 프로젝트(모듈)에 특정 Gradle 플러그인을 적용하는 역할을 합니다. 
  - 동작: 플러그인을 적용하면 해당 플러그인이 정의한 기능들이 프로젝트에 추가됩니다. 이는 다음과 같은 작업들을 포함할 수 있습니다:
    - 새로운 Task 추가: 빌드 과정에 필요한 새로운 작업(예: Android 리소스 처리, Kotlin 코드 컴파일, Proguard 실행, Hilt 코드 생성 등)을 추가합니다.
    - 설정(Configuration) 확장: android {}, kotlinOptions {} 와 같이 프로젝트 설정을 위한 새로운 DSL(Domain Specific Language) 블록을 사용할 수 있게 해줍니다.
    - 기본 의존성 추가: 일부 플러그인은 기본적인 라이브러리 의존성을 자동으로 추가하기도 합니다. (예: kotlin-android 플러그인은 Kotlin 표준 라이브러리를 추가할 수 있습니다.)
    - 새로운 의존성 설정(Configuration) 정의: 의존성을 선언하는 새로운 방식(scope)을 정의합니다. 예를 들어, com.google.devtools.ksp 플러그인은 ksp 라는 설정을 추가하여 어노테이션 프로세서를 지정할 수 있게 합니다.
  - 핵심: 빌드 프로세스 자체를 구성하고 확장하는 역할을 합니다.

- dependencies {} 블록:
  - 역할: 현재 프로젝트의 소스 코드가 컴파일되거나 실행될 때 필요한 외부 라이브러리 또는 다른 모듈과의 의존성을 선언하는 역할을 합니다.
  - 동작: 여기에 선언된 의존성들은 Gradle이 지정된 저장소(예: Maven Central, Google Maven Repository)에서 다운로드하거나 로컬 모듈을 찾아 해당 프로젝트의 클래스패스(Classpath)에 추가합니다. 이를 통해 프로젝트의 Kotlin/Java 코드에서 해당 라이브러리의 클래스나 함수를 import 하여 사용할 수 있게 됩니다.
  - 설정(Configuration): implementation, api, compileOnly, runtimeOnly, testImplementation, androidTestImplementation, ksp, kapt 등 다양한 설정을 사용하여 의존성이 필요한 범위(컴파일 시, 런타임 시, 테스트 시, 어노테이션 처리 시 등)를 지정합니다.
  - 핵심: 프로젝트의 소스 코드가 직접 사용하는 라이브러리나 모듈을 지정하는 역할을 합니다.

**요약**
- `plugins {}`: 프로젝트의 빌드 방식과 기능을 정의하고 확장합니다. (예: "이 프로젝트는 Android 라이브러리로 빌드하고, Kotlin과 Hilt를 사용할 거야.")
- `dependencies {}`: 프로젝트의 소스 코드가 사용할 라이브러리 및 모듈을 명시합니다. (예: "내 코드는 Hilt 어노테이션과 Retrofit 라이브러리가 필요해.")

두 블록은 서로 긴밀하게 연관되어 있으며, 특정 플러그인을 사용하기 위해 해당 플러그인이 요구하는 라이브러리를 `dependencies {}` 에 추가해야 하는 경우가 많습니다.

## 라이브러리 궁금증
### org.jetbrains.kotlinx:kotlinx-coroutines-android - implementation(libs.kotlinx.coroutines.android)
- 코루틴의 런타임 라이브러리(suspend 함수 실행, CoroutineScope 만들기, launch, async 같은 코루틴 빌더 사용, Dispatchers 같은 기본적인 디스패처 사용)
- kotlinx-coroutines-core에 대한 전이 의존성(transitive dependency) 을 가지고 있습니다. 즉, 이 라이브러리를 추가하면 core 라이브러리도 자동으로 포함

### navigation-compose / hilt-navigation-compose
- `navigation-compose`: Compose에서 화면 이동 기능을 구현하기 위한 필수 기반 라이브러리입니다.
- `hilt-navigation-compose`: navigation-compose 위에서 동작하며, Hilt를 사용하여 각 Composable 화면에 최적화된 생명주기를 갖는 ViewModel을 쉽게 주입받을 수 있도록 도와주는 편의성/통합 라이브러리입니다.