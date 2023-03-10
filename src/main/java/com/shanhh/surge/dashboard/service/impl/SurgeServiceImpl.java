package com.shanhh.surge.dashboard.service.impl;

import com.shanhh.surge.dashboard.client.SurgeClient;
import com.shanhh.surge.dashboard.client.model.Devices;
import com.shanhh.surge.dashboard.client.model.Traffic;
import com.shanhh.surge.dashboard.model.mapper.DeviceMapper;
import com.shanhh.surge.dashboard.model.mapper.RequestMapper;
import com.shanhh.surge.dashboard.model.vo.DeviceVO;
import com.shanhh.surge.dashboard.model.vo.RequestVO;
import com.shanhh.surge.dashboard.model.vo.SpeedVO;
import com.shanhh.surge.dashboard.service.SurgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author honghao.shan
 * @since 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SurgeServiceImpl implements SurgeService {

    private final SurgeClient surgeClient;
    private final RequestMapper requestMapper;
    private final DeviceMapper deviceMapper;

    @Override
    public SpeedVO getSpeed() {
        Collection<Traffic.Interface> interfaces = surgeClient.getTraffic().getInterfaces().values();
        SpeedVO speed = new SpeedVO();
        interfaces.forEach(iface -> {
            speed.setIn(speed.getIn() + iface.getIn())
                    .setInCurrentSpeed(speed.getInCurrentSpeed() + iface.getInCurrentSpeed())
                    .setInMaxSpeed(Math.max(speed.getInMaxSpeed(), iface.getInMaxSpeed()))
                    .setOut(speed.getOut() + iface.getOut())
                    .setOutCurrentSpeed(speed.getOutCurrentSpeed() + iface.getOutCurrentSpeed())
                    .setOutMaxSpeed(Math.max(speed.getInMaxSpeed(), iface.getInMaxSpeed()));
        });
        return speed;
    }

    @Override
    public List<DeviceVO> findDevices() {
        Devices devices = surgeClient.getDevices();
        return devices.getDevices().stream()
                .filter(device -> Objects.nonNull(device.getSourceIP()))
                .map(deviceMapper::toVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestVO> findRequests() {
        var requests = surgeClient.getRecentRequests();
        return requestMapper.toVOList(requests.getRequests());
    }
}
