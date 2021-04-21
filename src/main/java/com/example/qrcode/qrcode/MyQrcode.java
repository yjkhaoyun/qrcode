package com.example.qrcode.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Hashtable;

public class MyQrcode {
    private  final int black = 0xFF000000;
    private  final int white  = 0xFFFFFFFF;

    private  BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage p_w_picpath = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                p_w_picpath.setRGB(x, y, matrix.get(x, y) ? black : white);
            }
        }
        return p_w_picpath;
    }


    private  void writeToFile(BitMatrix matrix, String format, File file)
            throws IOException {
        BufferedImage p_w_picpath = toBufferedImage(matrix);

        ImageIO.write(p_w_picpath, format, file);
    }

    public  void createQRImage(String content, int width, int height, String path, String fileName) throws Exception {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        if (!path.equals(null)) {
            if (!path.endsWith("/")) {
                path = path + "/";
            }
        } else {
            path = "";
        }
        String suffix = "jpg";
        if (fileName.indexOf(".") <= -1) {
            fileName = fileName + "." + suffix;
        } else {
            suffix = fileName.split("[.]")[1];
        }
        fileName = path + fileName;
        File file = new File(fileName);
        writeToFile(bitMatrix, suffix, file);
    }

    /*生成二维码的内容以及宽 高*/
    private  BufferedImage createQRImageBuffer(String content, int width, int height) throws  Exception{
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage p_w_picpath = toBufferedImage(bitMatrix);
        return p_w_picpath;
    }


    /*将图片字节流转为base64编码*/
    private String getBase64(BufferedImage bufferedImage) throws IOException {
       // BufferedImage qrImageBuffer = QRGenUtils.createQRImageBuffer(content, 200, 200);
        ByteArrayOutputStream OutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", OutputStream);
        //ImageIO.createImageOutputStream(OutputStream);
        byte[] bytes = OutputStream.toByteArray();
        Base64.Encoder encoder = Base64.getEncoder();
        String pngBase64 = encoder.encodeToString(bytes);
        pngBase64.replaceAll("\n","").replaceAll("\r","");//删除"\n"和"\r"
        pngBase64="data:image/png;base64,"+pngBase64;
        return pngBase64;
    }


    public String getQrcode(String content, int width, int height) throws Exception {
        BufferedImage qrImageBuffer = this.createQRImageBuffer(content, width, height);
        return this.getBase64(qrImageBuffer);
    }
}
