package com.hykj.liuzhi.androidcomponents.bean;

import com.contrarywind.interfaces.IPickerViewData;

public class UserTableBean implements IPickerViewData{
    private int id;
    private String table;

    public UserTableBean(int id, String table) {
        this.id = id;
        this.table = table;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @Override
    public String getPickerViewText() {
        return table;
    }

        }
