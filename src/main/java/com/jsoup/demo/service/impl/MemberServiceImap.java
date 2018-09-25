package com.jsoup.demo.service.impl;

import com.jsoup.demo.entity.Member;
import com.jsoup.demo.mapper.MemberMapper;
import com.jsoup.demo.service.MemberService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2018-08-29
 */
@Service
public class MemberServiceImap extends ServiceImpl<MemberMapper, Member> implements MemberService {

}
