package com.jaysen.leagueoflegendmanual.util;

/**
 * RC4 encrypt algorithm
 */
public class RC4 {
    /**
     * @param data byte[] data
     * @param key  string key
     * @return
     */
    public static String decry_RC4(byte[] data, String key) {
        if (data == null || key == null) {
            return null;
        }
        return asString(RC4Base(data, key));
    }

    /**
     * @param data string data
     * @param key  string key
     * @return
     */
    public static String decry_RC4(String data, String key) {
        if (data == null || key == null) {
            return null;
        }
        return new String(RC4Base(HexString2Bytes(data), key));
    }

    /**
     * @param data
     * @param key
     * @return
     */
    public static byte[] encry_RC4_byte(String data, String key) {
        if (data == null || key == null) {
            return null;
        }
        byte b_data[] = data.getBytes();
        for (int i = 0; i < b_data.length; i++) {
            System.out.println("b_data[" + i + "] = " + Integer.toHexString(b_data[i]) + ", key = [" + key + "]");
        }
        return RC4Base(b_data, key);
    }

    /**
     * @param data
     * @param key
     * @return
     */
    public static String encry_RC4_string(String data, String key) {
        if (data == null || key == null) {
            return null;
        }
        return toHexString(asString(encry_RC4_byte(data, key)));
    }

    /**
     * @param buf
     * @return
     */
    private static String asString(byte[] buf) {
        StringBuffer strbuf = new StringBuffer(buf.length);
        for (int i = 0; i < buf.length; i++) {
            strbuf.append((char) buf[i]);
        }
        return strbuf.toString();
    }


    /**
     * @param s
     * @return
     */
    private static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            System.out.println("ch = [" + s.charAt(i) + "]");
            String s4 = Integer.toHexString(ch & 0xFF);
//            System.out.println("s = [" + s + "]");
//            System.out.println("s4= [" + s4+ "]");
            if (s4.length() == 1) {
                s4 = '0' + s4;
            }
            str = str + s4;
        }
        return str;// 0x表示十六进制
    }

    /**
     * @param src
     * @return
     */
    private static byte[] HexString2Bytes(String src) {
        int    size = src.length();
        byte[] ret  = new byte[size / 2];
        byte[] tmp  = src.getBytes();
        for (int i = 0; i < size / 2; i++) {
            ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
        }
        return ret;
    }

    /**
     * 单元字节（8位二进制）
     *
     * @param src0 ASCII 码
     * @param src1 ASCII 码
     * @return
     */
    private static byte uniteBytes(byte src0, byte src1) {
        String low = "0x" + new String(new byte[]{src0});
        char   _b0 = (char) Byte.decode(low).byteValue();
        _b0 = (char) (_b0 << 4);
        String high = "0x" + new String(new byte[]{src1});
        char   _b1  = (char) Byte.decode(high).byteValue();
//        byte ret = (byte) (_b0 ^ _b1);
        byte ret = (byte) (_b0 | _b1);
        return ret;
    }

    /**
     * 初始化算法和伪随机子密码生成算法
     *
     * @param aKey
     * @return
     */
    private static byte[] initKey(String aKey) {
        if (aKey == null) {
            return null;
        }
        byte[] b_key   = aKey.getBytes();
        byte   state[] = new byte[256];

        for (int i = 0; i < 256; i++) {
            state[i] = (byte) i;
        }
        int x = 0;
        int y = 0;
        if (b_key.length == 0) {
            return null;
        }
        for (int i = 0; i < 256; i++) {
            x = (x + 1) % b_key.length;
            y = ((b_key[x] & 0xff) + (state[i] & 0xff) + y) & 0xff;
            swap(state, i, y);
        }
        return state;
    }

    /**
     * RC4 algorithm
     *
     * @param input byte[] data
     * @param mKkey crypto string key
     * @return
     */
    private static byte[] RC4Base(byte[] input, String mKkey) {
        int  x     = 0;
        int  y     = 0;
        byte key[] = initKey(mKkey);
        if (key == null) {
            System.out.println("input private key is null");
            System.exit(-1);
        }
        int    xorIndex;
        byte[] result = new byte[input.length];

        for (int i = 0; i < input.length; i++) {
            x = (x + 1) & 0xff;//mod 256
            y = ((key[x] & 0xff) + y) & 0xff;
            swap(key, x, y);
            xorIndex = ((key[x] & 0xff) + (key[y] & 0xff)) & 0xff;
            result[i] = (byte) (input[i] ^ key[xorIndex]);
        }
        return result;
    }

    private static void swap(byte[] S, int x, int y) {
        byte tmp = S[x];
        S[x] = S[y];
        S[y] = tmp;
    }
}