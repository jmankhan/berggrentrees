#Berggren Trees

A program to find a vector w under c such that w satisfies the constraints:

w1, w2, w2 > 0

gcd(w1, w2, w3) = 1
for each w, let q = w1^2 + cw2^2 - w3^2. q must divide 2c

let d = (sqrt(2) - 1) / (sqrt(1+c)).

then w3^2(w1^2 + c^2*w2^2) <= q^2/(d^2)
