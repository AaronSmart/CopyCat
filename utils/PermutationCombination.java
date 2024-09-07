package com.kikenn.util;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class PermutationCombination
{
    //["apple","iOS","dog","nana","man","good","goodman"]
    public static void main(String args[])
    {
        String[] words={"apple","iOS","dog","nana","man","good","goodman","goodboy","boy","goodboynana"};
      // System.out.println(longestWord(words));
        longestWord(words);

    }

    public static String longestWord(String[]words)
    {
        ArrayList<String> wordsList=new ArrayList<>();
        ArrayList<String> resultList=new ArrayList<>();
        ArrayList<String> resultListlex=new ArrayList<>();

        for(int i=0;i<words.length;i++)
        {
            wordsList.add(words[i]);
        }
        for(int j=0;j<words.length;j++)
        {
            String  word=words[j];
            for(int k=0;k<words.length;k++)
            {
                String combine=word+words[k];
                if(wordsList.contains(combine))
                {
                    if(!resultList.contains(combine))
                    resultList.add(combine);
                }
                for(int m=0;m<words.length;m++)
                {
                    String combine3=word+words[k]+words[m];
                    if(wordsList.contains(combine3))
                    {
                        resultList.add(combine3);
                    }
                }
            }
        }
        //先判断长度
        resultList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length()<o2.length())
                    return 1;
                return -1;
            }
        });
        for(int t=0;t<resultList.size();t++)
            System.out.println(resultList.get(t)+" xx");

        if(resultList.size()==0)
            return "";
        if(resultList.size()==1)
            return resultList.get(0);
        if(resultList.size()>=2)
        {
            if(resultList.get(0).length()>resultList.get(1).length())
            {
                return resultList.get(0);
            }
            if(resultList.get(0).length()==resultList.get(1).length())
            {
                resultListlex.addAll(resultList);
                resultListlex.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        if(o1.compareTo(o2)<0)
                        {
                            return -1;
                        }
                        return 1;
                    }
                });
                resultListlex.get(0);
            }
        }
        return resultList.get(0);
    }



    public String longestWord2 (String[] words) {
        ArrayList<String> wordsList=new ArrayList<>();
        ArrayList<String> resultList=new ArrayList<>();
        for(int i=0;i<words.length;i++)
        {
            wordsList.add(words[i]);
        }
        for(int j=0;j<words.length;j++)
        {
            String  word=words[j];
            for(int k=0;k<words.length;k++)
            {
                String combine=word+words[k];
                if(wordsList.contains(combine))
                {
                    resultList.add(combine);
                }
            }
        }
        if(resultList.size()==0)
            return "";
        if(resultList.size()==1)
            return resultList.get(0);
        if(resultList.size()>=2)
        {
            resultList.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if(o1.compareTo(o2)<0)
                    {
                        return -1;
                    }
                    return 1;
                }
            });
        }
        return resultList.get(0);
    }




















    public static void main2(String[] args)
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
        System.out.println("###############################");
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
    public <E> void permutation(List<E> data,List<E> workspace, int k,List<List<E>>result) 
    {  
        List<E> copyData;  
        List<E> copyTarget;  
        if(workspace.size() == k) 
        {  
            result.add(workspace);
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
    public <E> void combination(List<E> data, List<E> workSpace, int n, int k, List<List<E>>result) 
    {  
        List<E> copyData;  
        List<E> copyTarget;
        if(workSpace.size() == k)
        {  
            result.add(workSpace);
        }  
        for(int i = 0; i < data.size(); i++) 
        {  
            copyData = new ArrayList<E>(data);
            copyTarget = new ArrayList<E>(workSpace);
            copyTarget.add(copyData.get(i));
            for(int j = i; j >=  0; j--)
            {
                copyData.remove(j);  
            }
            combination(copyData, copyTarget, n, k,result);
        }  
    }  
}
