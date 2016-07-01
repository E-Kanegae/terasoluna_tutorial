/**
 * @(#)ValidationEnum.java
 * 
 */
package todo5.app.dynamic;

/**
 * [このクラスの説明を書きましょう]
 * @author E-Kanegae
 */
public enum ValidationEnum {
    NotNull(0), // NotNullチェック
    DateFormat(1) // 日付フォーマットチェック
    ;

    private final int num;

    private ValidationEnum(final int num) {
        this.num = num;
    }

    public int getNum() {
        return this.num;
    }
}
