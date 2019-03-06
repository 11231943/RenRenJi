package com.trade.rrenji.bean.order;

public class NetGetUserCreateOrderBean {


    /**
     * code : 0
     * msg : null
     * data : {"address":{"addressId":"280","consigneeName":"李小萌3","consigneeTel":"13687942309","province":"广东省","city":"深圳市","district":"南山区","location":"湾畔百货11栋09室a","isDefault":1},"couponCount":0}
     */

    private String code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address : {"addressId":"280","consigneeName":"李小萌3","consigneeTel":"13687942309","province":"广东省","city":"深圳市","district":"南山区","location":"湾畔百货11栋09室a","isDefault":1}
         * couponCount : 0
         */

        private AddressBean address;
        private int couponCount;

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public int getCouponCount() {
            return couponCount;
        }

        public void setCouponCount(int couponCount) {
            this.couponCount = couponCount;
        }

        public static class AddressBean {
            /**
             * addressId : 280
             * consigneeName : 李小萌3
             * consigneeTel : 13687942309
             * province : 广东省
             * city : 深圳市
             * district : 南山区
             * location : 湾畔百货11栋09室a
             * isDefault : 1
             */

            private String addressId;
            private String consigneeName;
            private String consigneeTel;
            private String province;
            private String city;
            private String district;
            private String location;
            private int isDefault;

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

            public int getIsDefault() {
                return isDefault;
            }

            public void setIsDefault(int isDefault) {
                this.isDefault = isDefault;
            }
        }
    }
}
