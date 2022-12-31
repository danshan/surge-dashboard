package com.shanhh.surge.dashboard.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author honghao.shan
 * @since
 */
@Data
public class DeviceVO implements Serializable {
    private String physicalAddress;
    private String sourceIP;

    private Long totalBytes;
    private Long outBytes;
    private Long currentOutSpeed;
    private Long inBytes;
    private Long currentInSpeed;

    private String name;
}
