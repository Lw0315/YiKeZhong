package com.example.asus.yikezhong.bean;

import java.util.List;

/**
 * Created by asus on 2017/12/6.
 */

public class GetUserShiPin {
    /**
     * msg : 获取作品列表成功
     * code : 0
     * data : [{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/151246320352120171205163847.jpg","createTime":"2017-12-05T16:40:03","favoriteNum":null,"latitude":"40.040404","localUri":null,"longitude":"116.299781","playNum":null,"praiseNum":null,"uid":2997,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512463203521wx_camera_1509596718115.mp4","wid":43,"workDesc":""},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/151246390747520171205165122.jpg","createTime":"2017-12-05T16:51:47","favoriteNum":null,"latitude":"40.040475","localUri":null,"longitude":"116.300124","playNum":null,"praiseNum":null,"uid":2997,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512463907475wx_camera_1509596718115.mp4","wid":45,"workDesc":"ll"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/151253954369320171206135144.jpg","createTime":"2017-12-06T13:52:23","favoriteNum":null,"latitude":"40.040466","localUri":null,"longitude":"116.300095","playNum":null,"praiseNum":null,"uid":2997,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512539543693wx_camera_1509596718115.mp4","wid":68,"workDesc":"1212"},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/151254034039620171206140504.jpg","createTime":"2017-12-06T14:05:40","favoriteNum":null,"latitude":"40.04047","localUri":null,"longitude":"116.300109","playNum":null,"praiseNum":null,"uid":2997,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512540340396wx_camera_1509596718115.mp4","wid":75,"workDesc":"lwlw"}]
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
         * cover : https://www.zhaoapi.cn/images/quarter/151246320352120171205163847.jpg
         * createTime : 2017-12-05T16:40:03
         * favoriteNum : null
         * latitude : 40.040404
         * localUri : null
         * longitude : 116.299781
         * playNum : null
         * praiseNum : null
         * uid : 2997
         * videoUrl : https://www.zhaoapi.cn/images/quarter/1512463203521wx_camera_1509596718115.mp4
         * wid : 43
         * workDesc :
         */
        private Object commentNum;
        private String cover;
        private String createTime;
        private Object favoriteNum;
        private String latitude;
        private Object localUri;
        private String longitude;
        private Object playNum;
        private Object praiseNum;
        private int uid;
        private String videoUrl;
        private int wid;
        private String workDesc;
        public Object getCommentNum() {
            return commentNum;
        }
        public void setCommentNum(Object commentNum) {
            this.commentNum = commentNum;
        }
        public String getCover() {
            return cover;
        }
        public void setCover(String cover) {
            this.cover = cover;
        }
        public String getCreateTime() {
            return createTime;
        }
        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
        public Object getFavoriteNum() {
            return favoriteNum;
        }
        public void setFavoriteNum(Object favoriteNum) {
            this.favoriteNum = favoriteNum;
        }
        public String getLatitude() {
            return latitude;
        }
        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }
        public Object getLocalUri() {
            return localUri;
        }
        public void setLocalUri(Object localUri) {
            this.localUri = localUri;
        }
        public String getLongitude() {
            return longitude;
        }
        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
        public Object getPlayNum() {
            return playNum;
        }
        public void setPlayNum(Object playNum) {
            this.playNum = playNum;
        }
        public Object getPraiseNum() {
            return praiseNum;
        }
        public void setPraiseNum(Object praiseNum) {
            this.praiseNum = praiseNum;
        }
        public int getUid() {
            return uid;
        }
        public void setUid(int uid) {
            this.uid = uid;
        }
        public String getVideoUrl() {
            return videoUrl;
        }
        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }
        public int getWid() {
            return wid;
        }
        public void setWid(int wid) {
            this.wid = wid;
        }
        public String getWorkDesc() {
            return workDesc;
        }
        public void setWorkDesc(String workDesc) {
            this.workDesc = workDesc;
        }
    }
}
