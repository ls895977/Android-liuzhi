package com.hykj.liuzhi.androidcomponents.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/10/18
 * @describe:
 */


public class CircleBean1 implements MultiItemEntity {

    public static final int THREE_SMALL = 1;
    public static final int RIGHT_BIG = 2;
    public static final int LEFT_BIG = 3;
    private int type;

    @Override
    public int getItemType() {
        return type;
    }

    public CircleBean1(int type, List<ArrayBean> array) {
        this.type = type;
        this.array = array;
    }

    private List<ArrayBean> array;

    public List<ArrayBean> getArray() {
        return array;
    }
    public void setArray(List<ArrayBean> array) {
        this.array = array;
    }

    public static class ArrayBean {
        private int imagetext_id;
        public int getImagetext_id() {
            return imagetext_id;
        }

        public void setImagetext_id(int imagetext_id) {
            this.imagetext_id = imagetext_id;
        }
        /**
         * imagetextimage_url : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/imagetext1/image/0487e8305af7eb8cf92e5a86b69d6a54.jpg
         */
        private String imagetextimage_url;

        public String getImagetextimage_url() {
            return imagetextimage_url;
        }

        public void setImagetextimage_url(String imagetextimage_url) {
            this.imagetextimage_url = imagetextimage_url;
        }
    }
}
