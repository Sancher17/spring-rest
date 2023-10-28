package com.alex.rest;

import com.alex.User;
import com.alex.UserController;
import com.alex.UserRequestDto;
import com.alex.UserResponseDto;
import com.alex.service.UserService;
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
@RequestMapping("/api/users")
@RestController
@Tag(name = "User", description = "User APIs")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.create(userRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto,
                                                      @PathVariable Long id) {
        UserResponseDto userResponseDto = userService.update(userRequestDto, id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Operation(
            summary = "Retrieve a User by Id",
            description = "Get a User object by specifying its id. The response is User object",
            tags = { "Users", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.getById(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getAllUser() {
        List<UserResponseDto> userResponseDtos = userService.getAll();
        return new ResponseEntity<>(userResponseDtos, HttpStatus.OK);
    }
}