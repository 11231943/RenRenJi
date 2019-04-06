package com.trade.rrenji.bean.address;

import java.io.Serializable;
import java.util.List;

public class NetAddressBean {

    /**
     * code : 0
     * msg : SUCCESS
     * result : {"addressList":[{"addressId":"369","consigneeName":"命名","consigneeTel":"16666666666","province":"","city":"","district":"","location":"红米傻X"},{"addressId":"373","consigneeName":"彭浩","consigneeTel":"17603009825","province":"北京市","city":"北京市","district":"宣武区","location":"朝阳门地铁站"}],"pageInfo":{"currentPage":1,"totalPage":1,"pageSize":10,"totalRow":2}}
     */

    private String code;
    private String msg;
    private ResultBean result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * addressList : [{"addressId":"369","consigneeName":"命名","consigneeTel":"16666666666","province":"","city":"","district":"","location":"红米傻X"},{"addressId":"373","consigneeName":"彭浩","consigneeTel":"17603009825","province":"北京市","city":"北京市","district":"宣武区","location":"朝阳门地铁站"}]
         * pageInfo : {"currentPage":1,"totalPage":1,"pageSize":10,"totalRow":2}
         */

        private PageInfoBean pageInfo;
        private List<AddressListBean> addressList;

        public PageInfoBean getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfoBean pageInfo) {
            this.pageInfo = pageInfo;
        }

        public List<AddressListBean> getAddressList() {
            return addressList;
        }

        public void setAddressList(List<AddressListBean> addressList) {
            this.addressList = addressList;
        }

        public static class PageInfoBean {
            /**
             * currentPage : 1
             * totalPage : 1
             * pageSize : 10
             * totalRow : 2
             */

            private int currentPage;
            private int totalPage;
            private int pageSize;
            private int totalRow;

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getTotalRow() {
                return totalRow;
            }

            public void setTotalRow(int totalRow) {
                this.totalRow = totalRow;
            }
        }

        public static class AddressListBean implements Serializable {
            /**
             * addressId : 369
             * consigneeName : 命名
             * consigneeTel : 16666666666
             * province :
             * city :
             * district :
             * location : 红米傻X
             */

            private String addressId;
            private String consigneeName;
            private String consigneeTel;
            private String province;
            private String city;
            private String district;
            private String location;
            private int isDefault;

            public boolean isDefault() {
                return isDefault == 1 ? true : false;
            }

            public void setIsDefault(int isDefault) {
                this.isDefault = isDefault;
            }

            public String getAddressId() {
                return addressId;
            }

            public void setAddressId(String addressId) {
                this.addressId = addressId;
            }

            public String getConsigneeName() {
                return consigneeName;
            }

            public void setConsigneeName(String consigneeName) {
                this.consigneeName = consigneeName;
            }

            public String getConsigneeTel() {
                return consigneeTel;
            }

            public void setConsigneeTel(String consigneeTel) {
                this.consigneeTel = consigneeTel;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }
        }
    }
}
