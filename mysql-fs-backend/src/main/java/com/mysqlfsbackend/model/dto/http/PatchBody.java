package com.mysqlfsbackend.model.dto.http;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchBody {
    private Integer newParentDirId;
    private Integer id;
}