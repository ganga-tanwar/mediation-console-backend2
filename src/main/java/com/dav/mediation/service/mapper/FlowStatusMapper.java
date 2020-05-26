package com.dav.mediation.service.mapper;


import com.dav.mediation.domain.*;
import com.dav.mediation.service.dto.FlowStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FlowStatus} and its DTO {@link FlowStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FlowStatusMapper extends EntityMapper<FlowStatusDTO, FlowStatus> {



    default FlowStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        FlowStatus flowStatus = new FlowStatus();
        flowStatus.setId(id);
        return flowStatus;
    }
}
