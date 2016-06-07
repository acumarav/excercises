package org.alext.algorithms;

/**
 * Created by tsumaraa on 06/06/2016.
 */
public class ReverseString {

    public String reverseRecursively(String str){

        if(str==null || str.length()<2){
            return str;
        }

        return reverseRecursively(str.substring(1))+str.charAt(0);
    }

    public String reverseInArray(String str) {
        if(str==null || str.length()<2){
            return str;
        }

        char[] chars = str.toCharArray();

        for(int i=0, j=str.length()-1; i < j; i++, j--){
            char ch=chars[i];
            chars[i]=chars[j];
            chars[j]=ch;
        }

        return new String(chars);
    }
}
