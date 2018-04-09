/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 封装各种格式的编码解码工具类. 1.Commons-Codec的 hex/base64 编码 2.自制的base62 编码
 * 3.Commons-Lang的xml/html escape 4.JDK提供的URLEncoder
 *
 * @author Nick Lv
 * @version 2013 -01-15
 * @created 2017 /03/01 10:42:04
 */
public class Encodes {
    /**
     *日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Encodes.class);


    /**
     * The constant DEFAULT_URL_ENCODING.
     */
    private static final String DEFAULT_URL_ENCODING = "UTF-8";
    /**
     * The constant BASE62.
     */
    private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    /**
     * Hex编码.
     *
     * @param input
     *            the input
     * @return the string
     * @author Nick Lv
     * @created 2017 /03/01 10:42:04 Encode hex string.
     */
    public static String encodeHex(byte[] input) {
        return new String(Hex.encodeHex(input));
    }

    /**
     * Hex解码.
     *
     * @param input
     *            the input
     * @return the byte [ ]
     * @author Nick Lv
     * @created 2017 /03/01 10:42:04 Decode hex byte [ ].
     */
    public static byte[] decodeHex(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * Base64编码.
     *
     * @param input
     *            the input
     * @return the string
     * @author Nick Lv
     * @created 2017 /03/01 10:42:04 Encode base 64 string.
     */
    public static String encodeBase64(byte[] input) {
        String str=null;
        try {
            str= new String(Base64.encodeBase64(input),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
        return str;


        //return new String(Base64.encodeBase64(input),"UTF-8");
    }

    /**
     * Base64编码.
     *
     * @param input
     *            the input
     * @return the string
     * @author Nick Lv
     * @created 2017 /03/01 10:42:04 Encode base 64 string.
     */
    public static String encodeBase64(String input) {
        try {
            return new String(Base64.encodeBase64(input.getBytes(DEFAULT_URL_ENCODING)),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    // /**
    // * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
    // */
    // public static String encodeUrlSafeBase64(byte[] input) {
    // return Base64.encodeBase64URLSafe(input);
    // }

    /**
     * Base64解码.
     *
     * @param input
     *            the input
     * @return the byte [ ]
     * @author Nick Lv
     * @created 2017 /03/01 10:42:04 Decode base 64 byte [ ].
     */
    public static byte[] decodeBase64(String input) {
        byte[] bt=null;
        try {
            bt= Base64.decodeBase64(input.getBytes(DEFAULT_URL_ENCODING));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("编码转换出错",e);
        }
        return bt;
    }

    /**
     * Base64解码.
     *
     * @param input
     *            the input
     * @return the string
     * @author Nick Lv
     * @created 2017 /03/01 10:42:04 Decode base 64 string string.
     */
    public static String decodeBase64String(String input) {
        try {
            return new String(Base64.decodeBase64(input.getBytes(DEFAULT_URL_ENCODING)), DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * Base62编码。
     *
     * @param input
     *            the input
     * @return the string
     * @author Nick Lv
     * @created 2017 /03/01 10:42:04 Encode base 62 string.
     */
    public static String encodeBase62(byte[] input) {
        char[] chars = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
        }
        return new String(chars);
    }

    /**
     * Html 转码.
     *
     * @param html
     *            the html
     * @return the string
     * @author Nick Lv
     * @created 2017 /03/01 10:42:04 Escape html string.
     */
    public static String escapeHtml(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }

    /**
     * Html 解码.
     *
     * @param htmlEscaped
     *            the html escaped
     * @return the string
     * @author Nick Lv
     * @created 2017 /03/01 10:42:04 Unescape html string.
     */
    public static String unescapeHtml(String htmlEscaped) {
        return StringEscapeUtils.unescapeHtml4(htmlEscaped);
    }

    /**
     * Xml 转码.
     *
     * @param xml
     *            the xml
     * @return the string
     * @author Nick Lv
     * @created 2017 /03/01 10:42:04 Escape xml string.
     */
    public static String escapeXml(String xml) {
        return StringEscapeUtils.escapeXml10(xml);
    }

    /**
     * Xml 解码.
     *
     * @param xmlEscaped
     *            the xml escaped
     * @return the string
     * @author Nick Lv
     * @created 2017 /03/01 10:42:04 Unescape xml string.
     */
    public static String unescapeXml(String xmlEscaped) {
        return StringEscapeUtils.unescapeXml(xmlEscaped);
    }

    /**
     * URL 编码, Encode默认为UTF-8.
     *
     * @param part
     *            the part
     * @return the string
     * @author Nick Lv
     * @created 2017 /03/01 10:42:04 Url encode string.
     */
    public static String urlEncode(String part) {
        try {
            return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * URL 解码, Encode默认为UTF-8.
     *
     * @param part
     *            the part
     * @return the string
     * @author Nick Lv
     * @created 2017 /03/01 10:42:04 Url decode string.
     */
    public static String urlDecode(String part) {

        try {
            return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw Exceptions.unchecked(e);
        }
    }
}
