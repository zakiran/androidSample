package com.example.katsuya.mydbapp;

import android.provider.BaseColumns;

/**
 * Created by Katsuya on 2015/08/24.
 */
public final class UserContract {

    public UserContract(){}

    public static abstract class Users implements BaseColumns { // _idが主キーとなる
        public static final String TABLE_NAME = "users";
        public static final String COL_NAME = "name";
        public static final String COL_SCORE = "score";
    }
}
