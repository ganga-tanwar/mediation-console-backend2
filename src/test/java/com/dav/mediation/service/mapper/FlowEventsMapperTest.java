package com.dav.mediation.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FlowEventsMapperTest {

    private FlowEventsMapper flowEventsMapper;

    @BeforeEach
    public void setUp() {
        flowEventsMapper = new FlowEventsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(flowEventsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(flowEventsMapper.fromId(null)).isNull();
    }
}
