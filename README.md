# API 문서 보기
localhost:8080/swagger-ui/index.html

## 문자 예약 API 요청 예시

POST: localhost:8080/api/sms/send

{
    "additionalContent" : "",
    "reserveTime" : "2023-07-25 19:58",
    "messageId" : 5
}

* 문자 전송은 알람이 꺼지는 시간을 감안해서 예약시간의 3분 이후로 발송됩니다

## 문자 예약 취소 API 요청 예시

DELETE: localhost:8080/api/sms/cancel

{   "userId" : 1,
    "requestId" : "RSSA-1690283255957-5717-57645231-roxmBCUD"
}

## 주변 경찰서 찾기 API 요청 예시

GET : localhost:8080/api/police

{
    "latitude" : 35.1744657,
    "longitude" : 129.0625562,
    "radius" : 1
}

* radius의 단위는 km
