package com.trade.rrenji.bean.category;

import java.util.List;

public class NetScreenBean {


    /**
     * code : 0
     * msg : SUCCESS
     * result : {"condition":[{"id":"1","name":"99新"},{"id":"2","name":"95新"},{"id":"4","name":"85新"}],"memory":[{"id":"29","name":"128G"},{"id":"22","name":"32G"}],"color":[{"id":"38","name":"金"},{"id":"39","name":"玫瑰金　"},{"id":"37","name":"银"},{"id":"41","name":"黑色"}],"version":[{"id":"439","name":"其他"},{"id":"48","name":"国行"}],"network":[{"id":"30","name":"移动4G联通4G电信4G"}]}
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
        private List<ConditionBean> condition;
        private List<MemoryBean> memory;
        private List<ColorBean> color;
        private List<VersionBean> version;
        private List<NetworkBean> network;

        public List<ConditionBean> getCondition() {
            return condition;
        }

        public void setCondition(List<ConditionBean> condition) {
            this.condition = condition;
        }

        public List<MemoryBean> getMemory() {
            return memory;
        }

        public void setMemory(List<MemoryBean> memory) {
            this.memory = memory;
        }

        public List<ColorBean> getColor() {
            return color;
        }

        public void setColor(List<ColorBean> color) {
            this.color = color;
        }

        public List<VersionBean> getVersion() {
            return version;
        }

        public void setVersion(List<VersionBean> version) {
            this.version = version;
        }

        public List<NetworkBean> getNetwork() {
            return network;
        }

        public void setNetwork(List<NetworkBean> network) {
            this.network = network;
        }

        public static class ConditionBean {
            /**
             * id : 1
             * name : 99新
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class MemoryBean {
            /**
             * id : 29
             * name : 128G
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class ColorBean {
            /**
             * id : 38
             * name : 金
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class VersionBean {
            /**
             * id : 439
             * name : 其他
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class NetworkBean {
            /**
             * id : 30
             * name : 移动4G联通4G电信4G
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
