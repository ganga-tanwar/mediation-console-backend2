package com.dav.mediation.service.mapper;


import com.dav.mediation.domain.*;
import com.dav.mediation.service.dto.EventMasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EventMaster} and its DTO {@link EventMasterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EventMasterMapper extends EntityMapper<EventMasterDTO, EventMaster> {



    default EventMaster fromId(Long id) {
        if (id == null) {
            return null;
        }
        EventMaster eventMaster = new EventMaster();
        eventMaster.setId(id);
        return eventMaster;
    }
}
