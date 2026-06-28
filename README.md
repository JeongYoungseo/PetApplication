# 🐶 Pet Application

반려동물의 정보를 등록하고 건강을 관리할 수 있는 Android 애플리케이션입니다.

반려동물의 기본 정보를 등록한 후 추천 사료와 간식을 확인할 수 있으며, 산책 기록, 예방접종 일정, 메모를 관리하여 반려동물의 일상을 효율적으로 관리할 수 있도록 구현하였습니다.

---

# 📖 프로젝트 소개

본 프로젝트는 Android(Java)를 기반으로 개발한 반려동물 관리 애플리케이션입니다.

사용자는 회원가입 및 로그인 후 반려동물의 이름, 품종, 나이, 몸무게, 알레르기, 건강 상태 등의 정보를 등록할 수 있습니다. 등록된 정보를 기반으로 반려동물에게 적합한 사료 및 간식을 추천하며, 산책 기록과 예방접종 일정, 메모 기능을 통해 반려동물의 건강을 체계적으로 관리할 수 있도록 구현하였습니다.

---

# 👨‍💻 개발 환경

### IDE

* Android Studio

### Language

* Java

### SDK

* Android SDK 36
* Minimum SDK 24

### Library

* AndroidX
* Material Design
* RecyclerView
* ConstraintLayout

---

# 📂 프로젝트 구조

```text
activity
├── IntroActivity
├── LoginActivity
├── SignupActivity
├── PetRegisterActivity
└── MainActivity

fragment
├── HomeFragment
├── CareFragment
└── ProfileFragment

adapter
├── RecommendAdapter
└── CareAdapter

model
├── RecommendItem
└── CareItem

data
├── PetStore
├── UserStore
├── CareRepository
└── ScoreRule
```

---

# ✨ 주요 기능

## 🚀 시작 화면

* 앱 실행 시 Intro 화면 출력
* 로그인 및 회원가입 화면으로 이동

---

## 👤 회원 기능

* 회원가입
* 로그인
* 회원 정보 관리

---

## 🐶 반려동물 등록

* 프로필 이미지 등록
* 이름 입력
* 품종 입력
* 나이 입력
* 몸무게 입력
* 알레르기 정보 입력
* 건강 상태 입력
* 활동량 입력

등록한 정보는 앱 내에서 저장되어 Home과 Profile 화면에서 사용할 수 있도록 구현하였다.

---

## 🏠 Home

* 등록된 반려동물 프로필 표시
* 이름, 품종, 나이, 몸무게 출력
* 반려동물 특성을 기반으로 추천 사료 및 간식 제공
* RecyclerView를 이용한 추천 목록 출력

추천 결과는 등록한 반려동물의 건강 상태와 활동량 등을 기반으로 구성되도록 구현하였다.

---

## 🩺 Care

반려동물의 건강 관리를 위한 기능을 제공한다.

### 산책 관리

* 산책 일정 추가
* 산책 기록 조회

### 예방접종 관리

* 예방접종 일정 등록
* 접종 일정 확인

### 메모 관리

* 건강 메모 작성
* 메모 조회

각 관리 항목은 버튼을 이용하여 전환할 수 있으며, RecyclerView를 통해 등록된 내용을 확인할 수 있도록 구현하였다.

---

## 👤 Profile

* 반려동물 프로필 확인
* 알레르기 정보 조회
* 건강 상태 조회
* 활동량 조회
* 로그아웃 기능

등록된 반려동물 정보를 한 화면에서 확인할 수 있도록 구성하였다.

---

# 🛠 사용 기술

| 기술                   | 적용 내용                         |
| -------------------- | ----------------------------- |
| Java                 | Android 애플리케이션 개발             |
| Activity             | 로그인, 회원가입, 반려동물 등록 및 메인 화면 구현 |
| Fragment             | Home, Care, Profile 화면 구성     |
| RecyclerView         | 추천 상품 및 관리 목록 출력              |
| BottomNavigationView | 화면 전환 기능 구현                   |
| AlertDialog          | 일정 및 메모 추가 입력                 |
| DatePickerDialog     | 예방접종 및 일정 날짜 선택               |
| Intent               | Activity 간 화면 이동              |
| URI(Image)           | 반려동물 프로필 이미지 등록               |
| Java Collection      | 사용자 정보 및 반려동물 데이터 저장          |
| Material Design      | UI 구성                         |

---

# 📱 화면 구성

### Intro

* 앱 시작 화면

### Login

* 로그인

### Signup

* 회원가입

### Pet Register

* 반려동물 정보 등록

### Home

* 반려동물 정보 확인
* 추천 사료 및 간식

### Care

* 산책 관리
* 예방접종 관리
* 메모 관리

### Profile

* 반려동물 정보 확인
* 로그아웃

---

# 💡 프로젝트 특징

* Activity와 Fragment를 함께 사용하여 화면을 구성하였다.
* Bottom Navigation을 이용하여 Home, Care, Profile 화면을 자유롭게 이동할 수 있도록 구현하였다.
* RecyclerView를 활용하여 추천 목록과 관리 목록을 동적으로 출력하였다.
* AlertDialog와 DatePickerDialog를 이용하여 일정과 메모를 쉽게 등록할 수 있도록 구현하였다.
* 반려동물 정보를 기반으로 추천 사료와 간식을 제공하도록 구성하였다.
* Java Collection을 이용하여 회원 정보와 반려동물 정보를 관리하였다.

---
