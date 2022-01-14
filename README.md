# E2EE 실습

### 1대1 채팅 종단간 암호화 구현


※ 암호화 키 교환 방식 이해와  OpenSSL을 사용해보기 위한 프로젝트입니다.

```
 + OpenSSL
 
 + SpringBoot
 
 + Thymeleaf
 
 + Javascript
  
```

1. OpenSSL을 사용해 RSA 알고리즘의 Public, Private Key를 생성.
2. Public Key를 공유하고 Message를 상대의 PubKey로 암호화.
3. 받은 Message를 자신의 Private Key로 복호화.


### OpenSSL 실행에 필요한 도구

```
  + ActivePerl or StrawberryPerl
  + Nasm
  + OpenSSL (1.1.1g)
  + Visual Studio Community (2019 사용)
```
### 과정
![1](https://user-images.githubusercontent.com/48385816/149459453-58c079aa-6e96-456f-93a4-6c8bfb31f0f2.png)




![image](https://user-images.githubusercontent.com/48385816/149458320-97e8e91f-5760-479d-bdf2-ff9736c13090.png)

로컬에서 해당 Command Shell 실행 -> public,private key생성됨

E2EE 버튼 클릭시, Public Key 교환.

메시지 입력, 전송

수신시, 자신의 PrivateKey로 복호화.


- PlainText와 서버로 전송된 Encryted Text

![image](https://user-images.githubusercontent.com/48385816/149458960-d068b63e-161b-4e6d-a512-f0b8a3ab8719.png)

