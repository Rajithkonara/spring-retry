package com.rkdevblog.retry.controller;

import com.rkdevblog.retry.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getInventoryStatus(@PathVariable String id, @RequestParam(required = false) String query) {
            String inventoryStatus = inventoryService.getInventoryStatus(id, query);
            log.info("Status: "+ inventoryStatus);
            return ResponseEntity.ok(inventoryStatus);
    }

    @GetMapping("/external")
    public ResponseEntity<String> get() {
        inventoryService.callToExternalService();
        return ResponseEntity.ok("ok");
    }

}
