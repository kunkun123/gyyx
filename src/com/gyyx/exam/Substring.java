package com.gyyx.exam;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

/**
 * 用于计算字符串内最长不重复的子串长度是多少
 * @author liuyk
 *
 */
public class Substring {
	/**
	 * 时间复杂度是O(n^4)
	 * @param str	输入的字符串用来判断
	 * @return	不重复的最大子串的长度
	 */
	@Test
	public static int max_unique_substring_1(String string){
		//如果字符串为空，则不进行运算
        if(string==null||string.equals("")){  
            return 0;  
        }
		//将字符串转成字符型数组操作，方便且快捷
		char[] str = string.toCharArray();
		//最大子串的长度
	    int maxlen = 0;  
	    //开始的索引
	    int begin = 0;
	    //输入字符串的长度
	    int n = str.length;  
	    //双重遍历，逐步选中一个字符与之后的字符进行比较，类似于选择排序
	    //复杂度也参考选择排序的性能
	    //abcabcbb
	    for(int i = 0; i < n; ++i){  
	        for(int j = i+1; j < n; ++j){  
	        	//重复标识
	            int flag = 0;  
	            //判断子串是否有重复字符  
	            for(int m = i; m < j; ++m){  
	                for(int k = m+1; k < j; ++k){  
	                    if(str[m] == str[k]){  
	                        flag = 1;  
	                        //重复则跳出循环不做比较，说明最长子串已经产生
	                        break;  
	                    }  
	                }  
	            //    if(1 == flag) break;                  
	            } 
	            //记录最大子串的长度,短路判断提升性能
	            if(0 == flag && j-i+1 > maxlen){  
	                maxlen = j-i+1;  
	                begin = i;  
	            }  
	        }  
	          
	    }  
	    //用于输出测试结果，直观判断输出是否正确
	    StringBuffer s = new StringBuffer();  
	    for(int g = 0; g < n; ++g){  
	        s.append(str[g]);  
	    }  
	    System.out.println(s.toString().substring(begin,begin+maxlen));  
	    return maxlen;  
	}  
	
	/**
	 * 时间复杂度较低，为O（n^2）
	 * @param string	输入的字符串用来判断
	 * @return	不重复的最大子串的长度
	 */
	public static int max_unique_substring_2(String string){  
		//如果字符串为空，则不进行运算
        if(string==null||string.equals("")){  
            return 0;  
        }  
        //hashset内部存的对象是不重复的。巧妙利用hashset中的hashcode和compare来
        //判断是否重复,不需判断子串是否重复，性能提升约一倍,set无序，难以获得子串，只能获得长度
        Set<Character> set=new HashSet<Character>();  
        //最长子串的长度
        int maxlen=0;
        //头指针，指向--》不包含重复字符子串的第一个字符
        int pre=0;  
        //尾指针，指向--》不包含重复子串的最后一个字符
        int after=0; 
        StringBuffer s = new StringBuffer();
        //遍历整个字符串，不重复则将遍历的最后一个字符添加到set中
        while(after<string.length()){  
            if(!set.contains(string.charAt(after))){  
                set.add(string.charAt(after));  
                after++;  
            }else{  
//            	for (Iterator iterator = set.iterator(); iterator.hasNext();) {
//					Character character = (Character) iterator.next();
//					s.append(character);
//				}
            	//清空集合，用于下次判断
                set.clear();    
                if((after-pre)>maxlen){  
                	maxlen=after-pre;  
                }  
                pre++;  
                after=pre;  
            }  
        }  
          
        if((after-pre)>maxlen){  
        	maxlen=after-pre;  
        }  
        System.out.println(s.toString().substring(pre,after));
        return maxlen;  
    } 
	
	public static void main(String[] args) {
		//System.out.println(max_unique_substring_1("abcabcbb"));
		//System.out.println(max_unique_substring_1(""));
		//System.out.println(max_unique_substring_1("abcdefghijklmnopqrstuvdxyz1234567890"));
		//System.out.println(max_unique_substring_1("abcabcbb"));
		//System.out.println(max_unique_substring_1(""));
		System.out.println(max_unique_substring_2("abcdefghijklmnopqrstuvdxyz1234567890"));
	}
}
