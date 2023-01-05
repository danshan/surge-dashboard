package com.shanhh.surge.dashboard.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author honghao.shan
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class SpeedVO implements Serializable {
    private long in = 0;
    private long inCurrentSpeed = 0;
    private long inMaxSpeed = 0;
    private long out = 0;
    private long outCurrentSpeed = 0;
    private long outMaxSpeed = 0;
}
