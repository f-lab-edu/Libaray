package flab.library.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class LibraryPolicyValues {

    @Value("${external.late-fee:100}")
    private long lateFeePerDay;


    @Value("${external.renew-days:14}")
    private long renewDays;
}
