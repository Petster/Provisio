# Provisio 
 CSD460 Provisio Capstone Project

**Step 1:** Clone the project
- if you already have the project just run `git pull -r` to get the latest changes.
- if you do not have the project run `git clone https://github.com/Petster/Provisio.git`

**Step 2:** Install tomcat 9 if you do not have it
- run `brew install tomcat@9`
- copy the working dir for where tomcat 9 was installed. (i.e, /usr/local/Cellar/tomcat@9/9.0.69/libexec)

**Step 3:** Open up the project in the IDE of your choosing...
- IntelliJ recommended, you can get a free account with your student email

**Step 4:** Use John's awesome screen shots to edit config and point to tomcat installation

![](https://user-images.githubusercontent.com/78231948/201999470-f942a736-8581-46c7-97a2-451e69453260.png)
![](https://user-images.githubusercontent.com/78231948/201999300-732f25ae-e755-4dc7-bd98-724234df1021.png)
![](https://user-images.githubusercontent.com/78231948/201999030-fd570e89-ef34-43b4-9f6d-712139992d80.png)
![](https://user-images.githubusercontent.com/78231948/201999046-f169f025-df02-4387-92a1-7c94ae00eae7.png)

**Step 5:** Right click the docker-compose.yaml to run the yaml file
- If port 3306 is already being used you can change the port or run `sudo kill `sudo lsof -t -i:3306``

![](https://user-images.githubusercontent.com/78231948/202038681-9117975d-6309-4b32-b47f-275baa4aeb66.png)

**Step 6:** Press the green play button in your IDE to deploy and start your Tomcat instance
