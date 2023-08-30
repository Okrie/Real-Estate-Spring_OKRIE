/*
 *  RService.java
 *  Flutter_R_Spring Project
 *  v0.1
 * 
 *  전세금/매매금 예측을 위한 공용 Service interface단
 *  
 *  Created by Okrie on 2023/08/12.
 */

package com.team4.spring_team4.service;

public interface RService{
    public String rPredict(double busStations, double distance, double leaseableArea, double floor, double yoc, double contractDate, double baseRate, double x, double y, String size, String isSale) throws Exception;
}