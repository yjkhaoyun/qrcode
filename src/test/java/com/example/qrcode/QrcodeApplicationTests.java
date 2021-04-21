package com.example.qrcode;

import com.example.qrcode.qrcode.MyQrcode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QrcodeApplicationTests {

    @Test
    void contextLoads() {
        try {
            MyQrcode myQrcode = new MyQrcode();
            String qrcode = myQrcode.getQrcode("123456789", 200, 200);
            System.out.println(qrcode);
        }catch (Exception e){}

    }

}
