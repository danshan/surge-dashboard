package com.shanhh.surge.dashboard.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author honghao.shan
 * @since 1.0.0
 */
@Data
public class SpeedVO implements Serializable {
    private Long in;
    private Long inCurrentSpeed;
    private Long inMaxSpeed;
    private Long out;
    private Long outCurrentSpeed;
    private Long outMaxSpeed;
}
