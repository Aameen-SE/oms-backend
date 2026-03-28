package com.aameen.oms.dto;


import lombok.*;

@Data
@Builder
public class ApiResponse<T>{

    private boolean success;
    private String message;
    private T data;
}
