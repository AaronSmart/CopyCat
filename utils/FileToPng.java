package com.kikenn.tool;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 *  文件和图片映射关系 by copycat 2024-09-07 特殊功能类
 *    <dependencies>
 *         <dependency>
 *             <groupId>commons-io</groupId>
 *             <artifactId>commons-io</artifactId>
 *             <version>2.11.0</version>
 *         </dependency>
 *         <dependency>
 *             <groupId>commons-codec</groupId>
 *             <artifactId>commons-codec</artifactId>
 *             <version>1.15</version>
 *         </dependency>
 *     </dependencies>
 *
 */

public class FileToPng {

    // 将文件转换为PNG图片
    public static void fileToPng(String inputFilePath, String outputPngPath) throws IOException {
        File inputFile = new File(inputFilePath);
        FileInputStream fis = new FileInputStream(inputFile);

        // 读取文件内容到字节数组中
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        while ((read = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        fis.close();
        byte[] fileBytes = baos.toByteArray();
        //一个像素可以存三个字节的数据
        int width = (int) Math.ceil(Math.sqrt(fileBytes.length / 3.0));
        int height = width;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        int byteIndex = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int r = (byteIndex < fileBytes.length) ? fileBytes[byteIndex++] & 0xFF : 0;
                int g = (byteIndex < fileBytes.length) ? fileBytes[byteIndex++] & 0xFF : 0;
                int b = (byteIndex < fileBytes.length) ? fileBytes[byteIndex++] & 0xFF : 0;
                int color = (r << 16) | (g << 8) | b | (255 << 24);  // 透明度设为不透明
                bufferedImage.setRGB(x, y, color);
            }
        }

        ImageIO.write(bufferedImage, "png", new File(outputPngPath));
    }

    // 从PNG图片还原文件
    public static void pngToFile(String inputPngPath, String outputFilePath) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(inputPngPath));

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int color = bufferedImage.getRGB(x, y);
                byte r = (byte) ((color >> 16) & 0xFF);
                byte g = (byte) ((color >> 8) & 0xFF);
                byte b = (byte) (color & 0xFF);
                if (byteArrayOutputStream.size() < (width * height * 3)) {
                    byteArrayOutputStream.write(r);
                    byteArrayOutputStream.write(g);
                    byteArrayOutputStream.write(b);
                }
            }
        }

        FileOutputStream fos = new FileOutputStream(new File(outputFilePath));
        byteArrayOutputStream.writeTo(fos);
        fos.close();
    }

    public static void main(String[] args) {
        try {
            //fileToPng("C:\\Users\\Administrator\\Desktop\\hello.java", "hello.png");
            pngToFile("hello.png", "hello.java");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


