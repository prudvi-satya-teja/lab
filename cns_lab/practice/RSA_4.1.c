#include<stdio.h>
#include<math.h>

int gcd(int n, int totient) {
	if(n == 0) return totient;
	return gcd(totient % n,  n);
}

int main() {
	
	int p, q;
	scanf("%d %d", &p,&q );
	
	int n = p * q;
	
	int totient = (p - 1) * (q - 1);
	
	int count = 2;
	int e = 2;
	while(e < totient) {
		int num = gcd(e, totient);
		if(num == 1) break;
		e++;
	}
	
	
	int d;
	double k = 2;
	
	d = (1 + (k * totient)) / e;
	
	double msg = 12;
	double cipher  = pow(msg, e);
	double plain = pow(cipher, d);
	
	cipher = fmod(cipher, n);
	plain = fmod(msg, n);
	
	printf("Message data = %lf", msg); 
    printf("\np = %lf", p); 
    printf("\nq = %lf", q); 
    printf("\nn = pq = %lf", n); 
    printf("\ntotient = %lf", totient); 
    printf("\ne = %lf", e); 
    printf("\nd = %lf", d); 
    printf("\nEncrypted data = %lf", cipher); 
    printf("\nOriginal Message Sent = %lf\n", plain); 

    return 0; 
	
	
}
