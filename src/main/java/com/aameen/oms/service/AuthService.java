package com.aameen.oms.service;

import com.aameen.oms.dto.*;


public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
