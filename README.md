# EventBookingApp

# 프로젝트 개요

## 구조

### Clean Architecture

아키텍처는 프로젝트의 규모와 용도에 따라 선택하는 것이라는 것을 인지하고 있음

의존성 주입 (DI) 및 관심사 분리와 단일 책임의 원칙에 대한 깊은 학습을 위해 Clean Architecture 선택

### Multi Module

Clean Architecture 구조의 관심사 분리와 각 레이어의 관심사에 대해 깊게 이해하기 위해 각 레이어를 모듈로 분리하는 멀티 모듈 도입

#### 분리된 모듈
1. app
   - 프로젝트의 모듈을 하나로 모아 전체 프로젝트를 구성하는 모듈
   - 프로젝트에 필요한 모듈의 집합체
   - 각 모듈의 필요한 의존성 주입은 이 모듈에서 일어남.
2. presentation
   - Android Library 모듈
   - UI를 담당하는 레이어
   - domain 모듈을 의존해서 usecase의 비즈니스 로직을 사용한다.
3. domain
    - 비즈니스 로직을 담당하는 레이어
    - java/kotlin 모듈로 생성
      - 비즈니스 로직만을 담당해야 하기 때문에 Android와 같은 OS에 관련된 라이브러리를 참조해서는 안 된다.
      - 외부 라이브러리의 변화에 전혀 상관이 없어야 함. KMM과 같은 경우 아이폰에서도 domain의 로직은 보호받을 수 있어야만 한다.
4. data
   - Android Library 모듈
   - 실질적인 데이터 제공자의 역할
   - domain 모듈을 의존해서 repository의 구현체를 제공해야 한다.