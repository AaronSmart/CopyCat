/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.gidcms.common.controller;

import net.evecom.gsmp.gidcms.common.service.CommonCodeService;
import net.evecom.gsmp.gidcms.common.vo.VCommCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Objects;

/**
 * 码表控制层
 *
 * @author Nick Lv
 * @created 2017 /7/6 20:19
 */
@Controller
@ResponseBody
@RequestMapping("/common")
public class CommonController {
    /**
     * 码表服务层
     */
    @Autowired
    CommonCodeService commonCodeService;

    /**
     * 附件上传路径
     */
    @Value("#{configProperties.UPLOAD_PATH}")
    private String rootPath;
    /**
     * 日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

    /**
     * 根据表名获取码表
     *
     * @param tableName the table name
     * @return the codes by table name
     * @author Nick Lv
     * @created 2017 /7/6 20:19
     */
    @RequestMapping("/getCodes")
    public List<VCommCode> getCodesByTableName(@RequestParam("type") String tableName) {
        return commonCodeService.getCodesByTableName(tableName);
    }

    /**
     * 图片下载接口
     *
     * @param request   the request
     * @param response  the response
     * @param imageName the image name
     * @author Nick Lv
     * @created 2017 /02/16 15:16:25 Download file.
     */
    @RequestMapping("/image")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam("imageName") String imageName) {
        if (Objects.equals(imageName, "null")) {
            imageName = "user.jpg";
        }
        if (StringUtils.isNotEmpty(imageName)) {
            OutputStream out = null;
            InputStream fis = null;
            try {
                response.reset();// 清空输出流
                response.setHeader("Content-disposition",
                        "attachment; filename=" +
                                new String(imageName.getBytes("utf-8"), "ISO-8859-1"));// 设定输出文件头
                response.setContentType("multipart/form-data");// 定义输出类型
                out = response.getOutputStream();
                // 以流的形式下载文件。
                String srcPath = null;
                srcPath = rootPath + imageName;
                fis = new BufferedInputStream(new FileInputStream(rootPath + imageName));
                //返回文件的大小
                File calcFile = new File(srcPath);
                long length = calcFile.length();
                response.setContentLength((int) length);
                byte[] buffer = new byte[fis.available()];
                // 缓冲区大小1024
                byte[] s = new byte[10240];
                int len = 0;
                //避免最后一次读取数据时，不满10240b的数据被填充，造成数据不准确性
                while ((len = fis.read(s)) != -1) {
                    out.write(s, 0, len);
                }
                fis.read(buffer);
                out.write(buffer);
                response.flushBuffer();
            } catch (Exception e) {
                LOGGER.error("文件下载出现异常！,参数为：" + "imageName:" + imageName, e);
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (Exception e) {
                        LOGGER.error("文件输出流关闭异常！,参数为：" + "imageName:" + imageName, e);
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (Exception e) {
                        LOGGER.error("文件输出流关闭异常！,参数为：" + "imageName:" + imageName, e);
                    }
                }
            }
        }
    }
}
