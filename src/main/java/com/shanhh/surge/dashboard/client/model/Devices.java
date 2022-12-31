package com.shanhh.surge.dashboard.client.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author honghao.shan
 * @since
 */
@Data
public class Devices implements Serializable {
    private List<Device> devices;

    @Data
    public static class Device implements Serializable{
        private String physicalAddress;
        private String sourceIP;

        private Long totalBytes;
        private Long outBytes;
        private Long currentOutSpeed;
        private Long inBytes;
        private Long currentInSpeed;

        private String name;
    }
}
