package com.utils;

/** api调用类型
 * 1：实名开户 2：用户激活 3：变更银行卡
 * Created by zhoujian on 2018/4/9.
 */
public enum InvokeType {
    ESTAB_ACCOUNT(1),
    USER_ACTIVATION(2),
    UPDATE_BANKINFO(3);


    InvokeType(Integer code) {
        this.code = code;
    }

    private Integer code;

    public Integer getCode() {
        return code;
    }


}
