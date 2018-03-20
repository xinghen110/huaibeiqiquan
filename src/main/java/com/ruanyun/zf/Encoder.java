package com.ruanyun.zf;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author zhangwei
 * @description：
 * @create 2018-02-24 15:19
 **/
public interface Encoder {
    int encode(byte[] var1, int var2, int var3, OutputStream var4) throws IOException;

    int decode(byte[] var1, int var2, int var3, OutputStream var4) throws IOException;

    int decode(String var1, OutputStream var2) throws IOException;
}
