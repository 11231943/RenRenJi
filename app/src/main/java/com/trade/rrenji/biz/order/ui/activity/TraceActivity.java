package com.trade.rrenji.biz.order.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.trade.rrenji.R;
import com.trade.rrenji.bean.order.NetLogisticsBean;
import com.trade.rrenji.bean.order.RouteBean;
import com.trade.rrenji.biz.base.BaseActivity;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@ContentView(R.layout.activity_trace)
public class TraceActivity extends BaseActivity {

    private ListView lvTrace;
    private TraceListAdapter adapter;
    private List<RouteBean> mTraceList = new ArrayList<RouteBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findView();
        setActionBarTitle("物流详情");
        NetLogisticsBean bean =(NetLogisticsBean) getIntent().getSerializableExtra("data");
        mTraceList =bean.getResult().getRoute();
        Collections.sort(mTraceList,new Comparator<RouteBean>(){
            public int compare(RouteBean arg0, RouteBean arg1){
                return arg1.getAcceptTime().compareTo(arg0.getAcceptTime());
            }
        });

        adapter = new TraceListAdapter(this, mTraceList);
        lvTrace.setAdapter(adapter);
    }

    private void findView() {
        lvTrace = (ListView) findViewById(R.id.lvTrace);
    }


    @Override
    protected void attachPresenter() {

    }

    @Override
    protected void detachPresenter() {

    }

    class TraceListAdapter extends BaseAdapter {
        private Context context;
        private List<RouteBean> traceList = new ArrayList<RouteBean>();
        private static final int TYPE_TOP = 0x0000;
        private static final int TYPE_NORMAL = 0x0001;

        public TraceListAdapter(Context context, List<RouteBean> traceList) {
            this.context = context;
            this.traceList = traceList;


        }

        @Override
        public int getCount() {
            return traceList.size();
        }

        @Override
        public RouteBean getItem(int position) {
            return traceList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            final RouteBean trace = getItem(position);
            if (convertView != null) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_trace, parent, false);
                holder.tvAcceptTime = (TextView) convertView.findViewById(R.id.tvAcceptTime);
                holder.tvAcceptStation = (TextView) convertView.findViewById(R.id.tvAcceptStation);
                holder.tvTopLine = (TextView) convertView.findViewById(R.id.tvTopLine);
                holder.tvDot = (TextView) convertView.findViewById(R.id.tvDot);
                convertView.setTag(holder);
            }

            if (getItemViewType(position) == TYPE_TOP) {
                // 第一行头的竖线不显示
                holder.tvTopLine.setVisibility(View.INVISIBLE);
                // 字体颜色加深
                holder.tvAcceptTime.setTextColor(0xff555555);
                holder.tvAcceptStation.setTextColor(0xff555555);
                holder.tvDot.setBackgroundResource(R.drawable.timelline_dot_first);
            } else if (getItemViewType(position) == TYPE_NORMAL) {
                holder.tvTopLine.setVisibility(View.VISIBLE);
                holder.tvAcceptTime.setTextColor(0xff999999);
                holder.tvAcceptStation.setTextColor(0xff999999);
                holder.tvDot.setBackgroundResource(R.drawable.timelline_dot_normal);
            }

            holder.tvAcceptTime.setText(trace.getAcceptTime());
            holder.tvAcceptStation.setText(trace.getRemark());
            return convertView;
        }

        @Override
        public int getItemViewType(int id) {
            if (id == 0) {
                return TYPE_TOP;
            }
            return TYPE_NORMAL;
        }

        class ViewHolder {
            public TextView tvAcceptTime, tvAcceptStation;
            public TextView tvTopLine, tvDot;
        }
    }
}
