#Nemo MVC framework 

一个模仿springmvc，用来学习mvc架构的简单实现。<br />
此框架大体只为学习mvc架构方面的东西，简单实现，所以肯定还会存在不少的问题。<br />
框架基于servlet,提供了类似springmvc中Controller，RequestBOdy,ResponseBody,RequestMapping类似的注解支持。<br />
目前数据接收支持json、地址栏参数接收。<br />
数据返回支持html/text及json/application，不过因为没有完成模板引擎，所以html/text暂时只支持页面跳转，不能传参。后续再有空闲时间会添加模板引擎。<br />

框架使用：<br />
目前打包jar尚有问题未能解决，不过不影响学习使用。<br />
1、新建maven项目，导入framework-core和framework-mvc模块。<br />
2、新建你的web层模块，框架中的framework-web就是一个web层模块，在新模块的pom层中添加framework-core和framework-mvc的依赖即可：<br />
&lt;dependency&gt;<br />
&lt;groupId&gt;com.nemo&lt;/groupId&gt;<br />
&lt;artifactId&gt;framework.core&lt;/artifactId&gt;<br />
&lt;version&gt;1.0.0&lt;/version&gt;<br />
&lt;/dependency&gt;<br />
&lt;dependency&gt;<br />
&lt;groupId&gt;com.nemo&lt;/groupId&gt;<br />
&lt;artifactId&gt;framework.mvc&lt;/artifactId&gt;<br />
&lt;version&gt;1.0.0&lt;/version&gt;<br />
&lt;/dependency&gt;<br />
<br />
3、在你的web层添加nemo.framework.properties配置文件，可以参考framework-web中的nemo.framework.properties做一些简单的配置。 <br />

