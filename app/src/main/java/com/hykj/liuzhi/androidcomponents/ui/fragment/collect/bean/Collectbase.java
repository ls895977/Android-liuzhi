package com.hykj.liuzhi.androidcomponents.ui.fragment.collect.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

public class Collectbase implements MultiItemEntity, Serializable {
    public static final int FashionItem1 = 1; //图在底文字里面
    public static final int FashionItem2 = 2;//左边图片右边文字
    public static final int Imge1 = 3;
    public static final int Imge2 = 4;
    public static final int Imge3 = 5;
    public static final int Shoping_1 = 6;
    public static final int Shoping_2 = 7;
    private int type;

    public Collectbase(int type) {
        this.type = type;
    }
    @Override
    public int getItemType() {
        return type;
    }

    /**
     * user_id : 1
     * softtext_id : 1
     * collection_type : 1
     * authordata : {"user_nickname":"姓王名小贱","user_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg","user_autograph":"我是你爸爸啊"}
     * softtextimage : {"softtextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/softtext1/image/bfa5296e04f07c9151f8bfda8957b9e4.jpg"}
     * imagetext_id : 1
     * imagetextimage : {"imagetext_id":1,"imagetextimage_url":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext1/image/0487e8305af7eb8cf92e5a86b69d6a54.jpg"}
     * video_id : 2
     * videodata : {"video_id":2,"video_name":"测试视频","video_image":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video2/image/9c0a88d326c1da9f6a6ec00f4fffa369.jpg"}
     * goods_id : 1
     * goodsdata : {"goods_id":1,"goods_pic":"http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods1/firstpic/76a4f4a5c497404f39e919ee5468d31f.jpg","goods_name":"商品4","goods_price":"1"}
     */

    private int user_id;
    private int softtext_id;
    private int collection_type;
    private int video_id;
    private int goods_id;
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSofttext_id() {
        return softtext_id;
    }

    public void setSofttext_id(int softtext_id) {
        this.softtext_id = softtext_id;
    }

    public int getCollection_type() {
        return collection_type;
    }

    public void setCollection_type(int collection_type) {
        this.collection_type = collection_type;
    }
    /**
     * user_nickname : 姓王名小贱
     * user_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/user1/userpic/02ced9b994088598aceae34bb8b895f5.jpg
     * user_autograph : 我是你爸爸啊
     */

    private String user_nickname;
    private String user_pic;
    private String user_autograph;

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

    public String getUser_autograph() {
        return user_autograph;
    }

    public void setUser_autograph(String user_autograph) {
        this.user_autograph = user_autograph;
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
    /**
     * imagetext_id : 1
     * imagetextimage_url : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext1/image/0487e8305af7eb8cf92e5a86b69d6a54.jpg
     */
    private int imagetext_id;
    private String imagetextimage_url;

    public int getImagetext_id() {
        return imagetext_id;
    }

    public void setImagetext_id(int imagetext_id) {
        this.imagetext_id = imagetext_id;
    }

    public String getImagetextimage_url() {
        return imagetextimage_url;
    }

    public void setImagetextimage_url(String imagetextimage_url) {
        this.imagetextimage_url = imagetextimage_url;
    }
    /**
     * video_id : 2
     * video_name : 测试视频
     * video_image : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video2/image/9c0a88d326c1da9f6a6ec00f4fffa369.jpg
     */
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
    /**
     * goods_id : 1
     * goods_pic : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/goods1/firstpic/76a4f4a5c497404f39e919ee5468d31f.jpg
     * goods_name : 商品4
     * goods_price : 1
     */
    private String goods_pic;
    private String goods_name;
    private String goods_price;

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_pic() {
        return goods_pic;
    }

    public void setGoods_pic(String goods_pic) {
        this.goods_pic = goods_pic;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }
}