package com.enumtest;

/**
 * Created by zhoujian on 2017/9/28.
 */
public enum EnumCode {
    LOGIN("登录"),
    FORGET_PASSWORD("忘记密码"),
    FORGET_PASSWORD_TEXT_MSG("忘记密码短信验证"),
    FORGET_PASSWORD_VOICE_MSG("忘记密码语音验证"),
    CHANGE_GESTURE_PASSWORD("修改手势密码"),
    REGISTER("注册"),
    REGISTER_TEXT_MSG("注册短信验证"),
    REGISTER_VOICE_MSG("注册语音验证"),
    ;

    private String code;

    private EnumCode(String code) {
        this.code = code;
    }

    public static EnumCode valueOfCode(String code) {
        EnumCode[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            EnumCode type = var1[var3];
            if(type.code == code) {
                return type;
            }
        }

        return null;
    }

}
