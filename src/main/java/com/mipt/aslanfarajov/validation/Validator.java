package com.mipt.aslanfarajov.validation;
import java.lang.reflect.Field;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mipt.aslanfarajov.validation.annotations.NotNull;
import com.mipt.aslanfarajov.validation.annotations.Size;
import com.mipt.aslanfarajov.validation.annotations.Range;
import com.mipt.aslanfarajov.validation.annotations.Email;
import com.mipt.aslanfarajov.validation.model.User;

public class Validator {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public static ValidationResult validate(Object object) {
        ValidationResult result = new ValidationResult();

        if (object == null) {
            result.addError("Validated object cannot be null");
            return result;
        }

        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(object);
                validateField(field, value, result);
            } catch (IllegalAccessException e) {
                result.addError("Cannot access field: " + field.getName());
            }
        }

        return result;
    }

    private static void validateField(Field field, Object value, ValidationResult result) {
        // @NotNull проверка
        if (field.isAnnotationPresent(NotNull.class)) {
            NotNull annotation = field.getAnnotation(NotNull.class);
            if (value == null) {
                result.addError(annotation.message());
            }
        }

        // @Size проверка для строк
        if (field.isAnnotationPresent(Size.class) && value instanceof String) {
            Size annotation = field.getAnnotation(Size.class);
            String strValue = (String) value;
            int length = strValue.length();

            if (length < annotation.min() || length > annotation.max()) {
                result.addError(annotation.message());
            }
        }

        // @Range проверка для чисел
        if (field.isAnnotationPresent(Range.class) && value != null) {
            Range annotation = field.getAnnotation(Range.class);

            if (value instanceof Integer) {
                int intValue = (Integer) value;
                if (intValue < annotation.min() || intValue > annotation.max()) {
                    result.addError(annotation.message());
                }
            } else if (value instanceof Long) {
                long longValue = (Long) value;
                if (longValue < annotation.min() || longValue > annotation.max()) {
                    result.addError(annotation.message());
                }
            }
        }

        // @Email проверка
        if (field.isAnnotationPresent(Email.class) && value instanceof String) {
            Email annotation = field.getAnnotation(Email.class);
            String email = (String) value;

            if (email != null && !EMAIL_PATTERN.matcher(email).matches()) {
                result.addError(annotation.message());
            }
        }
    }
}
