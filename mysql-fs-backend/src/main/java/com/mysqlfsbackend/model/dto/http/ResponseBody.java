package com.mysqlfsbackend.model.dto.http;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBody<T> {
    private String message;
    private T payload;
}
