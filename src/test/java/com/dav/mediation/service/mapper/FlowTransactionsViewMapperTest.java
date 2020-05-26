package com.dav.mediation.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FlowTransactionsViewMapperTest {

    private FlowTransactionsViewMapper flowTransactionsViewMapper;

    @BeforeEach
    public void setUp() {
        flowTransactionsViewMapper = new FlowTransactionsViewMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(flowTransactionsViewMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(flowTransactionsViewMapper.fromId(null)).isNull();
    }
}
