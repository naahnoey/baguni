package com.baguni.baguni.controller;

import com.baguni.baguni.domain.user.*;
import com.baguni.baguni.payload.request.LoginRequest;
import com.baguni.baguni.payload.request.SignupRequest;
import com.baguni.baguni.payload.response.MessageResponse;
import com.baguni.baguni.payload.response.UserInfoResponse;
import com.baguni.baguni.security.jwt.JwtUtils;
import com.baguni.baguni.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    BasicUserRepository basicUserRepository;

    @Autowired
    WelfareUserRepository welfareUserRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        userDetails.getRealname(),
                        userDetails.getHeadcount(),
                        userDetails.getNickname(),
                        userDetails.getAddress(),
                        role));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (basicUserRepository.existsByUsername(signupRequest.getUsername())
            || basicUserRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username or email is already taken!"));
        }
        if (welfareUserRepository.existsByEmail(signupRequest.getEmail())
            || welfareUserRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username or email is already taken!"));
        }
        if (adminRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user's account
        String strRole = signupRequest.getRole();
        UserRole role;

        if (strRole == null) {  // 일반 유저 생성
            basicUserRepository.save(createBasicUser(signupRequest));
        } else {
            switch (strRole) {
                case "admin":
                    Admin admin = new Admin(signupRequest.getUsername(), encoder.encode(signupRequest.getPassword()));
                    adminRepository.save(admin);
                    break;
                case "welfare": // 복지관 유저 생성
                    WelfareUser user = new WelfareUser(signupRequest.getUsername(),
                            signupRequest.getEmail(),
                            encoder.encode(signupRequest.getPassword()),
                            signupRequest.getRealname(),
                            signupRequest.getHeadcount(),
                            signupRequest.getNickname(),
                            signupRequest.getAddress(),
                            signupRequest.getCategory(),
                            signupRequest.getTelephone(),
                            signupRequest.getIntroduction());

                    user.setRole(UserRole.ROLE_WELFARE);

                    welfareUserRepository.save(user);

                    break;
                default:
                    basicUserRepository.save(createBasicUser(signupRequest));
            };
        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
    
    // signup request 정보로 basic user 생성
    private BasicUser createBasicUser(SignupRequest signupRequest) {
        Set<Category> categories = new HashSet<>();
        signupRequest.getCategories().forEach((strCategory) -> {
            Category category = switch (strCategory) {
                case "mentoring" -> Category.MENTORING;
                case "education" -> Category.EDUCATION;
                case "assistant" -> Category.ASSISTANT;
                case "public_interest" -> Category.PUBLIC_INTEREST;
                case "disaster" -> Category.DISASTER;
                case "overseas" -> Category.OVERSEAS;
                case "rural" -> Category.RURAL;
                case "convenience" -> Category.CONVENIENCE;
                case "residential_environment" -> Category.RESIDENTIAL_ENVIRONMENT;
                case "culture" -> Category.CULTURE;
                case "consulting" -> Category.CONSULTING;
                case "environment_protection" -> Category.ENVIRONMENT_PROTECTION;
                case "health_care" -> Category.HEALTH_CARE;
                case "safety" -> Category.SAFETY;
                case "abandoned_animal" -> Category.ABANDONED_ANIMAL;
                case "entire" -> Category.ENTIRE;
                default -> throw new IllegalArgumentException("Invalid input: " + strCategory);
            };

            categories.add(category);
        });

        Set<Day> days = new HashSet<>();
        signupRequest.getDays().forEach((strDay) -> {
            Day day = switch (strDay) {
                case "monday" -> Day.MONDAY;
                case "tuesday" -> Day.TUESDAY;
                case "wednesday" -> Day.WEDNESDAY;
                case "thursday" -> Day.THURSDAY;
                case "friday" -> Day.FRIDAY;
                case "saturday" -> Day.SATURDAY;
                case "sunday" -> Day.SUNDAY;
                default -> throw new IllegalArgumentException("Invalid input: " + strDay);
            };

            days.add(day);
        });

        ActivityType activityType = switch (signupRequest.getActivityType()) {
            case "face_to_face" -> ActivityType.FACE_TO_FACE;
            case "non_face_to_face" -> ActivityType.NON_FACE_TO_FACE;
            default -> ActivityType.ENTIRE;
        };

        BasicUser user = new BasicUser(signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()),
                signupRequest.getRealname(),
                signupRequest.getHeadcount(),
                signupRequest.getNickname(),
                signupRequest.getAddress(),
                categories,
                days,
                new Time(10),   // fix
                new Time(10),   // fix
                activityType);

        user.setRole(UserRole.ROLE_USER);
        
        return user;
    }
}
