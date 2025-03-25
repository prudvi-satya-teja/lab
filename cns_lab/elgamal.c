#include <stdio.h>
#include <stdlib.h>
#include <math.h>

// Function to compute (base^exp) % mod using pow and fmod
long long modExp(long long base, long long exp, long long mod) {
    double m = pow(base, exp);  // Compute base^exp
    return (long long)fmod(m, mod);  // Compute (m % mod) and return as integer
}

// Function to check if 'g' is a primitive root modulo 'p'
int isPrimitiveRoot(int g, int p) {
    int seen[100] = {0}; // To track already encountered values

    for (int i = 1; i < p - 1; i++) {
        int val = modExp(g, i, p);
        if (seen[val] == 1) return 0; // Duplicate found, not primitive
        seen[val] = 1;
    }
    return 1; // It is a primitive root
}

// Function to find the smallest primitive root of 'p'
int findPrimitiveRoot(int p) {
    for (int g = 2; g < p; g++) {
        if (isPrimitiveRoot(g, p)) return g;
    }
    return -1; // No primitive root found
}

// Function to compute modular inverse using Fermat's theorem (for prime p)
long long modInverse(long long a, long long p) {
    return modExp(a, p - 2, p);  // Since a^(p-1) ≡ 1 (mod p), a^(p-2) ≡ a^(-1) (mod p)
}
 
int main() {
    // Step 1: Key Generation
    long long p = 11;  // Prime number
    long long g = findPrimitiveRoot(p);  // Finding a primitive root
    long long x = 3;   // Private key (randomly chosen)
    
    // Compute public key y = g^x mod p
    long long y = modExp(g, x, p);
    printf("Prime Number (p): %lld\n", p);
    printf("Primitive Root (g): %lld\n", g);
    printf("Public Key: (p = %lld, g = %lld, y = %lld)\n", p, g, y);
    printf("Private Key: x = %lld\n", x);

    // Step 2: Encryption
    long long M = 7;  // Message to encrypt
    long long k = 4;   // Random number (should be chosen randomly)
    
    // Compute c1 and c2
    long long c1 = modExp(g, k, p);
    long long c2 = (M * modExp(y, k, p)) % p;
    printf("\nEncrypted Message: (c1 = %lld, c2 = %lld)\n", c1, c2);

    // Step 3: Decryption
    long long s = modExp(c1, x, p);  // Compute shared secret s = c1^x mod p
    long long s_inv = modInverse(s, p);  // Compute modular inverse of s
    long long decrypted_M = (c2 * s_inv) % p;
    printf("\nDecrypted Message: M = %lld\n", decrypted_M);

    return 0;
}
 