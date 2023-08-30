/*
 *  PredictLeaseService.java
 *  Flutter_R_Spring Project
 * 
 *  전세금 예측을 위한 service interface
 * 
 *  Created by Okrie on 2023/08/13.
 */

package com.team4.spring_team4.service;

public interface PredictLeaseService{
    public String predictLease(double busStations, double distance, double leaseableArea, double floor, double yoc, double contractDate, double baseRate, double x, double y, String model) throws Exception;
}