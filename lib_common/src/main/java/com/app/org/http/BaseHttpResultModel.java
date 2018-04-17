package com.app.org.http;

import java.io.InputStream;

/**
 * 网络请求结果
 * Created by lixingxing on 2018/4/17.
 */
public class BaseHttpResultModel {
    public int responseCode = -10001 ; // 结果码  默认错误
    public Exception exception; // 错误类型
    public InputStream inputStream; // 结果输入流
}
