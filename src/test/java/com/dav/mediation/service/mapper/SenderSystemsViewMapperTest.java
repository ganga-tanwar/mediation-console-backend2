package com.dav.mediation.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SenderSystemsViewMapperTest {

    private SenderSystemsViewMapper senderSystemsViewMapper;

    @BeforeEach
    public void setUp() {
        senderSystemsViewMapper = new SenderSystemsViewMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(senderSystemsViewMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(senderSystemsViewMapper.fromId(null)).isNull();
    }
}
