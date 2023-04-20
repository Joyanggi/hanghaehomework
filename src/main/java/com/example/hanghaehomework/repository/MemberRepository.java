package com.example.hanghaehomework.repository;
import com.example.hanghaehomework.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
