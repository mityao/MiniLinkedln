package com.example.android.minilinkedln;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.minilinkedln.model.Education;
import com.example.android.minilinkedln.util.DateUtils;

import java.util.Arrays;

/**
 * Created by mitya on 1/25/2017.
 */

//public class EducationEditActivity extends EditBaseActivity<Education> {
//    @BindView(R.id.education_edit_delete) TextView deleteBtn;
//    @BindView(R.id.education_edit_school) EditText educationEditSchool;
//    @BindView(R.id.education_edit_major) EditText educationEditMajor;
//    @BindView(R.id.education_edit_courses) EditText educationEditCourses;
//    @BindView(R.id.education_edit_start_date) EditText educationEditStartDate;
//    @BindView(R.id.education_edit_end_date) EditText educationEditEndDate;
//
//
//
//    public static final String KEY_EDUCATION = "education";
//    public static final String KEY_EDUCATION_ID = "education_id";
//
//    protected int getLayoutId() {
//        return R.layout.activity_education_edit;
//    }
//
//    protected void setupUIForCreate() {
//        deleteBtn.setVisibility(View.GONE);
//        findViewById(R.id.education_edit_delete).setVisibility(View.GONE);
//    }
//    @Override
//    protected void setupUIForEdit(@NonNull final Education data) {
////-------------------------ButterKnife-------------------------------------
//        deleteBtn.setOnClickListener(new View.OnClickListener() {
////--------------------------------------------------------------------------
//            @Override
//            public void onClick(View v) {
//                Intent resultIntent = new Intent();
//                resultIntent.putExtra(KEY_EDUCATION_ID, data.id);
//                setResult(Activity.RESULT_OK, resultIntent);
//                finish();
//            }
//        });
//    }
//
//    protected void saveAndExit(Education data) {
//        if (data == null) {
//            data = new Education();
//        }
////--------------------ButterKnife
//        Intent resultIntent = new Intent();
//        resultIntent.putExtra(KEY_EDUCATION, data);
//        setResult(Activity.RESULT_OK, resultIntent);
//        finish();
////-----------------------------------------------------
//    }
//
//    protected Education initializeData() {
//        return getIntent().getParcelableExtra(KEY_EDUCATION);
//    }
//}
public class EducationEditActivity extends EditBaseActivity<Education> {

    public static final String KEY_EDUCATION = "education";
    public static final String KEY_EDUCATION_ID = "education_id";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_education_edit;
    }

    @Override
    protected void setupUIForCreate() {
        findViewById(R.id.education_edit_delete).setVisibility(View.GONE);
    }

    @Override
    protected void setupUIForEdit(@NonNull final Education data) {
        ((EditText) findViewById(R.id.education_edit_school))
                .setText(data.school);
        ((EditText) findViewById(R.id.education_edit_major))
                .setText(data.major);
        ((EditText) findViewById(R.id.education_edit_courses))
                .setText(TextUtils.join("\n", data.courses));

        ((EditText) findViewById(R.id.education_edit_start_date))
                .setText(DateUtils.dateToString(data.startDate));
        ((EditText) findViewById(R.id.education_edit_end_date))
                .setText(DateUtils.dateToString(data.endDate));

        findViewById(R.id.education_edit_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(KEY_EDUCATION_ID, data.id);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    @Override
    protected void saveAndExit(@Nullable Education data) {
        if (data == null) {
            data = new Education();
        }

        data.school = ((EditText) findViewById(R.id.education_edit_school)).getText().toString();
        data.major = ((EditText) findViewById(R.id.education_edit_major)).getText().toString();
        data.startDate = DateUtils.stringToDate(
                ((TextView) findViewById(R.id.education_edit_start_date)).getText().toString());
        data.endDate = DateUtils.stringToDate(
                ((TextView) findViewById(R.id.education_edit_end_date)).getText().toString());
        data.courses = Arrays.asList(TextUtils.split(
                ((EditText) findViewById(R.id.education_edit_courses)).getText().toString(), "\n"));

        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EDUCATION, data);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    @Override
    protected Education initializeData() {
        return getIntent().getParcelableExtra(KEY_EDUCATION);
    }

}

