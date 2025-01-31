package com.example.jpa.domain.member.repository;

import com.example.jpa.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>{
    Optional<Member> findByUsername(String username);
}
