package com.trade.rrenji.biz.order.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trade.rrenji.R;
import com.trade.rrenji.biz.base.BaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * 待收货
 */
@ContentView(R.layout.base_activity_super_recyclerview)
public class ReceivedFragment extends BaseFragment {

    public static ReceivedFragment newInstance() {
        Bundle args = new Bundle();
        ReceivedFragment fragment = new ReceivedFragment();
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
