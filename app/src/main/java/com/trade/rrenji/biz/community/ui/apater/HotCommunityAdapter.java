package com.trade.rrenji.biz.community.ui.apater;

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
import com.trade.rrenji.biz.ad.AdActivity;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.GlideUtils;

public class HotCommunityAdapter extends RecyclerListAdapter<EveryoneCommunityListBean> {

    Context mContext;
//    List<EveryoneCommunityListBean> mEveryoneCommunityListBean;

    public HotCommunityAdapter(Context context) {
        super(context);
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_hot_community_item, parent, false);
        return new CommunityViewHolder(view);

    }

    public class CommunityViewHolder extends ViewHolder {
        private TextView community_desc;
        private TextView hot_community_text;
        private ImageView hot_community_image;
        private RelativeLayout main_layout;

        public CommunityViewHolder(View itemView) {
            super(itemView);
            main_layout = (RelativeLayout) itemView.findViewById(R.id.main_layout);
            community_desc = (TextView) itemView.findViewById(R.id.community_desc);
            hot_community_text = (TextView) itemView.findViewById(R.id.hot_community_text);
            hot_community_image = (ImageView) itemView.findViewById(R.id.hot_community_image);
        }

        @Override
        public void bindData(final EveryoneCommunityListBean data, int position) {
            super.bindData(data, position);

            GlideUtils.getInstance().loadImageUrl(mContext, data.getEveryoneCommunityImg(), R.drawable.ic_launcher, hot_community_image);
            community_desc.setText(data.getContent());
            hot_community_text.setText(data.getTitle());
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

