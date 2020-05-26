package com.dav.mediation.service.mapper;


import com.dav.mediation.domain.*;
import com.dav.mediation.service.dto.FlowEventsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FlowEvents} and its DTO {@link FlowEventsDTO}.
 */
@Mapper(componentModel = "spring", uses = {FlowStatusMapper.class, EventMasterMapper.class, EventStatusMasterMapper.class})
public interface FlowEventsMapper extends EntityMapper<FlowEventsDTO, FlowEvents> {

    @Mapping(source = "flowTransactions.id", target = "flowTransactionsId")
    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "status.id", target = "statusId")
    FlowEventsDTO toDto(FlowEvents flowEvents);

    @Mapping(source = "flowTransactionsId", target = "flowTransactions")
    @Mapping(source = "eventId", target = "event")
    @Mapping(source = "statusId", target = "status")
    FlowEvents toEntity(FlowEventsDTO flowEventsDTO);

    default FlowEvents fromId(Long id) {
        if (id == null) {
            return null;
        }
        FlowEvents flowEvents = new FlowEvents();
        flowEvents.setId(id);
        return flowEvents;
    }
}
