/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package net.evecom.gsmp.gidcms.common.util;

/**
 * Token无效异常
 * @author Iverson Cai 
 * @created 2017 /08/15 09:45:35
 */
public class TokenException extends EveException {

    public TokenException() {
        super("10001", "TOKEN无效");
    }
}
