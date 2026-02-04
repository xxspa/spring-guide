package com.newzhxu.validator;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class Main {
    // AES-CBC 模式下，IV 的长度固定为 16 字节
    private static final int IV_SIZE = 16;

    public static void main(String[] args) throws Exception {
        // 1. 生成密钥对 (KeyPair)
        KeyPair keyPair = generateRSAKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        System.out.println("=== 第一阶段：RSA 密钥交换 (非对称) ===");

        // 1. 客户端：生成一个随机的 AES 密钥种子 (类似 HTTPS 的 Pre-Master Secret)
        String clientSeed = "AES-Seed-" + System.currentTimeMillis();
        System.out.println("1. 客户端生成的 AES 种子: " + clientSeed);

        // --- 加解密演示 ---
        // 2. 客户端：用服务端的公钥加密这个种子
        System.out.println("2. 客户端使用服务端公钥加密种子并发送给服务端");
        // 使用公钥加密
        String encryptedData = encrypt(clientSeed, publicKey);
        System.out.println("加密后的密文: " + encryptedData);

        // 使用私钥解密
        // 3. 服务端：用私钥解密，拿到 AES 种子
        String decryptedData = decrypt(encryptedData, privateKey);
        System.out.println("3. 服务端使用私钥解密收到的密文");
        System.out.println("解密后的明文: " + decryptedData);
//        String originalData = "这是需要签名的原始数据 - Hello Gemini 2026";

        // 4. 服务端：为了证明自己身份，对种子进行签名并传回
        System.out.println("4. 服务端使用私钥对解密后的种子进行签名并发送给客户端");
        String signature = sign(decryptedData, privateKey);
        System.out.println("生成的签名: " + signature);

        // 5. 客户端：验证签名，确认服务器身份
        System.out.println("5. 客户端使用服务端公钥验证签名以确认服务器身份");
        boolean isCorrect = verify(clientSeed, signature, publicKey);
        System.out.println("签名验证结果: " + (isCorrect ? "✅ 成功" : "❌ 失败"));
        if (!isCorrect) {
            throw new SecurityException("签名验证失败，服务器身份无法确认！");
        }

        System.out.println("\n=== 第二阶段：AES 数据传输 (对称加密) ===");

        // 双方各自根据种子生成相同的 AES 密钥对象
        SecretKeySpec aesKey = deriveAESKey(decryptedData);

        // 6. 服务端：发送一段长网页数据给客户端
        String webPageData = "<html><body><h1>这是加密的网页内容 - Hello 2026</h1></body></html>";
        String encryptedPage = aesEncrypt(webPageData, aesKey);
        System.out.println("6. 服务端发送 AES 加密后的网页: " + encryptedPage);

        // 7. 客户端：用同样的 AES 密钥解密
        String decryptedPage = aesDecrypt(encryptedPage, aesKey);
        System.out.println("7. 客户端显示解密后的网页: " + decryptedPage);
    }

    /**
     * 使用公钥加密
     */
    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * 使用私钥解密
     */
    public static String decrypt(String encryptedText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    /**
     * 生成 RSA 2048位密钥对
     */
    public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    /**
     * 用私钥对数据进行签名
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(data.getBytes());
        byte[] signature = privateSignature.sign();
        return Base64.getEncoder().encodeToString(signature);
    }

    /**
     * 用公钥验证签名
     */
    public static boolean verify(String data, String signature, PublicKey publicKey) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(data.getBytes());
        byte[] signatureBytes = Base64.getDecoder().decode(signature);
        return publicSignature.verify(signatureBytes);
    }

    public static SecretKeySpec deriveAESKey(String seed) throws Exception {
        byte[] keyBytes = MessageDigest.getInstance("SHA-256").digest(seed.getBytes());
        return new SecretKeySpec(keyBytes, "AES");
    }

    public static String aesEncrypt(String data, SecretKeySpec key) throws Exception {
        // 1. 使用带有 CBC 模式和 PKCS5Padding 的 AES
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // 2. 获取本次加密随机生成的 IV
        byte[] iv = cipher.getIV();
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());

        // 3. 把 IV 和 密文 拼在一起传给对方 (IV 通常放在最前面)
        byte[] combined = new byte[iv.length + encryptedBytes.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(encryptedBytes, 0, combined, iv.length, encryptedBytes.length);

        return Base64.getEncoder().encodeToString(combined);
    }

    /**
     * 3. 完善解密：从头部拆分出 IV 并在初始化时使用它
     */
    public static String aesDecrypt(String combinedBase64, SecretKeySpec key) throws Exception {
        byte[] combined = Base64.getDecoder().decode(combinedBase64);

        // a. 提取 IV (前 16 字节)
        byte[] iv = new byte[IV_SIZE];
        System.arraycopy(combined, 0, iv, 0, IV_SIZE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // b. 提取真正的密文 (从 16 字节之后开始)
        int encryptedSize = combined.length - IV_SIZE;
        byte[] encryptedBytes = new byte[encryptedSize];
        System.arraycopy(combined, IV_SIZE, encryptedBytes, 0, encryptedSize);

        // c. 初始化解密器，必须传入提取出的 IV
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}