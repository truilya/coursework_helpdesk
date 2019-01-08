package coursework_helpdesk.util;

import coursework_helpdesk.model.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Listes {

    public static List<Integer> getListInteger(List<? extends BaseEntity> list){
        if (list==null){
            List<Integer> resultList = new ArrayList<>();
            resultList.add(-1);
            return resultList;
        }
        return list.stream().map(BaseEntity::getId).collect(Collectors.toList());
    }

}
