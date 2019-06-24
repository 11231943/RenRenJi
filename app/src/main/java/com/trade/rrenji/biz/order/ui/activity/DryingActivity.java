package com.trade.rrenji.biz.order.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gelitenight.superrecyclerview.GridSpacingDecoration;
import com.gelitenight.superrecyclerview.SuperRecyclerView;
import com.google.gson.Gson;
import com.moonster.imagepicker.ImagePicker;
import com.moonster.imagepicker.data.ImageBean;
import com.moonster.imagepicker.data.ImagePickType;
import com.moonster.imagepicker.utils.GlideImagePickerDisplayer;
import com.trade.rrenji.MiGoApplication;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.goods.NetAccessoryListBean;
import com.trade.rrenji.bean.upload.NetUploadBean;
import com.trade.rrenji.biz.account.ui.activity.AccountActivity;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.base.NetBaseResultBean;
import com.trade.rrenji.biz.order.model.OrderModel;
import com.trade.rrenji.biz.order.model.OrderModelImpl;
import com.trade.rrenji.biz.order.ui.view.StarBar;
import com.trade.rrenji.biz.upload.model.UploadModel;
import com.trade.rrenji.biz.upload.model.UploadModelImpl;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.GlideUtils;
import com.trade.rrenji.utils.ThreadPoolManager;
import com.trade.rrenji.utils.ViewUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 晒单页面
 */
@ContentView(R.layout.drying_main_layout)
public class DryingActivity extends BaseActivity {

    @ViewInject(R.id.recycler_view_photo)
    public RecyclerView mPhotos;

    @ViewInject(R.id.starBar)
    public StarBar mStarBar;
    @ViewInject(R.id.starBarTxt)
    public TextView mStarBarTxt;
    @ViewInject(R.id.goods_image)
    public ImageView mGoodsImage;

    @ViewInject(R.id.comment)
    public EditText mComment;

    UploadModel mUploadModel;
    OrderModel mOrderModel;

    private static int REQUEST_CODE = 10001;
    PhotoAdapter mPhotoAdapter;
    List<ImageBean> mPhotoPath = new ArrayList<ImageBean>();
    private String mOrderId;
    private String mGoodsImg;
    ProgressDialog mProgressDialog;
    private List<String> mUplodPath = new ArrayList<String>();
    List<ImageBean> mResultList;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("发表晒单");
        mOrderId = getIntent().getStringExtra("orderId");
        mGoodsImg = getIntent().getStringExtra("goodsImg");
        mUploadModel = new UploadModelImpl(this);
        mOrderModel = new OrderModelImpl(this);
        init();
    }

    private void init() {
        GlideUtils.getInstance().loadImageUrl(this, mGoodsImg, R.drawable.shoiye_zw, mGoodsImage);
        mStarBar.setStarMark(5);
        mStarBar.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
            @Override
            public void onStarChange(float mark) {
                if (mark <= 1) {
                    mStarBarTxt.setText("非常不好");
                } else if (mark > 1 && mark <= 2) {
                    mStarBarTxt.setText("不好");
                } else if (mark > 2 && mark <= 3) {
                    mStarBarTxt.setText("一般");
                } else if (mark > 3 && mark <= 4) {
                    mStarBarTxt.setText("满意");
                } else if (mark > 4 && mark <= 5) {
                    mStarBarTxt.setText("非常满意");
                }
            }
        });
        mPhotos.setLayoutManager(new GridLayoutManager(this, 3));
        mPhotos.addItemDecoration(new GridSpacingDecoration(
                ViewUtils.dip2px(this, 12), ViewUtils.dip2px(this, 4)));
        mPhotoAdapter = new PhotoAdapter(this);
        mPhotoAdapter.setOnItemClickListener(new RecyclerListAdapter.OnItemClickListener<ImageBean>() {
            @Override
            public void onItemClick(View v, ImageBean data) {
                if (TextUtils.isEmpty(data.getImagePath())) {
                    pick();
                } else {
//                    Intent intent = new Intent(PostComposeActivity.this, CoverRevealActivity.class);
//                    intent.putExtra(CoverRevealActivity.KEY_ITEMS, mPhotoAdapter.getAllPhotoPath());
//                    intent.putExtra(CoverRevealActivity.KEY_POSITION,
//                            mPhotoAdapter.getChildPosition(data));
//                    intent.putExtra(CoverRevealActivity.KEY_SHOWDELETE, true);
//                    intent.putExtra(CoverRevealActivity.KEY_SHOWSAVELOCAL, false);
//                    intent.putExtra(CoverRevealActivity.KEY_LOCALFILE, true);
//                    startActivityForResult(intent, REQUEST_NEED_UPDATE_CHOOSE_ITEM);
                }
            }
        });
        mPhotos.setAdapter(mPhotoAdapter);
    }

    private void pick() {
        new ImagePicker.Builder()
                .pickType(ImagePickType.MUTIL)//设置选取类型(拍照、单选、多选)
                .maxNum(9)//设置最大选择数量(拍照和单选都是1，修改后也无效)
                .needCamera(true)//是否需要在界面中显示相机入口(类似微信)
                .cachePath(MiGoApplication.CACHE_PATH)//自定义缓存路径
                .doCrop(1, 1, 300, 300)//裁剪功能需要调用这个方法，多选模式下无效
                .displayer(new GlideImagePickerDisplayer())//自定义图片加载器，默认是Glide实现的,可自定义图片加载器
                .build()
                .start(this, REQUEST_CODE, REQUEST_CODE);

    }

    //重写Activity或Fragment中OnActivityResult()
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == REQUEST_CODE && data != null) {
            //获取选择的图片数据
            mResultList = data.getParcelableArrayListExtra(ImagePicker.INTENT_RESULT_DATA);
            mPhotoPath.clear();
            mPhotoPath.addAll(mResultList);
            mPhotoAdapter.clear();
            mPhotoAdapter.addAll(mResultList);
            buildPhoto(mResultList);
        }
    }

    private int upSize = 0;

    private void buildPhoto(List<ImageBean> resultList) {
        upSize = resultList.size();
        for (ImageBean bean : resultList) {
            ThreadPoolManager.getInstance().addTask(new MyRunnable(bean));
        }
        showUploadDialog();
    }

    private void showUploadDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("上传图像");
        mProgressDialog.setMessage("上传中...");
        mProgressDialog.setIndeterminate(true);// 是否形成一个加载动画  true表示不明确加载进度形成转圈动画  false 表示明确加载进度
        mProgressDialog.setCancelable(false);//点击返回键或者dialog四周是否关闭dialog  true表示可以关闭 false表示不可关闭
        mProgressDialog.show();
    }

    private class MyRunnable implements Runnable {
        ImageBean mBean;

        MyRunnable(ImageBean bean) {
            mBean = bean;
        }

        @Override
        public void run() {
            Log.d("MyRunnable", "upload  = " + mBean.getImagePath());
            mUploadModel.upload(DryingActivity.this, mBean.getImagePath(), new XUtils.ResultListener() {
                @Override
                public void onResponse(String result) {
                    try {
                        upSize--;
                        if (mProgressDialog != null && upSize <= 0) {
                            mProgressDialog.dismiss();
                        }
                        Gson gson = new Gson();
                        NetUploadBean uploadBean = gson.fromJson(result, NetUploadBean.class);
                        if (uploadBean.getCode().equals(Contetns.RESPONSE_OK)) {
                            String mPathStr = uploadBean.getResult().getUrl();
                            mUplodPath.add(mPathStr);
                            Toast.makeText(DryingActivity.this, "上传图片成功！", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        upSize--;
                        e.printStackTrace();
                        Toast.makeText(DryingActivity.this, "上传失败！", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(Throwable error) {
                    upSize--;
                    Toast.makeText(DryingActivity.this, "上传失败！", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_dry:
                if (upSize <= 0) {
                    share();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void share() {
        if (TextUtils.isEmpty(mComment.getText().toString().trim())) {
            Toast.makeText(DryingActivity.this, "辛苦您给我们留下珍贵的评论吧！", Toast.LENGTH_SHORT).show();
            return;
        }
        mOrderModel.share(DryingActivity.this, mOrderId, mComment.getText().toString(), mUplodPath, "深圳", new XUtils.ResultListener() {
            @Override
            public void onResponse(String result) {
                try {

                    Gson gson = new Gson();
                    final NetBaseResultBean netShareBean = gson.fromJson(result, NetBaseResultBean.class);
                    if (Integer.valueOf(netShareBean.getCode()) == Contetns.STATE_OK) {
                        Toast.makeText(DryingActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 1000);
                    } else {
                        Toast.makeText(DryingActivity.this, "评论失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(DryingActivity.this, "评论失败", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onError(Throwable error) {
                Toast.makeText(DryingActivity.this, "评论失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void attachPresenter() {

    }

    @Override
    protected void detachPresenter() {

    }

    private class PhotoAdapter extends RecyclerListAdapter<ImageBean> {
        public static final int MAX_ITEM_COUNT = 9;

        private static final int ITEM_ADD = 1;
        private static final int ITEM_NORMAL = 2;
        private Context mContext;

        public PhotoAdapter(Context context) {
            super(context);
            mContext = context;
        }

        public String[] getAllPhotoPath() {
            int len = super.getItemCount();
            String[] path = new String[len];
            for (int i = 0; i < len; i++) {
                path[i] = getItem(i).getImagePath();
            }

            return path;
        }

        @Override
        public int getItemCount() {
            int count = super.getItemCount();
            if (count < MAX_ITEM_COUNT) {
                count++;
            }
            return count;
        }

        public int getRealItemCount() {
            return super.getItemCount();
        }

        @Override
        public ImageBean getItem(int position) {
            return getItemViewType(position) == ITEM_ADD ? null : super.getItem(position);
        }

        @Override
        public int getItemViewType(int position) {
            if (super.getItemCount() < MAX_ITEM_COUNT && position == super.getItemCount()) {
                return ITEM_ADD;
            }
            return ITEM_NORMAL;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.subject_compose_photo_item, parent, false);
            return viewType == ITEM_ADD ? new PhotoAddViewHolder(view) : new PhotoViewHolder(view);
        }

        public class PhotoViewHolder extends ViewHolder {
            ImageView mDraweeView;

            public PhotoViewHolder(View itemView) {
                super(itemView);
                mDraweeView = (ImageView) itemView.findViewById(R.id.imgQueue);
            }

            @Override
            public void bindData(ImageBean data, int position) {
                super.bindData(data, position);
                Glide.with(mContext).load(new File(data.getImagePath())).into(mDraweeView);

            }
        }

        public class PhotoAddViewHolder extends ViewHolder {
            ImageView mDraweeView;

            public PhotoAddViewHolder(View itemView) {
                super(itemView);
                mDraweeView = (ImageView) itemView.findViewById(R.id.imgQueue);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pick();
                    }
                });
            }

            @Override
            public void bindData(ImageBean data, int position) {
                super.bindData(data, position);
                GlideUtils.getInstance().loadIcon(mContext, R.drawable.add_photo, R.drawable.ic_launcher, mDraweeView);
//                FrescoUtils.displayImageWithRes(
//                        R.drawable.pub_add_photo, mDraweeView);
            }
        }
    }
}
