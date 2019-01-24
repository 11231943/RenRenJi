package com.trade.rrenji.bean.account;

/**
 * Created by ${Wangyun} on 2017/10/13.
 */

public class LoginJsonBean {

    @Override
    public String toString() {
        return "LoginJsonBean{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {

        private int age;
        private String birthday;
        private String constellation;
        private String create_time;
        private String easemob_id;
        private int follow;
        private int gender;
        private String height;
        private int id;
        private String income;
        private int is_effective;
        private int is_first_login;
        private int is_register_easemob;
        private int job_id;
        private String job_name;
        private String login_time;
        private int login_type;
        private String phone;
        private String pic;
        private String qq_open_id;
        private int state;
        private String tag_ids;
        private String tags;
        private int to_follow;
        private String token;
        private String user_name;
        private String weight;
        private String wx_open_id;
        private String wb_open_id;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getEasemob_id() {
            return easemob_id;
        }

        public void setEasemob_id(String easemob_id) {
            this.easemob_id = easemob_id;
        }

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public int getIs_effective() {
            return is_effective;
        }

        public void setIs_effective(int is_effective) {
            this.is_effective = is_effective;
        }

        public int getIs_first_login() {
            return is_first_login;
        }

        public void setIs_first_login(int is_first_login) {
            this.is_first_login = is_first_login;
        }

        public int getIs_register_easemob() {
            return is_register_easemob;
        }

        public void setIs_register_easemob(int is_register_easemob) {
            this.is_register_easemob = is_register_easemob;
        }

        public int getJob_id() {
            return job_id;
        }

        public void setJob_id(int job_id) {
            this.job_id = job_id;
        }

        public String getJob_name() {
            return job_name;
        }

        public void setJob_name(String job_name) {
            this.job_name = job_name;
        }

        public String getLogin_time() {
            return login_time;
        }

        public void setLogin_time(String login_time) {
            this.login_time = login_time;
        }

        public int getLogin_type() {
            return login_type;
        }

        public void setLogin_type(int login_type) {
            this.login_type = login_type;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getQq_open_id() {
            return qq_open_id;
        }

        public void setQq_open_id(String qq_open_id) {
            this.qq_open_id = qq_open_id;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getTag_ids() {
            return tag_ids;
        }

        public void setTag_ids(String tag_ids) {
            this.tag_ids = tag_ids;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public int getTo_follow() {
            return to_follow;
        }

        public void setTo_follow(int to_follow) {
            this.to_follow = to_follow;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getWx_open_id() {
            return wx_open_id;
        }

        public void setWx_open_id(String wx_open_id) {
            this.wx_open_id = wx_open_id;
        }

        public String getWb_open_id() {
            return wb_open_id;
        }

        public void setWb_open_id(String wb_open_id) {
            this.wb_open_id = wb_open_id;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "age=" + age +
                    ", birthday='" + birthday + '\'' +
                    ", constellation='" + constellation + '\'' +
                    ", create_time='" + create_time + '\'' +
                    ", easemob_id='" + easemob_id + '\'' +
                    ", follow=" + follow +
                    ", gender=" + gender +
                    ", height='" + height + '\'' +
                    ", id=" + id +
                    ", income='" + income + '\'' +
                    ", is_effective=" + is_effective +
                    ", is_first_login=" + is_first_login +
                    ", is_register_easemob=" + is_register_easemob +
                    ", job_id=" + job_id +
                    ", job_name='" + job_name + '\'' +
                    ", login_time='" + login_time + '\'' +
                    ", login_type=" + login_type +
                    ", phone='" + phone + '\'' +
                    ", pic='" + pic + '\'' +
                    ", qq_open_id='" + qq_open_id + '\'' +
                    ", state=" + state +
                    ", tag_ids='" + tag_ids + '\'' +
                    ", tags='" + tags + '\'' +
                    ", to_follow=" + to_follow +
                    ", token='" + token + '\'' +
                    ", user_name='" + user_name + '\'' +
                    ", weight='" + weight + '\'' +
                    ", wx_open_id='" + wx_open_id + '\'' +
                    ", wb_open_id='" + wb_open_id + '\'' +
                    '}';
        }
    }
}
