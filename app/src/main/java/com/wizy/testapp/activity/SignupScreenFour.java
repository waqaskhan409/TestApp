package com.wizy.testapp.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wizy.testapp.R;

import java.util.ArrayList;
import java.util.HashSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignupScreenFour extends BaseActivity {

    @BindView(R.id.LLClass1)
    LinearLayout LLClass1;

    @BindView(R.id.LLClass2)
    LinearLayout LLClass2;

    @BindView(R.id.LLClass3)
    LinearLayout LLClass3;

    @BindView(R.id.LLClass4)
    LinearLayout LLClass4;

    @BindView(R.id.LLClass5)
    LinearLayout LLClass5;

    @BindView(R.id.LLClass6)
    LinearLayout LLClass6;

    @BindView(R.id.LLClass7)
    LinearLayout LLClass7;

    @BindView(R.id.LLClass8)
    LinearLayout LLClass8;

    @BindView(R.id.LLClass9)
    LinearLayout LLClass9;

    @BindView(R.id.LLClass10)
    LinearLayout LLClass10;

    @BindView(R.id.LLClass11)
    LinearLayout LLClass11;

    @BindView(R.id.LLClass12)
    LinearLayout LLClass12;

    @BindView(R.id.ivClass1)
    ImageView ivClass1;

    @BindView(R.id.ivClass2)
    ImageView ivClass2;

    @BindView(R.id.ivClass3)
    ImageView ivClass3;

    @BindView(R.id.ivClass4)
    ImageView ivClass4;

    @BindView(R.id.ivClass5)
    ImageView ivClass5;

    @BindView(R.id.ivClass6)
    ImageView ivClass6;

    @BindView(R.id.ivClass7)
    ImageView ivClass7;

    @BindView(R.id.ivClass8)
    ImageView ivClass8;

    @BindView(R.id.ivClass9)
    ImageView ivClass9;

    @BindView(R.id.ivClass10)
    ImageView ivClass10;

    @BindView(R.id.ivClass11)
    ImageView ivClass11;

    @BindView(R.id.ivClass12)
    ImageView ivClass12;

    private ArrayList<String> stringArrayList;
    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screenfour);
        ButterKnife.bind(this);
        stringArrayList = new ArrayList<>();
    }

    @OnClick(R.id.btnNext)
    public void onNextClick() {
        stringBuilder = new StringBuilder();
        int size = stringArrayList.size();
        if (size > 0) {
            stringBuilder.append(stringArrayList.get(0));
            for (int i = 1; i < stringArrayList.size(); i++) {
                stringBuilder.append(", ").append(stringArrayList.get(i));
            }

        }
        if (stringBuilder.length() == 0) {
            Toast.makeText(this, R.string.choose_classes, Toast.LENGTH_SHORT).show();
            return;
        }
        getAppPreferenceHelper().setScreenFour(stringBuilder.toString());

        startActivity(new Intent(this, SignupScreenFive.class));
    }

    @OnClick(R.id.LLClass4)
    public void onClass4Click() {
        if (!ivClass4.isShown()) {
            if (!stringArrayList.contains("Class 4")) {
                stringArrayList.add("Class 4");
            }
            ivClass4.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("Class 4");
            ivClass4.setVisibility(View.GONE);
        }
        if (stringArrayList.contains("Class 11")) {
            stringArrayList.remove("Class 11");
        }
        if (stringArrayList.contains("Class 12")) {
            stringArrayList.remove("Class 12");
        }
        ivClass11.setVisibility(View.GONE);
        ivClass12.setVisibility(View.GONE);
    }@OnClick(R.id.LLClass3)
    public void onClass3Click() {
        if (!ivClass3.isShown()) {
            if (!stringArrayList.contains("Class 3")) {
                stringArrayList.add("Class 3");
            }
            ivClass3.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("Class 3");
            ivClass3.setVisibility(View.GONE);
        }
        if (stringArrayList.contains("Class 11")) {
            stringArrayList.remove("Class 11");
        }
        if (stringArrayList.contains("Class 12")) {
            stringArrayList.remove("Class 12");
        }
        ivClass11.setVisibility(View.GONE);
        ivClass12.setVisibility(View.GONE);
    }@OnClick(R.id.LLClass2)
    public void onClass2Click() {
        if (!ivClass2.isShown()) {
            if (!stringArrayList.contains("Class 2")) {
                stringArrayList.add("Class 2");
            }
            ivClass2.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("Class 2");
            ivClass2.setVisibility(View.GONE);
        }
        if (stringArrayList.contains("Class 11")) {
            stringArrayList.remove("Class 11");
        }
        if (stringArrayList.contains("Class 12")) {
            stringArrayList.remove("Class 12");
        }
        ivClass11.setVisibility(View.GONE);
        ivClass12.setVisibility(View.GONE);
    }@OnClick(R.id.LLClass1)
    public void onClass1Click() {
        if (!ivClass1.isShown()) {
            if (!stringArrayList.contains("Class 1")) {
                stringArrayList.add("Class 1");
            }
            ivClass1.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("Class 1");
            ivClass1.setVisibility(View.GONE);
        }
        if (stringArrayList.contains("Class 11")) {
            stringArrayList.remove("Class 11");
        }
        if (stringArrayList.contains("Class 12")) {
            stringArrayList.remove("Class 12");
        }
        ivClass11.setVisibility(View.GONE);
        ivClass12.setVisibility(View.GONE);
    }
public void onClass5Click() {
        if (!ivClass5.isShown()) {
            if (!stringArrayList.contains("Class 5")) {
                stringArrayList.add("Class 5");
            }
            ivClass5.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("Class 5");
            ivClass5.setVisibility(View.GONE);
        }
        if (stringArrayList.contains("Class 11")) {
            stringArrayList.remove("Class 11");
        }
        if (stringArrayList.contains("Class 12")) {
            stringArrayList.remove("Class 12");
        }
        ivClass11.setVisibility(View.GONE);
        ivClass12.setVisibility(View.GONE);
    }

    @OnClick(R.id.LLClass6)
    public void onClass6Click() {
        if (!ivClass6.isShown()) {
            if (!stringArrayList.contains("Class 6")) {
                stringArrayList.add("Class 6");
            }
            ivClass6.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("Class 6");
            ivClass6.setVisibility(View.GONE);
        }
        if (stringArrayList.contains("Class 11")) {
            stringArrayList.remove("Class 11");
        }
        if (stringArrayList.contains("Class 12")) {
            stringArrayList.remove("Class 12");
        }
        ivClass11.setVisibility(View.GONE);
        ivClass12.setVisibility(View.GONE);
    }

    @OnClick(R.id.LLClass7)
    public void onClass7Click() {
        if (!ivClass7.isShown()) {
            if (!stringArrayList.contains("Class 7")) {
                stringArrayList.add("Class 7");
            }
            ivClass7.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("Class 7");
            ivClass7.setVisibility(View.GONE);
        }
        if (stringArrayList.contains("Class 11")) {
            stringArrayList.remove("Class 11");
        }
        if (stringArrayList.contains("Class 12")) {
            stringArrayList.remove("Class 12");
        }
        ivClass11.setVisibility(View.GONE);
        ivClass12.setVisibility(View.GONE);
    }

    @OnClick(R.id.LLClass8)
    public void onClass8Click() {
        if (!ivClass8.isShown()) {
            if (!stringArrayList.contains("Class 8")) {
                stringArrayList.add("Class 8");
            }
            ivClass8.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("Class 8");
            ivClass8.setVisibility(View.GONE);
        }
        if (stringArrayList.contains("Class 11")) {
            stringArrayList.remove("Class 11");
        }
        if (stringArrayList.contains("Class 12")) {
            stringArrayList.remove("Class 12");
        }
        ivClass11.setVisibility(View.GONE);
        ivClass12.setVisibility(View.GONE);
    }

    @OnClick(R.id.LLClass9)
    public void onClass9Click() {
        if (!ivClass9.isShown()) {
            if (!stringArrayList.contains("Class 9")) {
                stringArrayList.add("Class 9");
            }
            ivClass9.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("Class 9");
            ivClass9.setVisibility(View.GONE);
        }

        if (stringArrayList.contains("Class 11")) {
            stringArrayList.remove("Class 11");
        }
        if (stringArrayList.contains("Class 12")) {
            stringArrayList.remove("Class 12");
        }
        ivClass11.setVisibility(View.GONE);
        ivClass12.setVisibility(View.GONE);
    }

    @OnClick(R.id.LLClass10)
    public void onClass10Click() {
        if (!ivClass10.isShown()) {
            if (!stringArrayList.contains("Class 10")) {
                stringArrayList.add("Class 10");
            }
            ivClass10.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("Class 10");
            ivClass10.setVisibility(View.GONE);
        }
        if (stringArrayList.contains("Class 11")) {
            stringArrayList.remove("Class 11");
        }
        if (stringArrayList.contains("Class 12")) {
            stringArrayList.remove("Class 12");
        }
        ivClass11.setVisibility(View.GONE);
        ivClass12.setVisibility(View.GONE);
    }

    @OnClick(R.id.LLClass11)
    public void onClass11Click() {
        if (!ivClass11.isShown()) {
            if (!stringArrayList.contains("Class 11")) {
                stringArrayList.add("Class 11");
            }
            ivClass11.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("Class 11");
            ivClass11.setVisibility(View.GONE);
        }
        if (stringArrayList.contains("Class 5")) {
            stringArrayList.remove("Class 5");
        }
        if (stringArrayList.contains("Class 6")) {
            stringArrayList.remove("Class 6");
        }
        if (stringArrayList.contains("Class 7")) {
            stringArrayList.remove("Class 7");
        }
        if (stringArrayList.contains("Class 8")) {
            stringArrayList.remove("Class 8");
        }
        if (stringArrayList.contains("Class 9")) {
            stringArrayList.remove("Class 9");
        }
        if (stringArrayList.contains("Class 10")) {
            stringArrayList.remove("Class 10");
        }
        ivClass5.setVisibility(View.GONE);
        ivClass6.setVisibility(View.GONE);
        ivClass7.setVisibility(View.GONE);
        ivClass8.setVisibility(View.GONE);
        ivClass9.setVisibility(View.GONE);
        ivClass10.setVisibility(View.GONE);
    }

    @OnClick(R.id.LLClass12)
    public void onClass12Click() {
        if (!ivClass12.isShown()) {
            if (!stringArrayList.contains("Class 12")) {
                stringArrayList.add("Class 12");
            }
            ivClass12.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("Class 12");
            ivClass12.setVisibility(View.GONE);
        }
        if (stringArrayList.contains("Class 5")) {
            stringArrayList.remove("Class 5");
        }
        if (stringArrayList.contains("Class 6")) {
            stringArrayList.remove("Class 6");
        }
        if (stringArrayList.contains("Class 7")) {
            stringArrayList.remove("Class 7");
        }
        if (stringArrayList.contains("Class 8")) {
            stringArrayList.remove("Class 8");
        }
        if (stringArrayList.contains("Class 9")) {
            stringArrayList.remove("Class 9");
        }
        if (stringArrayList.contains("Class 10")) {
            stringArrayList.remove("Class 10");
        }
        ivClass5.setVisibility(View.GONE);
        ivClass6.setVisibility(View.GONE);
        ivClass7.setVisibility(View.GONE);
        ivClass8.setVisibility(View.GONE);
        ivClass9.setVisibility(View.GONE);
        ivClass10.setVisibility(View.GONE);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
