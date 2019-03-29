package com.trade.rrenji.biz.search.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.address.NetAddressBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.search.presenter.SearchActivityPresenter;
import com.trade.rrenji.biz.search.presenter.SearchActivityPresenterImpl;
import com.trade.rrenji.biz.search.ui.adapter.SearchAdapter;
import com.trade.rrenji.biz.search.ui.view.SearchActivityView;
import com.trade.rrenji.fragment.DryingTabFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;


@ContentView(R.layout.search_main_layout)
public class SearchActivity extends BaseActivity implements SearchActivityView {

    private static String TAG = DryingTabFragment.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

    }

    private void loadData() {

    }

    @Override
    protected void attachPresenter() {

    }

    @Override
    protected void detachPresenter() {

    }

    @Event(value = {R.id.back_layout, R.id.address_layout})
    private void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_layout:
                finish();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getCollectionList(NetAddressBean netShareBean) {

    }

    @Override
    public void getCollectionListError(int code, String msg) {

    }
}
