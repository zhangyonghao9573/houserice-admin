package com.luobida.houserice.admin.common.filter;

import org.springframework.core.Ordered;

/**
 * @author zhangyonghao
 * 抽象责任链
 */
public interface AbstractChainHandler<T> extends Ordered {

    void handle(T requestParam);

    String mark();
}
