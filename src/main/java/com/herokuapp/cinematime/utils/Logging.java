package com.herokuapp.cinematime.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Logging {
    public void log(String s, Object o) {
        log.info(s,o);
    }
}