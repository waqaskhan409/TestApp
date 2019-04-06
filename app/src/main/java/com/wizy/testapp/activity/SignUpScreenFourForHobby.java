package com.wizy.testapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wizy.testapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignUpScreenFourForHobby extends BaseActivity{
    @BindView(R.id.LLGuitar)
    LinearLayout LLGuitar;

    @BindView(R.id.LLPainting)
    LinearLayout LLPainting;

    @BindView(R.id.LLMartialArts)
    LinearLayout LLmartialArts;

    @BindView(R.id.LLDrum)
    LinearLayout LLDrum;

    @BindView(R.id.LLkeyboard)
    LinearLayout LLKeyboard;

    @BindView(R.id.LLDance)
    LinearLayout LLDance;

    @BindView(R.id.ivGuitar)
    ImageView ivGUitar;

    @BindView(R.id.ivpainting)
    ImageView ivPainting;

    @BindView(R.id.ivMartialArts)
    ImageView ivMartialArts;

    @BindView(R.id.ivDrum)
    ImageView ivDrum;

    @BindView(R.id.ivKeyboard)
    ImageView ivKeyboard;

    @BindView(R.id.ivDance)
    ImageView ivDance;
    private ArrayList<String> stringArrayList;
    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.signup_screenfour_hobby);

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
        Intent intent = new Intent(this, SignupScreenSixForHobbies.class);
        intent.putExtra("category", getString(R.string.hobby));
        startActivity(intent);
    }

    @OnClick(R.id.LLGuitar)
    public void onGuitarClick() {
        if (!ivGUitar.isShown()) {
            if (!stringArrayList.contains("Guitar")) {
                stringArrayList.add("Guitar");
            }
            ivGUitar.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("Guitar");
            ivGUitar.setVisibility(View.GONE);
        }

    }

    @OnClick(R.id.LLPainting)
    public void onPaintingClick() {
        if (!ivPainting.isShown()) {
            if (!stringArrayList.contains("Painting")) {
                stringArrayList.add("Painting");
            }
            ivPainting.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("Painting");
            ivPainting.setVisibility(View.GONE);
        }

    }

    @OnClick(R.id.LLMartialArts)
    public void onMartialArtClick() {
        if (!ivMartialArts.isShown()) {
            if (!stringArrayList.contains("Martial Arts")) {
                stringArrayList.add("Martial Arts");
            }
            ivMartialArts.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("Martial Arts");
            ivMartialArts.setVisibility(View.GONE);
        }

    }

    @OnClick(R.id.LLDrum)
    public void onDrumClick() {
        if (!ivDrum.isShown()) {
            if (!stringArrayList.contains("Drum and Precaution")) {
                stringArrayList.add("Drum and Precaution");
            }
            ivDrum.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("Drum and Precaution");
            ivDrum.setVisibility(View.GONE);
        }

    }

    @OnClick(R.id.LLkeyboard)
    public void onKeyBoardClick() {
        if (!ivKeyboard.isShown()) {
            if (!stringArrayList.contains("Keyboard")) {
                stringArrayList.add("Keyboard");
            }
            ivKeyboard.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("Keyboard");
            ivKeyboard.setVisibility(View.GONE);
        }


    }

    @OnClick(R.id.LLDance)
    public void onDanceClick() {
        if (!ivDance.isShown()) {
            if (!stringArrayList.contains("Dance")) {
                stringArrayList.add("Dance");
            }
            ivDance.setVisibility(View.VISIBLE);
        } else {
            stringArrayList.remove("Dance");
            ivDance.setVisibility(View.GONE);
        }

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
