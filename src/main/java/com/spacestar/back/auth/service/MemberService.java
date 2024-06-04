package com.spacestar.back.auth.service;

public interface MemberService {

    void withdrawal(String uuid);

    void withdrawalForce(String uuid);
}
