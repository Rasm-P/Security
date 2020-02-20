# Week-04 A3 and GDPR

From OWASP 3: *Many web applications and APIs do not properly protect sensitive data, such as financial, healthcare, and PII. Attackers may steal or modify such weakly protected data to conduct credit card fraud, identity theft, or other crimes. Sensitive data may be compromised without extra protection, such as encryption at rest or in transit, and requires special precautions when exchanged with the browser.*

Paraphrased, this week is not about how to prevent break-ins into your system, but how to make sure your sensitive data should be protected even in the case someone break into your system.

## Learning Goals

After this week, you should be able to:

* Enumerate the major kinds of *sensitive data* as defined by the EU law (Danish law is the same).
* Be able to explain the difference between controller and processor roles in protection of private data.
* Be able to explain the concepts of privacy by design and privacy by default.
* write code that encrypt/decrypt  data using best practice encryption libraries in Java (or in an other programming language)


## Resources:

### What to encrypt

* The General Data Protection Regulation in short from [EU*GDPR*.ORG](https://eugdpr.org/the-regulation/).
* The regulations are being passed into Danish law (and into the laws of all the other member states). [This Danish text to explain things well too](Persondataforordningen_DetSkalDuHaveStyrPaai2018.pdf)

### How to encrypt
We will use the [slides](04-A3%20and%20GDPR.pdf) and sample code for this.

## Exercises
The main focus of the exercises for this week is to be able to encrypt and decrypt data using the libraries of Java.

### Exercise 0

Design a system for handling signatures for new parties.
* You can sign up for at most one party
* Signatures are binding can't be withdrawn

### Exercise 1
The first thing we will do is to look at Cesarean rotation. I am not sure it was ever used, but it is good for making text which can't be read by the named eye, but is easy to crack.

In the sample code, find out how many letters to rotate the hidden message. Notice, the rotator can rotate both forwards and backwards (though that is not really necessary - why?)

### Exercise 2
This exercise uses an "incredible dumb and stupid" password scheme, as the actual input is a number between 1000 and 9999. You must find out which one. Your attack form here is brute force (trying them all).

Hint: If you want to automate validation, the word "everything" occurs in the original text.

### Exercise 3
This exercise is about writing a small toy editor which allow you to store small texts in encrypted format. The save function is there, but you have to write the load.

## Some abreviations

* __DPA__ Data Protection Authorities
* __DPO__ Data Protection Officer
* __GDPR__ General Data Protection Regulation
* __TLS__ Transport Layer Security
* __TDE__ Transparent Data Encryption

## Exam questions

* Enumerate the major kinds of sensitive data as defined by the EU law (Danish law is the same).
* Explain the concepts of privacy by default and by design
* Explain the difference between controller and processor roles in the protection of private data.
* Explain how to use Java to encrypt and decrypt information
