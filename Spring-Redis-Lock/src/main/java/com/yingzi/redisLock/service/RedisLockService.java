package com.yingzi.redisLock.service;

import java.util.concurrent.locks.Lock;

/**
 * @author yingzi
 * @date 2025/3/12:14:51
 */
public interface RedisLockService {

    void lock(String lockKey);

    boolean tryLock(String lockKey);

    boolean tryLock(String lockKey, long seconds);

    void unlock(String lockKey);

    Lock obtainLock(String lockKey);

}
