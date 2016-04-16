import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alex on 4/4/2016.
 */
public class Prog {


   public static void main(String[] args){

       //Solution s=new Solution();
       //int res = s.solution(Arrays.asList(5,3,-1,5));
       //int res=s.solution(5, 3,-1,5);

      Solution2 s2=new Solution2();
       int res = s2.solution(new int[]{9, 4, -3, -10});

      // Solution3 s3=new Solution3();
       //int res = s3.solution(new int[]{1,1,0,1,0,0});
       //int res = s3.solution(new int[]{1,1,1,1,0,0,0,1,0,0});
      // int res = s3.solution(new int[]{1,1,1,1,0,0,0,1,1,0,1,1,0,0});

       System.out.println("Result: "+res);
   }

}
