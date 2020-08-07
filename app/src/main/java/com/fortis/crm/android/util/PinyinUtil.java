package com.fortis.crm.android.util;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.regex.Pattern;

public class PinyinUtil {

    public static String getFistLetter(char c) {
        if (String.valueOf(c).matches("^[A-Za-z]+$")) {
            // 如果是英文字母则直接返回
            return String.valueOf(c).toUpperCase();
        }

        String[] strings = PinyinHelper.toHanyuPinyinStringArray(c);
        if (strings != null) {
            // 如果是中文字符，则返回汉语拼音首字母
            return  String.valueOf(strings[0].charAt(0)).toUpperCase();
        } else {
            // 其它字符返回“#”
            return "#";
        }
    }

}
