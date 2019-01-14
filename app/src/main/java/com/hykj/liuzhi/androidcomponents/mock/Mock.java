package com.hykj.liuzhi.androidcomponents.mock;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.MutiItem;

/**
 * @author: lujialei
 * @date: 2018/9/28
 * @describe:
 */


public class Mock {

    public static ArrayList list = new ArrayList();
    static {
        list.add(new MutiItem(MutiItem.SECTION_HEADER));
        list.add(new MutiItem(MutiItem.IMAGE_TEXT_INSIDE));
        list.add(new MutiItem(MutiItem.IMAGE_TEXT_INSIDE));
        list.add(new MutiItem(MutiItem.IMAGE_TEXT_INSIDE));
        list.add(new MutiItem(MutiItem.SOFT_ARTICLE));
        list.add(new MutiItem(MutiItem.IMAGE_TEXT_TOP));
        list.add(new MutiItem(MutiItem.IMAGE_TEXT_TOP));
        list.add(new MutiItem(MutiItem.IMAGE_TEXT_TOP));
        list.add(new MutiItem(MutiItem.SECTION_HEADER));
        list.add(new MutiItem(MutiItem.IMAGE_TEXT_BOTTOM));
        list.add(new MutiItem(MutiItem.IMAGE_TEXT_BOTTOM));
        list.add(new MutiItem(MutiItem.IMAGE_TEXT_BOTTOM));
    }
    public static ArrayList getRecommendList(){
        return list;
    }

    public static List<?> getBannerList() {
        String url = "http://pic14.nipic.com/20110605/1369025_165540642000_2.jpg";
        ArrayList list = new ArrayList();
        list.add(url);
        list.add(url);
        list.add(url);
        return list;
    }

    public static List<?> getTextureBannerList() {
        String url = "http://pic1.win4000.com/wallpaper/7/53bce10032f18.jpg";
        ArrayList list = new ArrayList();
        list.add(url);
        list.add(url);
        list.add(url);
        return list;
    }


}
