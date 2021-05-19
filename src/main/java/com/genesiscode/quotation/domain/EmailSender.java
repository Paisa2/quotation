package com.genesiscode.quotation.domain;

public interface EmailSender {
    void send(String to, String email);
}
