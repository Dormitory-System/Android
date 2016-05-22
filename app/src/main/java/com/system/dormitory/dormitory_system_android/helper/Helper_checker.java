package com.system.dormitory.dormitory_system_android.helper;

/**
 * Created by Administrator on 2016-05-09.
 */

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016-05-08.
 */

//TODO 전화번호 검사를 해줘야함
public class Helper_checker {
    public static final int MIN=5;

    public static boolean validName(String name){
        if( name == null ) return false;
        return true;
    }

    public static boolean validId(String id){
        //TODO 아이디 중복 검사
        if( id == null ) return false;
        if(Pattern.matches("^[0-9]+$", id)) return true;
        return false;
    }

    public static boolean validPassword(String pw){
        if( pw == null ) return false;
        if( pw.length() < MIN ) return false;
        return true;
    }

    public static boolean validId_context(Context context, String id){
        if(!validId(id)){
            Toast.makeText(context, "학번은 숫자로만된 학번을 입력하세요. ", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public static boolean id_check_ok(Context context, boolean id_check){
        if(!id_check){
            Toast.makeText(context, "중복된 아이디 입니다. ", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    public static boolean validJoin(Context context, String name, String id, String pw){
        if( !validId(id) ){
            Toast.makeText(context, "학번은 숫자로만된 학번을 입력하세요. ", Toast.LENGTH_LONG).show(); return false;
        }
        if( !validPassword(pw) ){
            Toast.makeText(context, "비밀번호는 5글자 이상이어야합니다. ", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!validName(name)) {
            Toast.makeText(context, "이름을 입력하세요 ", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
