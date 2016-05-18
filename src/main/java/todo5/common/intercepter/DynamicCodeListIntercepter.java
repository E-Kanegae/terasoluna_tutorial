
package todo5.common.intercepter;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.terasoluna.gfw.common.codelist.CodeList;
import org.terasoluna.gfw.common.codelist.i18n.I18nCodeList;
import org.terasoluna.gfw.web.codelist.CodeListInterceptor;

public class DynamicCodeListIntercepter extends HandlerInterceptorAdapter
                                                                implements
                                                                ApplicationContextAware,
                                                                InitializingBean {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory
            .getLogger(CodeListInterceptor.class);

    /**
     * list of {@link CodeList}
     */
    private Collection<CodeList> codeLists;

    /**
     * application context
     */
    private ApplicationContext applicationContext;

    /**
     * Pattern of Codelist IDs (Bean IDs) of codelists which are target to be
     * set to attribute of {@link HttpServletRequest}.
     */
    private Pattern codeListIdPattern;

    /**
     * the default locale to fall back.<br>
     * <p>
     * this locale is used if the requested locale is not found in i18nCodeList.
     * </p>
     */
    private Locale fallbackTo = Locale.getDefault();
    
    /**
     * 動的にクエリを変更する必要のあるリクエストパス
     * set to attribute of {@link HttpServletRequest}.
     */
    private String targetRequestPath;
    private String[] targetRequestPathList;

    /**
     * Sets codelist to the attribute of {@link HttpServletRequest}
     * <p>
     * Sets codelist to the attribute of {@link HttpServletRequest} before the
     * execution of Controller.
     * </p>
     * 
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object)
     * @since 5.0.1
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {

        if (codeLists == null) {
            return true;
        }

        Locale locale = RequestContextUtils.getLocale(request);
        logger.debug("locale for I18nCodelist is '{}'.", locale);

        for (CodeList codeList : codeLists) {
            String attributeName = codeList.getCodeListId();
            if (codeList instanceof I18nCodeList) {
                I18nCodeList i18nCodeList = (I18nCodeList) codeList;
                Map<String, String> codeListMap = getLocalizedCodeMap(
                        i18nCodeList, locale);
                request.setAttribute(attributeName, codeListMap);
            } else {
                request.setAttribute(attributeName, codeList.asMap());
            }
        }
        return true;
    }

    /*
     * 
     */
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView)
            throws Exception {
        targetRequestPathList = parseTargetRequestpathList(targetRequestPath);
        
        StringBuilder strb = new StringBuilder();
        strb.append(request.getRequestURI());
        
        int deleteStr = request.getContextPath().length();
        strb.delete(0,deleteStr - 1);
        String requestPath = strb.toString();

        for(int i=0;i<targetRequestPathList.length;i++){
            if(requestPath.equals(targetRequestPathList[i])){
               //対象のリクエストの場合、コードリストを作り直す。 
            }
        }
    }

    /**
     * Returns the map of codelists which match to the specified locale.
     * <p>
     * If the map of codelists which match to the specified locale does not
     * exist, returns the map of codelists which match<br>
     * with fallback locale. If the map corresponding to fallback locale also
     * does not exist, then log of WARN level is output<br>
     * and an empty map is returned.<br>
     * </p>
     * 
     * @param i18nCodeList International Codelist
     * @param requestLocale Locale of request
     * @return Map of codelists which match to the specified locale
     */
    protected Map<String, String> getLocalizedCodeMap(
            I18nCodeList i18nCodeList, Locale requestLocale) {
        Map<String, String> codeListMap = i18nCodeList.asMap(requestLocale);
        if (codeListMap.isEmpty()
                && (fallbackTo != null && !fallbackTo.equals(requestLocale))) {
            logger.debug("There is no mapping for '{}'. fall back to '{}'.",
                    requestLocale, fallbackTo);
            codeListMap = i18nCodeList.asMap(fallbackTo);
            if (codeListMap.isEmpty()) {
                logger.warn("could not fall back to '{}'.", fallbackTo);
            }
        }
        return codeListMap;
    }

    /**
     * Extracts the {@code CodeList}s which are to be set to the attribute of
     * {@link HttpServletRequest}
     * <p>
     * Among the Beans which implement {@code CodeList} interface, extract the
     * Codelist IDs(Bean IDs) which match<br>
     * with the regular expression specified in {@link #codeListIdPattern}.
     * </p>
     * 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() {

        Assert.notNull(applicationContext, "applicationContext is null.");

        if (codeListIdPattern == null) {
            this.codeListIdPattern = Pattern.compile(".+");
        }

        Map<String, CodeList> definedCodeLists = BeanFactoryUtils
                .beansOfTypeIncludingAncestors(applicationContext,
                        CodeList.class, false, false);
        Map<String, CodeList> targetCodeLists = new HashMap<String, CodeList>();
        for (CodeList codeList : definedCodeLists.values()) {
            String codeListId = codeList.getCodeListId();
            if (codeListId != null) {
                Matcher codeListIdMatcher = codeListIdPattern.matcher(codeListId);
                if (codeListIdMatcher.matches()) {
                    targetCodeLists.put(codeListId, codeList);
                }
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("registered codeList : {}", targetCodeLists.keySet());
        }

        this.codeLists = Collections.unmodifiableCollection(targetCodeLists
                .values());

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setCodeListIdPattern(Pattern codeListIdPattern) {
        this.codeListIdPattern = codeListIdPattern;
    }

    public void setFallbackTo(Locale fallbackTo) {
        this.fallbackTo = fallbackTo;
    }

    protected Collection<CodeList> getCodeLists() {
        return codeLists;
    }

    public String getTargetRequestPath() {
        return targetRequestPath;
    }

    public void setTargetRequestPath(String targetRequestPath) {
        this.targetRequestPath = targetRequestPath;
    }
    
    /*
     * 対象リクエストパスを文字列配列に変更して返却する。
     */
    public String[] parseTargetRequestpathList(String targetRequestPath) {
        return targetRequestPath.split(",", -1);
    }

}
