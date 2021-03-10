package com.liulin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class McLeave implements Serializable {
    private Integer mcLeaveId;
    private String key;
    private String mcUserNiName;
    private Integer mcLeaveUserId;
    private String mcLeaveName;
    private Integer mcLeaveDay;
    private String mcLeaveReason;
    private Date mcLeaveStartTime;
    private Integer mcLeaveFinish;
    private String taskId;
}
