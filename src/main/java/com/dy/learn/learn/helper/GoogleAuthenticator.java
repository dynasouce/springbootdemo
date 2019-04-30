
//Google  Authenticator

// 只从google出了双重身份验证后，就方便了大家，等同于有了google一个级别的安全，但是我们该怎么使用google authenticator (双重身份验证)，

//下面是java的算法，这样大家都可以得到根据key得到公共的秘钥了,直接复制，记得导入JAR包：

//

//commons-codec-1.8.jar

//

//junit-4.10.jar

//测试方法：

//

//1、执行测试代码中的“genSecret”方法，将生成一个KEY（用户为testuser），URL打开是一张二维码图片。

//

//2、在手机中下载“GOOGLE身份验证器”。

//

//3、在身份验证器中配置账户，输入账户名（第一步中的用户testuser）、密钥（第一步生成的KEY），选择基于时间。

//

//4、运行authcode方法将key和要测试的验证码带进去（codes，key），就可以知道是不是正确的秘钥了！返回值布尔

//main我就不写了大家~~因为这个可以当做util工具直接调用就行了

//

package com.dy.learn.learn.helper;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.jpos.iso.ISOUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 使用说明：
 * <pre>
 *     依赖于APP（Google身份验证器或阿里巴巴的身份宝），需先下载此app，
 *     调用GoogleAuthenticator.getQRBarcodeURL(,,,)进行扫码，app上会生成一个每1分钟自动刷新的
 *     当登录时，调用GoogleAuthenticator.authcode(,,)进行校验
 *
 *     注意：系统运行的时间必须 = 手机的时间（时区、时间）当校验失败时，多半是时间不一直的情况。
 *
 *     实现原理：根据特定算法SHA1PRNG，依据当前时间生成的6位密码，和银行的U盾类似.
 * </pre>
 */
public class GoogleAuthenticator {

    //   just To Test
    /*
    public static void main(String[] args) {
        String key = "ll7BE20C13F58EAF6820F5295CFB1BD4216DF51E73A827452A";
        Base32 codec = new Base32();

        byte[] bEncodedKey = codec.encode(ISOUtil.trim(key.getBytes(), 10));

        String encodedKey = new String(bEncodedKey);

        System.out.println(getQRBarcodeURL("admin", "xxx.com", encodedKey));
    }*/

    public static final int SECRET_SIZE = 10;

    public static final String SEED = "g8GjEvTbW5oVSV7avLBdwIHqGlUYNzKFI7izOF8GwLDVKs2m0QN7vxRs2im5MDaNCWGmcD2rvcZx";

    public static final String RANDOM_NUMBER_ALGORITHM = "SHA1PRNG";

    int window_size = 3; // default 3 - max 17 (from google docs)最多可偏移的时间

    public void setWindowSize(int s) {
        if (s >= 1 && s <= 17) {
            window_size = s;
        }
    }
    /**
     * 鉴权
     * @param codes 6位数的验证码
     * @param savedSecret 原加密串
     * @return
     */
    public static boolean authcode(String codes, String savedSecret) {
        long code = Long.parseLong(codes);
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(1);
        return ga.check_code(savedSecret, code, t);
    }

    /**
     * 随机生成一组key(length=10)
     * @return
     */
    public static String generateSecretKey() {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance(RANDOM_NUMBER_ALGORITHM);
            sr.setSeed(Base64.decodeBase64(SEED));
            byte[] buffer = sr.generateSeed(SECRET_SIZE);
            Base32 codec = new Base32();
            byte[] bEncodedKey = codec.encode(buffer);
            String encodedKey = new String(bEncodedKey);
            return encodedKey;
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    /**
     * 转化为扫码格式的url，显示为二维码后，app可以直接扫码
     * @param user 扫码后显示的用户名
     * @param host 扫码后显示的host，或者网站
     * @param secret 扫码的 密钥
     * @return
     */
    public static String getQRBarcodeURL(String user, String host, String secret) {
        String format = "otpauth://totp/%s@%s?secret=%s";
        return String.format(format, user, host, secret);
    }

    private boolean check_code(String secret, long code, long timeMsec) {
        Base32 codec = new Base32();
        byte[] decodedKey = codec.decode(secret);

        long t = (timeMsec / 1000L) / 30L;

        for (int i = -window_size; i <= window_size; ++i) {
            long hash;
            try {
                hash = verify_code(decodedKey, t + i);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
            if (hash == code) {
                return true;
            }
        }
        return false;
    }

    private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        long value = t;

        for (int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte) value;
        }

        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);
        int offset = hash[20 - 1] & 0xF;
        long truncatedHash = 0;

        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            truncatedHash |= (hash[offset + i] & 0xFF);
        }

        truncatedHash &= 0x7FFFFFFF;
        truncatedHash %= 1000000;
        return (int) truncatedHash;
    }

}
