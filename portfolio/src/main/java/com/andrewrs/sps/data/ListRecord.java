package com.andrewrs.sps.data;

import java.time.LocalDateTime;

public class ListRecord {
    private LocalDateTime timeStamp; 
    private String message;
    public ListRecord(LocalDateTime timeStamp, String message)
    {
      this.timeStamp = timeStamp; 
      this.message = message;
    }
}