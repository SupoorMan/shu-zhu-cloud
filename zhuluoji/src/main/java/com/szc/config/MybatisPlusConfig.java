package com.szc.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus 组件注册
 * 1. 自动分页: PaginationInnerInterceptor
 * 2. 多租户: TenantLineInnerInterceptor
 * 3. 动态表名: DynamicTableNameInnerInterceptor
 * 4. 乐观锁: OptimisticLockerInnerInterceptor
 * 5. sql 性能规范: IllegalSQLInnerInterceptor
 * 6. 防止全表更新与删除: BlockAttackInnerInterceptor
 * <p>
 * 使用多个功能需要注意顺序关系,建议使用如下顺序
 * 多租户,动态表名
 * 分页,乐观锁
 * sql 性能规范,防止全表更新与删除
 */
@Configuration
@ConditionalOnClass(value = {MybatisPlusInterceptor.class})
public class MybatisPlusConfig {

    @Bean
    public PaginationInnerInterceptor paginationInterceptor() {
        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();
        //你的最大单页限制数量，默认 100 条，小于 0 如 -1 不受限制
        paginationInterceptor.setMaxLimit(1000L);
        return paginationInterceptor;
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(paginationInterceptor());
        return interceptor;
    }


}
