package others;

import com.rose.common.util.IdUtil;

import java.nio.charset.Charset;

/**
 * 功能：与运算加密、解密 demo
 * @author sunpeng
 * @date 2018
 */
public class EncryTestDemo {

    private static final String key0 = IdUtil.getID();
    private static final Charset charset = Charset.forName("UTF-8");
    private static byte[] keyBytes = key0.getBytes(charset);

    public static String encode(String enc){
        byte[] b = enc.getBytes(charset);
        for(int i=0, size=b.length; i < size; i++){
            for(byte keyBytes0 : keyBytes){
                b[i] = (byte) (b[i]^keyBytes0);
            }
        }
        return new String(b);
    }

    public static String decode(String dec){
        byte[] e = dec.getBytes(charset);
        byte[] dee = e;
        for(int i=0, size=e.length; i < size; i++){
            for(byte keyBytes0 : keyBytes){
                e[i] = (byte) (dee[i]^keyBytes0);
            }
        }
        return new String(e);
    }

    public static void main(String[] args) {
        for (int x = 0; x<10 ;x++) {
            String s = IdUtil.getID();
            String enc = encode(s);
            String dec = decode(enc);
            System.out.println(enc);
            System.out.println(dec);
        }

        System.out.println(decode("1f35d66?777030d?f771d7261e?6>4fa"));
    }
}