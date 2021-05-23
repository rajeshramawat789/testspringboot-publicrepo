package com.test.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
@Component
public class MessageUtil {

    @Autowired
    private MessageSource messageSource;

    private static final Locale LOCALE = new Locale("ENG");

    public String getMessage(String key){
        return messageSource.getMessage(key, null, LOCALE);
    }

    public String getMessage(String key, String... args) {
        return messageSource.getMessage(key, args, LOCALE);
    }

}
