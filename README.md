# bootSkeleton
spring boot skeleton
---
스프링 부트 사용을 위한 기본 뼈대

TODO-LIST
```
1. restful api(validation, 표준 response, exception 관리)
2. JPA, Mapper
3. redis, session
4. elk, kafka
5. develop 서버 구축(CI/CD)
```
```
1. restful api(표준 response, validation, exception 관리)
 1) 표준 response -> dto.CommonResponse
 2) exception 관리 -> exception.ExceptionController
  - handleMethodArgumentNotValid(validation)
    , handleExceptionInternal -> override
  - /error path
```
```
2. JPA, mapper
 1) JPA
 2) Mapper
  - dao.*, resouces.mapper.*
```
