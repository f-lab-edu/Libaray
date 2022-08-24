package flab.library.infrastructure.cache;

import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Optional;
import java.util.Set;

public interface CacheStore {
    Optional<Long> incrementScore(String key, String member, Long score);

    Set<ZSetOperations.TypedTuple<String>> getRanksWithScore(String key, Pageable pageable);
}
