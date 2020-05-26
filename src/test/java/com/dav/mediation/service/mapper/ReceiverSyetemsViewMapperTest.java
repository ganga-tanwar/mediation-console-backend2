package com.dav.mediation.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ReceiverSyetemsViewMapperTest {

    private ReceiverSyetemsViewMapper receiverSyetemsViewMapper;

    @BeforeEach
    public void setUp() {
        receiverSyetemsViewMapper = new ReceiverSyetemsViewMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(receiverSyetemsViewMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(receiverSyetemsViewMapper.fromId(null)).isNull();
    }
}
