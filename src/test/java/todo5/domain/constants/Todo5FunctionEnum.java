/**
 * @(#)Todo5FunctionEnum.java
 * 
 * Copyright (c) 2017 E-Kanegae.
 */
package todo5.domain.constants;

/**
 * 本APの機能名（DBunit時に、Excelファイル読み込みで使用）
 * @author E-Kanegae
 * @version $Revision$
 */
public enum Todo5FunctionEnum {

    TODO(1,"todo") 
    ,COMMON(2,"common")
    ,MANAGE(3,"manage")
    ;
    
    private int num;
    private String funcName;
    
    /*
     * コンストラクタ
     */
    private Todo5FunctionEnum (int num, String funcName){
        this.num = num;
        this.funcName = funcName;
    }
    
    /*
     * 番号を取得する。
     */
    public int getNum(){
        return this.num;
    }
    
    /*
     * テーブル名（Excelファイルのシート名）を取得する。
     */
    public String getFuncName(){
        return this.funcName;
    }
}
