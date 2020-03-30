package com.creditapp.loginservice.service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class PasswordService {

    private SecureRandom random;
    private KeySpec spec;
    private byte[] hash;
    private byte[] salt;

    public byte[] salt() {
        if (salt == null) {
            random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            return salt;
        }
        return this.salt;
    }

    public byte[] hashPassword(String password, byte[] salt) {
        spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            try {
                hash = factory.generateSecret(spec).getEncoded();
                return hash;
            } catch (InvalidKeySpecException e) {
                e.getMessage();
            }
        } catch (NoSuchAlgorithmException e) {
            e.getMessage();
        }
        return hash;
    }
}
