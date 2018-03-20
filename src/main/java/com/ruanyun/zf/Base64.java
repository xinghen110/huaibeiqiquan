package com.ruanyun.zf;

import java.io.ByteArrayOutputStream;

public class Base64 {
    public Base64() {
    }

    public static String encode(byte[] data) {
        return encode(data, true);
    }

    public static String encode(byte[] data, boolean splitlines) {
        byte[] bytes = SuperBase64.encode(data);
        if (!splitlines) {
            return new String(bytes);
        } else {
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            for(int i = 0; i < bytes.length; i += 64) {
                if (i + 64 < bytes.length) {
                    os.write(bytes, i, 64);
                    os.write(10);
                } else {
                    os.write(bytes, i, bytes.length - i);
                }
            }

            return new String(os.toByteArray());
        }
    }

    public static byte[] decode(byte[] bytes) {
        return SuperBase64.decode(bytes);
    }

    public static byte[] decode(String data) {
        return SuperBase64.decode(data);
    }
}

