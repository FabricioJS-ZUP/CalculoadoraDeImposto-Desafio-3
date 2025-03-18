# Project documentation:
## Step by step guide on how to use the software
- 1**° Download the project and extract it to your computer.**
- 2**° After this, take the version that was extracted, right-click on it, go to show more options and search for edit with intelij idea comunit.**
- **3° After this, go to the top of the screen and look for the run button or use the shift + f10 keys.**
- 4**° Use a tool to make the requests, it can be postman or another (I used Thunder Client (to use this tool you have to download it in the visual studio extensions)).**
- 5 **° To start making the requests, start by creating the user for this, go to** :http://localhost:8080/user/register
- 6° place in the body part (Post)
    
    ```java
    {
      "username": "name",
      "password": "pasword",
      "role": "ADMIN or USER" 
    }
    ```
    
- 7° After this, get your token at http://localhost:8080/user/login (Post)

```java
{
  "username": "name you chose",
  "password": "pasword you chose"
}
```

- 8° After this, take the token that was given in the previous section and in all the next requests you will have to put it like this: (after the Bearer you have to put your token)
    
    ![image.png](attachment:bb2484f1-d0e2-49b6-8a77-8b53dbf9fbc0:image.png)
    
- 9° Now to see the taxes you can:
    - 1**° See all taxes (GET):**  http://localhost:8080/impostos
        
        ![image.png](attachment:51457b63-59f8-4262-960b-c9427cac00d7:image.png)
        
        - 2**° View by id**
            
            ![image.png](attachment:960e1de2-1bac-4b1f-af20-02cd9d54b8af:image.png)
            
- **10° To create a tax go to** http://localhost:8080/impostos (POST)
![image](https://github.com/user-attachments/assets/a3883c4b-9eea-4903-929d-c46c57bdb9b2)

- **11° To calculate go to**  http://localhost:8080/impostos/calculo (POST)
    
    ```java
    {
      "id": 2,
      "baseValue": 1000.0 //Here you put the base value
    }
    ```
    
- 12° To delete go to http://localhost:8080/impostos/2 (DELETE) in place of 2 you put the ID of the tax you want to delete.
- 13°  And if you want to see more than one user, place the request [http://localhost:8080/find/](http://localhost:8080/find/teste)name


## UML do projeto
https://app.diagrams.net/#HFabricioJS-ZUP%2FCalculoadoraDeImposto%2Fmain%2FDiagrama%20sem%20nome.drawio#%7B%22pageId%22%3A%22q6dhUPsjB3l89_tuy7Ky%22%7D
