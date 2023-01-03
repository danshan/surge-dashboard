package com.shanhh.surge.dashboard.model.mapper;

import com.shanhh.surge.dashboard.client.model.Devices;
import com.shanhh.surge.dashboard.model.vo.DeviceVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author honghao.shan
 * @since
 */
@Mapper(componentModel = "spring")
public interface DeviceMapper {

    DeviceMapper INSTANCE = Mappers.getMapper(DeviceMapper.class);

    DeviceVO toVO(Devices.Device device);

    List<DeviceVO> toVOList(List<Devices.Device> devices);

}
