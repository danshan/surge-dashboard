package com.shanhh.surge.dashboard.service;

import com.shanhh.surge.dashboard.model.vo.DeviceVO;
import com.shanhh.surge.dashboard.model.vo.RequestVO;
import com.shanhh.surge.dashboard.model.vo.SpeedVO;

import java.util.List;

/**
 * @author honghao.shan
 * @since 1.0.0
 */
public interface SurgeService {

    SpeedVO getSpeed();

    List<DeviceVO> findDevices();

    List<RequestVO> findRequests();

}
