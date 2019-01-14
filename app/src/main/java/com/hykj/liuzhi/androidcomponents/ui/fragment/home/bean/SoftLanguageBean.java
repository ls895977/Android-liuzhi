package com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/9/28
 * @describe:
 */


public class SoftLanguageBean implements MultiItemEntity, Serializable {
    public static final int IMAGE_TEXT_INSIDE = 1; //图在底文字里面
    public static final int SECTION_HEADER = 2;//分区标题
    public static final int IMAGE_TEXT_TOP = 3;
    public static final int IMAGE_TEXT_BOTTOM = 4;
    public static final int SOFT_ARTICLE = 5;
    public static final int MORE = 6;
    public static final int IMAGE_HADER = 7;
    public static final int IMAGE_BUTTOM = 8;
    private int type;

    public SoftLanguageBean(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }

    private int softtext_id;
    private String softtext_title;
    private int user_id;
    private String user_nickname;
    private String user_pic;

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }

    private SofttextimageBean softtextimage;
    public int getSofttext_id() {
        return softtext_id;
    }

    public void setSofttext_id(int softtext_id) {
        this.softtext_id = softtext_id;
    }

    public String getSofttext_title() {
        return softtext_title;
    }

    public void setSofttext_title(String softtext_title) {
        this.softtext_title = softtext_title;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public SofttextimageBean getSofttextimage() {
        return softtextimage;
    }

    public void setSofttextimage(SofttextimageBean softtextimage) {
        this.softtextimage = softtextimage;
    }
    private String SofttextimageURL;

    public String getSofttextimageURL() {
        return SofttextimageURL;
    }

    public void setSofttextimageURL(String softtextimageURL) {
        SofttextimageURL = softtextimageURL;
    }

    public static class SofttextimageBean {
        /**
         * softtextimage_url : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg
         */

        private String softtextimage_url;

        public String getSofttextimage_url() {
            return softtextimage_url;
        }

        public void setSofttextimage_url(String softtextimage_url) {
            this.softtextimage_url = softtextimage_url;
        }

        private String user_pic;
        private String user_nickname;

        public String getUser_pic() {
            return user_pic;
        }

        public void setUser_pic(String user_pic) {
            this.user_pic = user_pic;
        }

        public String getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(String user_nickname) {
            this.user_nickname = user_nickname;
        }
    }

    private int video_id;
    private String video_name;
    private String video_image;

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public String getVideo_image() {
        return video_image;
    }

    public void setVideo_image(String video_image) {
        this.video_image = video_image;
    }

    private String adv_url;

    public String getAdv_url() {
        return adv_url;
    }

    public void setAdv_url(String adv_url) {
        this.adv_url = adv_url;
    }
}
