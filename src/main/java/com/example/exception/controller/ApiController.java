package com.example.exception.controller;

import com.example.exception.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api/user")
@Validated
public class ApiController {

    @GetMapping("")
    public User get(
            @Size(min = 2)
            @RequestParam String name,

            @NotNull
            @Min(1)
            @RequestParam Integer age){
        //required = false이면 값이 없어도 실행이 가능하고 없는경우 null로 들어가기때문에 nullException발생가능
        User user = new User();
        user.setAge(age);
        user.setName(name);

        int a = 10+age;//nullPointException발생

        return user;
    }

    @PostMapping("")
    public User post(@Valid @RequestBody User user){
        System.out.println(user);
        return user;

    }
    /*
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity myException(MethodArgumentNotValidException e){
        System.out.println("이 컨트롤러의 예외는 여기를 탑니다!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity missingException(MissingServletRequestParameterException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

     */
}
