package com.moonster.imagepicker.ui.grid.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.moonster.imagepicker.R;
import com.moonster.imagepicker.base.adapter.IImagePickerItemView;
import com.moonster.imagepicker.base.adapter.ImagePickerViewHolder;
import com.moonster.imagepicker.data.ImageContants;
import com.moonster.imagepicker.data.ImageBean;
import com.moonster.imagepicker.data.ImageDataModel;
import com.moonster.imagepicker.data.ImagePickType;
import com.moonster.imagepicker.data.ImagePickerOptions;
import com.moonster.imagepicker.ui.grid.view.IImageDataView;

/**
 * Created by LWK
 * TODO 显示图片的GridItem
 */
public class ImageContentItemView implements IImagePickerItemView<ImageBean>
{
    private IImageDataView mViewImpl;
    private ImagePickerOptions mOptions;
    private ImageDataAdapter mAdapter;

    public ImageContentItemView(IImageDataView viewImpl, ImageDataAdapter adapter)
    {
        this.mViewImpl = viewImpl;
        this.mOptions = mViewImpl.getOptions();
        this.mAdapter = adapter;
    }

    @Override
    public int getItemViewLayoutId()
    {
        return R.layout.layout_image_data_content_listitem;
    }

    @Override
    public boolean isForViewType(ImageBean item, int position)
    {
        return mOptions != null && (!mOptions.isNeedCamera() || (mOptions.isNeedCamera() && position != 0));
    }

    @Override
    public void setData(ImagePickerViewHolder holder, final ImageBean imageBean, final int position, ViewGroup parent)
    {
        ImageView imgContent = holder.findView(R.id.img_imagepicker_grid_content);
        View viewIndicator = holder.findView(R.id.ck_imagepicker_grid_content);

        //显示UI
        if (imageBean != null)
            ImageDataModel.getInstance().getDisplayer()
                    .display(holder.getContext(), imageBean.getImagePath(), imgContent
                            , R.drawable.glide_default_picture, R.drawable.glide_default_picture
                            , ImageContants.DISPLAY_THUMB_SIZE, ImageContants.DISPLAY_THUMB_SIZE);
        imgContent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mViewImpl != null)
                    mViewImpl.onImageClicked(imageBean, position);
            }
        });

        if (mOptions.getType() == ImagePickType.SINGLE)
        {
            viewIndicator.setVisibility(View.GONE);
        } else
        {
            viewIndicator.setVisibility(View.VISIBLE);
            if (ImageDataModel.getInstance().hasDataInResult(imageBean))
                viewIndicator.setBackgroundResource(R.drawable.ck_imagepicker_grid_selected);
            else
                viewIndicator.setBackgroundResource(R.drawable.ck_imagepicker_grid_normal);

            viewIndicator.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int curNum = ImageDataModel.getInstance().getResultNum();
                    if (curNum == mOptions.getMaxNum())
                    {
                        mViewImpl.warningMaxNum();
                        return;
                    } else
                    {
                        if (ImageDataModel.getInstance().hasDataInResult(imageBean))
                            ImageDataModel.getInstance().delDataFromResult(imageBean);
                        else
                            ImageDataModel.getInstance().addDataToResult(imageBean);
                        mAdapter.notifyDataSetChanged();
                        mViewImpl.onSelectNumChanged(ImageDataModel.getInstance().getResultNum());
                    }
                }
            });
        }
    }
}
