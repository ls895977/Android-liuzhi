package com.hykj.liuzhi.androidcomponents.ui.fragment.home.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: lujialei
 * @date: 2018/9/28
 * @describe:
 */


public class TextureBase implements MultiItemEntity, Serializable {
    public static final int TextureIitem1 = 1; //图在底文字里面
    public static final int TextureIitem2 = 2;//光图片
    public static final int TextureIitem3 = 3;//左边图片右边文字

    public TextureBase(int type, int video_id, String video_name, String video_image, String video_labels, String video_detail, List<LabelsdataBean> labelsdata) {
        this.type = type;
        this.video_id = video_id;
        this.video_name = video_name;
        this.video_image = video_image;
        this.video_labels = video_labels;
        this.video_detail = video_detail;
        this.labelsdata = labelsdata;
    }

    private int type;
    public TextureBase(int type) {
        this.type = type;
    }
    @Override
    public int getItemType() {
        return type;
    }

    /**
     * video_id : 2
     * video_name : 测试视频
     * video_image : http://liuzhiapp.oss-cn-beijing.aliyuncs.com/uploads/video2/image/9c0a88d326c1da9f6a6ec00f4fffa369.jpg
     * video_labels : 1,2,3,4
     * video_detail : 测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频测试视频
     * labelsdata : [{"videolabel_id":1,"videolabel_name":"测试标签1","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":2,"videolabel_name":"测试标签2","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":3,"videolabel_name":"测试标签3","videolabel_creattime":1542080807,"videolabel_status":1},{"videolabel_id":4,"videolabel_name":"测试标签4","videolabel_creattime":1542080807,"videolabel_status":1}]
     */

    private int video_id;
    private String video_name;
    private String video_image;
    private String video_labels;
    private String video_detail;
    private List<LabelsdataBean> labelsdata;

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

    public String getVideo_labels() {
        return video_labels;
    }

    public void setVideo_labels(String video_labels) {
        this.video_labels = video_labels;
    }

    public String getVideo_detail() {
        return video_detail;
    }

    public void setVideo_detail(String video_detail) {
        this.video_detail = video_detail;
    }

    public List<LabelsdataBean> getLabelsdata() {
        return labelsdata;
    }

    public void setLabelsdata(List<LabelsdataBean> labelsdata) {
        this.labelsdata = labelsdata;
    }

    public static class LabelsdataBean {
        /**
         * videolabel_id : 1
         * videolabel_name : 测试标签1
         * videolabel_creattime : 1542080807
         * videolabel_status : 1
         */

        private int videolabel_id;
        private String videolabel_name;
        private int videolabel_creattime;
        private int videolabel_status;

        public int getVideolabel_id() {
            return videolabel_id;
        }

        public void setVideolabel_id(int videolabel_id) {
            this.videolabel_id = videolabel_id;
        }

        public String getVideolabel_name() {
            return videolabel_name;
        }

        public void setVideolabel_name(String videolabel_name) {
            this.videolabel_name = videolabel_name;
        }

        public int getVideolabel_creattime() {
            return videolabel_creattime;
        }

        public void setVideolabel_creattime(int videolabel_creattime) {
            this.videolabel_creattime = videolabel_creattime;
        }

        public int getVideolabel_status() {
            return videolabel_status;
        }

        public void setVideolabel_status(int videolabel_status) {
            this.videolabel_status = videolabel_status;
        }
    }
}
