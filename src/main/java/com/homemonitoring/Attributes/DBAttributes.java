package com.homemonitoring.Attributes;

import com.homemonitoring.dao.DBConnection;

public enum DBAttributes {
    MODULE_ID("module_id"),
    READING("reading"),
    CREATE_DATE("create_date");

    private String columnName;

    private DBAttributes(String colName){
        columnName = colName;
    }

    public String getColumnName(){
        return columnName;
    }
}
