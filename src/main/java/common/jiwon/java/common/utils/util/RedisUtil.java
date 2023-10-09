package common.jiwon.java.common.utils.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtil {

    private final RedisTemplate redisTemplate;

    public String getValue(String key) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();

        return values.get(key);
    }

    public void setValue(String key, String value) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();

        values.set(key,value);
    }

    public void setValue(String key, String value, Long validityInMilliSeconds) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();

        values.set(key, value, validityInMilliSeconds, TimeUnit.MILLISECONDS);
    }

    public Boolean delValue(String key) {
        return redisTemplate.delete(key);
    }
}
