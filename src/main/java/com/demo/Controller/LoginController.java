package com.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.Service.LoginService;
import com.demo.common.APIResponse;
import com.demo.dto.LoginRequestDTO;
import com.demo.dto.SignUpRequestDTO;
import com.demo.util.JwtUtils;

@Controller
@RequestMapping("/user")
public class LoginController {

	@Autowired
	LoginService loginService;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/signUp")
	public ResponseEntity<APIResponse> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {

		APIResponse apiResponse = new APIResponse();

		apiResponse = loginService.signUp(signUpRequestDTO);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	@PostMapping("/login")
	public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO) {

		APIResponse apiResponse = new APIResponse();

		apiResponse = loginService.login(loginRequestDTO);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	@GetMapping("/privateApi")
	public ResponseEntity<APIResponse> privateApi(			
			@RequestHeader(value = "authorization", defaultValue = " ") String auth) throws Exception {
		
		APIResponse apiResponse = new APIResponse();

		jwtUtils.verify(auth);
		
		apiResponse.setData("This is private Data");
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

	}
}
