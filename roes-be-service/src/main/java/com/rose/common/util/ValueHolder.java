package com.rose.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;

@Slf4j
@Singleton
@Component
public class ValueHolder {

    private static final ThreadLocal<Long> userIdHolder = new ThreadLocal<>();

    public void setUserIdHolder(Long userId) {
        this.userIdHolder.set(userId);
    }

    public Long getUserIdHolder() {
        return this.userIdHolder.get();
    }

    public void removeAll() {
        this.userIdHolder.remove();
    }
}