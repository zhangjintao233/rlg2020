package com.itdr.pojo.vo;

import java.math.BigDecimal;
import java.util.List;

public class GwcVo {

    private List<GwcProductVo> gwcProductVoList;

        private Boolean allChecked;

    private BigDecimal carTotalPrice;

    public List<GwcProductVo> getGwcProductVoList() {
        return gwcProductVoList;
    }

    public void setGwcProductVoList(List<GwcProductVo> gwcProductVoList) {
        this.gwcProductVoList = gwcProductVoList;
    }

    public boolean isAllChecked() {
        return allChecked;
    }

    public void setAllChecked(Boolean allChecked) {
        this.allChecked = allChecked;
    }

    public BigDecimal getCarTotalPrice() {
        return carTotalPrice;
    }

    public void setCarTotalPrice(BigDecimal carTotalPrice) {
        this.carTotalPrice = carTotalPrice;
    }
}
