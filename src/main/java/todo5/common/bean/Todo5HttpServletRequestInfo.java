/**
 * @(#)Todo5HttpServletRequestInfo.java
 * 
 * Copyright (c) 2017 E-Kanegae.
 */
package todo5.common.bean;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * [このクラスの説明を書きましょう]
 * @author E-Kanegae
 * @version $Revision$
 */
public class Todo5HttpServletRequestInfo {

    private static HttpServletRequest req = 
            ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    
    public static String getScheme(){
        return req.getScheme();
    }
    
    public static String getServerName(){     
        return ((String)req.getHeaders("HOST").nextElement());
    }
    
    public static String getRequestURL(){
        StringBuilder strb = new StringBuilder();
        strb.append(getScheme());
        strb.append("://");
        strb.append(getServerName());
        return strb.toString();
    }

}
