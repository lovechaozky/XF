package DAO;

import domain.Introduction;
import org.springframework.stereotype.Component;

/**
 * Created by zky on 2017/1/12.
 */
@Component
public interface IntroductionDAO extends BaseDAO<Introduction>{

    public void add(Introduction introduction);

    public void update(Introduction introduction);

    public Introduction get();

}
