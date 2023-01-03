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
@Mapper(componentModel = "spring", uses = {TimingRecordMapper.class})
public abstract interface RequestMapper {

    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    RequestVO toVO(Requests.Request request);

    List<RequestVO> toVOList(List<Requests.Request> requests);
}
