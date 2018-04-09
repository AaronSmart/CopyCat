/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.util;

/**
 * Token为空异常
 * @author Iverson Cai  
 * @created 2017 /08/15 09:44:34
 */
public class TokenEmptyException extends EveException {

    public TokenEmptyException() {
        super("10006", "TOKEN为空，请重新登陆");
    }

}
