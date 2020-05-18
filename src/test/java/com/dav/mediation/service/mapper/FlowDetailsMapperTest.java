package com.dav.mediation.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FlowDetailsMapperTest {

    private FlowDetailsMapper flowDetailsMapper;

    @BeforeEach
    public void setUp() {
        flowDetailsMapper = new FlowDetailsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(flowDetailsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(flowDetailsMapper.fromId(null)).isNull();
    }
}
