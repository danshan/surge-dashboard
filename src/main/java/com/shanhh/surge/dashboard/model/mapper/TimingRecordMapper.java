package com.shanhh.surge.dashboard.model.mapper;

import com.shanhh.surge.dashboard.client.model.Requests;
import com.shanhh.surge.dashboard.model.vo.RequestVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author honghao.shan
 * @since
 */
@Mapper(componentModel = "spring")
public interface TimingRecordMapper {

    TimingRecordMapper INSTANCE = Mappers.getMapper(TimingRecordMapper.class);

    RequestVO.TimingRecords toVO(Requests.TimingRecords timingRecords);

    List<RequestVO.TimingRecords> toVOList(List<Requests.TimingRecords> timingRecords);

}
