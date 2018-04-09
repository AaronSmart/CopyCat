/*
* Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
* EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
*/
package net.evecom.gsmp.core.utils;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件数组
 *
 * @author Nick Lv
 * @created 2017 /03/01 10:43:46
 */
public class MultiUploadFile {
    /**
     * The Id.
     */
    private String id;

    /**
     * The File.
     */
    private MultipartFile[] file;

    /**
     * 描述 create.
     *
     * @return the multipart file [ ]
     * @author Nick Lv
     * @created 2017 /03/01 10:43:46 Get file multipart file [ ].
     */
    public MultipartFile[] getFile() {
        return file;
    }

    /**
     * 描述 Sets file.
     *
     * @param file the file
     * @author Nick Lv
     * @created 2017 /03/01 10:43:46
     */
    public void setFile(MultipartFile[] file) {
         this.file = file;
    }

    /**
     * 描述 Gets id.
     *
     * @return the id
     * @author Nick Lv
     * @created 2017 /03/01 10:43:46
     */
    public String getId() {
        return id;
    }

    /**
     * 描述 Sets id.
     *
     * @param id the id
     * @author Nick Lv
     * @created 2017 /03/01 10:43:46
     */
    public void setId(String id) {
        this.id = id;
    }

}
