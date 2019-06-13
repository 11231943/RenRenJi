//package com.trade.rrenji.biz.photo;
//
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.PorterDuff;
//import android.graphics.PorterDuffXfermode;
//import android.graphics.Rect;
//import android.graphics.RectF;
//import android.os.Bundle;
//import android.support.v7.app.ActionBar;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.edmodo.cropper.CropImageView;
//import com.trade.rrenji.biz.base.BaseActivity;
//import com.trade.rrenji.utils.GlideUtils;
//import java.io.IOException;
//import com.trade.rrenji.R;
///**
// * 图片上传编辑，剪切
// *
// * @author 浩浩
// * @version v.4.1 2015-4-16
// * @since v.4.1
// */
//public class ImageEditActivity extends BaseActivity {
//    public static final String SRC_FILE_KEY = "src_file";
//    public static final String DEST_FILE_KEY = "dest_file";
//    public static final String CROP_OPTION_KEY = "crop_option";
//
//    String mSrcFilePath;
//    String mDestFilePath;
//    int mCropOption;
//
//    private CropImageView mImageView;
//    private Button[] mARButtons;
//    private int[] ar_x = {9, 3, 1, 4, 16};
//    private int[] ar_y = {16, 4, 1, 3, 9};
//
//
//    @Override
//    protected void attachPresenter() {
//
//    }
//
//    @Override
//    protected void detachPresenter() {
//
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
////        setSwipeable(false);
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_image_edit);
//
//
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.black));
//        actionBar.setHomeAsUpIndicator(R.drawable.profile_back_btn);
//        setActionBarTitle("", Color.WHITE);
//
////        initTitleNav();
//        initImageView();
//    }
//
//    public void initImageView() {
//        mImageView = (CropImageView) findViewById(R.id.image_editor_photo);
//        mImageView.setImageBitmap(GlideUtils.getBitmap(this, R.drawable.default_photo));
//        Intent intent = getIntent();
//        mSrcFilePath = intent.getStringExtra(SRC_FILE_KEY);
//        mDestFilePath = intent.getStringExtra(DEST_FILE_KEY);
//        mCropOption = intent.getIntExtra(CROP_OPTION_KEY,
//                GlideUtils.CROP_OPTIONS_SQUARE_ONLY);
//
//        if (mSrcFilePath == null) {
//            Toast.makeText(ImageEditActivity.this, R.string.src_file_empty,
//                    Toast.LENGTH_LONG).show();
//            setResult(RESULT_CANCELED);
//            finish();
//            return;
//        }
//        if (mDestFilePath == null) {
//            Toast.makeText(ImageEditActivity.this, R.string.dest_file_empty,
//                    Toast.LENGTH_LONG).show();
//            setResult(RESULT_CANCELED);
//            finish();
//            return;
//        }
//
//        Bitmap bitmap = GlideUtils.getScaledRotatedBitmap(this, mSrcFilePath, 1280, 640);
//        if (bitmap == null) {
//            Toast.makeText(this, R.string.src_file_decode_error, Toast.LENGTH_LONG).show();
//            setResult(RESULT_CANCELED);
//            finish();
//            return;
//        }
////        int w = bitmap.getWidth(), h = bitmap.getHeight();
////        Bitmap roundBitmap = getRoundedCornerBitmap(this, bitmap, 100, w, h, true, false, true, false);
//        mImageView.setImageBitmap(bitmap);
//
//        View mRotateLeftBtn = findViewById(R.id.image_editor_left);
//        mRotateLeftBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                mImageView.rotateImage(270);
//            }
//        });
//
//        View mRotateRightBtn = findViewById(R.id.image_editor_right);
//        mRotateRightBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                mImageView.rotateImage(90);
//            }
//        });
//
//        mARButtons = new Button[5];
//        mARButtons[0] = (Button) findViewById(R.id.ar_9_16);
//        mARButtons[1] = (Button) findViewById(R.id.ar_3_4);
//        mARButtons[2] = (Button) findViewById(R.id.ar_1_1);
//        mARButtons[3] = (Button) findViewById(R.id.ar_4_3);
//        mARButtons[4] = (Button) findViewById(R.id.ar_16_9);
//        for (int i = 0; i < 5; i++) {
//            final int t = i;
//            mARButtons[t].setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    for (int j = 0; j < 5; j++) {
//                        mARButtons[j].setSelected(j == t);
//                    }
//                    mImageView.setAspectRatio(ar_x[t], ar_y[t]);
//                }
//            });
//        }
//
//        mARButtons[2].performClick();
//        if (mCropOption == GlideUtils.CROP_OPTIONS_SQUARE_ONLY) {
//            findViewById(R.id.ar_button_con).setVisibility(View.GONE);
//        }
//    }
//
//    public static Bitmap getRoundedCornerBitmap(Context context, Bitmap input, int pixels, int w, int h, boolean squareTL, boolean squareTR, boolean squareBL, boolean squareBR) {
//        Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(output);
//        final float densityMultiplier = context.getResources().getDisplayMetrics().density;
//        final int color = 0xff424242;
//        final Paint paint = new Paint();
//        final Rect rect = new Rect(0, 0, w, h);
//        final RectF rectF = new RectF(rect);
//        //make sure that our rounded corner is scaled appropriately
//        final float roundPx = pixels * densityMultiplier;
//        paint.setAntiAlias(true);
//        canvas.drawARGB(0, 0, 0, 0);
//        paint.setColor(color);
//        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
//        //draw rectangles over the corners we want to be square
//        if (squareTL) {
//            canvas.drawRect(0, 0, w / 2, h / 2, paint);
//        }
//        if (squareTR) {
//            canvas.drawRect(w / 2, 0, w, h / 2, paint);
//        }
//        if (squareBL) {
//            canvas.drawRect(0, h / 2, w / 2, h, paint);
//        }
//        if (squareBR) {
//            canvas.drawRect(w / 2, h / 2, w, h, paint);
//        }
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawBitmap(input, 0, 0, paint);
//        return output;
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.next, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                cancel();
//                return false;
//            case R.id.menu_next:
//
//                try {
//                    Bitmap croppedBitmap = mImageView.getCroppedImage();
////                    String newFile = mDestFilePath.substring(0, mDestFilePath.length() - 4) + System.currentTimeMillis() + ".jpg";
////                    File file = PhotoSelector.compressAndSaveToFile(croppedBitmap,
////                            newFile, 100, 75);
////                    Log.d("onOptionsItemSelected", file.getAbsolutePath());
////                    Intent intent = new Intent(ImageEditActivity.this, ImageAddTagActivity.class);
////                    intent.putExtra("mDestFilePath", file.getAbsolutePath());
////                    startActivityForResult(intent, RESULT_OK);
//                    Intent intent = getIntent();
//                    intent.putExtra("mDestFilePath", mDestFilePath);
//                    setResult(RESULT_OK, intent);
//                    GlideUtils.compressAndSaveToFile(croppedBitmap,
//                            mDestFilePath, 100, 75);
//                    setResult(RESULT_OK, getIntent());
//                } catch (IOException e) {
//                    Log.e("Error save", e.getMessage());
//                    Toast.makeText(ImageEditActivity.this, R.string.edit_image_failed,
//                            Toast.LENGTH_SHORT).show();
//                } finally {
//                    finish();
//                }
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        data.putExtra("mDestFilePath", mDestFilePath);
//        setResult(RESULT_OK, data);
////        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    @Override
//    public void onBackPressed() {
//        cancel();
//    }
//
//    private void cancel() {
//        new android.support.v7.app.AlertDialog.Builder(this)
//                .setMessage(R.string.cancel_confirm)
//                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        setResult(RESULT_CANCELED);
//                        finish();
//                    }
//                })
//                .setNegativeButton(R.string.cancel, null)
//                .show();
//    }
//
//    protected void initTitleNav() {
//        setActionBarTitle(R.string.edit_image);
//    }
//}
