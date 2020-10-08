package com.welearn.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

enum CharacterType {
    NUMBER,
    LOWERCASE,
    UPPERCASE,
    SPECIAL_CHARACTER
}

public class PasswordGenerator {
    //密码能包含的特殊字符
    private static final char[] allowedSpecialCharacters = {
            '`', '~', '@', '#', '$', '%', '^', '&',
            '*', '(', ')', '-', '_', '=', '+', '[',
            '{', '}', ']', '\\', '|', ';', ':', '"',
            '\'', ',', '<', '.', '>', '/', '?'};
    private static final int letterRange = 26;
    private static final int numberRange = 10;
    private static final int spCharacterRange = allowedSpecialCharacters.length;
    private static final Random random = new Random();

    /**
     * 生成密码
     * @param passwordLength 密码的长度
     * @param minVariousType 密码包含字符的最少种类 1 - 4 见 CharacterType
     * @return 生成的密码
     */
    public static String get(int passwordLength, int minVariousType) {
        if (minVariousType > CharacterType.values().length)
            minVariousType = CharacterType.values().length;
        if (minVariousType < 1)
            minVariousType = 1;
        final int min = minVariousType;
        List<CharacterType> types = Arrays.stream(CharacterType.values())
                .filter(t -> t.ordinal() + 1 <= min).collect(Collectors.toList());
        char[] password = new char[passwordLength];
        for (int i = 0; i < password.length; i++) {
            password[i] = generateCharacter(types.get(random.nextInt(types.size())));
        }
        return String.valueOf(password);
    }

    private static Character generateCharacter(CharacterType type) {
        Character c = null;
        int rand;
        switch (type) {
            // 随机小写字母
            case LOWERCASE:
                rand = random.nextInt(letterRange);
                rand += 97;
                c = (char) rand;
                break;
            // 随机大写字母
            case UPPERCASE:
                rand = random.nextInt(letterRange);
                rand += 65;
                c = (char) rand;
                break;
            // 随机数字
            case NUMBER:
                rand = random.nextInt(numberRange);
                rand += 48;
                c = (char) rand;
                break;
            // 随机特殊字符
            case SPECIAL_CHARACTER:
                rand = random.nextInt(spCharacterRange);
                c = allowedSpecialCharacters[rand];
                break;
        }
        return c;
    }
}
