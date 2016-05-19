/**
 * @(#)Todo5TableEnum.java
 * 
 * Copyright (c) 2017 E-Kanegae.
 */
package todo5.domain.constants;

/**
 * 本APでアクセスするテーブル名（DBunit時に、Excelファイル読み込みで使用）
 * @author E-Kanegae
 * @version $Revision$
 */
public enum Todo5TableEnum {
    
    TODO(1,"todo") 
    ,TODO_FILE(2,"todo_file")
    ,CATEGORY(3,"category")
    ,ACCOUNT(4,"account")
    ;
    
    private int num;
    private String tableName;
    
    /*
     * コンストラクタ
     */
    private Todo5TableEnum (int num, String tableName){
        this.num = num;
        this.tableName = tableName;
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
    public String getTableName(){
        return this.tableName;
    }
}
