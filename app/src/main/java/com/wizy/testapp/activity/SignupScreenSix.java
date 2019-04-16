package com.wizy.testapp.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.wizy.testapp.R;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/*This Activity is not in use*/
public class SignupScreenSix extends BaseActivity {
    private static final String TAG = "SignupScreenSix";
    /*Referencing the widgets through the bind view API*/
    /*Start*/
    @BindView(R.id.tvSelectOccupation)
    TextView tvSelectOccupation;

    @BindView(R.id.tvSelectExp)
    TextView tvSelectExp;

    @BindView(R.id.tvSelectQualification)
    TextView tvSelectQualification;

    @BindView(R.id.tvSelectAreaQualification)
    TextView tvSelectAreaQualification;

    @BindView(R.id.edtUniversity)
    EditText edtUniversity;

    @BindView(R.id.imageForDocument)
    ImageView imageForDocument;

    @BindView(R.id.imageForOriginalDocument)
    ImageView imageForOriginalDocument;

    @BindView(R.id.Picture)
    CircleImageView imageView;

    @BindView(R.id.tvAddPicture)
    TextView addPics;
    int f, s, t ;

    @BindView(R.id.edtSpecialisation)
    EditText edtSpecialisation;

    @BindView(R.id.edtQualification)
    EditText edtQualification;

    @BindView(R.id.mainLayout)
    RelativeLayout relativeLayout;
    /*end*/

    /*Declaration of Variables*/
    private StorageReference mStorage;
    private PopupWindow popupWindowOccupation;
    private PopupWindow popupWindowExp;
    private PopupWindow popupWindowQualification;
    private PopupWindow popupWindowArea;
    private String[] selectedOccupation;
    private String[] selectedExp;
    private String[] selectedQualification;
    private String[] selectedArea;
    private int width;
    private boolean isImageFitToScreen;
    private String profileImageUrl, certificateImageUrl, proofImageUrl;
    private Uri resultUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screensix);
        ButterKnife.bind(this);
        /*Initialization*/
        selectedOccupation = getResources().getStringArray(R.array.occupation_arrays);
        selectedExp = getResources().getStringArray(R.array.exp_arrays);
        selectedQualification = getResources().getStringArray(R.array.qualification_arrays);
        selectedArea = getResources().getStringArray(R.array.area_arrays_for_grad);
        tvSelectOccupation.setText(selectedOccupation[0]);
        tvSelectExp.setText(selectedExp[0]);
        tvSelectQualification.setText(selectedQualification[0]);
        tvSelectAreaQualification.setText(selectedArea[0]);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        mStorage = FirebaseStorage.getInstance().getReference();
        profileImageUrl = "";
        certificateImageUrl = "";
        proofImageUrl = "";
        /*Initialization End*/

    }
    @OnClick(R.id.btnNext)
    public void onNextClick() {
        if (edtUniversity.getText().length() == 0) {
            Toast.makeText(this, R.string.enter_university, Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtSpecialisation.getText().length() == 0) {
            Toast.makeText(this, R.string.enter_specialisation, Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtQualification.getText().length() == 0) {
            Toast.makeText(this, R.string.enter_qualification_year, Toast.LENGTH_SHORT).show();
            return;
        }if(TextUtils.isEmpty(profileImageUrl)){
            Toast.makeText(this, R.string.profile_image, Toast.LENGTH_SHORT).show();
            return;
        }if(TextUtils.isEmpty(certificateImageUrl)){
            Toast.makeText(this, R.string.certificate, Toast.LENGTH_SHORT).show();
            return;
        }if(TextUtils.isEmpty(proofImageUrl)){
            Toast.makeText(this, R.string.proof_document, Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d(TAG, "onNextClick: " + profileImageUrl);
        Log.d(TAG, "onNextClick: " + certificateImageUrl);
        Log.d(TAG, "onNextClick: " + proofImageUrl);
        getAppPreferenceHelper().setUserScreenSix(tvSelectOccupation.getText().toString(), tvSelectExp.getText().toString(), tvSelectQualification.getText().toString(),
                tvSelectAreaQualification.getText().toString(), edtUniversity.getText().toString()
                , edtSpecialisation.getText().toString(), edtQualification.getText().toString()
                ,profileImageUrl, certificateImageUrl, proofImageUrl
        );

        startActivity(new Intent(this, SignupScreenSeven.class));
    }

    @OnClick(R.id.tvAddCert)
    public void setOnAddCert(){

        getImageFromAlbum(1);

    }

    @OnClick(R.id.tvAddProof)
    public void setOnAddProof(){
        getImageFromAlbum(2);

    }

    @OnClick(R.id.tvAddPicture)
    public void setOnAddpicture(){
        getImageFromAlbum(0);
    }
    private void getImageFromAlbum(int i){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, i);
    }
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if(data == null){
            return;
        }

        String des = "Croped" +".jpg";

        if(reqCode == 0){
            f = reqCode;
        }else if(reqCode == 1){
            f = reqCode;
        }else if(reqCode == 2){
            f = reqCode;
        }

        final Uri imageUri = data.getData();
        setImages(f, imageUri);
        /*UCrop.of(imageUri, Uri.fromFile(new File(getCacheDir(), des)))
                .withAspectRatio(4, 3)
                .withMaxResultSize(450 , 450)
                .start(this);

*/
        /*if (resultCode == RESULT_OK && reqCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
//            setImages(f, resultUri);


        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        }
*/

    }

    void setImages(int req, Uri result) {
        if (req == 0) {
            resultUri = result;
            Bitmap newImage = null;
            try {
                newImage = getResizedBitmap(getBitmapFromUri(resultUri),64, 64 );
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.requestLayout();
            imageView.getLayoutParams().height = 550;
            imageView.getLayoutParams().width = 550;
            imageView.setImageBitmap(newImage);
//            Picasso.get().load(resultUri).fit().centerCrop().into(imageView);
            addPics.setVisibility(View.GONE);
            threadForProfile.start();

        }else if(req == 1){
            resultUri = result;
            Uri resultUri = result;
            Bitmap newImage = null;
            try {
                newImage = getResizedBitmap(getBitmapFromUri(resultUri),64, 64 );
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageForDocument.setVisibility(View.VISIBLE);
            Picasso.get().load(resultUri).fit().centerCrop().into(imageForDocument);
//            imageForDocument.setImageBitmap(newImage);
//            imageForDocument.setScaleType(ImageView.ScaleType.FIT_XY);
            mStorage = mStorage.child("users/profiles/certifcates/" + FirebaseAuth.getInstance().getCurrentUser().getUid()+"Certificates");
            UploadTask uploadTask =  mStorage.putFile(resultUri);
            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return mStorage.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        certificateImageUrl = downloadUri.toString();
                        Log.d(TAG, "onComplete: "+ certificateImageUrl);
                        mStorage = FirebaseStorage.getInstance().getReference();

                    } else {
                        // Handle failures
                        // ...
                    }
                }

            });


            try {
                threadForCertificates.start();
            }catch (IllegalThreadStateException e){
                Toast.makeText(this, "Sorry, try again", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }else if(req == 2){
            resultUri = result;
            Uri resultUri = result;
            final InputStream imageStream;
            Bitmap newImage = null;
            try {
                newImage = getResizedBitmap(getBitmapFromUri(resultUri),64, 64 );
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageForOriginalDocument.setVisibility(View.VISIBLE);
            Picasso.get().load(resultUri).fit().centerCrop().into(imageForOriginalDocument);
            mStorage = mStorage.child("users/profiles/proof_document/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "Original");
            UploadTask uploadTask =  mStorage.putFile(resultUri);
            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return mStorage.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        proofImageUrl = downloadUri.toString();
                        Log.d(TAG, "onComplete: "+ proofImageUrl);
                        mStorage = FirebaseStorage.getInstance().getReference();
                    } else {
                        // Handle failures
                        // ...
                    }
                }

            });
//            imageForOriginalDocument.setImageBitmap(newImage);
//            imageForDocument.setScaleType(ImageView.ScaleType.FIT_XY);

        }
    }

    public Bitmap getResizedBitmap(Bitmap image, int bitmapWidth,
                                   int bitmapHeight) {
        return Bitmap.createScaledBitmap(image, bitmapWidth, bitmapHeight,
                true);
    }
    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    /*public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

        int width = bm.getWidth();
        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);

        return resizedBitmap;
    }*/

    Thread threadForProofDocument = new Thread(new Runnable() {
        @Override
        public void run() {
            final InputStream imageStream;


                mStorage = mStorage.child("users/profiles/proof_document/" + FirebaseAuth.getInstance().getCurrentUser().getUid()+ "proofDocument");
                UploadTask uploadTask =  mStorage.putFile(resultUri);
                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        // Continue with the task to get the download URL
                        return mStorage.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            proofImageUrl = downloadUri.toString();
                            Log.d(TAG, "onComplete: "+ proofImageUrl);
                        } else {
                            // Handle failures
                            // ...
                        }
                    }

                });
            mStorage = FirebaseStorage.getInstance().getReference();
//            threadForCertificates.suspend();



        }
    });


    Thread threadForCertificates = new Thread(new Runnable() {
        @Override
        public void run() {

            mStorage = mStorage.child("users/profiles/certifcates/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "certificate");
            mStorage.putFile(resultUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return mStorage.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        certificateImageUrl = downloadUri.toString();
                        Log.d(TAG, "onComplete: "+ certificateImageUrl);

                    } else {
                        // Handle failures
                        // ...
                    }
                }


            });;
mStorage = FirebaseStorage.getInstance().getReference();
//threadForCertificates.suspend();


        }
    });


    Thread threadForProfile = new Thread(new Runnable() {
        @Override
        public void run() {

            mStorage = mStorage.child("users/profiles/profile_image/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "UserImage");
            UploadTask uploadTask =  mStorage.putFile(resultUri);
            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return mStorage.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        profileImageUrl = downloadUri.toString();
                        Log.d(TAG, "onComplete: "+ profileImageUrl);

                    } else {
                        // Handle failures
                        // ...
                    }
                }

            });
            mStorage = FirebaseStorage.getInstance().getReference();
//            threadForCertificates.suspend();

        }
    });





    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.tvSelectOccupation)
    public void onOccupationClick(View v) {
        Drawable img = this.getResources().getDrawable( R.mipmap.chevron_with_circle_upxhdpi );
        img.setBounds( 0, 0, 120, 120 );
        tvSelectOccupation.setCompoundDrawables(null,null, img,null);
        PopupWindow popUp = popupOccupation(v);
        popUp.showAsDropDown(v, 0, 10);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.tvSelectExp)
    public void onExpClick(View v) {
        Drawable img = this.getResources().getDrawable( R.mipmap.chevron_with_circle_upxhdpi );
        img.setBounds( 0, 0, 120, 120 );
        tvSelectExp.setCompoundDrawables(null,null, img,null);
        PopupWindow popUp = popupWindowExp();
        popUp.showAsDropDown(v, 0, 10);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.tvSelectQualification)
    public void onQualificationClick(View v) {
        Drawable img = this.getResources().getDrawable( R.mipmap.chevron_with_circle_upxhdpi );
        img.setBounds( 0, 0, 120, 120 );
        tvSelectQualification.setCompoundDrawables(null,null,img,null);

        PopupWindow popUp = popupWindowQualification();
        popUp.showAsDropDown(v, 0, 10);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.tvSelectAreaQualification)
    public void onAreaClick(View v) {
        Drawable img = this.getResources().getDrawable( R.mipmap.chevron_with_circle_upxhdpi );
        img.setBounds( 0, 0, 120, 120 );
        tvSelectAreaQualification.setCompoundDrawables(null,null, img,null);
        PopupWindow popUp = popupWindowArea();
        popUp.showAsDropDown(v, 0, 10);
    }

    // initialize a pop up window type
    private PopupWindow popupOccupation(View view) {

        popupWindowOccupation = new PopupWindow(this);
        popupWindowOccupation.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.reactangle_cert));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_dropdown_items,
                getResources().getStringArray(R.array.occupation_arrays));
        popupWindowOccupation.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDismiss() {
                Drawable img = getDrawable( R.mipmap.chevron_with_circle_downxhdpi );
                img.setBounds( 0, 0, 120, 120 );
                tvSelectOccupation.setCompoundDrawables(null,null,img,null);

            }
        });
        ListView listViewSort = new ListView(this);
        listViewSort.setDivider(null);
        listViewSort.setAdapter(adapter);
        listViewSort.setOnItemClickListener(onItemClickListener());
        setPopWidth(popupWindowOccupation);
        popupWindowOccupation.setFocusable(true);
        popupWindowOccupation.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindowOccupation.setContentView(listViewSort);

        return popupWindowOccupation;
    }

    private AdapterView.OnItemClickListener onItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Drawable img = getDrawable( R.mipmap.chevron_with_circle_downxhdpi );
                img.setBounds( 0, 0, 120, 120 );
                tvSelectOccupation.setText(selectedOccupation[position]);
                tvSelectOccupation.setCompoundDrawables(null,null,img,null);

                if (popupWindowOccupation != null) {
                    popupWindowOccupation.dismiss();
                }
            }
        };
    }

    // initialize a pop up window type
    private PopupWindow popupWindowExp() {
        popupWindowExp = new PopupWindow(this);
        popupWindowExp.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.reactangle_cert));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_dropdown_items,
                getResources().getStringArray(R.array.exp_arrays));
        ListView listViewSort = new ListView(this);
        listViewSort.setDivider(null);
        listViewSort.setAdapter(adapter);
        listViewSort.setOnItemClickListener(onItemClickListenerExp());
        setPopWidth(popupWindowExp);
        popupWindowExp.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDismiss() {
                Drawable img = getDrawable( R.mipmap.chevron_with_circle_downxhdpi );
                img.setBounds( 0, 0, 120, 120 );
                tvSelectExp.setCompoundDrawables(null,null,img,null);

            }
        });
        popupWindowExp.setFocusable(true);
        popupWindowExp.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindowExp.setContentView(listViewSort);

        return popupWindowExp;
    }

    private AdapterView.OnItemClickListener onItemClickListenerExp() {
        return new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Drawable img = getDrawable( R.mipmap.chevron_with_circle_downxhdpi );
                img.setBounds( 0, 0, 120, 120 );
                tvSelectExp.setText(selectedExp[position]);
                tvSelectExp.setCompoundDrawables(null,null, img,null);

                if (popupWindowExp != null) {
                    popupWindowExp.dismiss();
                }
            }
        };
    }

    // initialize a pop up window type
    private PopupWindow popupWindowQualification() {
        popupWindowQualification = new PopupWindow(this);
        popupWindowQualification.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.reactangle_cert));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_dropdown_items,
                getResources().getStringArray(R.array.qualification_arrays));
        ListView listViewSort = new ListView(this);
        listViewSort.setDivider(null);
        listViewSort.setAdapter(adapter);
        listViewSort.setOnItemClickListener(onItemClickListenerQualification());
        setPopWidth(popupWindowQualification);
        popupWindowQualification.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDismiss() {
                Drawable img = getDrawable( R.mipmap.chevron_with_circle_downxhdpi );
                img.setBounds( 0, 0, 120, 120 );
                tvSelectQualification.setCompoundDrawables(null,null,img,null);

            }
        });
        popupWindowQualification.setFocusable(true);
        popupWindowQualification.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindowQualification.setContentView(listViewSort);

        return popupWindowQualification;
    }

    private AdapterView.OnItemClickListener onItemClickListenerQualification() {
        return new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Drawable img = getDrawable( R.mipmap.chevron_with_circle_downxhdpi );
                img.setBounds( 0, 0, 120, 120 );
                tvSelectQualification.setText(selectedQualification[position]);
                tvSelectQualification.setCompoundDrawables(null,null, img,null);

                if (popupWindowQualification != null) {
                    popupWindowQualification.dismiss();
                }
            }
        };
    }

    // initialize a pop up window type
    private PopupWindow popupWindowArea() {
        popupWindowArea = new PopupWindow(this);
        popupWindowArea.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.reactangle_cert));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_dropdown_items,
                getResources().getStringArray(R.array.area_arrays_for_grad));
        ListView listViewSort = new ListView(this);
        listViewSort.setDivider(null);
        listViewSort.setAdapter(adapter);
        listViewSort.setOnItemClickListener(onItemClickListenerArea());
        setPopWidth(popupWindowArea);
        popupWindowArea.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDismiss() {
                Drawable img = getDrawable( R.mipmap.chevron_with_circle_downxhdpi );
                img.setBounds( 0, 0, 120, 120 );
                tvSelectAreaQualification.setCompoundDrawables(null,null,img,null);

            }
        });
        popupWindowArea.setFocusable(true);
        popupWindowArea.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindowArea.setContentView(listViewSort);

        return popupWindowArea;
    }

    private AdapterView.OnItemClickListener onItemClickListenerArea() {
        return new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Drawable img = getDrawable( R.mipmap.chevron_with_circle_downxhdpi );
                img.setBounds( 0, 0, 120, 120 );
                tvSelectAreaQualification.setText(selectedArea[position]);
                tvSelectAreaQualification.setCompoundDrawables(null,null, img,null);

                if (popupWindowArea != null) {
                    popupWindowArea.dismiss();
                }
            }
        };
    }

    // Set Pop up width according to resolution
    private void setPopWidth(PopupWindow popupWindow) {
        if (width == 1440) {
            popupWindow.setWidth(width - 200);
        } else if (width == 1080) {
            popupWindow.setWidth(width - 150);
        } else if (width == 720) {
            popupWindow.setWidth(width - 100);
        } else {
            popupWindow.setWidth(width - 100);
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
