package com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/9/28
 * @describe:
 */

public class FashionBase implements MultiItemEntity, Serializable {
    public static final int FashionItem1 = 1; //图在底文字里面
    public static final int FashionItem2 = 2;//光图片
    public static final int FashionItem3 = 3;//左边图片右边文字
    private int type;

    public FashionBase(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }

    /**
     * softtext_id : 1
     * softtext_title : 测试标题
     * user_id : 1
     * softtextimage : {"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"}
     * userdata : {"user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_nickname":"姓王名小贱"}
     */

    private int softtext_id;
    private String softtext_title;
    private int user_id;
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

    /**
     * adv_url : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/firstpage/advertisement/image/ed455cfb85485888486ce6843f4b24e3.jpg
     */

    private String adv_url;

    public String getAdv_url() {
        return adv_url;
    }

    public void setAdv_url(String adv_url) {
        this.adv_url = adv_url;
    }
}
