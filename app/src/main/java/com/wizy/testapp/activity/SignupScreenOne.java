package com.wizy.testapp.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.wizy.testapp.R;
import com.wizy.testapp.utills.AppConstants;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class SignupScreenOne extends BaseActivity {

    @BindView(R.id.tvSelectTitle)
    TextView tvSelectTitle;

    @BindView(R.id.edtFirstName)
    EditText edtFirstName;

    @BindView(R.id.edtLastName)
    EditText edtLastName;

    @BindView(R.id.edtPhoneNumber)
    EditText edtPhoneNumber;

    @BindView(R.id.edtPassword)
    EditText edtPassword;

    private PopupWindow popupWindow;
    private String[] selectedTitle;
    private int width;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screenone);
        ButterKnife.bind(this);
        selectedTitle = getResources().getStringArray(R.array.title_arrays);
        tvSelectTitle.setText(selectedTitle[0]);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.tvSelectTitle)
    public void onTitleClick(View v) {
        PopupWindow popUp = popupWindowsort();
//        Drawable img = getDrawable( R.mipmap.layers_for_up_arrowxxhdpi );

//        img.setBounds( 0, 0, 80, 40 );
        tvSelectTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.layers_for_up_arrowxxhdpi, 0);

//        tvSelectTitle.setCompoundDrawables(null,null, img,null);

        popUp.showAsDropDown(v, 0, 0);
    }


    /*The below method is for to getting data from user and save to the shared preferences*/
    @OnClick(R.id.btnNext)
    public void onNextClick() {

        /*Validate the EditeText either its empty or not*/

      if (edtFirstName.getText().length() == 0) {

          snackbar =Snackbar.make((findViewById(android.R.id.content)), getString(R.string.enter_name), Snackbar.LENGTH_LONG);
          View view = snackbar.getView();
          TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
          view.setBackgroundColor(Color.parseColor("#ba0505"));
          textView.setTextColor(Color.WHITE);
          snackbar.show();

//            Toast.makeText(this, getString(R.string.enter_name), Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtLastName.getText().length() == 0) {

            snackbar =Snackbar.make((findViewById(android.R.id.content)), getString(R.string.enter_lastname), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }
        if (edtPhoneNumber.getText().length() == 0) {
            snackbar =Snackbar.make((findViewById(android.R.id.content)), getString(R.string.enter_phone), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }
        if (edtPhoneNumber.getText().length() < 10) {
            snackbar =Snackbar.make((findViewById(android.R.id.content)), getString(R.string.enter_correct_phone), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }
        if (edtPassword.getText().length() == 0) {
            snackbar =Snackbar.make((findViewById(android.R.id.content)), getString(R.string.enter_password), Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            view.setBackgroundColor(Color.parseColor("#ba0505"));
            textView.setTextColor(Color.WHITE);
            snackbar.show();
            return;
        }

        try {
            /*Save the data to the shared preferences*/
            getAppPreferenceHelper().setUserScreenOne(tvSelectTitle.getText().toString(), edtFirstName.getText().toString(),
                    edtLastName.getText().toString(), edtPhoneNumber.getText().toString(), encrypt(edtPassword.getText().toString()));
            /*redirect to the second activity*/
            startActivity(new Intent(this, SignupScreenTwo.class));
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

    }



    // initialize a pop up window type
    private PopupWindow popupWindowsort() {
        popupWindow = new PopupWindow(this);
        popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.reactangle_cert));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_dropdown_items,
                getResources().getStringArray(R.array.title_arrays));
        ListView listViewSort = new ListView(this);
        listViewSort.setDivider(null);
        listViewSort.setAdapter(adapter);
        listViewSort.setOnItemClickListener(onItemClickListener());
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDismiss() {
                /*Setting the icons according to display*/
                tvSelectTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.layer_for_down_arrowxxhdpi, 0);

            }
        });
        popupWindow.setFocusable(true);
        setPopWidth(popupWindow);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(listViewSort);

        return popupWindow;
    }

    private AdapterView.OnItemClickListener onItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                tvSelectTitle.setText(getResources().getStringArray(R.array.title_arrays)[position].toString());
                dismissPopup();
            }
        };
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void dismissPopup() {
        if (popupWindow != null) {
            /*Setting the icons according to display*/
            tvSelectTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.layer_for_down_arrowxxhdpi, 0);

            popupWindow.dismiss();
        }
    }

    // Set Pop up width according to resolution
    private void setPopWidth(PopupWindow popupWindow) {
        if (width == 1440) {
            popupWindow.setWidth(width - 900);
        } else if (width == 1080) {
            popupWindow.setWidth(width - 700);
        } else if (width == 720) {
            popupWindow.setWidth(width - 450);
        } else {
            popupWindow.setWidth(width - 450);
        }
    }

    // Encryption Process
    public static String encrypt(String value) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(AppConstants.KEY.getBytes(), AppConstants.ALGORITHM);
        Cipher cipher = Cipher.getInstance(AppConstants.MODE);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(AppConstants.IV.getBytes()));
        byte[] values = cipher.doFinal(value.getBytes());
        return Base64.encodeToString(values, Base64.DEFAULT);
    }
    /*The below function is for Same font to the whole Activity*/
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
