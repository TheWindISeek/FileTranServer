# 远程库地址

# git clone

# https://github.com/TheWindISeek/FileTran.git



先进行需求分析

再进行需要执行的目标

然后进行任务的划分和规整。

先前端，先确定页面数，再确定每个页面干啥，最后确定分工。



每个人在制作时需要写文档去记录。

​	记录的内容：修改的内容，修改的目的，遇到的bug

使用git进行版本控制，会分为不同的阶段，每人每个阶段会有任务。



# 第一次会议

##  目的

确定需求，也就是我们最终要做的是什么东西

明确前端的页面，每个页面的布局，应当有的功能，具体的分工。

后端可能的实现

## 具体内容

### 需求

```

	首先必须支持文件上传到服务器，由服务器来进行文件的分享。P2P局域网。有用户的注册登录。分享文件。分享文件可以是单个或者文件夹，可以设置标签。有搜索功能，根据名称搜索。分享文件标注文件的查看权限。
	根据标签去推荐


	用户类别：未登录  登录 会员 管理员 1111 0111 0011 0001
	可下载 
	查看 
		消息 
		删除自己发的
							编辑 控制 查看 重置 （增删改查） 
							
	局域网下数据传输 未登录
		TCP,
		C/S B/S  有一台机器作为服务器. 当 服务器退出的时候,直接告诉下载失败. 令牌根据连接到当前推出服务器的时间先后吗,做一个服务器. 新服务器进行广播.其他主机可以选择是否连接.
		共享文件 
		主机的管理 IP端口号 维护一个表 当前网络内 多久更新一下 怎么更新.
		加速传输 (怎么加速 广域网情况下如何加速传播)
		假如需要传输时,可以先查看是否可以直接在局域网下进行传播 如果可以,就直接利用局域网去进行传播,不走广域网.
		
		在线文件分享
			服务器中转
			
		局域网下传输可选 P2P(更加私密) C/S B/S(多主机连接)
		
		广域网(C/S B/S)
		
		
		文件的分类怎么去分
		
		文件的类别
		 程序
		 
		按用途分类(手动添加)
		后缀名分类
		大小分类
		付费与免费
		
		后缀
			.zip .c
		用途
			程序 脚本
		大小
		付费与免费 (预留)
		
		可选的推荐功能
		
		猜你喜欢的推荐(可折叠)
		
		
		右边空白
		
		
		[文件名 分享者 分享时间 文件大小 标签 付费内容(预留) 评价(预留)]
		 
		 文件名 大小 
		 
		 
		 可选多种视图(预留)
		 
		 
		 文件详情
		 
		 简介 作者 评价(预留) 弹窗
		 
		 
		 
		 登录做页面 开一个新页面
		 
		 
		 
		 
		 管理员页面
		 增删改查
		 
```

# 文件夹说明
## .vs
之前上传github仓库总是失败 所以使用了vs打开了这个文件夹 因此就创建了.vs文件

## Css
这个文件夹放所有的样式表
每个样式表的名字对应一个Html文件
由于我认为我们的HTML还没有复杂到需要多个CSS来做样式 
因此是Css和HTML是一对一的关系
## Hmtl
放主体的HTML文件
Lan.html : 局域网下互传的页面
login.html : 登录页面
main.html : 主页面
manage.html : 管理者页面
user.html : 用户页面

## images
放用到的图片素材

## JS
放未来可能用到的JS代码

## README.md
一些说明和后续修改

# 提交说明

由于某人是一个文件一个文件提交的，所以会有很多的提交记录，实际上一个时间段的都只是一个，并且由于他不写提交信息，所以干了啥具体的不知道。



# javaweb设计前后端接口

## 登录 | 注册页面



boolean login(String userName, String userPassword);

boolean register(String userName, String userPassword);

忘记密码需要做的功能为，转发页面到忘记密码页面。

忘记密码需要做的事情是使用用户的邮箱或者电话号码去验证。但是目前咱并没有做。故跳过

## 主页

在搜索框中输入关键词进行搜索

```java
//输入的关键词keyWords按照空格划分。
List<File> searchByKeyWords(String[] keyWords);

List<File> searchByLabels(Suffix[] suffixs , Usage[] usages, Size[]  sizes);

//参数为空请按照如下方式实现
List<File> searchByLabels(Suffix[] suffixs, Usage[] usages) {
	Size[] sizes = new Size[0];//大概就是这个意思 传过来的长度为0
	getByLabels(suffixs, usages, sizes);
}
```

```java
//返回的file仅仅包含以下内容
File 
	fileId : 用于后续文件查找
	fileName: 文件名字
	fileSuffix: 文件后缀
	fileSize: 文件大小
	fileImage: 文件封面 （可固定 先预设置）
```
联系我们
跳转到联系方式页面
展示联系方式

问题反馈

//是否发送成功 虽然有点废，但是还是给个返回值吧

boolean sendQuestion(int userId, String question);



加入我们
同样跳转到联系方式页面



## 管理员页面




## 用户界面



## 局域网传输页面

```
无特殊说明
以下文件均指
	 	fileId : 用于后续文件查找
        fileName: 文件名字
        fileSuffix: 文件后缀
        fileSize: 文件大小
        fileImage: 文件封面 （可固定 先预设置）



前端需要完成的逻辑

页面加载时
        请求服务器文件列表资源
            1.服务器直接返回当前所有的文件 
            	List<File> searchAllFiles();
            2.服务器返回当页可容纳下的所有文件的内容
            	获取指定页面的files
                //是否考虑到服务器的中对应的index不是我预期的index
                //因为服务器中的数据是不断更新的
                List<File> searchFileFromIndex(int index);
        获得List<File> files


	
上传    
	点击上传按钮
	弹出文件选择框
	选择文件路径
	从该文件路径中生成一个File对象
    
    
    点击左箭头
    将文件发送到服务器
    boolean uploadFile(File file); {
    	//与后端建立连接
    	首先将File对象发送给后端。
    	//最好开一个线程
    	发送文件到指定文件夹
    	发送文件结束符号
    	关闭流
    	返回是否发送成功
    	//结束线程
    }
  	
  	发送选定文件给服务器
  	boolean uploadFiles(File[] files) {
  		for file : files
 			uploadFile(file);
 		return true
  	}
  	
  
下载
	点击右箭头（服务器文件列表到下载文件列表）
	前端完成处理逻辑
	将选中标签的Files从服务器文件列表复制到下载文件列表
	
	点击下载
	将选中的内容下载到本地
	boolean downloadFile(File file) {
		//与后端建立连接
		发送File对象到后端
		后端发送Blob回来
		前端绑定File和Blob
		将Blob持久化到本地指定下载文件夹
		return true;
	}
	
	boolean downloadFiles(File[] files) {
		for file : files
			downloadFile(file);
		return true;
	}
```



## 用户页面

```
此时的文件可能还会需要多一个属性
	 	fileId : 用于后续文件查找
        fileName: 文件名字
        fileSuffix: 文件后缀
        fileSize: 文件大小
        fileImage: 文件封面 （可固定 先预设置）
        fileParent: 父文件ID
        
 亦或者采取局部更新的方式，即，当我进入一个文件夹时我再向后端去申请数据，以下叙述这种方式。
 
 
 首先在页面加载时，我们需要拿到上传，下载和收藏的一级目录下的所有文件。
 List<File> searchUploadFiles(User user);
 List<File> searchDownloadFiles(User user);
 List<File> searchFavoriteFiles(User user);
 
 与之类似
 //file指定了具体是哪个文件夹下的文件 当然如果这个file到时候不是文件夹 就会返回false。我们还可以使用一种方式来实现上面的函数，即设置一种特殊的文件，作为根目录
 List<File> searchUploadFiles(User user, File file);
 List<File> searchDownloadFiles(User user, File file);
 List<File> searchFavoriteFiles(User user, File file);
 
 
 上传文件还会有一个按钮
 	功能和局域网传输页面中的upload的功能一样
 下载文件同理
 
 收藏文件则有上传和下载两个功能
 	不过上传功能需要做到将文件file上传到用户user的指定文件夹target下，并将其标星
 	uploadFile(User user, File file, File target)；
```



## 留言本

暂时不做



## 购物车

暂时不做



## 文件详情页面

```
应当有如下功能

1.依据file的信息展示基础的内容
2.显示下载次数 标星次数
	AdditionalFile searchAdditionalFile(File file);
	AddtionalFile
		除文件内容外的所有信息
	
3.显示当前文件的评论
	根据当前文件的Additional 获取到所有的评论
	Content
		contentId:评论ID
		sendId:发送者ID
		sendContent:发送内容
		sendTime:发送时间
		replyContent:回复内容
	List<Content> searchContentsFromFile(AdditionalFile file);
	仅获取一层（当然要是数据库直接获取了所有的内容那就获取了所有的内容吧）
	当点击显示更多的时候
	再度向后端发起请求
	函数内容为
	List<Content> searchContentsFromFile(AdditionalFile file, Content context);
```



## 管理员

```
增删改查四件套
没想好 暂时先定一下功能


增加文件
	参考之前在用户界面提到的
	boolean uploadFile(File file, User user);
	
删除文件
	//删除特定user的特定file
	boolean deleteFileFromUser(Usre user, File file);
	
修改文件
	修改用户归属
	修改文件类型
	修改。。。。
	boolean updateFile(File file);
	boolean updateFile(AdditionalFile file);
	
搜索文件
	....主页的查找文件函数
    //输入的关键词keyWords按照空格划分。
    List<File> searchByKeyWords(String[] keyWords);

    List<File> searchByLabels(Suffix[] suffixs , Usage[] usages, Size[]  sizes);

    //参数为空请按照如下方式实现
    List<File> searchByLabels(Suffix[] suffixs, Usage[] usages) {
        Size[] sizes = new Size[0];//大概就是这个意思 传过来的长度为0
        getByLabels(suffixs, usages, sizes);
    }
```



## 服务器访问路径设计

```
规则总结如下
html页面直接访问
所有函数访问路径为
函数通用前缀 选择特定的servlet类
函数后缀选择这个类中的函数


http://ip:port/
	fileTran/
		main.html
		...
		manage.html
		
		search/
			searchByLabels() -> byLabel
			searchByKeyWords() -> byKeyWord
			searchFileFromIndex() -> FileFromIndex
			
		favorite/
		
		content/
		
		delete/
		
		update/
		
		upload/
			
		download/
```

```java
现需要实现的函数如下
	
boolean login(String userName, String userPassword);

boolean register(String userName, String userPassword);


//输入的关键词keyWords按照空格划分。
List<File> searchByKeyWords(String[] keyWords);

List<File> searchByLabels(Suffix[] suffixs , Usage[] usages, Size[]  sizes);

//参数为空请按照如下方式实现
List<File> searchByLabels(Suffix[] suffixs, Usage[] usages) {
	Size[] sizes = new Size[0];//大概就是这个意思 传过来的长度为0
	getByLabels(suffixs, usages, sizes);
}

List<File> searchAllFiles();
                //是否考虑到服务器的中对应的index不是我预期的index
                //因为服务器中的数据是不断更新的
                List<File> searchFileFromIndex(int index);

    boolean uploadFile(File file); {
    	//与后端建立连接
    	首先将File对象发送给后端。
    	//最好开一个线程
    	发送文件到指定文件夹
    	发送文件结束符号
    	关闭流
    	返回是否发送成功
    	//结束线程
    }
  	
  	发送选定文件给服务器
  	boolean uploadFiles(File[] files) {
  		for file : files
 			uploadFile(file);
 		return true
  	}

	boolean downloadFile(File file) {
		//与后端建立连接
		发送File对象到后端
		后端发送Blob回来
		前端绑定File和Blob
		将Blob持久化到本地指定下载文件夹
		return true;
	}
	
	boolean downloadFiles(File[] files) {
		for file : files
			downloadFile(file);
		return true;
	}

 //首先在页面加载时，我们需要拿到上传，下载和收藏的一级目录下的所有文件。
 List<File> searchUploadFiles(User user);
 List<File> searchDownloadFiles(User user);
 List<File> searchFavoriteFiles(User user);

 //file指定了具体是哪个文件夹下的文件 当然如果这个file到时候不是文件夹 就会返回false。我们还可以使用一种方式来实现上面的函数，即设置一种特殊的文件，作为根目录
 List<File> searchUploadFiles(User user, File file);
 List<File> searchDownloadFiles(User user, File file);
 List<File> searchFavoriteFiles(User user, File file);

 上传文件还会有一个按钮
 	功能和局域网传输页面中的upload的功能一样
 下载文件同理
 
 收藏文件则有上传和下载两个功能
 	不过上传功能需要做到将文件file上传到用户user的指定文件夹target下，并将其标星
 	uploadFile(User user, File file, File target)；
     

//	AddtionalFile		除文件内容外的所有信息     
 	AdditionalFile searchAdditionalFile(File file);

	Content
		contentId:评论ID
		sendId:发送者ID
		sendContent:发送内容
		sendTime:发送时间
		replyContent:回复内容
	List<Content> searchContentsFromFile(AdditionalFile file);
	//仅获取一层（当然要是数据库直接获取了所有的内容那就获取了所有的内容吧）当点击显示更多的时候
	List<Content> searchContentsFromFile(AdditionalFile file, Content context);

	boolean uploadFile(File file, User user);
	//删除特定user的特定file
	boolean deleteFileFromUser(Usre user, File file);
	修改用户归属
	修改文件类型
	修改。。。。
	boolean updateFile(File file);
	boolean updateFile(AdditionalFile file);
	....主页的查找文件函数
    //输入的关键词keyWords按照空格划分。
    List<File> searchByKeyWords(String[] keyWords);

    List<File> searchByLabels(Suffix[] suffixs , Usage[] usages, Size[]  sizes);

    //参数为空请按照如下方式实现
    List<File> searchByLabels(Suffix[] suffixs, Usage[] usages) {
        Size[] sizes = new Size[0];//大概就是这个意思 传过来的长度为0
        getByLabels(suffixs, usages, sizes);
    }
```

