/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import org.springframework.web.multipart.MultipartFile;

/**
 * <P><B>Description: </B> 上传文件的数据模型 </P>
 * Revision Trail: (Date/Author/Description)
 *
 * @author Nick Lv
 * @version 1.0
 * @created 2017 /03/01 10:47:07
 */
public class UploadFile {
    /**
     * id
     */
    private String id;
    /**
     * file
     */
    private MultipartFile file;

    /**
     * 描述 Gets file.
     *
     * @return the file
     * @author Nick Lv
     * @created 2017 /03/01 10:47:07
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * 描述 Sets file.
     *
     * @param file the file
     * @author Nick Lv
     * @created 2017 /03/01 10:47:07
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    /**
     * 描述 Gets id.
     *
     * @return the id
     * @author Nick Lv
     * @created 2017 /03/01 10:47:07
     */
    public String getId() {
        return id;
    }

    /**
     * 描述 Sets id.
     *
     * @param id the id
     * @author Nick Lv
     * @created 2017 /03/01 10:47:07
     */
    public void setId(String id) {
        this.id = id;
    }

}
