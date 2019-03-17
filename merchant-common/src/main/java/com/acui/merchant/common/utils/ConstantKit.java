package com.acui.merchant.common.utils;

public final class ConstantKit {

    /**
     * 设置删除标志为真
     */
    public static final Integer DEL_FLAG_TRUE = 1;

    /**
     * 设置删除标志为假
     */
    public static final Integer DEL_FLAG_FALSE = 0;

    /**
     * redis存储token设置的过期时间，10分钟
     */
    public static final Long TOKEN_EXPIRE_TIME = 60l * 10l;

    /**
     * 设置可以重置token过期时间的时间界限
     */
    public static final Long TOKEN_RESET_TIME = 1000l * 100l;


}