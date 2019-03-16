package com.moonster.imagepicker.ui.grid.view;

import com.moonster.imagepicker.base.activity.IImageBaseView;
import com.moonster.imagepicker.data.ImageBean;
import com.moonster.imagepicker.data.ImageFloderBean;
import com.moonster.imagepicker.data.ImagePickerOptions;

import java.util.List;

/**
 * Created by LWK
 * TODO ImageDataActivity的View层接口
 */

public interface IImageDataView extends IImageBaseView
{
    ImagePickerOptions getOptions();

    void startTakePhoto();

    void showLoading();

    void hideLoading();

    void onDataChanged(List<ImageBean> dataList);

    void onFloderChanged(ImageFloderBean floderBean);

    void onImageClicked(ImageBean imageBean, int position);

    void onSelectNumChanged(int curNum);

    void warningMaxNum();
}
