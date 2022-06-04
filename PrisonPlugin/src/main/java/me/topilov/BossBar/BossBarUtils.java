package me.topilov.BossBar;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BossBarUtils {

    public static String getSpacedInt(String string){
        String[] splitted = string.split("(?<=.)");
        IntStream.range(0, splitted.length).forEach(i -> splitted[i] = splitted[i].replaceAll(",", " "));
        String a = Arrays.toString(splitted);
        String aa = a.replaceAll("[,]", "");
        return aa.replace("[","").replace("]","");
    }

    public static String getBossBarBoost(String string, IconType icon) {
        String[][] arr = {
                {"1", "\uE40F"},
                {"2", "\uE410"},
                {"3", "\uE411"},
                {"4", "\uE412"},
                {"5", "\uE413"},
                {"6", "\uE414"},
                {"7", "\uE415"},
                {"8", "\uE416"},
                {"9", "崒"},
                {"0", "\uE418"},
                {".", "\uE419"},
                {" ", "\uF801"}
        };
        String str = string;
        for (String[] a : arr) {
            str = str.replace(a[0], a[1]);
        }
        if (icon == IconType.BLOCKS) {
            return "\uE400\uF801\uE40A\uF801" + str + "\uF801\uE401";
        }
        if (icon == IconType.MONEY) {
            return "\uE400\uF801\uE402\uF801" + str + "\uF801\uE401";
        }
        return null;
    }
}
