package com.example.frontoprosnik.json;

import java.io.Serializable;

public class JSONResult implements Serializable {
    private long id_attempt;
    private String data_attempt;
    private int points_general;
    private int points_plan;
    private int points_cele;
    private int points_nast;
    private int points_fiks;
    private int points_samo;
    private int points_orie;
    private String name_general;
    private String name_plan;
    private String name_cele;
    private String name_nast;
    private String name_fiks;
    private String name_samo;
    private String name_orie;
    private String description_general;
    private String description_plan;
    private String description_cele;
    private String description_nast;
    private String description_fiks;
    private String description_samo;
    private String description_orie;
    private int mcriticMale_general;
    private int mcriticMale_plan;
    private int mcriticMale_cele;
    private int mcriticMale_nast;
    private int mcriticMale_fiks;
    private int mcriticMale_samo;
    private int mcriticMale_orie;
    private int qcriticMale_general;
    private int qcriticMale_plan;
    private int qcriticMale_cele;
    private int qcriticMale_nast;
    private int qcriticMale_fiks;
    private int qcriticMale_samo;
    private int qcriticMale_orie;
    private int mcriticFemale_general;
    private int mcriticFemale_plan;
    private int mcriticFemale_cele;
    private int mcriticFemale_nast;
    private int mcriticFemale_fiks;
    private int mcriticFemale_samo;
    private int mcriticFemale_orie;
    private int qcriticFemale_general;
    private int qcriticFemale_plan;
    private int qcriticFemale_cele;
    private int qcriticFemale_nast;
    private int qcriticFemale_fiks;
    private int qcriticFemale_samo;
    private int qcriticFemale_orie;

    public JSONResult() {
    }

    public JSONResult(long id_attempt, String data_attempt, int points_general, int points_plan, int points_cele, int points_nast, int points_fiks, int points_samo, int points_orie, String name_general, String name_plan, String name_cele, String name_nast, String name_fiks, String name_samo, String name_orie, String description_general, String description_plan, String description_cele, String description_nast, String description_fiks, String description_samo, String description_orie, int mcriticMale_general, int mcriticMale_plan, int mcriticMale_cele, int mcriticMale_nast, int mcriticMale_fiks, int mcriticMale_samo, int mcriticMale_orie, int qcriticMale_general, int qcriticMale_plan, int qcriticMale_cele, int qcriticMale_nast, int qcriticMale_fiks, int qcriticMale_samo, int qcriticMale_orie, int mcriticFemale_general, int mcriticFemale_plan, int mcriticFemale_cele, int mcriticFemale_nast, int mcriticFemale_fiks, int mcriticFemale_samo, int mcriticFemale_orie, int qcriticFemale_general, int qcriticFemale_plan, int qcriticFemale_cele, int qcriticFemale_nast, int qcriticFemale_fiks, int qcriticFemale_samo, int qcriticFemale_orie) {
        this.id_attempt = id_attempt;
        this.data_attempt = data_attempt;
        this.points_general = points_general;
        this.points_plan = points_plan;
        this.points_cele = points_cele;
        this.points_nast = points_nast;
        this.points_fiks = points_fiks;
        this.points_samo = points_samo;
        this.points_orie = points_orie;
        this.name_general = name_general;
        this.name_plan = name_plan;
        this.name_cele = name_cele;
        this.name_nast = name_nast;
        this.name_fiks = name_fiks;
        this.name_samo = name_samo;
        this.name_orie = name_orie;
        this.description_general = description_general;
        this.description_plan = description_plan;
        this.description_cele = description_cele;
        this.description_nast = description_nast;
        this.description_fiks = description_fiks;
        this.description_samo = description_samo;
        this.description_orie = description_orie;
        this.mcriticMale_general = mcriticMale_general;
        this.mcriticMale_plan = mcriticMale_plan;
        this.mcriticMale_cele = mcriticMale_cele;
        this.mcriticMale_nast = mcriticMale_nast;
        this.mcriticMale_fiks = mcriticMale_fiks;
        this.mcriticMale_samo = mcriticMale_samo;
        this.mcriticMale_orie = mcriticMale_orie;
        this.qcriticMale_general = qcriticMale_general;
        this.qcriticMale_plan = qcriticMale_plan;
        this.qcriticMale_cele = qcriticMale_cele;
        this.qcriticMale_nast = qcriticMale_nast;
        this.qcriticMale_fiks = qcriticMale_fiks;
        this.qcriticMale_samo = qcriticMale_samo;
        this.qcriticMale_orie = qcriticMale_orie;
        this.mcriticFemale_general = mcriticFemale_general;
        this.mcriticFemale_plan = mcriticFemale_plan;
        this.mcriticFemale_cele = mcriticFemale_cele;
        this.mcriticFemale_nast = mcriticFemale_nast;
        this.mcriticFemale_fiks = mcriticFemale_fiks;
        this.mcriticFemale_samo = mcriticFemale_samo;
        this.mcriticFemale_orie = mcriticFemale_orie;
        this.qcriticFemale_general = qcriticFemale_general;
        this.qcriticFemale_plan = qcriticFemale_plan;
        this.qcriticFemale_cele = qcriticFemale_cele;
        this.qcriticFemale_nast = qcriticFemale_nast;
        this.qcriticFemale_fiks = qcriticFemale_fiks;
        this.qcriticFemale_samo = qcriticFemale_samo;
        this.qcriticFemale_orie = qcriticFemale_orie;
    }

    public long getId_attempt() {
        return id_attempt;
    }

    public void setId_attempt(long id_attempt) {
        this.id_attempt = id_attempt;
    }

    public String getData_attempt() {
        return data_attempt;
    }

    public void setData_attempt(String data_attempt) {
        this.data_attempt = data_attempt;
    }

    public int getPoints_general() {
        return points_general;
    }

    public void setPoints_general(int points_general) {
        this.points_general = points_general;
    }

    public int getPoints_plan() {
        return points_plan;
    }

    public void setPoints_plan(int points_plan) {
        this.points_plan = points_plan;
    }

    public int getPoints_cele() {
        return points_cele;
    }

    public void setPoints_cele(int points_cele) {
        this.points_cele = points_cele;
    }

    public int getPoints_nast() {
        return points_nast;
    }

    public void setPoints_nast(int points_nast) {
        this.points_nast = points_nast;
    }

    public int getPoints_fiks() {
        return points_fiks;
    }

    public void setPoints_fiks(int points_fiks) {
        this.points_fiks = points_fiks;
    }

    public int getPoints_samo() {
        return points_samo;
    }

    public void setPoints_samo(int points_samo) {
        this.points_samo = points_samo;
    }

    public int getPoints_orie() {
        return points_orie;
    }

    public void setPoints_orie(int points_orie) {
        this.points_orie = points_orie;
    }

    public String getName_general() {
        return name_general;
    }

    public void setName_general(String name_general) {
        this.name_general = name_general;
    }

    public String getName_plan() {
        return name_plan;
    }

    public void setName_plan(String name_plan) {
        this.name_plan = name_plan;
    }

    public String getName_cele() {
        return name_cele;
    }

    public void setName_cele(String name_cele) {
        this.name_cele = name_cele;
    }

    public String getName_nast() {
        return name_nast;
    }

    public void setName_nast(String name_nast) {
        this.name_nast = name_nast;
    }

    public String getName_fiks() {
        return name_fiks;
    }

    public void setName_fiks(String name_fiks) {
        this.name_fiks = name_fiks;
    }

    public String getName_samo() {
        return name_samo;
    }

    public void setName_samo(String name_samo) {
        this.name_samo = name_samo;
    }

    public String getName_orie() {
        return name_orie;
    }

    public void setName_orie(String name_orie) {
        this.name_orie = name_orie;
    }

    public String getDescription_general() {
        return description_general;
    }

    public void setDescription_general(String description_general) {
        this.description_general = description_general;
    }

    public String getDescription_plan() {
        return description_plan;
    }

    public void setDescription_plan(String description_plan) {
        this.description_plan = description_plan;
    }

    public String getDescription_cele() {
        return description_cele;
    }

    public void setDescription_cele(String description_cele) {
        this.description_cele = description_cele;
    }

    public String getDescription_nast() {
        return description_nast;
    }

    public void setDescription_nast(String description_nast) {
        this.description_nast = description_nast;
    }

    public String getDescription_fiks() {
        return description_fiks;
    }

    public void setDescription_fiks(String description_fiks) {
        this.description_fiks = description_fiks;
    }

    public String getDescription_samo() {
        return description_samo;
    }

    public void setDescription_samo(String description_samo) {
        this.description_samo = description_samo;
    }

    public String getDescription_orie() {
        return description_orie;
    }

    public void setDescription_orie(String description_orie) {
        this.description_orie = description_orie;
    }

    public int getMcriticMale_general() {
        return mcriticMale_general;
    }

    public void setMcriticMale_general(int mcriticMale_general) {
        this.mcriticMale_general = mcriticMale_general;
    }

    public int getMcriticMale_plan() {
        return mcriticMale_plan;
    }

    public void setMcriticMale_plan(int mcriticMale_plan) {
        this.mcriticMale_plan = mcriticMale_plan;
    }

    public int getMcriticMale_cele() {
        return mcriticMale_cele;
    }

    public void setMcriticMale_cele(int mcriticMale_cele) {
        this.mcriticMale_cele = mcriticMale_cele;
    }

    public int getMcriticMale_nast() {
        return mcriticMale_nast;
    }

    public void setMcriticMale_nast(int mcriticMale_nast) {
        this.mcriticMale_nast = mcriticMale_nast;
    }

    public int getMcriticMale_fiks() {
        return mcriticMale_fiks;
    }

    public void setMcriticMale_fiks(int mcriticMale_fiks) {
        this.mcriticMale_fiks = mcriticMale_fiks;
    }

    public int getMcriticMale_samo() {
        return mcriticMale_samo;
    }

    public void setMcriticMale_samo(int mcriticMale_samo) {
        this.mcriticMale_samo = mcriticMale_samo;
    }

    public int getMcriticMale_orie() {
        return mcriticMale_orie;
    }

    public void setMcriticMale_orie(int mcriticMale_orie) {
        this.mcriticMale_orie = mcriticMale_orie;
    }

    public int getQcriticMale_general() {
        return qcriticMale_general;
    }

    public void setQcriticMale_general(int qcriticMale_general) {
        this.qcriticMale_general = qcriticMale_general;
    }

    public int getQcriticMale_plan() {
        return qcriticMale_plan;
    }

    public void setQcriticMale_plan(int qcriticMale_plan) {
        this.qcriticMale_plan = qcriticMale_plan;
    }

    public int getQcriticMale_cele() {
        return qcriticMale_cele;
    }

    public void setQcriticMale_cele(int qcriticMale_cele) {
        this.qcriticMale_cele = qcriticMale_cele;
    }

    public int getQcriticMale_nast() {
        return qcriticMale_nast;
    }

    public void setQcriticMale_nast(int qcriticMale_nast) {
        this.qcriticMale_nast = qcriticMale_nast;
    }

    public int getQcriticMale_fiks() {
        return qcriticMale_fiks;
    }

    public void setQcriticMale_fiks(int qcriticMale_fiks) {
        this.qcriticMale_fiks = qcriticMale_fiks;
    }

    public int getQcriticMale_samo() {
        return qcriticMale_samo;
    }

    public void setQcriticMale_samo(int qcriticMale_samo) {
        this.qcriticMale_samo = qcriticMale_samo;
    }

    public int getQcriticMale_orie() {
        return qcriticMale_orie;
    }

    public void setQcriticMale_orie(int qcriticMale_orie) {
        this.qcriticMale_orie = qcriticMale_orie;
    }

    public int getMcriticFemale_general() {
        return mcriticFemale_general;
    }

    public void setMcriticFemale_general(int mcriticFemale_general) {
        this.mcriticFemale_general = mcriticFemale_general;
    }

    public int getMcriticFemale_plan() {
        return mcriticFemale_plan;
    }

    public void setMcriticFemale_plan(int mcriticFemale_plan) {
        this.mcriticFemale_plan = mcriticFemale_plan;
    }

    public int getMcriticFemale_cele() {
        return mcriticFemale_cele;
    }

    public void setMcriticFemale_cele(int mcriticFemale_cele) {
        this.mcriticFemale_cele = mcriticFemale_cele;
    }

    public int getMcriticFemale_nast() {
        return mcriticFemale_nast;
    }

    public void setMcriticFemale_nast(int mcriticFemale_nast) {
        this.mcriticFemale_nast = mcriticFemale_nast;
    }

    public int getMcriticFemale_fiks() {
        return mcriticFemale_fiks;
    }

    public void setMcriticFemale_fiks(int mcriticFemale_fiks) {
        this.mcriticFemale_fiks = mcriticFemale_fiks;
    }

    public int getMcriticFemale_samo() {
        return mcriticFemale_samo;
    }

    public void setMcriticFemale_samo(int mcriticFemale_samo) {
        this.mcriticFemale_samo = mcriticFemale_samo;
    }

    public int getMcriticFemale_orie() {
        return mcriticFemale_orie;
    }

    public void setMcriticFemale_orie(int mcriticFemale_orie) {
        this.mcriticFemale_orie = mcriticFemale_orie;
    }

    public int getQcriticFemale_general() {
        return qcriticFemale_general;
    }

    public void setQcriticFemale_general(int qcriticFemale_general) {
        this.qcriticFemale_general = qcriticFemale_general;
    }

    public int getQcriticFemale_plan() {
        return qcriticFemale_plan;
    }

    public void setQcriticFemale_plan(int qcriticFemale_plan) {
        this.qcriticFemale_plan = qcriticFemale_plan;
    }

    public int getQcriticFemale_cele() {
        return qcriticFemale_cele;
    }

    public void setQcriticFemale_cele(int qcriticFemale_cele) {
        this.qcriticFemale_cele = qcriticFemale_cele;
    }

    public int getQcriticFemale_nast() {
        return qcriticFemale_nast;
    }

    public void setQcriticFemale_nast(int qcriticFemale_nast) {
        this.qcriticFemale_nast = qcriticFemale_nast;
    }

    public int getQcriticFemale_fiks() {
        return qcriticFemale_fiks;
    }

    public void setQcriticFemale_fiks(int qcriticFemale_fiks) {
        this.qcriticFemale_fiks = qcriticFemale_fiks;
    }

    public int getQcriticFemale_samo() {
        return qcriticFemale_samo;
    }

    public void setQcriticFemale_samo(int qcriticFemale_samo) {
        this.qcriticFemale_samo = qcriticFemale_samo;
    }

    public int getQcriticFemale_orie() {
        return qcriticFemale_orie;
    }

    public void setQcriticFemale_orie(int qcriticFemale_orie) {
        this.qcriticFemale_orie = qcriticFemale_orie;
    }
}