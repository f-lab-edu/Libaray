package flab.library.infrastructure.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RedisStore implements CacheStore {
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public Optional<Long> incrementScore(String key, String member, Long score) {
        return Optional.ofNullable(redisTemplate.opsForZSet().incrementScore(key, member, score))
                .map(Double::longValue);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> getRanksWithScore(String key, Pageable pageable) {
        return redisTemplate.opsForZSet()
                .reverseRangeWithScores(
                        key
                        , pageable.getOffset()
                        , pageable.getOffset() + pageable.getPageSize() - 1
                );
    }
}
