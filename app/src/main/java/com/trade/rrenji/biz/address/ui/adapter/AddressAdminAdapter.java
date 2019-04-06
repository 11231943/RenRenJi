package com.trade.rrenji.biz.address.ui.adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trade.rrenji.R;
import com.trade.rrenji.bean.address.NetAddressBean;
import com.trade.rrenji.bean.address.NetAddressBean.ResultBean.AddressListBean;
import com.trade.rrenji.fragment.RecyclerListAdapter;

import java.util.List;


/**
 * Created by wheat on 16/1/14.
 */
public class AddressAdminAdapter extends RecyclerListAdapter<NetAddressBean.ResultBean.AddressListBean> {

    private static final int ACTION_DEFAULT = 0;

    private static final int ACTION_DEL = 1;

    private Activity mContext;

    private OnClickSetDefaultListener onClickSetDefaultListener;

    private OnClickDelListener onClickDelListener;

    private onClickListener onClickListener;

    public void setOnClickListener(AddressAdminAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnClickDelListener(OnClickDelListener onClickDelListener) {
        this.onClickDelListener = onClickDelListener;
    }

    public void setOnClickSetDefaultListener(OnClickSetDefaultListener onClickSetDefaultListener) {
        this.onClickSetDefaultListener = onClickSetDefaultListener;
    }

    public void removeAddressAdmin(String packId) {
        List<AddressListBean> beans = getDataSet();
        for (AddressListBean bean : beans) {
            if (TextUtils.equals(bean.getAddressId(), packId)) {
                remove(bean);
                break;
            }
        }
    }

    public AddressAdminAdapter(Activity context) {
        super(context);
        mContext = context;
    }

    public class AddressViewHolder extends ViewHolder {

        TextView address_name;
        TextView address_phone;
        TextView address;

        TextView icon_name;
        ImageView check_del;

        LinearLayout address_edit_layout;
        LinearLayout address_del_layout;

        ImageView default_img;
        TextView set_default_address_text;
        LinearLayout set_default_address;
        RelativeLayout main_layout;

        public AddressViewHolder(View itemView) {
            super(itemView);
            main_layout = (RelativeLayout) itemView.findViewById(R.id.main_layout);
            address_edit_layout = (LinearLayout) itemView.findViewById(R.id.address_edit_layout);
            address_del_layout = (LinearLayout) itemView.findViewById(R.id.address_del_layout);
            address_name = (TextView) itemView.findViewById(R.id.address_name);
            check_del = (ImageView) itemView.findViewById(R.id.check_del);
            icon_name = (TextView) itemView.findViewById(R.id.icon_name);
            address_phone = (TextView) itemView.findViewById(R.id.address_phone);
            address = (TextView) itemView.findViewById(R.id.address);
            set_default_address = (LinearLayout) itemView.findViewById(R.id.set_default_address);
            default_img = (ImageView) itemView.findViewById(R.id.default_img);
            set_default_address_text = (TextView) itemView.findViewById(R.id.set_default_address_text);
        }

        @Override
        public void bindData(final AddressListBean data, int position) {
            super.bindData(data, position);
            address_name.setText(data.getConsigneeName());
            address_phone.setText(data.getConsigneeTel());
            main_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener == null) return;
                    onClickListener.onClick(data);
                }
            });
            if (data.isDefault()) {
                check_del.setVisibility(View.VISIBLE);
                icon_name.setVisibility(View.GONE);
            } else {
                check_del.setVisibility(View.GONE);
                icon_name.setVisibility(View.VISIBLE);
                icon_name.setText(data.getConsigneeName().substring(0, 1));
            }
            address.setText(mContext.getResources().getString(R.string.address_show_detail,
                    data.getProvince(), data.getCity(), data.getDistrict(), data.getLocation()));
            check_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setAddressTip(data.getAddressId(), mContext.getResources().getString(R.string.set_cancel_default_address_info), ACTION_DEFAULT, 1);
                }
            });
            icon_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!data.isDefault()) {
                        setAddressTip(data.getAddressId(), mContext.getResources().getString(R.string.set_default_address_info), ACTION_DEFAULT, 0);
                    }
//                    else {
//                        setAddressTip(data.getAddressId(), mContext.getResources().getString(R.string.set_cancel_default_address_info), ACTION_DEFAULT, 1);
//                    }
                }
            });
            address_del_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setAddressTip(data.getAddressId(), mContext.getResources().getString(R.string.del_address_info), ACTION_DEL, -1);
                }
            });
            address_edit_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(mContext, UpdateAddressActivity.class);
//                    intent.putExtra("type", UpdateAddressActivity.TYPE_UPDATE);
//                    intent.putExtra("address", data);
//                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new AddressViewHolder(inflater.inflate(R.layout.address_admin_item, parent, false));
    }

    private void setAddressTip(final String addressId, String message, final int action, final int type) {
        new AlertDialog.Builder(mContext)
                .setMessage(message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (action == ACTION_DEFAULT) {
                            if (onClickSetDefaultListener == null) return;
                            onClickSetDefaultListener.onClick(addressId, type);
                        } else {
                            if (onClickDelListener == null) return;
                            onClickDelListener.onClick(addressId);
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();

    }

    public interface onClickListener {
        void onClick(AddressListBean data);
    }

    public interface OnClickSetDefaultListener {
        void onClick(String addressId, int type);
    }

    public interface OnClickDelListener {
        void onClick(String addressId);
    }

}
