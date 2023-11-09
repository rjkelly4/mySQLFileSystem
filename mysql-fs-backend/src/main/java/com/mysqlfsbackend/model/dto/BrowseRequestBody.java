package com.mysqlfsbackend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrowseRequestBody {
    private Integer browseDepth;
    private Boolean isFile;
}
