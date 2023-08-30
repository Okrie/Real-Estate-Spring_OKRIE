# Real-Estate-Spring_OKRIE

프로젝트 내 앱에서 필요한 정보 API로 제작
- csv내 도로명 주소의 위경도 및 지하철역 최단거리 값 저장
- csv내 도로명 주소의 위경도 및 버스정류소 개수 저장
- 전세가 데이터 예측
- 매매가 데이터 예측
- 유저 중복 체크
- 유저 로그인 체크
- 인근 버스정류장 개수
- 제일 가까운 지하철역 및 거리 반환
- 해당 위치의 아파트 전세값 조회

| Endpoint      | Parameter                                                                                                           | Parameter Type                                                                                                  | Return                      | Return Type | Return Example                                                                     | Error Code | Error                   |
|---------------|---------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|-----------------------------|-------------|-----------------------------------------------------------------------------------------------|------------|-------------------------|
| /road_name    | .                                                                                                                   | .                                                                                                               | csv                         | 도로명, 지번, 호선, 역이름, x, y, 거리       |              |                           |
| /countBus      | .                                                                                                                  | .                                                                                                                | csv                         | 도로명, 지번, 정류장이름, x, y, count     |              |                           |
| /rdsRServe     | busStations<br>distance<br>leasebleArea<br>floot<br>yoc<br>contractDate<br>baseRate<br>x<br>y<br>size<br>isSale    | Double<br>Double<br>Double<br>Double<br>Double<br>Double<br>Double<br>Double<br>Double<br>String<br>String       | 예측된 금액 결과            | json        | {"result": "5.2억 ~ 8.2억"}                                                          | 400<br>404<br>500 | R서버 에러<br>서버에러<br>DB에러 |
| /rdsRServe     | busStations<br>distance<br>leasebleArea<br>floot<br>yoc<br>contractDate<br>baseRate<br>x<br>y<br>size<br>isSale   | Double<br>Double<br>Double<br>Double<br>Double<br>Double<br>Double<br>Double<br>Double (null)<br>String<br>String | 예측된 금액 결과            | json        | {"result": "3.3000 ~ 4.6000 단위(억)"}                                               | 400<br>404<br>500 | R서버 에러<br>서버에러<br>DB에러 |
| /dupCheck      | userid                                                                                                            | String                                                                                                           | 성공/실패                   | json        | {"result": "success"}                                                                  | 404<br>500 | 서버에러<br>DB에러        |
| /loginCheck      | userid<br>password                                                                                               | String<br>String                                                                                                 | 성공/실패                   | json        | {"result": "success"}                                                                  | 404<br>500 | 서버에러<br>DB에러        |
| /busCount        | xVal<br>yVal                                                                                                     | Double<br>Double                                                                                                 | 300m내 정류장 개수           | json        | {"result": 10}                                                                            | 404<br>500 | 서버에러<br>DB에러        |
| /ShortestStation | xVal<br>yVal                                                                                                     | Double<br>Double                                                                                                 | 제일 가까운 역, 거리, 호선   | json        | {"distance": -386.78203368635053, "station": "한티", "line": "분당선"}                | 404<br>500 | 서버에러<br>DB에러        |
| /getPrice        | address<br>floor                                                                                                | String<br>String                                                                                                 | 지번, 층, 아파트명, 보증금액    | json        | {"results": [{"202108": [{"아파트명": "역삼I'PARK", "보증금": 39900}, ...]}, ...]} | 404        |                           |

## Skill

<p align="center">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=git,github,vscode,mysql,r,spring," />
  </a>
</p>

----------------------

## /road_name
- csv내 도로명 주소의 위경도 및 지하철역 최단거리 값 저장

### Param

| Name    | Type          |  Require  |
| ------ | ------------  | ---- |
|   |     |  |

### Return

| Type          |  Ex  |
| ------------  | ---- |
| csv | 도로명, 지번, 호선, 역이름, x, y, 거리 |


## /countBus
- csv내 도로명 주소의 위경도 및 버스정류소 개수 저장

### Param

| Name    | Type          |  Require  |
| ------ | ------------  | ---- |
|   |     |  |

### Return

| Type          |  Ex  |
| ------------  | ---- |
| csv | 도로명, 지번, 정류장이름, x, y, count |


## /rdsRServe
- 전세가 데이터 예측

### Param

| Name    | Type          |  Require  | Info |
| ------ | ------------  | ---- | ---- |
| busStations | Double | Require | 버스정류장 개수 |
| distance | Double | Require | 지하철역과 최단거리 |
| leasebleArea | Double | Require | 전세 임대면적 |
| floot | Double | Require | 층 |
| yoc | Double | Require | 건축년도 |
| contractDate |  Double | Require | 계약년월일 |
| baseRate | Double | Require | 금리 |
| x | Double | Require | 위도 |
| y | Double | Require | 경도 |
| size | String | Require | R 예측 모델명 |
| isSale  | String | 매매 / 전세 |

### Return

| Type          |  Ex  |
| ------------  | ---- |
| json | {"result":"5.2억 ~ 8.2억"} |


## /rdsRServe
- 매매가 데이터 예측

### Param

| Name    | Type          |  Require  | Info |
| ------ | ------------  | ---- | ---- |
| busStations | Double | Require | 버스정류장 개수 |
| distance | Double | Require | 지하철역과 최단거리 |
| leasebleArea | Double | Require | 전세 임대면적 |
| floot | Double | Require | 층 |
| yoc | Double | Require | 건축년도 |
| contractDate |  Double | Require | 계약년월일 |
| baseRate | Double | Require | 금리 |
| x | Double | Require | 위도 |
| y | Double | Require | 경도 |
| size | String | Require | R 예측 모델명 |
| isSale  | String | 매매 / 전세 |

### Return

| Type          |  Ex  |
| ------------  | ---- |
| json | {"result":"3.3000 ~ 4.6000 단위(억)"} |


## /dupCheck
- 유저 중복 체크

### Param

| Name    | Type          |  Require  | Info |
| ------ | ------------  | ---- | ---- |
| userid | String | Require | 유저 ID |

### Return

| Type          |  Ex  |
| ------------  | ---- |
| json | {"result":"success"} |


## /loginCheck
- 유저 로그인 체크

### Param

| Name    | Type          |  Require  | Info |
| ------ | ------------  | ---- | ---- |
| userid | String | Require | 유저 ID |
| password | String | Require | 유저 PW |

### Return

| Type          |  Ex  |
| ------------  | ---- |
| json | {"result":"success"} |


## /busCount
- 300m내 인근 버스정류장 개수 

### Param

| Name    | Type          |  Require  | Info |
| ------ | ------------  | ---- | ---- |
| xVal | Double | Require | 위도 |
| yVal | Double | Require | 경도 |

### Return

| Type          |  Ex  |
| ------------  | ---- |
| json | {"result":10} |


## /ShortestStation
- 제일 가까운 지하철역 및 거리 반환

### Param

| Name    | Type          |  Require  | Info |
| ------ | ------------  | ---- | ---- |
| xVal | Double | Require | 위도 |
| yVal | Double | Require | 경도 |

### Return

| Type          |  Ex  |
| ------------  | ---- |
| json | {"distance":-386.78203368635053,"station":"한티","line":"분당선"} |


## /getPrice
- 해당 위치의 아파트 전세값 조회

### Param

| Name    | Type          |  Require  | Info |
| ------ | ------------  | ---- | ---- |
| address | Double | Require | 주소(지번) |
| floor | Double | Require | 층 |

### Return

| Type          |  Ex  |
| ------------  | ---- |
| json | {"results":[{"202108":[{"아파트명":"...","보증금": ...},{"아파트명":"...","보증금": ...}]},{"202109":[{"아파트명":"...","보증금": ...},{"아파트명":"...","보증금": ...}]},{...}] |
