package com.wizy.testapp.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.wizy.testapp.R;
import com.wizy.testapp.activity.BaseActivity;
import com.wizy.testapp.activity.SignupScreenSeven;
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

public class SignupScreenSixForHobbies extends BaseActivity {
    private static final String TAG = "SignupScreenSixForHobbi";
    /*Referencing the widgets through the bind view API*/
    /*Start*/
    @BindView(R.id.tvSelectOccupation)
    TextView tvSelectOccupation;
    @BindView(R.id.Picture)
    CircleImageView imageView;

    @BindView(R.id.tvSelectExp)
    TextView tvSelectExp;

    @BindView(R.id.tvSelectQualification)
    TextView tvSelectQualification;

    @BindView(R.id.tvSelectAreaQualification)
    TextView tvSelectAreaQualification;

    @BindView(R.id.tvAddProof)
    TextView addCert;

    @BindView(R.id.tvAddCert)
    TextView addProof;


    @BindView(R.id.imageForDocument)
    ImageView imageForDocument;

    @BindView(R.id.imageForOriginalDocument)
    ImageView imageForOriginalDocument;

    @BindView(R.id.edtUniversity)
    EditText edtUniversity;

    @BindView(R.id.bundleProfileImage)
    RelativeLayout addPics;
    int f, s, t ;

    @BindView(R.id.edtSpecialisation)
    EditText edtSpecialisation;

    @BindView(R.id.edtQualification)
    EditText edtQualification;
    /*end*/
    /*Declaration of Variables*/

    private PopupWindow popupWindowOccupation;
    private PopupWindow popupWindowExp;
    private PopupWindow popupWindowQualification;
    private PopupWindow popupWindowArea;
    private String[] selectedOccupation;
    private String[] selectedExp;
    private String[] selectedQualification;
    private String[] selectedArea;
    private int width;
    private StorageReference mStorage;
    private String profileImageUrl, certificateImageUrl, proofImageUrl;
    private Uri resultUri ;
    String category;

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
        Bundle data = getIntent().getExtras();
        category = data.getString("category");
        Log.d(TAG, "onCreate: " + category);

        width = displayMetrics.widthPixels;
        mStorage = FirebaseStorage.getInstance().getReference();
        profileImageUrl = "";
        certificateImageUrl = "";
        proofImageUrl = "";
        /*Initialization end*/

    }
    /*The below method is for to validate the user to select the university, specialization, qualification, profile image,
    * occupation, experience, area of graduation certification image, original document image.
    * */

    @OnClick(R.id.btnNext)
    public void onNextClick() {
        String occupation, experience, qualification, areaQualification;
        occupation = tvSelectOccupation.getText().toString();
        experience = tvSelectExp.getText().toString();
        qualification = tvSelectQualification.getText().toString();
        areaQualification = tvSelectAreaQualification.getText().toString();

        if(occupation.equals(getResources().getStringArray(R.array.occupation_arrays)[0])){
            Snackbar snackbar;
            snackbar = Snackbar.make((findViewById(android.R.id.content)), "Please select the occupation" , Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }if(experience.equals(getResources().getStringArray(R.array.exp_arrays)[0])){
            Snackbar snackbar;
            snackbar = Snackbar.make((findViewById(android.R.id.content)), "Please select the experience" , Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;

        }if(qualification.equals(getResources().getStringArray(R.array.qualification_arrays)[0])){
            Snackbar snackbar;
            snackbar = Snackbar.make((findViewById(android.R.id.content)), "Please select the qualification" , Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;

        }if(areaQualification.equals(getResources().getStringArray(R.array.area_arrays_for_grad)[0])){
            Snackbar snackbar;
            snackbar = Snackbar.make((findViewById(android.R.id.content)), "Please select the area qualification" , Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;

        }
        if (edtUniversity.getText().length() == 0) {
            Snackbar snackbar;
            snackbar = Snackbar.make((findViewById(android.R.id.content)), getString(R.string.enter_university) , Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }
        if (edtSpecialisation.getText().length() == 0) {
            Snackbar snackbar;
            snackbar = Snackbar.make((findViewById(android.R.id.content)), getString(R.string.enter_specialisation), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }
        if (edtQualification.getText().length() == 0) {
            Snackbar snackbar;
            snackbar = Snackbar.make((findViewById(android.R.id.content)), getString(R.string.enter_qualification_year), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }if(TextUtils.isEmpty(profileImageUrl)){
            Snackbar snackbar;
            snackbar = Snackbar.make((findViewById(android.R.id.content)), getString(R.string.profile_image), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }if(TextUtils.isEmpty(certificateImageUrl)){
            Snackbar snackbar;
            snackbar = Snackbar.make((findViewById(android.R.id.content)), getString(R.string.certificate), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }if(TextUtils.isEmpty(proofImageUrl)){
            Snackbar snackbar;
            snackbar = Snackbar.make((findViewById(android.R.id.content)), getString(R.string.proof_document), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }

            getAppPreferenceHelper().setUserScreenSix(tvSelectOccupation.getText().toString(), tvSelectExp.getText().toString(), tvSelectQualification.getText().toString(),
                    tvSelectAreaQualification.getText().toString(), edtUniversity.getText().toString(), edtSpecialisation.getText().toString(), edtQualification.getText().toString()
                    , profileImageUrl, certificateImageUrl, proofImageUrl);
        if(category.equals(getString(R.string.hobby))) {
        startActivity(new Intent(this, SignupScreenSevenForHobbies.class));
        }

        if(category.equals(getString(R.string.academics))){
            startActivity(new Intent(this, SignupScreenSeven.class));
        }

    }

    @OnClick(R.id.tvAddCert)
    public void setOnAddCert(){

        getImageFromAlbum(1);

    }

    @OnClick(R.id.tvAddProof)
    public void setOnAddProof(){
        getImageFromAlbum(2);

    }

    @OnClick(R.id.bundleProfileImage)
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
        showLoading();
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
/*

        if (resultCode == RESULT_OK && reqCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            setImages(f, resultUri);


        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        }
*/


        }

        /*Setting Images and uploading images to the Firebase storage and get downloadable link to the Firebase storage*/
    void setImages(int req, Uri result) {
        if (req == 0) {
            resultUri = result;
            /*Bitmap newImage = null;
            try {
                newImage = getResizedBitmap(getBitmapFromUri(resultUri),64, 64 );
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            imageView.requestLayout();
            imageView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            imageView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            imageView.setImageURI(resultUri);
            addPics.setBackgroundResource(0);
//            Picasso.get().load(resultUri).fit().centerCrop().into(imageView);
//            addPics.setVisibility(View.GONE);

            mStorage = mStorage.child("users/profiles/profile_image/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + ".jpeg");
            UploadTask uploadTask =  mStorage.putFile(resultUri);
            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        Snackbar snackbar;
                        snackbar = Snackbar.make((findViewById(android.R.id.content)), "Error in image loading", Snackbar.LENGTH_LONG);
                        View view = snackbar.getView();
                        TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                        view.setBackgroundColor(Color.parseColor("#ba0505"));
                        textView.setTextColor(Color.WHITE);
                        snackbar.show();
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
                        mStorage = FirebaseStorage.getInstance().getReference();
                        hideLoading();

                    } else {
                        // Handle failures
                        // ...
                    }
                }

            });
//            threadForProfile.start();

        }else if(req == 1){
            resultUri = result;
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
                        Snackbar snackbar;
                        snackbar = Snackbar.make((findViewById(android.R.id.content)), "Error in image loading", Snackbar.LENGTH_LONG);
                        View view = snackbar.getView();
                        TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                        view.setBackgroundColor(Color.parseColor("#ba0505"));
                        textView.setTextColor(Color.WHITE);
                        snackbar.show();
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
                        hideLoading();

                    } else {
                        // Handle failures
                        // ...
                    }
                }

            });




        }else if(req == 2){
            resultUri = result;
            Uri resultUri = result;
            imageForOriginalDocument.setVisibility(View.VISIBLE);
            Picasso.get().load(resultUri).fit().centerCrop().into(imageForOriginalDocument);
            mStorage = mStorage.child("users/profiles/proof_document/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "Original");
            UploadTask uploadTask =  mStorage.putFile(resultUri);
            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {

                        Snackbar snackbar;
                        snackbar = Snackbar.make((findViewById(android.R.id.content)), "Error in image loading", Snackbar.LENGTH_LONG);
                        View view = snackbar.getView();
                        TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                        view.setBackgroundColor(Color.parseColor("#ba0505"));
                        textView.setTextColor(Color.WHITE);
                        snackbar.show();
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
                        hideLoading();
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




    /*Show drop down menu*/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.tvSelectOccupation)
    public void onOccupationClick(View v) {
        /*Setting responsive icon*/
        tvSelectOccupation.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_upxhdpi, 0);

        PopupWindow popUp = popupOccupation(v);
        popUp.showAsDropDown(v, 0, 10);
    }

    /*Show drop down menu*/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.tvSelectExp)
    public void onExpClick(View v) {
        /*Setting responsive icon*/
        tvSelectExp.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_upxhdpi, 0);

        PopupWindow popUp = popupWindowExp();
        popUp.showAsDropDown(v, 0, 10);
    }

    /*Show drop down menu*/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.tvSelectQualification)
    public void onQualificationClick(View v) {

        /*Setting responsive icon*/
        tvSelectQualification.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_upxhdpi, 0);

        PopupWindow popUp = popupWindowQualification();
        popUp.showAsDropDown(v, 0, 10);
    }

    /*Show drop down menu*/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.tvSelectAreaQualification)
    public void onAreaClick(View v) {

        /*Setting responsive icon*/
        tvSelectAreaQualification.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_upxhdpi, 0);

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

                /*Setting responsive icon*/
                tvSelectOccupation.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_downxhdpi, 0);


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
                /*Setting responsive icon*/
                tvSelectOccupation.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_downxhdpi, 0);

                tvSelectOccupation.setText(selectedOccupation[position]);

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

                /*Setting responsive icon*/
                tvSelectExp.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_downxhdpi, 0);

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

                /*Setting responsive icon*/
                tvSelectQualification.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_downxhdpi, 0);

                tvSelectExp.setText(selectedExp[position]);

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

                /*Setting responsive icon*/
                tvSelectQualification.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_downxhdpi, 0);

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

                tvSelectQualification.setText(selectedQualification[position]);
                /*Setting responsive icon*/
                tvSelectQualification.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_downxhdpi, 0);

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
        ArrayAdapter<String> adapter = null;
        if(tvSelectQualification.getText().toString()
                .equals(selectedQualification[1])){
            adapter = new ArrayAdapter<String>(this, R.layout.spinner_dropdown_items,
                    getResources().getStringArray(R.array.area_arrays_for_grad));
        }else if (tvSelectQualification.getText().toString().equals
                (selectedQualification[2])){
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_dropdown_items,
                getResources().getStringArray(R.array.area_arrays_for_post_grad));

        }
        ListView listViewSort = new ListView(this);
        listViewSort.setDivider(null);
        listViewSort.setAdapter(adapter);
        listViewSort.setOnItemClickListener(onItemClickListenerArea());
        setPopWidth(popupWindowArea);
        popupWindowArea.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDismiss() {

                /*Setting responsive icon*/
                tvSelectAreaQualification.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_downxhdpi, 0);


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

                /*Setting responsive icon*/
                tvSelectAreaQualification.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.chevron_with_circle_downxhdpi, 0);

                tvSelectAreaQualification.setText(selectedArea[position]);


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
    /*The below function is for Same font to the whole Activity*/

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
