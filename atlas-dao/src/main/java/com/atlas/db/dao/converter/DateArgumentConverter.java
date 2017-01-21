package com.atlas.db.dao.converter;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.Argument;
import org.skife.jdbi.v2.tweak.ArgumentFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateArgumentConverter implements ArgumentFactory<LocalDate> {

    @Override
    public boolean accepts(Class<?> expectedType, Object value, StatementContext statementContext) {
        return expectedType == LocalDate.class && value instanceof LocalDate;

    }

    @Override
    public Argument build(Class<?> aClass, LocalDate value, StatementContext statementContext) {
        return (position, statement, ctx) -> statement.setDate(position, new Date(value.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()));
    }
}
