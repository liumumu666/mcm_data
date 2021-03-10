package com.liulin.entity;

import lombok.Data;

@Data
public class McUserJob {
    private Integer mcUserJobId;
    private String mcUserJobName;
    private Integer countJob;
    private String tags;
    private String details;
}
