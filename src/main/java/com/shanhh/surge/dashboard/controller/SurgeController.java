package com.shanhh.surge.dashboard.controller;

import com.shanhh.surge.dashboard.model.vo.DeviceVO;
import com.shanhh.surge.dashboard.model.vo.SpeedVO;
import com.shanhh.surge.dashboard.service.SurgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author honghao.shan
 * @since
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/surge")
public class SurgeController {

    private final SurgeService surgeService;

    @RequestMapping("speed")
    public SpeedVO getSpeed() {
        return surgeService.getSpeed();
    }

    @RequestMapping("devices")
    public List<DeviceVO> getDevices() {
        return surgeService.findDevices();
    }


}
