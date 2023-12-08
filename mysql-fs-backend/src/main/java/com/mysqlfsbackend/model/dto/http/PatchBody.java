package com.mysqlfsbackend.model.dto.http;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchBody {
    private String modifiedField;
    private String id;
}