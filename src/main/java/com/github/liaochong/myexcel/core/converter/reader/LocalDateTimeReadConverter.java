/*
 * Copyright 2019 liaochong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.liaochong.myexcel.core.converter.reader;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * LocalDateTime读取转换器
 *
 * @author liaochong
 * @version 1.0
 */
public class LocalDateTimeReadConverter extends AbstractReadConverter<LocalDateTime> {

    @Override
    public LocalDateTime doConvert(String v, Field field) {
        if (isNumber(v)) {
            final long time = Long.parseLong(v);
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), TimeZone
                    .getDefault().toZoneId());
        }
        if (isExcelNumber(v)) {
            final long time = convertExcelNumberDateToMilli(v);
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), TimeZone
                    .getDefault().toZoneId());
        }
        DateTimeFormatter dateTimeFormatter = getDateFormatFormatter(field);
        return LocalDateTime.parse(v, dateTimeFormatter);
    }
}
