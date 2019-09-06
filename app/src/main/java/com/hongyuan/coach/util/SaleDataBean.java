package com.hongyuan.coach.util;

import java.util.List;

public class SaleDataBean {

    /**
     * status : 200
     * msg : null
     * debugMsg : null
     * data : {"shopSaleInfo":{"saleAmount":63571.52,"saleAmountSelf":0,"saleAmountMemberSelf":0,"saleAmountProductSelf":0,"saleAmountGroup":63571.52,"saleAmountMemberGroup":399.01,"saleAmountProductGroup":63172.51},"chartList":[{"time":"2019-03","amount":63564.71}]}
     */

    private int status;
    private Object msg;
    private Object debugMsg;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public Object getDebugMsg() {
        return debugMsg;
    }

    public void setDebugMsg(Object debugMsg) {
        this.debugMsg = debugMsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * shopSaleInfo : {"saleAmount":63571.52,"saleAmountSelf":0,"saleAmountMemberSelf":0,"saleAmountProductSelf":0,"saleAmountGroup":63571.52,"saleAmountMemberGroup":399.01,"saleAmountProductGroup":63172.51}
         * chartList : [{"time":"2019-03","amount":63564.71}]
         */

        private ShopSaleInfoBean shopSaleInfo;
        private List<ChartListBean> chartList;

        private List<RevertChartList> revertChartList;

        public ShopSaleInfoBean getShopSaleInfo() {
            return shopSaleInfo;
        }

        public void setShopSaleInfo(ShopSaleInfoBean shopSaleInfo) {
            this.shopSaleInfo = shopSaleInfo;
        }

        public List<ChartListBean> getChartList() {
            return chartList;
        }

        public void setChartList(List<ChartListBean> chartList) {
            this.chartList = chartList;
        }

        public List<RevertChartList> getRevertChartList() {
            return revertChartList;
        }

        public void setRevertChartList(List<RevertChartList> revertChartList) {
            this.revertChartList = revertChartList;
        }

        public static class ShopSaleInfoBean {
            /**
             * saleAmount : 63571.52
             * saleAmountSelf : 0
             * saleAmountMemberSelf : 0
             * saleAmountProductSelf : 0
             * saleAmountGroup : 63571.52
             * saleAmountMemberGroup : 399.01
             * saleAmountProductGroup : 63172.51
             */

            private double saleAmount;
            private double saleAmountSelf;
            private double saleAmountMemberSelf;
            private double saleAmountProductSelf;
            private double saleAmountGroup;
            private double saleAmountMemberGroup;
            private double saleAmountProductGroup;

            public double getSaleAmount() {
                return saleAmount;
            }

            public void setSaleAmount(double saleAmount) {
                this.saleAmount = saleAmount;
            }

            public double getSaleAmountSelf() {
                return saleAmountSelf;
            }

            public void setSaleAmountSelf(double saleAmountSelf) {
                this.saleAmountSelf = saleAmountSelf;
            }

            public double getSaleAmountMemberSelf() {
                return saleAmountMemberSelf;
            }

            public void setSaleAmountMemberSelf(double saleAmountMemberSelf) {
                this.saleAmountMemberSelf = saleAmountMemberSelf;
            }

            public double getSaleAmountProductSelf() {
                return saleAmountProductSelf;
            }

            public void setSaleAmountProductSelf(double saleAmountProductSelf) {
                this.saleAmountProductSelf = saleAmountProductSelf;
            }

            public double getSaleAmountGroup() {
                return saleAmountGroup;
            }

            public void setSaleAmountGroup(double saleAmountGroup) {
                this.saleAmountGroup = saleAmountGroup;
            }

            public double getSaleAmountMemberGroup() {
                return saleAmountMemberGroup;
            }

            public void setSaleAmountMemberGroup(double saleAmountMemberGroup) {
                this.saleAmountMemberGroup = saleAmountMemberGroup;
            }

            public double getSaleAmountProductGroup() {
                return saleAmountProductGroup;
            }

            public void setSaleAmountProductGroup(double saleAmountProductGroup) {
                this.saleAmountProductGroup = saleAmountProductGroup;
            }
        }

        public static class ChartListBean {
            /**
             * time : 2019-03
             * amount : 63564.71
             */

            private String time;
            private double amount;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }
        }

        public static class RevertChartList {
            /**
             * time : 2019-03
             * amount : 63564.71
             */

            private String time;
            private double amount;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }
        }
    }
}
