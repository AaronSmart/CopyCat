/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
* 描述 The type Image util.
*
* @author Nick Lv
* @created 2016年7月24日 10:08:01
*/
public class ImageUtil {

/**
 * The Log.
 */
private Logger log = LoggerFactory.getLogger(getClass());
/**
 * The constant DEFAULT_PREVFIX.
 */
private final static String DEFAULT_PREVFIX = "thumb_";

/**
 * The constant DEFAULT_FORCE.
 */
private final static Boolean DEFAULT_FORCE = false;

/**
 * <p>Title: thumbnailImage</p>
 * <p>Description: 根据图片路径生成缩略图 </p>
 *
 * @param imgFile 原图片路径
 * @param w       缩略图宽
 * @param h       缩略图高
 * @param prevfix 生成缩略图的前缀
 * @param force   是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
 * @return the string
 * @author Nick Lv
 * @created 2017 /03/01 10:43:13 Thumbnail image string.
 */
public String thumbnailImage(File imgFile, int w, int h, String prevfix, boolean force) {
    if (imgFile.exists()) {
        try {
            // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
            String types = Arrays.toString(ImageIO.getReaderFormatNames());
            String suffix = null;
            // 获取图片后缀
            if (imgFile.getName().indexOf(".") > -1) {
                suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
            } // 类型和图片后缀全部小写，然后判断后缀是否合法
            if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0) {
                log.error("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
                return "";
            }
            log.debug("target image's size, width:{}, height:{}.", w, h);
            Image img = ImageIO.read(imgFile);
            if (!force) {
                // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
                int width = img.getWidth(null);
                int height = img.getHeight(null);
                if ((width * 1.0) / w < (height * 1.0) / h) {
                    if (width > w) {
                        h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w / (width * 1.0)));
                        log.debug("change image's height, width:{}, height:{}.", w, h);
                    }
                } else {
                    if (height > h) {
                        w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h / (height * 1.0)));
                        log.debug("change image's width, width:{}, height:{}.", w, h);
                    }
                }
            }
            BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.getGraphics();
            g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
            g.dispose();
            String p = imgFile.getPath();
            // 将图片保存在原目录并加上前缀
            String nameurl = p.substring(0, p.lastIndexOf(File.separator)) + File.separator + prevfix
                    + imgFile.getName();
            ImageIO.write(bi, suffix, new File(nameurl));
            return nameurl;
        } catch (IOException e) {
            log.error("generate thumbnail image failed.", e);
        }
    } else {
        log.warn("the image is not exist.");
    }
    return "";
}

/**
 * 描述 create.
 *
 * @param imagePath the image path
 * @param w         the w
 * @param h         the h
 * @param prevfix   the prevfix
 * @param force     the force
 * @return the string
 * @author Nick Lv
 * @created 2017 /03/01 10:43:13 Thumbnail image string.
 */
public String thumbnailImage(String imagePath, int w, int h, String prevfix, boolean force) {
    File imgFile = new File(imagePath);
    return thumbnailImage(imgFile, w, h, prevfix, force);
}

/**
 * 描述 create.
 *
 * @param imagePath the image path
 * @param w         the w
 * @param h         the h
 * @param force     the force
 * @return the string
 * @author Nick Lv
 * @created 2017 /03/01 10:43:13 Thumbnail image string.
 */
public String thumbnailImage(String imagePath, int w, int h, boolean force) {
    return thumbnailImage(imagePath, w, h, DEFAULT_PREVFIX, force);
}

/**
 * 描述 create.
 *
 * @param imagePath the image path
 * @param w         the w
 * @param h         the h
 * @return the string
 * @author Nick Lv
 * @created 2017 /03/01 10:43:13 Thumbnail image string.
 */
public String thumbnailImage(String imagePath, int w, int h) {
    return thumbnailImage(imagePath, w, h, DEFAULT_FORCE);
}

//    /**
//     * 描述 The entry point of application.
//     *
//     * @param args the input arguments
//     * @author Nick Lv
//     * @created 2017 /03/01 10:43:13
//     */
//    public static void main(String[] args) {
//        new ImageUtil().thumbnailImage(
//                "E://Workspaces//jjzdjcj//WebRoot//" + "files//upload/fkxctp/d5adfa2cf21f4903afe08776144f5f66.jpg",
//                100, 150);
//    }

}