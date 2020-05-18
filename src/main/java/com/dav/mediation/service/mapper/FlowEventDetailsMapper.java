package com.dav.mediation.service.mapper;


import com.dav.mediation.domain.*;
import com.dav.mediation.service.dto.FlowEventDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FlowEventDetails} and its DTO {@link FlowEventDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {FlowDetailsMapper.class})
public interface FlowEventDetailsMapper extends EntityMapper<FlowEventDetailsDTO, FlowEventDetails> {

    @Mapping(source = "flowId.id", target = "flowIdId")
    FlowEventDetailsDTO toDto(FlowEventDetails flowEventDetails);

    @Mapping(source = "flowIdId", target = "flowId")
    FlowEventDetails toEntity(FlowEventDetailsDTO flowEventDetailsDTO);

    default FlowEventDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        FlowEventDetails flowEventDetails = new FlowEventDetails();
        flowEventDetails.setId(id);
        return flowEventDetails;
    }
}
