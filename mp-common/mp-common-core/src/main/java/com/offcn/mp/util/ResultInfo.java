package com.offcn.mp.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回报文格式
 *
 * @author fuyao
 * @date 2019年7月14日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultInfo implements Serializable {
    private static final long serialVersionUID = -744567280769723204L;

    private int code = 200;
    private String msg;
    private Object data;

//    public boolean isSuccess() {
//        return this.code == 200;
//    }
}
