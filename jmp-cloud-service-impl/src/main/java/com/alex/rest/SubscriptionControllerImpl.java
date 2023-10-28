package com.alex.rest;

import com.alex.*;
import com.alex.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/subscriptions")
@RestController
@Tag(name = "Subscription", description = "Subscription APIs")
public class SubscriptionControllerImpl implements SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping()
    public ResponseEntity<SubscriptionResponseDto> createSubscription(@RequestBody SubscriptionRequestDto requestDto) {
        SubscriptionResponseDto responseDto = subscriptionService.create(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(@RequestBody SubscriptionRequestDto requestDto,
                                                      @PathVariable Long id) {
        SubscriptionResponseDto responseDto = subscriptionService.update(requestDto, id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteSubscription(@PathVariable Long id) {
        subscriptionService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Operation(
            summary = "Retrieve a Subscription by Id",
            description = "Get a Subscription object by specifying its id. The response is Subscription object",
            tags = { "Subscriptions", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Subscription.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(value = "/{id}")
    public ResponseEntity<SubscriptionResponseDto> getSubscription(@PathVariable Long id) {
        SubscriptionResponseDto responseDto = subscriptionService.getById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<SubscriptionResponseDto>> getAllSubscription() {
        List<SubscriptionResponseDto> responseDtos = subscriptionService.getAll();
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }
}
