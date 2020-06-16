package com.andrewrs.sps.data;

import java.time.LocalDateTime;

public class ListRecord {
    private long timeStamp; 
    private String message;
    public ListRecord(long timeStamp, String message)
    {
      this.timeStamp = timeStamp; 
      this.message = message;
    }
}