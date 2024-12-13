package com.study.autopus.login.service;

import com.study.autopus.login.aggregate.Login;
import com.study.autopus.login.aggregate.NaverToken;
import com.study.autopus.login.repository.LoginRepository;
import com.study.autopus.login.repository.NaverTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;
    private final NaverTokenRepository naverTokenRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public LoginServiceImpl(LoginRepository loginRepository, NaverTokenRepository naverTokenRepository, RestTemplate restTemplate) {
        this.loginRepository = loginRepository;
        this.naverTokenRepository = naverTokenRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public String loginByNaver(String code) {
        // 네이버 토큰 요청
        String tokenUrl = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code"
                + "&client_id=6OwTsvQkk2C19Qwt6Mw9"
                + "&client_secret=OYNs82qJ7z"
                + "&code=" + code;

        Map<String, String> tokenResponse = restTemplate.getForObject(tokenUrl, Map.class);
        String accessToken = tokenResponse.get("access_token");
        String refreshToken = tokenResponse.get("refresh_token");

        // 네이버 사용자 정보 요청
        String userInfoUrl = "https://openapi.naver.com/v1/nid/me";
        Map<String, Object> userResponse = restTemplate.getForObject(userInfoUrl + "?access_token=" + accessToken, Map.class);
        Map<String, Object> response = (Map<String, Object>) userResponse.get("response");

        // 사용자 정보 추출
        String name = (String) response.get("name");
        String email = (String) response.get("email");
        String mobile = (String) response.get("mobile");
        String birth = (String) response.get("birth"); // "YYYY-MM-DD" 형식으로 반환될 수도 있고 null일 수도 있음
        String gender = (String) response.get("gender");

        // birth 값 검증 및 변환
        java.sql.Date birthDate = null;
        if (birth != null && !birth.isEmpty()) {
            try {
                birthDate = java.sql.Date.valueOf(birth);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid birth format: " + birth);
            }
        }

        // DB에 사용자 정보 저장
        Login login = new Login();
        login.setMemberName(name);
        login.setMemberMobile(mobile);
        login.setMemberBirth(birthDate); // 변환된 birthDate를 설정
        login.setMemberAddress("N/A"); // 기본값
        login.setMemberGender(gender);
        login.setMemberJoin(new Date());
        login.setMemberStatus(1); // 활성화 상태
        loginRepository.save(login);

        // DB에 토큰 정보 저장
        NaverToken naverToken = new NaverToken();
        naverToken.setAccessToken(accessToken);
        naverToken.setRefreshToken(refreshToken);
        naverToken.setExpiresAt(LocalDateTime.now().plusHours(24)); // 토큰 만료 시간 설정
        naverToken.setTokenType("Bearer");
        naverToken.setCreatedAt(LocalDateTime.now());
        naverToken.setMember(login); // 연관된 사용자 정보
        naverTokenRepository.save(naverToken);

        System.out.println("Access Token: " + accessToken);
        System.out.println("Response: " + response); // 사용자 정보 전체 출력

        // 사용자 정보 반환
        return "이름: " + name + ", 이메일: " + email;
    }
//
//    @Override
//    public void saveNaverUser(OAuth2AuthenticationToken authentication) {
//        // 사용자 정보 추출
//        Map<String, Object> attributes = authentication.getPrincipal().getAttributes();
//        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
//
//        String name = (String) response.get("name");
//        String email = (String) response.get("email");
//        String mobile = (String) response.get("mobile");
//        String birth = (String) response.get("birth");
//        String gender = (String) response.get("gender");
//
//        // birth 값 검증 및 변환
//        java.sql.Date birthDate = null;
//        if (birth != null && !birth.isEmpty()) {
//            try {
//                birthDate = java.sql.Date.valueOf(birth);
//            } catch (IllegalArgumentException e) {
//                System.out.println("Invalid birth format: " + birth);
//            }
//        }
//
//        // 핸드폰 번호를 기준으로 회원 데이터 조회
//        Login existingUser = loginRepository.findByMemberMobile(mobile).orElse(null);
//        if (existingUser != null) {
//            // 이미 존재하는 회원 -> 로그인 성공 처리
//            System.out.println("기존 회원 로그인 성공: " + existingUser.getMemberName());
//            return; // 여기서 바로 /home 페이지로 이동하도록 컨트롤러에서 처리
//        }
//
//        // DB에 사용자 정보 저장
//        Login login = new Login();
//        login.setMemberName(name);
//        login.setMemberMobile(mobile);
//        login.setMemberBirth(birthDate); // 검증된 birthDate 사용
//        login.setMemberAddress("N/A"); // 기본값
//        login.setMemberGender(gender);
//        login.setMemberJoin(new Date());
//        login.setMemberEmail(email);
//        login.setMemberStatus(1); // 활성화 상태
//        loginRepository.save(login);
//
//        // OAuth2 인증 토큰에서 access_token 추출
//        Map<String, Object> tokenDetails = authentication.getPrincipal().getAttributes();
//        String accessToken = (String) tokenDetails.get("access_token");
//        String refreshToken = (String) tokenDetails.get("refresh_token");
//
//        // 로그 출력
//        System.out.println("Access Token: " + accessToken);
//        System.out.println("Response: " + response); // 사용자 정보 전체 출력
//
//        // 토큰 정보 저장
//        if (accessToken != null) {
//            NaverToken naverToken = new NaverToken();
//            naverToken.setAccessToken(accessToken);
//            naverToken.setRefreshToken(refreshToken);
//            naverToken.setExpiresAt(LocalDateTime.now().plusHours(24)); // 예제: 만료 시간 설정
//            naverToken.setTokenType("Bearer");
//            naverToken.setCreatedAt(LocalDateTime.now());
//            naverToken.setMember(login); // 연관된 사용자 정보
//            naverTokenRepository.save(naverToken);
//        }
//    }

    @Override
    public void saveNaverUser(OAuth2AuthenticationToken authentication) {
        // 사용자 정보 추출
        Map<String, Object> attributes = authentication.getPrincipal().getAttributes();
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        String name = (String) response.get("name");
        String email = (String) response.get("email");
        String mobile = (String) response.get("mobile");
        String birth = (String) response.get("birth");
        String gender = (String) response.get("gender");

        // birth 값 검증 및 변환
        java.sql.Date birthDate = null;
        if (birth != null && !birth.isEmpty()) {
            try {
                birthDate = java.sql.Date.valueOf(birth);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid birth format: " + birth);
            }
        }

        // 핸드폰 번호를 기준으로 회원 데이터 조회
        Login existingUser = loginRepository.findByMemberMobile(mobile).orElse(null);
        if (existingUser != null) {
            // 이미 존재하는 회원 -> 로그인 성공 처리
            System.out.println("기존 회원 로그인 성공: " + existingUser.getMemberName());

            // 토큰 정보 업데이트
            Map<String, Object> tokenDetails = authentication.getPrincipal().getAttributes();
            String accessToken = (String) tokenDetails.get("access_token");
            String refreshToken = (String) tokenDetails.get("refresh_token");

            if (accessToken != null) {
                NaverToken existingToken = naverTokenRepository.findByMember(existingUser).orElse(new NaverToken());
                existingToken.setAccessToken(accessToken);
                existingToken.setRefreshToken(refreshToken);
                existingToken.setExpiresAt(LocalDateTime.now().plusHours(24)); // 예제: 만료 시간 설정
                existingToken.setTokenType("Bearer");
                existingToken.setCreatedAt(LocalDateTime.now());
                existingToken.setMember(existingUser); // 연관된 사용자 정보
                naverTokenRepository.save(existingToken);
            }

                    // 로그 출력
        System.out.println("Access Token: " + accessToken);
        System.out.println("Response: " + response); // 사용자 정보 전체 출력

            return; // 기존 회원 처리 완료
        }

        // 새로운 회원 저장
        Login login = new Login();
        login.setMemberName(name);
        login.setMemberMobile(mobile);
        login.setMemberBirth(birthDate); // 검증된 birthDate 사용
        login.setMemberAddress("N/A"); // 기본값
        login.setMemberGender(gender);
        login.setMemberJoin(new Date());
        login.setMemberEmail(email);
        login.setMemberStatus(1); // 활성화 상태
        loginRepository.save(login);

        // OAuth2 인증 토큰에서 access_token 추출
        Map<String, Object> tokenDetails = authentication.getPrincipal().getAttributes();
        String accessToken = (String) tokenDetails.get("access_token");
        String refreshToken = (String) tokenDetails.get("refresh_token");

        // 토큰 정보 저장
        if (accessToken != null) {
            NaverToken naverToken = new NaverToken();
            naverToken.setAccessToken(accessToken);
            naverToken.setRefreshToken(refreshToken);
            naverToken.setExpiresAt(LocalDateTime.now().plusHours(24)); // 예제: 만료 시간 설정
            naverToken.setTokenType("Bearer");
            naverToken.setCreatedAt(LocalDateTime.now());
            naverToken.setMember(login); // 연관된 사용자 정보
            naverTokenRepository.save(naverToken);
        }

        System.out.println("새로운 회원 가입 성공: " + name);
    }
}