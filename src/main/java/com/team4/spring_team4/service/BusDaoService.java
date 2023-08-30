/*
 *  BusDaoService.java
 *  Flutter_R_Spring Project
 * 
 *  Bus 리스트를 db에서 불러오기 위한 service단
 * 
 *  Created by Okrie on 2023/08/13.
 */

package com.team4.spring_team4.service;

import java.util.List;

import com.team4.spring_team4.model.BusDto;

public interface BusDaoService {

    public List<BusDto> listDao() throws Exception;
    
}
