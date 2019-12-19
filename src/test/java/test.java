import java.util.ArrayList;
import java.util.List;

/**
 * Create By LiXiaoTian on 2019/12/17 15:59
 */
public class test {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            List<Long> workItemList=new ArrayList<>();
            long sss=11111;
            List<Long> longs = setTurnWorkItemId(i,sss, workItemList);
            workItemList=longs;
            System.out.println(i+"==="+workItemList.toString());
        }

    }
    public static List<Long> setTurnWorkItemId(int i,Long workItemId , List<Long> List){
        List.add(workItemId);
        //System.out.println(i+"==="+List.toString());
        return List;
    }
}
