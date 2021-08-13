package com.saket.service;

import javax.servlet.http.HttpServletRequest;

import com.saket.model.User;

import java.util.Map;

public interface AuthService {
    int authorize(HttpServletRequest req);
    Map<String, String> authenticate(User user);
}
