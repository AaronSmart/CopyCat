/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 描述 The type Stream utils.
 *
 * @author Nick Lv
 * @created 2017 /03/01 10:46:01
 * @mail Chenjunjun.ZJ @gmail.com
 */
public class StreamUtils {
    /**
     *日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtils.class);

    /**
     * The constant BUFFER_SIZE.
     */
    final static int BUFFER_SIZE = 4096;

    /**
     * 将InputStream转换成String
     *
     * @param in InputStream
     * @return String string
     * @throws Exception
     * @author Nick Lv
     * @created 2017 /03/01 10:46:02 Input stream to string string.
     */
    public static String inputStreamTOString(InputStream in) {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        String string = null;
        int count = 0;
        try {
            while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
                outStream.write(data, 0, count);
        } catch (IOException e) {
            LOGGER.error("字节数组输出流",e);
        }

        data = null;
        try {
            string = new String(outStream.toByteArray(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("不支持的编码异常",e);
        }
        return string;
    }

    /**
     * 将InputStream转换成某种字符编码的String
     *
     * @param in       the in
     * @param encoding the encoding
     * @return string
     * @throws Exception
     * @author Nick Lv
     * @created 2017 /03/01 10:46:02 Input stream to string string.
     */
    public static String inputStreamTOString(InputStream in, String encoding) {
        String string = null;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        try {
            while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
                outStream.write(data, 0, count);
        } catch (IOException e) {
            LOGGER.error("字节数组输出流",e);
        }

        data = null;
        try {
            string = new String(outStream.toByteArray(), encoding);
        } catch (UnsupportedEncodingException e) {
           LOGGER.error("不支持的编码异常",e);
        }
        return string;
    }

    /**
     * 将String转换成InputStream
     *
     * @param in the in
     * @return input stream
     * @throws Exception the exception
     * @author Nick Lv
     * @created 2017 /03/01 10:46:02 String to input stream input stream.
     */
    public static InputStream stringTOInputStream(String in) throws Exception {

        ByteArrayInputStream is = new ByteArrayInputStream(in.getBytes("UTF-8"));
        return is;
    }

    /**
     * 将String转换成InputStream
     *
     * @param in the in
     * @return byte [ ]
     * @throws Exception
     * @author Nick Lv
     * @created 2017 /03/01 10:46:02 String t obyte byte [ ].
     */
    public static byte[] stringTObyte(String in) {
        byte[] bytes = null;
        try {
            bytes = inputStreamTOByte(stringTOInputStream(in));
        } catch (IOException e) {
            LOGGER.error("输出流转为byte异常",e);
        } catch (Exception e) {
            LOGGER.error("字符串转为输入流异常",e);
        }
        return bytes;
    }

    /**
     * 将InputStream转换成byte数组
     *
     * @param in InputStream
     * @return byte[] byte [ ]
     * @throws IOException the io exception
     * @author Nick Lv
     * @created 2017 /03/01 10:46:02 Input stream to byte byte [ ].
     */
    public static byte[] inputStreamTOByte(InputStream in) throws IOException {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return outStream.toByteArray();
    }

    /**
     * 将byte数组转换成InputStream
     *
     * @param in the in
     * @return input stream
     * @throws Exception the exception
     * @author Nick Lv
     * @created 2017 /03/01 10:46:02 Byte to input stream input stream.
     */
    public static InputStream byteTOInputStream(byte[] in) throws Exception {

        ByteArrayInputStream is = new ByteArrayInputStream(in);
        return is;
    }

    /**
     * 将byte数组转换成String
     *
     * @param in the in
     * @return string
     * @throws Exception
     * @author Nick Lv
     * @created 2017 /03/01 10:46:02 Byte to string string.
     */
    public static String byteTOString(byte[] in) {

        String result = null;
        InputStream is = null;
        try {
            is = byteTOInputStream(in);
            result = inputStreamTOString(is, "UTF-8");
        } catch (Exception e) {
           LOGGER.error("转换异常",e);
        }
        return result;
    }

    /**
     * 将byte数组转换成String
     *
     * @param in the in
     * @return string
     * @throws Exception
     * @author Nick Lv
     * @created 2017 /03/01 10:46:02
     */
    public static String getString(String in) {

        String is = null;
        try {
            is = byteTOString(stringTObyte(in));
        } catch (Exception e) {
            LOGGER.error("转换异常",e);
        }
        return is;
    }

    /**
     * 描述 create.
     *
     * @param is the is
     * @return the byte [ ]
     * @throws IOException the io exception
     * @author Nick Lv
     * @created 2017 /03/01 10:46:02 Get bytes byte [ ].
     */
    // InputStream 转换成byte[]
    public byte[] getBytes(InputStream is) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[BUFFER_SIZE];
        int len = 0;

        while ((len = is.read(b, 0, BUFFER_SIZE)) != -1) {
            baos.write(b, 0, len);
        }

        baos.flush();

        byte[] bytes = baos.toByteArray();

        LOGGER.info(new String(bytes,"utf-8"));

        return bytes;
    }

    /**
     * 根据文件路径创建文件输入流处理
     * 以字节为单位（非 unicode ）
     *
     * @param filepath the filepath
     * @return file input stream
     * @author Nick Lv
     * @created 2017 /03/01 10:46:02
     */
    public static FileInputStream getFileInputStream(String filepath) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filepath);
        } catch (FileNotFoundException e) {
            LOGGER.error("错误信息:文件不存在",e);

        }
        return fileInputStream;
    }

    /**
     * 根据文件对象创建文件输入流处理
     * 以字节为单位（非 unicode ）
     *
     * @param file the file
     * @return file input stream
     * @author Nick Lv
     * @created 2017 /03/01 10:46:02
     */
    public static FileInputStream getFileInputStream(File file) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
           LOGGER.error("错误信息:文件不存在",e);

        }
        return fileInputStream;
    }

    /**
     * 根据文件对象创建文件输出流处理
     * 以字节为单位（非 unicode ）
     *
     * @param file   the file
     * @param append true:文件以追加方式打开,false:则覆盖原文件的内容
     * @return file output stream
     * @author Nick Lv
     * @created 2017 /03/01 10:46:02
     */
    public static FileOutputStream getFileOutputStream(File file, boolean append) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file, append);
        } catch (FileNotFoundException e) {
           LOGGER.error("错误信息:文件不存在",e);

        }
        return fileOutputStream;
    }

    /**
     * 根据文件路径创建文件输出流处理
     * 以字节为单位（非 unicode ）
     *
     * @param filepath the filepath
     * @param append   true:文件以追加方式打开,false:则覆盖原文件的内容
     * @return file output stream
     * @author Nick Lv
     * @created 2017 /03/01 10:46:02
     */
    public static FileOutputStream getFileOutputStream(String filepath, boolean append) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filepath, append);
        } catch (FileNotFoundException e) {
            LOGGER.error("错误信息:文件不存在",e);

        }
        return fileOutputStream;
    }

    /**
     * 描述 Gets file.
     *
     * @param filepath the filepath
     * @return the file
     * @author Nick Lv
     * @created 2017 /03/01 10:46:02
     */
    public static File getFile(String filepath) {
        return new File(filepath);
    }

    /**
     * 描述 Gets byte array output stream.
     *
     * @return the byte array output stream
     * @author Nick Lv
     * @created 2017 /03/01 10:46:02
     */
    public static ByteArrayOutputStream getByteArrayOutputStream() {
        return new ByteArrayOutputStream();
    }

}
