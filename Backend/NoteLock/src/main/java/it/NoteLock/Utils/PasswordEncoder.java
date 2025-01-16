package it.NoteLock.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Component;
/**
 * A component responsible for encoding and verifying passwords using Argon2.
 * 
 * @author Francesco Valentini
 */
@Component
public class PasswordEncoder {
    @Value("${security.argon2.salt-length}")
    private int saltLength;

    @Value("${security.argon2.hash-length}")
    private int hashLength;

    @Value("${security.argon2.parallelism}")
    private int parallelism;

    @Value("${security.argon2.memory}")
    private int memory;

    @Value("${security.argon2.iterations}")
    private int iterations;
    
    /**
     * Encodes a raw password using the Argon2 algorithm.
     * 
     * @param password The raw password to be encoded.
     * @return The encoded password as a hash string.
     */
	public String encodePassword(String password) {
		Argon2PasswordEncoder arg2Encoder = new Argon2PasswordEncoder(saltLength, hashLength, parallelism, memory, iterations);
		return arg2Encoder.encode(password);
	}
	

    /**
     * Verifies if a raw password matches an encoded hash.
     * 
     * @param password The raw password to verify.
     * @param hash The encoded hash to compare against.
     * @return {@code true} if the password matches the hash, {@code false} otherwise.
     */
	public boolean verifyPassword(String password, String hash) {
		Argon2PasswordEncoder arg2Encoder = new Argon2PasswordEncoder(saltLength, hashLength, parallelism, memory, iterations);
		return arg2Encoder.matches(password, hash);
	}
}
