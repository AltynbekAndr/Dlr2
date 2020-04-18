package com.springapp.mvc.service;


import org.hibernate.dialect.SQLServer2005Dialect;
import org.hibernate.dialect.function.NoArgSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class CustomDialect extends SQLServer2005Dialect {
    public CustomDialect() {
        this.registerColumnType(91, "date");
        this.registerColumnType(92, "time");
        this.registerColumnType(93, "datetime");
        this.registerFunction("current_timestamp", new NoArgSQLFunction("current_timestamp", StandardBasicTypes.TIMESTAMP, true));
    }
}
