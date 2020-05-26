package com.dav.mediation.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EventStatusMasterMapperTest {

    private EventStatusMasterMapper eventStatusMasterMapper;

    @BeforeEach
    public void setUp() {
        eventStatusMasterMapper = new EventStatusMasterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(eventStatusMasterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(eventStatusMasterMapper.fromId(null)).isNull();
    }
}
