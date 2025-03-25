#include <stdio.h>
#include <math.h>

int main() {
    long long p, g, a, b, A, B, keyA, keyB;

    printf("Enter a prime number (p): ");
    scanf("%lld", &p);

    printf("Enter a primitive root of %lld (g): ", p);
    scanf("%lld", &g);

    printf("Enter private key for Alice: ");
    scanf("%lld", &a);

    printf("Enter private key for Bob: ");
    scanf("%lld", &b);   

    double p1 = pow(g,a); 
    A=fmod(p1,p);	 // A = g^a % p

    double p2 = pow(g,b); 
    B=fmod(p2,p); // B = g^b % p

    printf("\nPublic key for Alice (A): %lld", A);
    printf("\nPublic key for Bob (B): %lld", B);

    double kA = pow(B,a);
    keyA= fmod(kA,p); // Secret key for Alice: (B^a) % p
    
   double kB = pow(A,b);  // Secret key for Bob: (A^b) % p
   keyB= fmod(kB,p);

    printf("\n\nShared Secret Key computed by Alice: %lld", keyA);
    printf("\nShared Secret Key computed by Bob: %lld", keyB);

    if (keyA == keyB)
        printf("\n\nKey Exchange Successful! Shared Secret Key: %lld\n", keyA);
    else
        printf("\n\nKey Exchange Failed! Keys do not match.\n");
    return 0;
}
