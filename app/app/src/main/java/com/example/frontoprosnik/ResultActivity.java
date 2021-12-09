package com.example.frontoprosnik;

import android.content.Intent;
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
    private TextView deskHeaderPoint;
    private TextView descriptionHeader;
    private TextView description;
    private Button btnNext;

    private String token;
    private JSONResult jsonResult;
    private int currentFactor = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();

        token = intent.getStringExtra("token_key");
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
        descriptionHeader = (TextView) findViewById(R.id.descriptionHeader);
        description = (TextView) findViewById(R.id.description);
        btnNext = (Button) findViewById(R.id.btnNext);

        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextFactor(currentFactor);
            }
        };
        btnNext.setOnClickListener(buttonClickListener);

        point_general.setText(Integer.toString(jsonResult.getPoints_general()));
        point_plan.setText(Integer.toString(jsonResult.getPoints_plan()));
        point_cele.setText(Integer.toString(jsonResult.getPoints_cele()));
        point_nast.setText(Integer.toString(jsonResult.getPoints_nast()));
        point_fiks.setText(Integer.toString(jsonResult.getPoints_fiks()));
        point_samo.setText(Integer.toString(jsonResult.getPoints_samo()));
        point_orie.setText(Integer.toString(jsonResult.getPoints_orie()));

        nextFactor(currentFactor);
    }

    private void nextFactor(int currentFactor) {
        switch (currentFactor) {
            case 1:
                headerNameFactor.setText("Показатель планомерности");
                headerPoints.setText(Integer.toString(jsonResult.getPoints_plan()));
                if (jsonResult.getPoints_plan() > 24) {
                    deskHeaderPoint.setText("Считается высоким показателем (>24)");
                } else {
                    if (jsonResult.getPoints_plan() < 14) {
                        deskHeaderPoint.setText("Считается низким показателем (<14)");
                    } else {
                        deskHeaderPoint.setText("Считается средним показателем (диапазон 14-24)");
                    }
                }
                description.setText(jsonResult.getDescription_plan());
                break;
            case 2:
                headerNameFactor.setText("Показатель целеустремленности");
                headerPoints.setText(Integer.toString(jsonResult.getPoints_cele()));
                if (jsonResult.getPoints_cele() > 38) {
                    deskHeaderPoint.setText("Считается высоким показателем (>38)");
                } else {
                    if (jsonResult.getPoints_cele() < 28) {
                        deskHeaderPoint.setText("Считается низким показателем (<28)");
                    } else {
                        deskHeaderPoint.setText("Считается средним показателем (диапазон 28-38)");
                    }
                }
                description.setText(jsonResult.getDescription_cele());
                break;
            case 3:
                headerNameFactor.setText("Показатель настойчивости");
                headerPoints.setText(Integer.toString(jsonResult.getPoints_nast()));
                if (jsonResult.getPoints_nast() > 25) {
                    deskHeaderPoint.setText("Считается высоким показателем (>25)");
                } else {
                    if (jsonResult.getPoints_nast() < 14) {
                        deskHeaderPoint.setText("Считается низким показателем (<14)");
                    } else {
                        deskHeaderPoint.setText("Считается средним показателем (диапазон 14-25)");
                    }
                }
                description.setText(jsonResult.getDescription_nast());
                break;
            case 4:
                headerNameFactor.setText("Показатель фиксации");
                headerPoints.setText(Integer.toString(jsonResult.getPoints_fiks()));
                if (jsonResult.getPoints_fiks() > 24) {
                    deskHeaderPoint.setText("Считается высоким показателем (>24)");
                } else {
                    if (jsonResult.getPoints_fiks() < 14) {
                        deskHeaderPoint.setText("Считается низким показателем (<14)");
                    } else {
                        deskHeaderPoint.setText("Считается средним показателем (диапазон 14-24)");
                    }
                }
                description.setText(jsonResult.getDescription_fiks());
                break;
            case 5:
                headerNameFactor.setText("Показатель самоорганизации");
                headerPoints.setText(Integer.toString(jsonResult.getPoints_samo()));
                if (jsonResult.getPoints_samo() > 15) {
                    deskHeaderPoint.setText("Считается высоким показателем (>15)");
                } else {
                    if (jsonResult.getPoints_samo() < 5) {
                        deskHeaderPoint.setText("Считается низким показателем (<5)");
                    } else {
                        deskHeaderPoint.setText("Считается средним показателем (диапазон 5-15)");
                    }
                }
                description.setText(jsonResult.getDescription_samo());
                break;
            case 6:
                headerNameFactor.setText("Показатель ориентации на настоящее");
                headerPoints.setText(Integer.toString(jsonResult.getPoints_orie()));
                if (jsonResult.getPoints_orie() > 10) {
                    deskHeaderPoint.setText("Считается высоким показателем (>10)");
                } else {
                    if (jsonResult.getPoints_orie() < 7) {
                        deskHeaderPoint.setText("Считается низким показателем (<7)");
                    } else {
                        deskHeaderPoint.setText("Считается средним показателем (диапазон 7-10)");
                    }
                }
                description.setText(jsonResult.getDescription_orie());
                break;
            case 7:
                headerNameFactor.setText("Общий показатель самоорганизации");
                headerPoints.setText(Integer.toString(jsonResult.getPoints_general()));
                if (jsonResult.getPoints_general() > 124) {
                    deskHeaderPoint.setText("Считается высоким показателем (>124)");
                } else {
                    if (jsonResult.getPoints_general() < 94) {
                        deskHeaderPoint.setText("Считается низким показателем (<94)");
                    } else {
                        deskHeaderPoint.setText("Считается средним показателем (диапазон 94-124)");
                    }
                }
                description.setText(jsonResult.getDescription_general());
                break;
        }
        if (currentFactor != 7) {
            this.currentFactor++;
        } else {
            this.currentFactor = 1;
        }
    }
}