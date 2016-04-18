package cn.sse.bupt.enums;

/**
 * Created by melot on 2016/4/7.
 */
public enum FileClassEnum {
    NEWS(0, "新闻"), NOTICE(1, "公告"), ATTRACTIVE_TENDER(2, "招标"), TENDER(3, "竞标"),
    LOWS(4, "法规"), POLICY(5, "政策"), REPORT(6, "政府工作报告");

    private int value;
    private String description;

    private FileClassEnum(int value, String description) {
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
