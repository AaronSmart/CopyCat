package com.kikenn.mod001.util;
import java.util.ArrayList;
import java.util.List;
public class PermutationCombination
{
    public static void main(String[] args) 
    {  
        PermutationCombination perm = new PermutationCombination();  
        List<Character> data = new ArrayList<Character>();  
        data.add('a');  
        data.add('b');  
        data.add('c');  
        data.add('d');  
        List<List<Character>>result=new ArrayList<List<Character>>();
        for(int i = 1; i <= data.size(); i++)
        {
            perm.permutation(data,new ArrayList<Character>(),i,result);
        }
        for(int i=0;i<result.size();i++)
        {
            for(int j=0;j<result.get(i).size();j++)
            {
                System.out.print(result.get(i).get(j));
            }
            System.out.println();
        }
        System.out.println("################the split line of the permutation and combination ##########################");      
        PermutationCombination comb=new PermutationCombination();
        List<Character>data2=new ArrayList<Character>();
        data2.add('a');  
        data2.add('b');  
        data2.add('c');  
        data2.add('d');  
        List<List<Character>>result2=new ArrayList<List<Character>>();
        for(int i=1;i<=data.size();i++)
        {
            comb.combination(data2, new ArrayList<Character>(), data2.size(), i, result2);
        }
        for(int i=0;i<result2.size();i++)
        {
            for(int j=0;j<result2.get(i).size();j++)
            {
                System.out.print(result2.get(i).get(j));
            }
            System.out.println();
        }
    }  
      
    /** 
     *   the function is used to get permutation according to the formula P(n,m)=n!/(n-m)! note that(m<=n)
     * @param data     the source data 
     * @param workspace   Customize a temporary space to store each qualified value
     * @param k        the number need permutation p(n,k)
     */  
    public <E> void permutation(List<E> data,List<E> workspace, int k,List<List<E>>result) 
    {  
        List<E> copyData;  
        List<E> copyTarget;  
        if(workspace.size() == k) 
        {  
            result.add(workspace);   /*we need a return in order to deal with data after permutation*/
          //  for(E i : workspace)   /*instead just print the result*/
          //  {
          //    System.out.print(i); 
          //  }
          //  System.out.println();  
        }  
        for(int i=0; i<data.size(); i++) 
        {  
            copyData = new ArrayList<E>(data);  
            copyTarget = new ArrayList<E>(workspace);  
            copyTarget.add(copyData.get(i));  
            copyData.remove(i);  
            permutation(copyData, copyTarget,k,result);  
        }  
    }  
    
    /** 
     *   according the formula C(n,m)=n!/(m!(n-m)!) and C(n,m)=C(n,n-m)  note that (m<=n)
     *   把数据的第一个元素添加到工作空间中，判断工作空间的大小，如果小于k,则需要继续递归，但此时，传入递归函数的 
     *   参数需要注意：假设当前插入的节点的下标是i,因为是顺序插入的,所以i之前的所有数据都应该舍去，只传入i之后的未使用过的数据。 
     *   因此在传参之前，应该对copyData作以处理
     *   (when the first element is added to the data in the working space, determine the size of the space, 
     *   if less than k, you need to continue recursion, but this time, parameter recursive function to note: 
     *   assuming the insertion of the subscript node is i, because it is the order of insertion, 
     *   so all data before I should give up. Only after the incoming i unused data.
     *   Therefore, copyData should be handled before passing arguments)
     * @param data      the source data
     * @param workSpace Customize a temporary space to store each qualified value 
     * @param k         k in C(n,k) 
     */  
    public <E> void combination(List<E> data, List<E> workSpace, int n, int k, List<List<E>>result) 
    {  
        List<E> copyData;  
        List<E> copyWorkSpace;  
        if(workSpace.size() == k)
        {  
            result.add(workSpace);       /*we need a return in order to deal with data after combination*/
           // for(Object c : workSpace)   /*instead just print the result*/
           // {
           //     System.out.print(c);
          //  }
          //  System.out.println();  
        }  
        for(int i = 0; i < data.size(); i++) 
        {  
            copyData = new ArrayList<E>(data);  
            copyWorkSpace = new ArrayList<E>(workSpace);  
            copyWorkSpace.add(copyData.get(i));  
            for(int j = i; j >=  0; j--)
            {
                copyData.remove(j);  
            }
            combination(copyData, copyWorkSpace, n, k,result);  
        }  
    }  
}
