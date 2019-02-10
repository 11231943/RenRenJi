package com.trade.rrenji.bean.address;

public class DelAdressBean  {

    /**
     * code : 0
     * msg : SUCCESS
     * result : {"addressId":"537"}
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
         * addressId : 537
         */

        private String addressId;

        public String getAddressId() {
            return addressId;
        }

        public void setAddressId(String addressId) {
            this.addressId = addressId;
        }
    }
}
