
package todo5.common.taglib.datetime;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.resources.Resources;
import org.apache.taglibs.standard.tag.common.core.Util;

public abstract class FormatLocalDateTimeSupport extends TagSupport {

    // *********************************************************************
    // Protected state

    protected LocalDateTime value; // 'value' attribute
    protected String type; // 'type' attribute
    protected String pattern; // 'pattern' attribute
    protected Object zoneId; // 'timeZone' attribute
    protected String dateStyle; // 'dateStyle' attribute
    protected String timeStyle; // 'timeStyle' attribute

    // *********************************************************************
    // Private state

    private String var; // 'var' attribute
    private int scope; // 'scope' attribute

    // *********************************************************************
    // Constructor and initialization

    public FormatLocalDateTimeSupport() {
        super();
        init();
    }

    private void init() {
        type = dateStyle = timeStyle = null;
        pattern = var = null;
        value = null;
        zoneId = null;
        scope = PageContext.PAGE_SCOPE;
    }

    // *********************************************************************
    // Tag attributes known at translation time

    public void setVar(String var) {
        this.var = var;
    }

    public void setScope(String scope) {
        this.scope = Util.getScope(scope);
    }

    // *********************************************************************
    // Tag logic

    /*
     * Formats the given date and time.
     */

    @Override
    public int doEndTag() throws JspException {

        String formatted = null;

        if (value == null) {
            if (var != null) {
                pageContext.removeAttribute(var, scope);
            }
            return EVAL_PAGE;
        }

        // Create formatter

        //
        // Locale取得処理は未実装
        //
        Locale locale = Locale.getDefault();

        if (locale != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern,locale);

            // Set time zone
            ZoneId tz = null;
            if ((zoneId instanceof String)
                    && ((String) zoneId).equals("")) {
                zoneId = null;
            }
            if (zoneId != null) {
                if (zoneId instanceof String) {
                    tz = ZoneId.of((String) zoneId);
                } else if (zoneId instanceof ZoneId) {
                    tz = ZoneId.of(zoneId.toString());
                } else {
                    throw new JspTagException(
                            Resources.getMessage("FORMAT_DATE_BAD_TIMEZONE"));
                }
            } else {
                tz = ZoneId.systemDefault();
            }
            if (tz != null) {
                formatter = formatter.withZone(tz);
            }

            formatted = formatter.format(value);

        } else {
            // no formatting locale available, use Date.toString()
            formatted = value.toString();
        }

        if (var != null) {
            pageContext.setAttribute(var, formatted, scope);
        } else {
            try {
                pageContext.getOut().print(formatted);
            } catch (IOException ioe) {
                throw new JspTagException(ioe.toString(), ioe);
            }
        }

        return EVAL_PAGE;
    }

    // Releases any resources we may have (or inherit)

    @Override
    public void release() {
        init();
    }
}
