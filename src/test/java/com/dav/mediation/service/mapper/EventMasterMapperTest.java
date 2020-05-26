package com.dav.mediation.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EventMasterMapperTest {

    private EventMasterMapper eventMasterMapper;

    @BeforeEach
    public void setUp() {
        eventMasterMapper = new EventMasterMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(eventMasterMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(eventMasterMapper.fromId(null)).isNull();
    }
}
