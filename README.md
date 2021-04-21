
二维码生成工具
                                  
   作者:颜金宽
                                  
   021-04-21

需要用到的依赖

`````
<!--二维码生成工具 https://mvnrepository.com/artifact/com.google.zxing/core -->

 <dependency>
     <groupId>com.google.zxing</groupId>
     <artifactId>core</artifactId>
     <version>3.4.1</version>
 </dependency>

`````

使用方法:
`````
MyQrcode myQrcode = new MyQrcode();
String qrcode = myQrcode.getQrcode("123456789", 200, 200); //内容,宽 高
System.out.println(qrcode);

`````

````
java 11

jdk 15.0.2


````