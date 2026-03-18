package com.example.weatherapppetprojectv2.service;

import com.example.weatherapppetprojectv2.dto.AddUserDto;
import com.example.weatherapppetprojectv2.dto.AddUserResponseDto;
import com.example.weatherapppetprojectv2.entity.Authority;
import com.example.weatherapppetprojectv2.exception.SameUsernameOrEmailUserExistsException;
import com.example.weatherapppetprojectv2.mapper.AddUserDtoMapper;
import com.example.weatherapppetprojectv2.mapper.AddUserResponseMapper;
import com.example.weatherapppetprojectv2.mapper.UserLoginResponseMapper;
import com.example.weatherapppetprojectv2.dto.UserLoginDto;
import com.example.weatherapppetprojectv2.dto.UserLoginDtoResponse;
import com.example.weatherapppetprojectv2.entity.User;
import com.example.weatherapppetprojectv2.exception.UsernameNotFoundException;
import com.example.weatherapppetprojectv2.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserLoginResponseMapper userLoginResponseMapper;
    private final AddUserResponseMapper addUserResponseMapper;
    private final AddUserDtoMapper addUserDtoMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthorityService authorityService;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return getUserByUsername(username);
    }

    public UserLoginDtoResponse loginUserAndGetToken(UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword())
        );
        if (authentication.isAuthenticated()) {
            String jwtToken = jwtService.generateToken(userLoginDto.getUsername());
            return new UserLoginDtoResponse(userLoginDto.getUsername(), jwtToken);
        } else {
            throw new UsernameNotFoundException(userLoginDto.getUsername());
        }
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public Boolean userWithSameUsernameOrEmailExists(String username, String email) {
        return userRepository.existsUserByUsernameOrEmail(username, email);
    }

    public AddUserResponseDto addUser(AddUserDto addUserDto) {
        if(userWithSameUsernameOrEmailExists(addUserDto.getUsername(), addUserDto.getEmail())) {
            throw new SameUsernameOrEmailUserExistsException(addUserDto.getUsername(), addUserDto.getEmail());
        }
        User user = addUserDtoMapper.toEntity(addUserDto);
        user.setPassword(encoder.encode(addUserDto.getPassword()));
        addAuthorityToUser(user, "USER");
        user = userRepository.save(user);
        return addUserResponseMapper.toDto(user);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    private void addAuthorityToUser(User user, String authority) {
        Authority userAuthority = authorityService.getAuthorityByAuthority(authority); //TODO do enumu?
        user.getAuthorities().add(userAuthority);
        userAuthority.getUsers().add(user);
    }
}
