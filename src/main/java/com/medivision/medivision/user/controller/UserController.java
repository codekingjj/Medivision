package com.medivision.medivision.user.controller;

import com.medivision.medivision.user.domain.Paging;
import com.medivision.medivision.user.domain.entity.AdminEntity;
import com.medivision.medivision.user.domain.service.AdminService;
import com.medivision.medivision.user.domain.service.UserService;
import com.medivision.medivision.user.dto.request.SignInRequestDto;
import com.medivision.medivision.user.dto.request.SignUpRequestDto;
import com.medivision.medivision.user.dto.response.SignInResponseDto;
import com.medivision.medivision.user.dto.response.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final AdminService adminService;
    private final UserService userService;
    @PostMapping("/admin")
    public ResponseEntity<? super SignUpResponseDto> signup(@RequestBody SignUpRequestDto requestBody){
        ResponseEntity<? super SignUpResponseDto> response = adminService.signUp(requestBody);
        return  response;
    }

    @GetMapping("/auth/sign-in")
    public String signinPage(){//@AuthenticationPrincipal String code
        return "user/login";
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<? super SignInResponseDto> signin(@RequestParam("userId") String userId, @RequestParam("userPassword") String userPassword){
        SignInRequestDto requestBody = new SignInRequestDto();
        requestBody.setUserId(userId);
        requestBody.setUserPassword(userPassword);
        ResponseEntity<? super  SignInResponseDto> response = userService.signin(requestBody);
        return response;
    }

    @GetMapping("/admin")
    public String userList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model){

        int pageSize = 8;
        int blockPage = 5;

        Page<AdminEntity> userList = adminService.userLIst(pageNum,pageSize);
        long totalCountLong = adminService.getTotalCount();
        int totalCount = (int)totalCountLong;
        String pagingImg = Paging.pagingStr(totalCount, pageSize, blockPage, pageNum, "/admin");
        System.out.println("pagingImg: "+ pagingImg);
        model.addAttribute("userLists", userList.getContent());
        model.addAttribute("pagingImg", pagingImg);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNum", pageNum);
        return "user/userList";
    }

    @GetMapping("/auth/select")
    public String authSelect(){
        return"user/select";
    }

    @GetMapping("/admin/sign-in")
    public String adminSigninPage(){
        return "user/adminLogin";
    }

    @PostMapping("/admin/sign-in")
    public String adminSignin(@RequestParam("userId") String userId, @RequestParam("userPassword") String userPassword){
        SignInRequestDto requestBody = new SignInRequestDto();
        requestBody.setUserId(userId);
        requestBody.setUserPassword(userPassword);
        System.out.println("id: "+ requestBody.getUserId());
        System.out.println("pw: "+ requestBody.getUserPassword());
        boolean isCheck = adminService.adminSignIn(requestBody);
        System.out.println("check: "+isCheck);
        if(isCheck)
            return "redirect:/admin";
        return "redirect:/auth/select";
    }

}
