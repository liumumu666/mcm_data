package com.liulin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class McLeave implements Serializable {
    private Integer mcLeaveId;
    private Integer mcLeaveUserId;
    private String mcLeaveName;
    private Integer mcLeaveDay;
    private String mcLeaveReason;
    private Date mcLeaveStartTime;
    private Integer mcLeaveFinish;
}
