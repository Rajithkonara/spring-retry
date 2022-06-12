package com.rkdevblog.retry.service;

import com.rkdevblog.retry.exception.BackendException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final RetryTemplate retryTemplate;
    private final RestTemplate restTemplate;

    private final AtomicInteger trigger = new AtomicInteger(0);

    @Override
    public String getInventoryStatus(String id, String additionalInfo) {
        log.info("Calling get inventory status " + trigger.get());
        trigger.getAndIncrement();
        if (additionalInfo != null && trigger.get() == 2) {
            return "Active";
        }
        throw new BackendException("Backend failed intentionally");
    }

    @Override
    public String handleInventoryBackendFailure(RuntimeException exception, String id, String additionalInfo) {
        String errorMessage = "Error occurred while calling backend with inventoryId: " + id +
              " additionalParams " + additionalInfo + " cause: " + exception.getMessage();
        log.error(errorMessage);
        throw new BackendException(errorMessage);
    }

    @Override
    public void callToExternalService() {
        retryTemplate.execute((retryContext -> {
            log.info("Calling external service, retry count: " + retryContext.getRetryCount());
            return restTemplate.exchange("http://localhost:8081/external", HttpMethod.GET, null, String.class);
        }));
    }

}
