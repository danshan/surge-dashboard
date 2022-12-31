package com.shanhh.surge.dashboard.service.impl;

import com.shanhh.surge.dashboard.client.SurgeClient;
import com.shanhh.surge.dashboard.client.model.Devices;
import com.shanhh.surge.dashboard.client.model.Traffic;
import com.shanhh.surge.dashboard.model.vo.DeviceVO;
import com.shanhh.surge.dashboard.model.vo.SpeedVO;
import com.shanhh.surge.dashboard.service.SurgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author honghao.shan
 * @since 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SurgeServiceImpl implements SurgeService {

    private final SurgeClient surgeClient;

    @Override
    public SpeedVO getSpeed() {
        Optional<Traffic.Interface> traffic = surgeClient.getTraffic().getInterfaces().values().stream().findFirst();
        if (traffic.isPresent()) {
            SpeedVO speed = new SpeedVO();
            BeanUtils.copyProperties(traffic.get(), speed);
            return speed;
        } else {
            throw new IllegalArgumentException("get surge traffic failed");
        }
    }

    @Override
    public List<DeviceVO> findDevices() {
        Devices devices = surgeClient.getDevices();
        List<DeviceVO> list = new ArrayList<>();
        devices.getDevices().stream()
                .filter(device -> Objects.nonNull(device.getSourceIP()))
                .forEach(device -> {
                    var vo = new DeviceVO();
                    BeanUtils.copyProperties(device, vo);
                    list.add(vo);
                });
        return list;
    }
}
