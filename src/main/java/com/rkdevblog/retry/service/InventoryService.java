package com.rkdevblog.retry.service;

import com.rkdevblog.retry.exception.BackendException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

public interface InventoryService {

    /**
     * Recover method should have same return type as the retry method
     * Default retry attempts is 3 (maxAttempts)
     * delay is in milliseconds
     * you can use the same params to recover method, so you can log them which will be helpful
     **/
    @Retryable(value = {BackendException.class},
          backoff = @Backoff(delay = 2000),
          maxAttempts = 2)
    String getInventoryStatus(String id, String additionalInfo);

    // This method is called if all the retry attempts failed
    @Recover
    String handleInventoryBackendFailure(RuntimeException exception, String id, String additionalInfo);

    // Method to test RetryTemplate
    void callToExternalService();

}
