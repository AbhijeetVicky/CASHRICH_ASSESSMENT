package com.common.cashrich.services;

import com.common.cashrich.entity.User;
import com.common.cashrich.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ThirdPartyAPI {
    @Service
    public class ApiService {

        @Autowired
        private UserRepository userRepository;

        private static final String API_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=BTC,ETH,LTC";
        private static final String API_KEY = "27ab17d1-215f-49e5-9ca4-afd48810c149";

        public String getCoinData(Long userId) {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.set("X-CMC_PRO_API_KEY", API_KEY);
                HttpEntity<String> entity = new HttpEntity<>(headers);

                ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.GET, entity, String.class);
                String responseData = response.getBody();

                // Save response data to DB with timestamp
                // ...

                return responseData;
            }
            return null;
        }

    }
}
