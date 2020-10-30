package com.practice.online_diagnost.api.resources.v1;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Validator {
    public static boolean isValidString(String string) {
        return !Objects.isNull(string) && !string.isEmpty();
    }

    public static boolean isValidDate(Date date) {
        if (Objects.isNull(date) || date.toString().isEmpty()) {
            return false;
        }

        LocalDate localDate7yearsAgo = LocalDate.now();
        localDate7yearsAgo = localDate7yearsAgo.minusYears(7);
        Date checkDate = Date.valueOf(localDate7yearsAgo);
        return checkDate.compareTo(date) > 0;
    }
}

