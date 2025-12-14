package com.seek.rpm.customer.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.seek.rpm.customer.dto.CustomResponse;
import com.seek.rpm.customer.dto.CustomerCreateDTO;
import com.seek.rpm.customer.dto.CustomerDTO;
import com.seek.rpm.customer.dto.CustomerMetricsDTO;
import com.seek.rpm.customer.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Customers", description = "Customer management operations")
public class CustomerController {
        private final CustomerService service;

        @Operation(summary = "Get all customers", description = "Returns a list of all registered customers")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Customers retrieved successfully"),
        })
        @GetMapping
        public ResponseEntity<CustomResponse<List<CustomerDTO>>> getCustomers() {
                return ResponseEntity.ok(
                                CustomResponse.<List<CustomerDTO>>builder()
                                                .timeStamp(LocalDateTime.now())
                                                .data(service.getAllCustomer())
                                                .typeData("CUSTOMER")
                                                .message("Customers retrieved successfully")
                                                .status(HttpStatus.OK)
                                                .statusCode(HttpStatus.OK.value())
                                                .build());
        }

        @Operation(summary = "Save  customer", description = "Allow create a customer and return it")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Customer created successfully"),
        })
        @PostMapping
        public ResponseEntity<CustomResponse<CustomerDTO>> createCustomer(
                        @Valid @RequestBody CustomerCreateDTO dto) {
                return ResponseEntity.ok(
                                CustomResponse.<CustomerDTO>builder()
                                                .timeStamp(LocalDateTime.now())
                                                .data(service.createCustomer(dto))
                                                .typeData("CUSTOMER")
                                                .message("Customers retrieved successfully")
                                                .status(HttpStatus.CREATED)
                                                .statusCode(HttpStatus.CREATED.value())
                                                .build());
        }

        @Operation(summary = "Get Metrics customer", description = "Returns a average and standard deviation of all customer")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Customer Metric retrieved successfully"),
        })
        @GetMapping("/metrics")
        public ResponseEntity<CustomResponse<CustomerMetricsDTO>> getMetrics() {
                return ResponseEntity.ok(
                                CustomResponse.<CustomerMetricsDTO>builder()
                                                .timeStamp(LocalDateTime.now())
                                                .status(HttpStatus.OK)
                                                .statusCode(HttpStatus.OK.value())
                                                .data(service.getMetrics())
                                                .build());
        }
}
