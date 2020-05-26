package com.dav.mediation.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FlowStatusMapperTest {

    private FlowStatusMapper flowStatusMapper;

    @BeforeEach
    public void setUp() {
        flowStatusMapper = new FlowStatusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(flowStatusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(flowStatusMapper.fromId(null)).isNull();
    }
}
