package com.xja.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author GodLamp
 * @date 2022/8/8 9:45
 */
@Getter
@ToString
public class Page {
    //当前索引
    private int pageIndex;
    //前一个索引
    private int preIndex;
    //后一个索引
    private  int nextIndex;
    //总页数
    private int pageCount;

    //每页的记录数
    public static final int PAGE_NUMBER = 4;

    private Page() {
    }

    /**
     * 唯一对外的构造函数
     * @param allData 总数据数
     * @param pageIndex 当前索引
     */
    public Page(int allData, int pageIndex) {
        this.pageIndex = pageIndex;
        this.preIndex = (pageIndex > 1 ? (pageIndex - 1) : 1);
        this.nextIndex = (pageIndex < allData ? (pageIndex + 1) : allData);
        this.pageCount = (int)Math.ceil( allData * 1.0 / PAGE_NUMBER);
    }
}
