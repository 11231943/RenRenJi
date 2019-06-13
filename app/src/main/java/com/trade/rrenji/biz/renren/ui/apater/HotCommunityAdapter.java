package com.trade.rrenji.biz.renren.ui.apater;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.trade.rrenji.R;
import com.trade.rrenji.bean.communty.NetCommuntyBean.DataBean.EveryoneCommunityListBean;
import com.trade.rrenji.bean.home.NetHomeBean;
import com.trade.rrenji.bean.renren.NetRenRebBean;
import com.trade.rrenji.biz.ad.AdActivity;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.GlideUtils;

public class HotCommunityAdapter extends RecyclerListAdapter<NetRenRebBean.DataBean.EveryoneHomeListBean> {

    Context mContext;
//    List<EveryoneCommunityListBean> mEveryoneCommunityListBean;

    public HotCommunityAdapter(Context context) {
        super(context);
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_renren_data, parent, false);
        return new CommunityViewHolder(view);

    }

    public class CommunityViewHolder extends ViewHolder {
        private TextView hot_renren_text;
        private TextView hot_renren_content;
        private ImageView renren_iamge;
        private RelativeLayout main_layout;

        public CommunityViewHolder(View itemView) {
            super(itemView);
            hot_renren_text = (TextView) itemView.findViewById(R.id.hot_renren_text);
            hot_renren_content = (TextView) itemView.findViewById(R.id.hot_renren_content);
            renren_iamge = (ImageView) itemView.findViewById(R.id.renren_iamge);
            main_layout = (RelativeLayout) itemView.findViewById(R.id.main_layout);
        }

        @Override
        public void bindData(final NetRenRebBean.DataBean.EveryoneHomeListBean data, int position) {
            super.bindData(data, position);

            GlideUtils.getInstance().loadImageUrl(mContext, data.getHomeImg(), R.drawable.huodong_zwt, renren_iamge);
            hot_renren_text.setText(data.getTitle());
            hot_renren_content.setText(data.getContent());
            main_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(data.getUrl())) {
                        AdActivity.start(mContext, data.getUrl());
                    } else {
                        Toast.makeText(mContext, "无效的连接", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}

