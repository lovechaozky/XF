package service;

import domain.Introduction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zky on 2017/1/12.
 */
@Service()
@Transactional
public interface IntroductionService {

    public void add(Introduction introduction);

    public void update(Introduction introduction);

    public Introduction get();

}
