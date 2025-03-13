package com.yingzi.redisLock.service.impl;

import com.yingzi.redisLock.service.RedisLockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author yingzi
 * @date 2025/3/12:14:51
 */
@Slf4j
@Service
public class RedisLockServiceImpl implements RedisLockService {

    @Autowired
    private RedisLockRegistry redisLockRegistry;

    private static final long DEFAULT_EXPIRE_UNUSED = 60000L;

    @Override
    public void lock(String lockKey) {
        Lock lock = obtainLock(lockKey);
        lock.lock();
    }

    @Override
    public boolean tryLock(String lockKey) {
        Lock lock = obtainLock(lockKey);
        return lock.tryLock();    }

    @Override
    public boolean tryLock(String lockKey, long seconds) {
        Lock lock = obtainLock(lockKey);
        try {
            return lock.tryLock(seconds, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return false;
        }    }

    @Override
    public void unlock(String lockKey) {
        try {
            Lock lock = obtainLock(lockKey);
            lock.unlock();
            redisLockRegistry.expireUnusedOlderThan(DEFAULT_EXPIRE_UNUSED);
        } catch (Exception e) {
            log.error("分布式锁 [{}] 释放异常", lockKey, e);
        }
    }

    @Override
    public Lock obtainLock(String lockKey) {
        return redisLockRegistry.obtain(lockKey);
    }
}
