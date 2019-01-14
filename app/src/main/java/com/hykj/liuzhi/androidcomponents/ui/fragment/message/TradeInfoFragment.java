package com.hykj.liuzhi.androidcomponents.ui.fragment.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hykj.liuzhi.R;
import com.hykj.liuzhi.androidcomponents.bean.UserMessageBean;
import com.hykj.liuzhi.androidcomponents.ui.adapter.MessageAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: lujialei
 * @date: 2018/9/27
 * @describe:
 */


public class TradeInfoFragment extends Fragment {


    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    MessageAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trade_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList list = new ArrayList();
        for (int i = 0; i < 3; i++) {
            list.add(new UserMessageBean());
        }
        mAdapter = new MessageAdapter(list);
        rv.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
