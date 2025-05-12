package com.luobida.houserice.admin.common.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhangyonghao
 * 责任链上下文
 * @param <T>
 */
@RequiredArgsConstructor
@Component
public class AbstractChainContext<T> implements CommandLineRunner {
    private final ApplicationContext applicationContext;
    private final Map<String, List<AbstractChainHandler>> abstractChainHandlerContainer = new HashMap<>();
    public void handler(String mark, T requestParam) {
        List<AbstractChainHandler> abstractChainHandlers = abstractChainHandlerContainer.get(mark);
        if (CollectionUtils.isEmpty(abstractChainHandlers)) {
            throw new RuntimeException(String.format("%s Chain is undefined",mark));
        }
        abstractChainHandlers.forEach(each -> each.handle(requestParam));
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, AbstractChainHandler> chainHandlerMap = applicationContext.getBeansOfType(AbstractChainHandler.class);
        chainHandlerMap.forEach((beanName, bean)->{
            List<AbstractChainHandler> abstractChainHandlers = abstractChainHandlerContainer.get(bean.mark());
            if (CollectionUtils.isEmpty(abstractChainHandlers)) {
                abstractChainHandlers = new ArrayList<>();
            }
            abstractChainHandlers.add(bean);
            List<AbstractChainHandler> chainHandlers = abstractChainHandlers.stream()
                    .sorted(Comparator.comparing(Ordered::getOrder))
                    .collect(Collectors.toList());
            abstractChainHandlerContainer.put(bean.mark(), chainHandlers);
        });
    }
}
