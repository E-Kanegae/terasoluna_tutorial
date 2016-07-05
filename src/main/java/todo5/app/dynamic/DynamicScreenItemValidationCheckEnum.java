/**
 * @(#)DynamicScreenItemValidationCheckEnum.java
 * 
 */
package todo5.app.dynamic;

/**
 * バリデーションチェックの列挙型クラス
 * @author E-Kanegae
 * @version $Revision$
 */
public enum DynamicScreenItemValidationCheckEnum {
    NotNullCheck("1"),
    StringLengthCheck("N"),
    AlphabetCheck("1"),
    NumericCheck("2"),
    NumericAndAlphabetCheck("3"),
    IntegerRangeCheck("3"),
    MinValueCheck("1"),
    MaxValueCheck("2");
    
    private String valStr;
    
    private DynamicScreenItemValidationCheckEnum(String valStr){
        this.valStr = valStr;
    }
    
    public String getValue(){
        return valStr;
    }
    
}
