package todo5.common.taglib.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.jsp.JspException;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

public class FormatLocalDateTimeTag extends FormatLocalDateTimeSupport {

    private String value_;                       // stores EL-based property
    private String type_;                        // stores EL-based property
    private String dateStyle_;                 // stores EL-based property
    private String timeStyle_;                 // stores EL-based property
    private String pattern_;                 // stores EL-based property
    private String zoneId_;                 // stores EL-based property

    public FormatLocalDateTimeTag() {
        super();
        init();
    }
    
    public int doStartTag() throws JspException {

        // evaluate any expressions we were passed, once per invocation
        evaluateExpressions();

        // chain to the parent implementation
        return super.doStartTag();
    }

    public void release() {
        super.release();
        init();
    }
    
    public void setValue(String value_) {
        this.value_ = value_;
    }

    // for EL-based attribute

    public void setType(String type_) {
        this.type_ = type_;
    }

    // for EL-based attribute

    public void setDateStyle(String dateStyle_) {
        this.dateStyle_ = dateStyle_;
    }

    // for EL-based attribute

    public void setTimeStyle(String timeStyle_) {
        this.timeStyle_ = timeStyle_;
    }

    // for EL-based attribute

    public void setPattern(String pattern_) {
        this.pattern_ = pattern_;
    }

    // for EL-based attribute

    public void setTimeZone(String zoneId_) {
        this.zoneId_ = zoneId_;
    }
    
    //*********************************************************************
    // Private (utility) methods

    // (re)initializes state (during release() or construction)

    private void init() {
        // null implies "no expression"
        value_ = type_ = dateStyle_ = timeStyle_ = pattern_ = zoneId_ = null;
    }

    // Evaluates expressions as necessary

    private void evaluateExpressions() throws JspException {
        /* 
         * Note: we don't check for type mismatches here; we assume
         * the expression evaluator will return the expected type
         * (by virtue of knowledge we give it about what that type is).
         * A ClassCastException here is truly unexpected, so we let it
         * propagate up.
         */

        // 'value' attribute (mandatory)
        value = (LocalDateTime) ExpressionEvaluatorManager.evaluate(
                "value", value_, LocalDate.class, this, pageContext);

        // 'type' attribute
        if (type_ != null) {
            type = (String) ExpressionEvaluatorManager.evaluate(
                    "type", type_, String.class, this, pageContext);
        }

        // 'dateStyle' attribute
        if (dateStyle_ != null) {
            dateStyle = (String) ExpressionEvaluatorManager.evaluate(
                    "dateStyle", dateStyle_, String.class, this, pageContext);
        }

        // 'timeStyle' attribute
        if (timeStyle_ != null) {
            timeStyle = (String) ExpressionEvaluatorManager.evaluate(
                    "timeStyle", timeStyle_, String.class, this, pageContext);
        }

        // 'pattern' attribute
        if (pattern_ != null) {
            pattern = (String) ExpressionEvaluatorManager.evaluate(
                    "pattern", pattern_, String.class, this, pageContext);
        }

        // 'timeZone' attribute
        if (zoneId_ != null) {
            zoneId = ExpressionEvaluatorManager.evaluate(
                    "zoneId", zoneId_, Object.class, this, pageContext);
        }
    }
}
