package com.dav.mediation.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FlowEventDetailsMapperTest {

    private FlowEventDetailsMapper flowEventDetailsMapper;

    @BeforeEach
    public void setUp() {
        flowEventDetailsMapper = new FlowEventDetailsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(flowEventDetailsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(flowEventDetailsMapper.fromId(null)).isNull();
    }
}
