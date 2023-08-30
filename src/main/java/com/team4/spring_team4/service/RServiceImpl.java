/*
 *  RServiceImpl.java
 *  Flutter_R_Spring Project
 *  v0.2
 * 
 *  전세금/매매금 예측을 위한 공용 Service단
 *  각 매개변수를 받고, isSale으로 전세금 예측인지 매매금 예측인지 판단하여 값 입력 하여 R 연결하여 실행한 결과값을 return
 *  
 *  Created by Okrie on 2023/08/12.
 */

package com.team4.spring_team4.service;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/*
 * v0.1
 * 
 * 각 매개변수를 받고, isSale으로 전세금 예측인지 매매금 예측인지 판단하여 값 입력 하여 R 연결하여 실행한 결과값을 return
 * buStation = 인근 버스정류장 수
 * distance = 가장 가까운 지하철 역과의 거리
 * leaseableArea = 해당 면적
 * floor = 층
 * yoc = 건축 년도
 * contractDate = 계약 시점
 * baseRate = 계약 시점 기준 금리
 * size = R 모델 명
 * 
 * v0.2
 * isSale = 전세/매매 분리 기준 추가
 * 
 */
@Service
public class RServiceImpl implements RService{
    public String rPredict(double busStations, double distance, double leaseableArea, double floor, double yoc, double contractDate, double baseRate, double x, double y, String size, String isSale) {
        try {
            RConnection conn = new RConnection();
            // String rdsPath = getClass().getResource("/rds/" + size + ".rds").getPath();

            Resource rdsResource = new ClassPathResource("rds/" + size + ".rds");
            String rdsPath = rdsResource.getFile().getAbsolutePath();
            System.out.println(rdsPath);

            conn.voidEval("library(randomForest)");
            conn.voidEval("around10_rf <- readRDS('" + rdsPath +"')");
            

            String predictionResult = "";
            // isSale 0 -> 전세가, isSale 1 -> 매매가
            // v0.2
            if(isSale.equals(0)){
                conn.voidEval("result <- predict(around10_rf, list(주변정류장개수=" + busStations + ", 역거리= " + distance +
                        ", 경도 = " + y + ", 위도 = " + x + ",임대면적=" + leaseableArea + " ,층= " + floor +
                        ",건축년도=" + yoc + ",계약시점=" + contractDate + ",계약시점기준금리=" + baseRate + "))");
                predictionResult = conn.eval("result").asString();
            }else{
                conn.voidEval("result <- predict(around10_rf, list(정류장수=" + busStations + ", 역간거리= " + distance + ", 지수=102.9551" +
                        ",전용면적=" + leaseableArea + " ,층= " + floor + ",건축년도=" + yoc + ",계약년월=" + contractDate + ",금리=" + baseRate + "))");
                // 3.3000 ~ 4.6000
                String result = conn.eval("result").asString();
                predictionResult = result + " 단위(억)";
            }

            
            conn.close();

            return predictionResult;
        } catch (Exception e) {
            e.printStackTrace();
            return "R Error";
        }
    }
}
