package com.hykj.liuzhi.androidcomponents.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.MyApplication;
import com.hykj.liuzhi.androidcomponents.bean.User;
import com.hykj.liuzhi.greendao.gen.DaoSession;
import com.hykj.liuzhi.greendao.gen.UserDao;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SQLactivity extends BaseActivity {
    @BindView(R.id.insert_btn)
    Button insertBtn;
    @BindView(R.id.delete_btn)
    Button deleteBtn;
    @BindView(R.id.update_btn)
    Button updateBtn;
    @BindView(R.id.query_btn)
    Button queryBtn;
    private UserDao mUserDao;
    private Query<User> mUserQuery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_test);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        DaoSession daoSession =((MyApplication) getApplication()).getDaoSession();
        mUserDao = daoSession.getUserDao();
        mUserQuery = mUserDao.queryBuilder().orderAsc(UserDao.Properties.Id).build();
    }

    @OnClick({R.id.insert_btn, R.id.delete_btn, R.id.update_btn, R.id.query_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.insert_btn:
                insertData();
                break;
            case R.id.delete_btn:
                deleteData();
                break;
            case R.id.update_btn:
                updataData();
                break;
            case R.id.query_btn:
                quesyData();
                break;
        }
    }

    private List<User> quesyData() {
        List<User> users = mUserQuery.list();
        return users;
    }

    private void updataData() {
//查询id是1位置的数据
        User user = mUserDao.load(5l);
        //对其进行修改
        user.setName("简国堂");
        mUserDao.update(user);
    }

    private void deleteData() {

        mUserDao.deleteByKey(5l);
    }

    private void insertData() {
        User user = new User(null,"jianguotang", "男","123456",10);
        mUserDao.insert(user);
    }
}
