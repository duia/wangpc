**************************************************************************************************

2017-03-10

将H2数据库集成到项目中，并将数据文件添加到项目的data文件夹下
    |——java
    |   |——listener
    |       |——H2DBServerStartListener.java
    |——resources
    |   |——config
    |       |——jdbc.properties（修改H2数据库连接参数）
    |       |——spring-shiro.xml（/console/* = anon，访问h2控制面板）
    |——webapp
    |   |——WEB-INF
    |       |——web.xml（监听器、H2Console）
    |——pom.xml（com.h2database）

**************************************************************************************************

2016-12-30

    |——java
    |   |——util
    |       |——*（工具类）
    |——pom.xml（org.apache.poi下的一些jar）


**************************************************************************************************

2016-10-20

    |——java
    |   |——websocket
    |   |   |——WebSocketConfig.java（简便方式配置websocket）
    |   |——controller
    |       |——SocketController2.java
    |——webapp
    |   |——js
    |       |——sockjs-1.1.1.min.js
    |       |——stomp.js
    |——WEB-INF
    |   |——socket
    |       |——socket.jsp

简便方式配置websocket

**************************************************************************************************

2016-09-14

添加artTemplate插件：js模板引擎插件

**************************************************************************************************

2016-09-13
添加color_admin帮助静态页面。

添加gritter：弹出提示信息插件
添加ionRangeSlider:滑动范围控件
添加password-indicator:密码安全性验证
添加masked-input:设置input掩码
添加jquery-file-upload：文件上传插件
添加jquery-tag-it：标签控件
添加bootstrap-datepicker:时间控件
添加bootstrap-colorpicker:颜色选择控件

**************************************************************************************************

2016-09-01

1. 新增ckeditor富文本插件，并实现文件上传功能
2. 增加上传文件的工具类UploadUtils.java，和返回信息FileMeta.java
3. 增加参数配置文件和读取方法，通过@WebListener注解，在启动容器是为程序加载配置文件中的参数。

************************************************************************************************

2016-08-30

整合shiro，新增认证和授权管理

************************************************************************************************

2016-08-18

修改pom.xml添加tomcat插件，可通过命令tomcat7:run启动项目

**************************************************************************************************

2016-08-15

添加color admin前端页面框架

**************************************************************************************************

多线程、可控制起停的的任务调度

	|——java
	|	|——controller
	|	|	|——TimesController.java(访问相关任务调度方法的控制器，控制任务线程的起停)
	|	|——times
	|		|——*(相关任务调度的一些实体类或工具类)
	|——resources
	|	|——applicationContext.xml(添加bean：schedulerFactoryBean)
	|——webapps
	|	|——views
	|		|——times(添加页面控制任务调度)
	|——pom.xml(需要spring使用的版本是4.2.4，添加org.quartz-scheduler2.2.1版本)
	
说明：启动任务调度的入口为JobTest.java,需要在spring容器中注入bean,也可以在该java中添加注解：@Component
	DataWorkContext.java实例化具体每个线程，可以链接数据库重数据库获取线程信息，ScheduleJob.java为具体的线程实体

**************************************************************************************************

解决轮询的性能问题，websocket

	|——java
	|	|——controller
	|	|	|——SocketController.java(页面入口)
	|	|——websocket
	|		|——*(websocket相关的配置)
	|——webapps
	|	|——views
	|		|——socket(页面入口，以及页面端调用的js)
	|——pom.xml(添加spring-websocket、spring-messaging、javax.servlet-api)
	
说明：必须搭配tomcat容器使用

**************************************************************************************************

2016-07-22

添加百度富文本编辑器（UEditor）功能

	|——java
	|	|——controller
	|	|	|——UEditorController.java
	|	|——core.ueditor
	|		|——百度提供的编辑器上传文件所需要的一些java源码
	|——resources
	|	|——spring-mvc.xml下添加以下代码：<mvc:resources mapping="/ueditor/**" location="/ueditor/" />
	|——webapps
	|	|——static
	|	|	|——plugins
	|	|		|——UEditor(ueditor相关的js和配置文件)
	|	|——ueditor(该目录需要和controller配置的路由相匹配，存放配置文件config.json，否则会读取不到，还可以作为文件上传的位置)
	|	|——WEB-INF
	|		|——views
	|			|——ueditor
	|				|——index.jsp(测试页面)
	|——pom.xml(添加commons-fileupload、commons-io、commons-codec、org.json（已）)

说明：spring-mvc.xml中的multipartResolver会拦截编辑器上传过来的文件，进行解析后后台无法识别，可以重写拦截器或注掉。

***************************************************************************************************