package com.ipro.sns.service;

import com.ipro.sns.model.UserModel;
import com.ipro.sns.model.Role;
import com.ipro.sns.repository.UserRepository;
import com.ipro.sns.model.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.transaction.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    public Optional<UserModel> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public Optional<UserModel> findById(int id) { return userRepository.findById(id); }
    public Optional<UserModel> findByUsernick(String usernick) {
        return userRepository.findByUsernick(usernick);
    }

    public boolean check(UserDto userDto, BindingResult bindingResult) {
        // 회원가입 유효성 검사
        if (bindingResult.hasErrors()) {
            return true;
        }

        if (findByUsername(userDto.getUsername()).isPresent()) {
            bindingResult.rejectValue("username", null, "중복된 아이디 입니다.");
            return true;
        }
        return false;
    }

    @Transactional
    public int joinUser(UserDto userDto){
        //비밀번호 암호화 후 회원가입 처리
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setUserpw(passwordEncoder.encode(userDto.getUserpw()));

        return userRepository.save(userDto.toEntity()).getId();
    }

    //프로필사진 변경
    public void img_edit(String username, String userimg) {
        Optional<UserModel> uModel = userRepository.findByUsername(username);
        UserModel userModel = uModel.get();
        userModel.setUsername(username);
        userModel.setUserimg(userimg);
        userRepository.save(userModel);
    }

    //유저 프로필 내용 수정
    public void user_edit(String username, String usernick, String userfull, String userintro) {
        Optional<UserModel> uModel = userRepository.findByUsername(username);
        UserModel userModel = uModel.get();
        userModel.setUsername(username);
        userModel.setUsernick(usernick);
        userModel.setUserfull(userfull);
        userModel.setUserintro(userintro);
        userRepository.save(userModel);
    }

    //spring security login method
    @Override //상세정보 조회 메서드. 사용자의 계정정보와 권한을 갖는 userDetail반환
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel>  modelwrapper = userRepository.findByUsername(username);
        UserModel userModel = modelwrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (userModel.getUsername().equals("admin@admin.com")) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        }
        else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        }

        //리턴값으로 넘겨받고 비밀번호와 조회후 로그인
        return new User(userModel.getUsername(), userModel.getUserpw(), authorities);

    }


}
