package com.breadwallet.presenter.activities.settings;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.breadwallet.R;
import com.breadwallet.presenter.activities.util.ActivityUTILS;
import com.breadwallet.presenter.activities.util.BRActivity;
import com.breadwallet.tools.animation.BRAnimator;
import com.breadwallet.tools.util.BRConstants;

import java.util.Locale;

public class AboutActivity extends BRActivity {
    private static final String TAG = AboutActivity.class.getName();
//    private TextView termsText;
    private TextView policyText;
    private TextView infoText;

    private ImageView websiteShare;
    private ImageView facebookShare;
    private ImageView telegramShare;
    private ImageView githubShare;
    private static AboutActivity app;

    public static AboutActivity getApp() {
        return app;
    }

    public static boolean appVisible = false;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        infoText = (TextView) findViewById(R.id.info_text);
//        termsText = (TextView) findViewById(R.id.terms_text);
        policyText = (TextView) findViewById(R.id.policy_text);

        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int verCode = pInfo != null ? pInfo.versionCode : 0;

        infoText.setText(String.format(Locale.getDefault(), getString(R.string.About_footer), verCode));

        websiteShare = (ImageView) findViewById(R.id.website_share_button);
        facebookShare = (ImageView) findViewById(R.id.facebook_share_button);
        telegramShare = (ImageView) findViewById(R.id.telegram_share_button);
        githubShare = (ImageView) findViewById(R.id.github_share);

        telegramShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(BRConstants.TELEGRAM_LINK));
                startActivity(browserIntent);
                app.overridePendingTransition(R.anim.enter_from_bottom, R.anim.empty_300);
            }
        });

        facebookShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(BRConstants.FACEBOOK_LINK));
                startActivity(browserIntent);
                app.overridePendingTransition(R.anim.enter_from_bottom, R.anim.empty_300);
            }
        });
        websiteShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(BRConstants.WEB_LINK));
                startActivity(browserIntent);
                app.overridePendingTransition(R.anim.enter_from_bottom, R.anim.empty_300);
            }
        });

        githubShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(BRConstants.GITHUB_LINK));
                startActivity(browserIntent);
                app.overridePendingTransition(R.anim.enter_from_bottom, R.anim.empty_300);
            }
        });

        policyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(BRConstants.TOS_LINK));
                startActivity(browserIntent);
                app.overridePendingTransition(R.anim.enter_from_bottom, R.anim.empty_300);
            }
        });
//        termsText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://breadapp.com/privacy-policy"));
//                startActivity(browserIntent);
//                app.overridePendingTransition(R.anim.enter_from_bottom, R.anim.empty_300);
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        appVisible = true;
        app = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        appVisible = false;
    }

    @Override
    public void onBackPressed() {
        if (ActivityUTILS.isLast(this)) {
            BRAnimator.startBreadActivity(this, false);
        } else {
            super.onBackPressed();
        }
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

}
