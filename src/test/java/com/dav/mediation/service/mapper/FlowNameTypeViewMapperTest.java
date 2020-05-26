package com.dav.mediation.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FlowNameTypeViewMapperTest {

    private FlowNameTypeViewMapper flowNameTypeViewMapper;

    @BeforeEach
    public void setUp() {
        flowNameTypeViewMapper = new FlowNameTypeViewMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(flowNameTypeViewMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(flowNameTypeViewMapper.fromId(null)).isNull();
    }
}
