package com.flow.util;

/**
 * Created by gaosh on 2017/6/24.
 */
public class Constants {

    /**
     * 网络开小差
     */
    public static final Integer CODE_NET_ERR = -1;

    /**
     * 数据库CRUD操作失败
     */
    public static final Integer CODE_DB_CRUD_ERR = -2;

    /**
     * 记录未查询到
     */
    public static final Integer CODE_RECORD_NOT_FIND = 50003;

    public static final String MSG_NET_ERR = "网络开小差，请稍后再试。";

    public static final String MSG_DB_CRUD_ERR = "网络异常，请稍后再试。";

    public static final String MSG_RECORD_NOT_FIND = "该条信息不存在或已删除。";

}
