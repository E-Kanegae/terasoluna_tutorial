/**
 * @(#)DynamicScreenItemRepository.java
 * 
 */
package todo5.domain.repository.dynamic;

import java.util.List;

/**
 * 
 * @author E-Kanegae
 */
public interface DynamicScreenItemRepository {
    
    public List<Object> select(List<String> parameterList);

}
