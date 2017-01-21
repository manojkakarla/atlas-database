package com.atlas.db.dao.converter;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.Argument;
import org.skife.jdbi.v2.tweak.ArgumentFactory;

import java.sql.Date;

public class DateTimeArgumentConverter implements ArgumentFactory<DateTime> {

    @Override
    public boolean accepts(Class<?> expectedType, Object value, StatementContext statementContext) {
        return expectedType == DateTime.class && value instanceof DateTime;

    }

    @Override
    public Argument build(Class<?> aClass, DateTime value, StatementContext statementContext) {
        return (position, statement, ctx) -> statement.setDate(position, new Date(value.toDate().getTime()));
    }
}
