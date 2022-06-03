package com.ikarabulut;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ClientThreadFactoryTest {
    @Test
    @DisplayName("When newThread is called the numOfThreads should increase by 1")
    void newThread_IncreasesNumBy1() {
        Runnable r = mock(Runnable.class);
        ClientThreadFactory threadFactory = new ClientThreadFactory();

        assertEquals(0, threadFactory.getNumOfThreads());
        threadFactory.newThread(r);
        assertEquals(1, threadFactory.getNumOfThreads());
    }

}
