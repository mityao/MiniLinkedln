package com.example.android.minilinkedln;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.minilinkedln.model.Experience;
import com.example.android.minilinkedln.util.DateUtils;

import butterknife.BindView;

/**
 * Created by mitya on 1/26/2017.
 */

public class ExperienceEditActivity extends EditBaseActivity<Experience> {

    @BindView(R.id.experience_edit_delete) TextView experienceEditDelete;
    @BindView(R.id.experience_edit_company) TextView experienceEditCompany;
    @BindView(R.id.experience_edit_title) TextView experienceEditTitle;
//    @BindView(R.id.experience_edit_start_date) TextView experienceEditStartDate;
//    @BindView(R.id.experience_edit_end_date) TextView experienceEditEndDate;
    @BindView(R.id.experience_edit_details) TextView experienceEditDetails;


    public static final String KEY_EXPERIENCE = "experience";
    public static final String KEY_EXPERIENCE_ID = "experience_id";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_experience_edit;
    }

    @Override
    protected void setupUIForCreate() {
        experienceEditDelete.setVisibility(View.GONE);
    }

    @Override
    protected void setupUIForEdit(@NonNull final Experience data) {

        ((EditText) findViewById(R.id.experience_edit_start_date))
                .setText(DateUtils.dateToString(data.startDate));
        ((EditText) findViewById(R.id.experience_edit_end_date))
                .setText(DateUtils.dateToString(data.endDate));

        experienceEditDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(KEY_EXPERIENCE_ID, data.id);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    @Override
    protected void saveAndExit(@Nullable Experience data) {

        if (data == null) {
            data = new Experience();
        }
//        data.company = ((EditText) findViewById(R.id.experience_edit_company)).getText().toString();
//        data.title = ((EditText) findViewById(R.id.experience_edit_title)).getText().toString();
        data.startDate = DateUtils.stringToDate(
                ((EditText) findViewById(R.id.experience_edit_start_date)).getText().toString());
        data.endDate = DateUtils.stringToDate(
                ((EditText) findViewById(R.id.experience_edit_end_date)).getText().toString());
//        data.details = Arrays.asList(TextUtils.split(
//                ((EditText) findViewById(R.id.experience_edit_details)).getText().toString(), "\n"));

        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EXPERIENCE, data);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    @Override
    protected Experience initializeData() {
        return getIntent().getParcelableExtra(KEY_EXPERIENCE);
    }
}
