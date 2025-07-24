package eatda.domain.storage;

import eatda.domain.config.CacheConfig;
import org.springframework.cache.CacheManager;

public abstract class BaseStorageTest {

    private final CacheManager cacheManager = new CacheConfig().cacheManager();

    protected CacheManager getCacheManager() {
        return cacheManager;
    }
}
