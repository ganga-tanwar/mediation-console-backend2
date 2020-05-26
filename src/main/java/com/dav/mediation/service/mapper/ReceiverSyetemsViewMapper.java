package com.dav.mediation.service.mapper;


import com.dav.mediation.domain.*;
import com.dav.mediation.service.dto.ReceiverSyetemsViewDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ReceiverSyetemsView} and its DTO {@link ReceiverSyetemsViewDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReceiverSyetemsViewMapper extends EntityMapper<ReceiverSyetemsViewDTO, ReceiverSyetemsView> {



    default ReceiverSyetemsView fromId(Long id) {
        if (id == null) {
            return null;
        }
        ReceiverSyetemsView receiverSyetemsView = new ReceiverSyetemsView();
        receiverSyetemsView.setId(id);
        return receiverSyetemsView;
    }
}
