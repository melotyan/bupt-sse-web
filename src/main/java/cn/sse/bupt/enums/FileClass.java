package cn.sse.bupt.enums;

/**
 * Created by melot on 2016/4/7.
 */
public enum FileClass {
    NOTICE(1, "公告"), ATTRACTIVE_TENDER(2, "招标"), TENDER(3, "竞标"),
    LOWS(4, "法规"), POLICY(5, "政策");

    private int value;
    private String description;

    private FileClass(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
