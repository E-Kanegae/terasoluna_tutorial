package todo5.domain.repository.todo;

import java.util.List;

import todo5.domain.model.Importance;

/**
 * [このクラスの説明を書きましょう]
 */
public interface ImportanceRepository{
    
    public List<Importance> selectImportanceList(boolean logicDeleteFlg);
    

}
