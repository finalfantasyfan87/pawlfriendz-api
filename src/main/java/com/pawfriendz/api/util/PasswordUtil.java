package com.pawfriendz.api.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PasswordUtil {

    public static String hashPassword(String text) throws NoSuchAlgorithmException {
        String hashedPw = "";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(text.getBytes());
        //Get the hash's bytes
        byte[] bytes = md.digest();
        String sb = IntStream.range(0, bytes.length).mapToObj(i -> Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1)).collect(Collectors.joining());
        //Get complete hashed password in hex format
        hashedPw = sb;
        return hashedPw;
    }
}
