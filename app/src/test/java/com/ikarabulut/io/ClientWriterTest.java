package com.ikarabulut.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ClientWriterTest {
    @Test
    @DisplayName("When an input of 'Hello' is received, then 'Hello' should be printed")
    void writeOutput_WithHello() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ClientWriter clientWriter = new ClientWriter(out);
        String input = "Hello\n";

        clientWriter.printOutput(input);

        assertEquals("Hello\n", out.toString());
    }

}
