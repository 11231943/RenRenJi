package com.trade.rrenji.biz.order.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trade.rrenji.R;
import com.trade.rrenji.biz.base.BaseFragment;
import com.trade.rrenji.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * 晒单
 */
@ContentView(R.layout.base_activity_super_recyclerview)
public class DryingFragment extends BaseFragment {

    public static DryingFragment newInstance() {
        Bundle args = new Bundle();
        DryingFragment fragment = new DryingFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = x.view().inject(this, inflater, container);
        return rootView;
    }


    @Override
    protected void attachPresenter() {

    }

    @Override
    protected void detachPresenter() {

    }

}
