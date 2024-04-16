package com.baguni.baguni.security.services;

import com.baguni.baguni.domain.user.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Time;
import java.util.*;

/*
 * 인증 과정 성공 후 Authentication 객체에서 가져온 유저 정보
 * username, password, authorities 외의 정보도 더 가져오기 위해 UserDetails 인터페이스 구현
 * 
 * 일반 유저, 복지관 유저, 관리자 구분
 */
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private Long adminId;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private String realname;

    private Integer headcount;

    private String nickname;

    private String address;

    private Collection<? extends GrantedAuthority> authorities;

    // Basic User
    private Set<Category> categories;
    private Set<Day> days;
    private Time startTime;
    private Time endTime;
    private ActivityType activityType;

    // Welfare User
    private String category;
    private String telephone;
    private String introduction;

    // 관리자
    public UserDetailsImpl(Long adminId, String username, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
    
    // 일반, 복지관 유저
    public UserDetailsImpl(UUID id, String username, String email, String password, String realname, Integer headcount, String nickname, String address,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.realname = realname;
        this.headcount = headcount;
        this.nickname = nickname;
        this.address = address;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(BasicUser user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

        UserDetailsImpl userDetails = new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRealname(),
                user.getHeadcount(),
                user.getNickname(),
                user.getAddress(),
                authorities);

        userDetails.setBasicUserImpl(user.getCategories(),
                user.getDays(),
                user.getStartTime(),
                user.getEndTime(),
                user.getActivityType());

        return userDetails;
    }
    public static UserDetailsImpl build(WelfareUser user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

        UserDetailsImpl userDetails = new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRealname(),
                user.getHeadcount(),
                user.getNickname(),
                user.getAddress(),
                authorities);

        userDetails.setWelfareUserImpl(user.getCategory(), user.getTelephone(), user.getIntroduction());

        return userDetails;
    }

    public static UserDetailsImpl build(Admin user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(UserRole.ROLE_ADMIN.name()));

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                authorities);
    }

    // 일반 유저 추가 정보
    public void setBasicUserImpl(Set<Category> categories, Set<Day> days, Time startTime, Time endTime, ActivityType activityType) {
        this.categories = categories;
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
    }

    // 복지관 유저 추가 정보
    public void setWelfareUserImpl(String category, String telephone, String introduction) {
        this.category = category;
        this.telephone = telephone;
        this.introduction = introduction;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public UUID getId() {
        return id;
    }

    public Long getAdminId() {
        return adminId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getRealname() {
        return realname;
    }

    public int getHeadcount() {
        return headcount;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
