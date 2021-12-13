package com.example.frontoprosnik;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frontoprosnik.json.JSONResult;


public class ResultActivity extends AppCompatActivity {

    private TextView headerNameFactor;
    private TextView headerPoints;
    private TextView point_general;
    private TextView point_plan;
    private TextView point_cele;
    private TextView point_nast;
    private TextView point_fiks;
    private TextView point_samo;
    private TextView point_orie;
    private TextView factor_general;
    private TextView factor_plan;
    private TextView factor_cele;
    private TextView factor_nast;
    private TextView factor_fiks;
    private TextView factor_samo;
    private TextView factor_orie;
    private TextView deskHeaderPoint;
    private TextView descriptionHeader;
    private TextView description;
    private Button btnNext;
    private Button btnPrevious;
    private Button btnToMenu;

    private String token;
    private String isAuth;
    private JSONResult jsonResult;
    private int currentFactor = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();

        token = intent.getStringExtra("token_key");
        isAuth = intent.getStringExtra("isAuth");
        jsonResult = (JSONResult) intent.getSerializableExtra("JSONResult");

        headerNameFactor = (TextView) findViewById(R.id.headerNameFactor);
        headerPoints = (TextView) findViewById(R.id.headerPoints);
        deskHeaderPoint = (TextView) findViewById(R.id.deskHeaderPoint);
        point_general = (TextView) findViewById(R.id.point_general);
        point_plan = (TextView) findViewById(R.id.point_plan);
        point_cele = (TextView) findViewById(R.id.point_cele);
        point_nast = (TextView) findViewById(R.id.point_nast);
        point_fiks = (TextView) findViewById(R.id.point_fiks);
        point_samo = (TextView) findViewById(R.id.point_samo);
        point_orie = (TextView) findViewById(R.id.point_orie);
        factor_general = (TextView) findViewById(R.id.factor_general);
        factor_plan = (TextView) findViewById(R.id.factor_plan);
        factor_cele = (TextView) findViewById(R.id.factor_cele);
        factor_nast = (TextView) findViewById(R.id.factor_nast);
        factor_fiks = (TextView) findViewById(R.id.factor_fiks);
        factor_samo = (TextView) findViewById(R.id.factor_samo);
        factor_orie = (TextView) findViewById(R.id.factor_orie);
        descriptionHeader = (TextView) findViewById(R.id.descriptionHeader);
        description = (TextView) findViewById(R.id.description);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnPrevious = (Button) findViewById(R.id.btnPrevious);
        btnToMenu = (Button) findViewById(R.id.btnToMenu);

        View.OnClickListener btnClickNext = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextFactor(true);
            }
        };
        btnNext.setOnClickListener(btnClickNext);

        View.OnClickListener btnClickPrevious = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextFactor(false);
            }
        };

        View.OnClickListener btnClickMenu = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        };
        btnPrevious.setOnClickListener(btnClickPrevious);
        btnToMenu.setOnClickListener(btnClickMenu);

        point_general.setText(Integer.toString(jsonResult.getPoints_general()));
        point_plan.setText(Integer.toString(jsonResult.getPoints_plan()));
        point_cele.setText(Integer.toString(jsonResult.getPoints_cele()));
        point_nast.setText(Integer.toString(jsonResult.getPoints_nast()));
        point_fiks.setText(Integer.toString(jsonResult.getPoints_fiks()));
        point_samo.setText(Integer.toString(jsonResult.getPoints_samo()));
        point_orie.setText(Integer.toString(jsonResult.getPoints_orie()));

        nextFactor(true);
    }

    private void goToMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        if (isAuth.equals("yes")) {
            i.putExtra("token_key", token);
        }
        startActivity(i);
        finish();
    }

    private void setNormalBoldFactors() {
        factor_general.setTypeface(null, Typeface.NORMAL);
        factor_plan.setTypeface(null, Typeface.NORMAL);
        factor_cele.setTypeface(null, Typeface.NORMAL);
        factor_nast.setTypeface(null, Typeface.NORMAL);
        factor_fiks.setTypeface(null, Typeface.NORMAL);
        factor_samo.setTypeface(null, Typeface.NORMAL);
        factor_orie.setTypeface(null, Typeface.NORMAL);
    }

    private void nextFactor(boolean isNext) {
        if (isNext) {
            if (currentFactor != 7) {
                this.currentFactor++;
            } else {
                this.currentFactor = 1;
            }
        } else {
            if (currentFactor != 1) {
                this.currentFactor--;
            } else {
                this.currentFactor = 7;
            }
        }

        switch (currentFactor) {
            case 1:
                setNormalBoldFactors();
                factor_plan.setTypeface(null, Typeface.BOLD);
                headerNameFactor.setText(getResources().getString(R.string.planHeader));
                headerPoints.setText(Integer.toString(jsonResult.getPoints_plan()));
                if (jsonResult.getPoints_plan() > 24) {
                    deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderHigh) + " (>24)");
                } else {
                    if (jsonResult.getPoints_plan() < 14) {
                        deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderLow) + " (<14)");
                    } else {
                        deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderMiddle) + " 14-24)");
                    }
                }
                description.setText(jsonResult.getDescription_plan());
                break;
            case 2:
                setNormalBoldFactors();
                factor_cele.setTypeface(null, Typeface.BOLD);
                headerNameFactor.setText(getResources().getString(R.string.celeHeader));
                headerPoints.setText(Integer.toString(jsonResult.getPoints_cele()));
                if (jsonResult.getPoints_cele() > 38) {
                    deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderHigh) + " (>38)");
                } else {
                    if (jsonResult.getPoints_cele() < 28) {
                        deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderLow) + " (<28)");
                    } else {
                        deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderMiddle) + " 28-38)");
                    }
                }
                description.setText(jsonResult.getDescription_cele());
                break;
            case 3:
                setNormalBoldFactors();
                factor_nast.setTypeface(null, Typeface.BOLD);
                headerNameFactor.setText(getResources().getString(R.string.nastHeader));
                headerPoints.setText(Integer.toString(jsonResult.getPoints_nast()));
                if (jsonResult.getPoints_nast() > 25) {
                    deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderHigh) + " (>25)");
                } else {
                    if (jsonResult.getPoints_nast() < 14) {
                        deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderLow) + " (<14)");
                    } else {
                        deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderMiddle) + " 14-25)");
                    }
                }
                description.setText(jsonResult.getDescription_nast());
                break;
            case 4:
                setNormalBoldFactors();
                factor_fiks.setTypeface(null, Typeface.BOLD);
                headerNameFactor.setText(getResources().getString(R.string.fiksHeader));
                headerPoints.setText(Integer.toString(jsonResult.getPoints_fiks()));
                if (jsonResult.getPoints_fiks() > 24) {
                    deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderHigh) + " (>24)");
                } else {
                    if (jsonResult.getPoints_fiks() < 14) {
                        deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderLow) + " (<14)");
                    } else {
                        deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderMiddle) + " 14-24)");
                    }
                }
                description.setText(jsonResult.getDescription_fiks());
                break;
            case 5:
                setNormalBoldFactors();
                factor_samo.setTypeface(null, Typeface.BOLD);
                headerNameFactor.setText(getResources().getString(R.string.samoHeader));
                headerPoints.setText(Integer.toString(jsonResult.getPoints_samo()));
                if (jsonResult.getPoints_samo() > 15) {
                    deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderHigh) + " (>15)");
                } else {
                    if (jsonResult.getPoints_samo() < 5) {
                        deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderLow) + " (<5)");
                    } else {
                        deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderMiddle) + " 5-15)");
                    }
                }
                description.setText(jsonResult.getDescription_samo());
                break;
            case 6:
                setNormalBoldFactors();
                factor_orie.setTypeface(null, Typeface.BOLD);
                headerNameFactor.setText(getResources().getString(R.string.orieHeader));
                headerPoints.setText(Integer.toString(jsonResult.getPoints_orie()));
                if (jsonResult.getPoints_orie() > 10) {
                    deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderHigh) + " (>10)");
                } else {
                    if (jsonResult.getPoints_orie() < 7) {
                        deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderLow) + " (<7)");
                    } else {
                        deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderMiddle) + " 7-10)");
                    }
                }
                description.setText(jsonResult.getDescription_orie());
                break;
            case 7:
                setNormalBoldFactors();
                factor_general.setTypeface(null, Typeface.BOLD);
                headerNameFactor.setText(getResources().getString(R.string.generalHeader));
                headerPoints.setText(Integer.toString(jsonResult.getPoints_general()));
                if (jsonResult.getPoints_general() > 124) {
                    deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderHigh) + " (>124)");
                } else {
                    if (jsonResult.getPoints_general() < 94) {
                        deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderLow) + " (<94)");
                    } else {
                        deskHeaderPoint.setText(getResources().getString(R.string.deskHeaderMiddle) + " 94-124)");
                    }
                }
                description.setText(jsonResult.getDescription_general());
                break;
        }
    }
}