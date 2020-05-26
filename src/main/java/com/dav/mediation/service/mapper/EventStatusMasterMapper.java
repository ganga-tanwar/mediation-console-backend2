package com.dav.mediation.service.mapper;


import com.dav.mediation.domain.*;
import com.dav.mediation.service.dto.EventStatusMasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EventStatusMaster} and its DTO {@link EventStatusMasterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EventStatusMasterMapper extends EntityMapper<EventStatusMasterDTO, EventStatusMaster> {



    default EventStatusMaster fromId(Long id) {
        if (id == null) {
            return null;
        }
        EventStatusMaster eventStatusMaster = new EventStatusMaster();
        eventStatusMaster.setId(id);
        return eventStatusMaster;
    }
}
