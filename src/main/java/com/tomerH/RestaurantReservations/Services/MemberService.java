package com.tomerH.RestaurantReservations.Services;

import com.tomerH.RestaurantReservations.Beans.Member;
import com.tomerH.RestaurantReservations.Repositories.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member registerMember (Member member){
        if (member.getId() == 0){

        }
        return memberRepository.save(member);
    }
}
