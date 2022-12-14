package com.szc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szc.config.RedisUtil;
import com.szc.constant.IntegralEnums;
import com.szc.entity.ConfigInfo;
import com.szc.entity.IntegralDetail;
import com.szc.entity.SignIn;
import com.szc.mapper.SignInMapper;
import com.szc.result.Result;
import com.szc.result.ResultUtil;
import com.szc.service.ConfigInfoService;
import com.szc.service.IntegralDetailService;
import com.szc.service.SignInService;
import com.szc.util.CollectionUtil;
import com.szc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 签到表 服务实现类
 * </p>
 *
 * @author Poor
 * @since 2022-12-13
 */
@Service
public class SignInServiceImpl extends ServiceImpl<SignInMapper, SignIn> implements SignInService {
    @Autowired
    private ConfigInfoService configInfoService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IntegralDetailService integralDetailService;

    @Override
    public SignIn findOne(SignIn signIn) {
        LambdaQueryWrapper<SignIn> wrapper = Wrappers.lambdaQuery();
        if (StringUtil.isNotEmpty(signIn.getOtherIntegral())) {
            wrapper.eq(SignIn::getOtherIntegral, signIn.getOtherIntegral());
        }
        if (signIn.getCreateTime() != null) {
            LocalDateTime min = signIn.getCreateTime().with(LocalTime.MIN);
            LocalDateTime max = signIn.getCreateTime().with(LocalTime.MAX);
            wrapper.between(SignIn::getCreateTime, min, max);
        }
        return this.getOne(wrapper);
    }

    @Transactional
    @Override
    public Result sign(Integer userId) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int day = LocalDate.now().getDayOfMonth() - 1;

        String key = "sign:" + userId + ":" + date;
        boolean signed = redisUtil.getBit(key, day);
        if (signed) {
            return ResultUtil.success("今日已签过到了");
        }

        //记录签到 并奖励积分
        SignIn signIn = new SignIn();
        signIn.setAppletUserId(userId);

        //统计累计签到 天数
        long count = redisUtil.execute(n -> n.bitCount(key.getBytes())) + 1;
        List<ConfigInfo> infos = configInfoService.findListByKey("sign_integral");
        if (CollectionUtil.isNotEmpty(infos)) {
            Optional<ConfigInfo> optional = infos.stream().filter(n -> n.getValue().equals("" + count)).findFirst();
            if (optional.isPresent()) {
                //验证本月满签是否已经 加过积分
                SignIn sign = new SignIn();
                sign.setCreateTime(LocalDateTime.now());
                sign.setOtherIntegral(optional.get().getContext());
                SignIn signInned = findOne(sign);
                if (signInned == null) {
                    signIn.setOtherIntegral(optional.get().getContext());

                    //记录积分详情
                    IntegralDetail detail = new IntegralDetail();
                    detail.setAppletUserId(userId);
                    detail.setIntegral(signIn.getOtherIntegral());
                    detail.setType(IntegralEnums.getKeys("" + count));
                    detail.setDetail("满签奖励积分");
                    integralDetailService.save(detail);
                } else {
                    signIn.setOtherIntegral("0");
                }
            }
        }

        Optional<ConfigInfo> optional = infos.stream().filter(n -> n.getValue().equals(IntegralEnums.SIGN_IN.getValue())).findFirst();
        optional.ifPresent(n -> signIn.setIntegral(n.getContext()));
        this.save(signIn);

        //记录积分详情
        IntegralDetail detail = new IntegralDetail();
        detail.setAppletUserId(userId);
        detail.setIntegral(signIn.getIntegral());
        detail.setType(IntegralEnums.getKeys(IntegralEnums.SIGN_IN.getValue()));
        detail.setDetail("签到奖励积分");
        integralDetailService.save(detail);

        redisUtil.setBit(key, day, true);
        return ResultUtil.success();
    }
}
