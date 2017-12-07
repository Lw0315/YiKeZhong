package com.example.asus.yikezhong.bean;

import java.util.List;

/**
 * Created by asus on 2017/11/28.
 */

public class QuarterBean  {


    /**
     * msg : 获取段子列表成功
     * code : 0
     * data : [{"commentNum":null,"content":"11","createTime":"2017-11-29T21:06:57","imgUrls":"https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg","jid":247,"praiseNum":null,"shareNum":null,"uid":99,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/99.jpg","nickname":"Zhu","praiseNum":"null"}},{"commentNum":null,"content":"11","createTime":"2017-11-29T21:06:33","imgUrls":null,"jid":246,"praiseNum":null,"shareNum":null,"uid":99,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/99.jpg","nickname":"Zhu","praiseNum":"null"}},{"commentNum":null,"content":"11","createTime":"2017-11-29T21:06:33","imgUrls":null,"jid":245,"praiseNum":null,"shareNum":null,"uid":99,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/99.jpg","nickname":"Zhu","praiseNum":"null"}},{"commentNum":null,"content":"see hello","createTime":"2017-11-29T21:04:20","imgUrls":"https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg","jid":244,"praiseNum":null,"shareNum":null,"uid":114,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/114.jpg","nickname":"Bount","praiseNum":"null"}},{"commentNum":null,"content":"啦啦啦","createTime":"2017-11-29T20:57:41","imgUrls":"https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg","jid":243,"praiseNum":null,"shareNum":null,"uid":2997,"user":{"age":null,"fans":"null","follow":"null","icon":null,"nickname":"李薇","praiseNum":"null"}},{"commentNum":null,"content":"啦啦啦","createTime":"2017-11-29T20:57:41","imgUrls":"https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg","jid":242,"praiseNum":null,"shareNum":null,"uid":2997,"user":{"age":null,"fans":"null","follow":"null","icon":null,"nickname":"李薇","praiseNum":"null"}},{"commentNum":null,"content":"明天你好","createTime":"2017-11-29T20:57:28","imgUrls":null,"jid":241,"praiseNum":null,"shareNum":null,"uid":675,"user":{"age":null,"fans":"null","follow":"null","icon":null,"nickname":null,"praiseNum":"null"}},{"commentNum":null,"content":"学习学习","createTime":"2017-11-29T20:55:43","imgUrls":null,"jid":240,"praiseNum":null,"shareNum":null,"uid":2956,"user":{"age":null,"fans":"null","follow":"null","icon":null,"nickname":null,"praiseNum":"null"}},{"commentNum":null,"content":"aaa","createTime":"2017-11-29T20:54:57","imgUrls":null,"jid":239,"praiseNum":null,"shareNum":null,"uid":3028,"user":{"age":null,"fans":"null","follow":"null","icon":null,"nickname":"哈哈","praiseNum":"null"}},{"commentNum":null,"content":"是多大的","createTime":"2017-11-29T20:53:27","imgUrls":"https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg","jid":238,"praiseNum":null,"shareNum":null,"uid":3028,"user":{"age":null,"fans":"null","follow":"null","icon":null,"nickname":"哈哈","praiseNum":"null"}}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * commentNum : null
         * content : 11
         * createTime : 2017-11-29T21:06:57
         * imgUrls : https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg|https://www.zhaoapi.cn/images/quarter/tu.jpg
         * jid : 247
         * praiseNum : null
         * shareNum : null
         * uid : 99
         * user : {"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/99.jpg","nickname":"Zhu","praiseNum":"null"}
         */

        private Object commentNum;
        private String content;
        private String createTime;
        private String imgUrls;
        private int jid;
        private Object praiseNum;
        private Object shareNum;
        private int uid;
        private UserBean user;

        public Object getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(Object commentNum) {
            this.commentNum = commentNum;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getImgUrls() {
            return imgUrls;
        }

        public void setImgUrls(String imgUrls) {
            this.imgUrls = imgUrls;
        }

        public int getJid() {
            return jid;
        }

        public void setJid(int jid) {
            this.jid = jid;
        }

        public Object getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(Object praiseNum) {
            this.praiseNum = praiseNum;
        }

        public Object getShareNum() {
            return shareNum;
        }

        public void setShareNum(Object shareNum) {
            this.shareNum = shareNum;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * age : null
             * fans : null
             * follow : null
             * icon : https://www.zhaoapi.cn/images/99.jpg
             * nickname : Zhu
             * praiseNum : null
             */

            private Object age;
            private String fans;
            private String follow;
            private String icon;
            private String nickname;
            private String praiseNum;

            public Object getAge() {
                return age;
            }

            public void setAge(Object age) {
                this.age = age;
            }

            public String getFans() {
                return fans;
            }

            public void setFans(String fans) {
                this.fans = fans;
            }

            public String getFollow() {
                return follow;
            }

            public void setFollow(String follow) {
                this.follow = follow;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(String praiseNum) {
                this.praiseNum = praiseNum;
            }
        }
    }
}
