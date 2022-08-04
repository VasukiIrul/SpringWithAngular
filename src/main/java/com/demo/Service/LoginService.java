package com.demo.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Model.User;
import com.demo.Repo.UserRepo;
import com.demo.common.APIResponse;
import com.demo.dto.LoginRequestDTO;
import com.demo.dto.SignUpRequestDTO;
import com.demo.util.JwtUtils;

@Service
public class LoginService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	private JwtUtils jwtUtils;

	public APIResponse signUp(SignUpRequestDTO signUpRequestDTO) {

		APIResponse apiResponse = new APIResponse();

		// validation

		// dto to entity
		User userEntity = new User();
		userEntity.setName(signUpRequestDTO.getName());
		userEntity.setEmailId(signUpRequestDTO.getEmailId());
		userEntity.setIsActive(Boolean.TRUE);
		userEntity.setGender(signUpRequestDTO.getGender());
		userEntity.setPhoneNumber(signUpRequestDTO.getPhoneNumber());
		userEntity.setPassword(signUpRequestDTO.getPassword());

		// store to entity
		userEntity = userRepo.save(userEntity);

		// return

		apiResponse.setData(userEntity);
		return apiResponse;

	}

	public APIResponse login(LoginRequestDTO loginRequestDTO) {
		APIResponse apiResponse = new APIResponse();

		// validation

		// verrify user given value with database

		User user = userRepo.findOneByEmailIdIgnoreCaseAndPassword(loginRequestDTO.getEmailId(),
				loginRequestDTO.getPassword());
		if (user == null) {
			apiResponse.setData("User login failed");
			return apiResponse;
		}

		// generate a jwt

		/*
		 * String token=jwtUtils.generateJwt(user); 
		 * apiResponse.setData(token); 
		 * return apiResponse;
		 */

		String token = jwtUtils.generateJwt(user);

		Map<String, Object> data = new HashMap<>();
		data.put("accessToken", token);
		apiResponse.setData(data);
		return apiResponse;

	}

}
