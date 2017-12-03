package com.whu.vo;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {
    private List<T> productList = new ArrayList<>();
    private int totalPage;
    private int currentPage;
    private int totalCount;
    private int currentCount;

    public int getTotalPage() {
        return totalPage;
    }

    public List<T> getProductList() {
        return productList;
    }

    public void setProductList(List<T> productList) {
        this.productList = productList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
