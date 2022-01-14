# JustC2file: Burp插件--C2 profile生成器
## 描述

可以通过Burp代理选中请求，生成Cobalt Strike的profile文件

## 使用

在Burp中安装插件之后，同时选中目标站点至少三条GET/POST请求，且必须同时存在GET/POST；将通过这三个请求响应的数据包生成profile文件，然后复制文本到Cobalt Strike服务器上，使用c2lint进行检测，通过之后可使用该文件启动CS，以达到规避流量检测的效果；测试可以执行命令再用于实际环境中。

![](https://github.com/Peithon/JustC2file/blob/master/imgs/text-bing.png)

## 演示

### 1、浏览器无痕页面产生流量

在浏览器中开启无痕模式（防止存在个人信息），然后开启代理，在无痕模式下产生流量，比如这里随便搜索一下：

![](https://github.com/Peithon/JustC2file/blob/master/imgs/bing_select.png)

chrome在无痕模式下默认不开启扩展程序，如果没有开启的话：点击右上角菜单图标【三个点】，依次选择【更多工具】-【扩展程序】- 详情，开启

![](https://github.com/Peithon/JustC2file/blob/master/imgs/proxy_burp.png)

### 2、Burp中选中相关流量

回到Burp中，将想用的流量右键高亮显示，找到足够多的流量时同时选中，右键生成CS的配置文件

![](https://github.com/Peithon/JustC2file/blob/master/imgs/check_http.png)

### 3、修改配置文件

***修改https-certificate***

可以不使用默认的配置参数值，利用目标站点的证书信息生成证书，可信度会更高一些。

 ```
 #默认配置，validity为证书有效期
 https-certificate {

    set C   "SI";
    set CN  "www.bing.com";
    set O   "bing.com";
    set OU  "bing.com";
    set validity "365";
}
 ```

***修改code-signer***

如果需要文件签名，修改成你的证书名称和证书密码，并去除注释

```
#code-signer{
#    set keystore "keystore.jks";
#    set password "123456";
#    set alias "google";
#}
```
### 4、使用生成的文件在服务上启动CS

在服务器使用c2lint测试文件，没有错误后启动CS，使用命令

```
nohup ./teamserver [external IP] [password] [/path/to/my.profile] &
```

生成可执行文件，然后在目标上执行，如果执行命令存在结果回显，说明配置文件可以正常使用。下面是在`Wireshark`中捕获的`Cobalt Strike`流量，[示例文本](https://github.com/Peithon/JustC2file/blob/master/test.profile)，图片被压缩了比较糊：

![](https://github.com/Peithon/JustC2file/blob/master/imgs/http-get.jpg)

![](https://github.com/Peithon/JustC2file/blob/master/imgs/http-post.png)

## 启动CS前的注意事项
### 关于https-certificate

可以不使用默认的配置参数值，利用目标站点的证书信息生成证书，可信度会更高一些。

 ```
 #默认配置，validity为证书有效期
 https-certificate {

    set C   "SI";
    set CN  "www.bing.com";
    set O   "bing.com";
    set OU  "bing.com";
    set validity "365";
}
 ```

在浏览器查看目标站点相关的证书信息，然后填充到https-certificate中的对应的配置参数中。

![](https://github.com/Peithon/JustC2file/blob/master-test/imgs/https-certificate.png)

### 关于code-signer

在`Attacks -> Packages -> Windows Executable和Windows Executable (S)`生成可执行文件或DLL文件时，默认是不进行签名的，如果需要对可执行文件或 DLL 文件进行签名，要在CS服务器上生成`jks`证书，然后在profile文件中配置code-signer，步骤如下：

***C2profile与keystore证书最好在相同目录下***

1、利用`keytool`生成自己的免费证书

注意，记住`-alias`后面的参数和`-keystore`后面的参数后面会在`C2-profile`文件中使用。
```
keytool -genkey -alias google -keyalg RSA -validity 36500 -keystore keystore.store
``` 
2、将store证书转成`.p12`格式

这里原来的`keystore.store`证书会重命名为`keystore.store.old`
```
keytool -importkeystore -srckeystore keystore.store -destkeystore keystore.store -deststoretype pkcs12
```
3、将`.p12`格式证书转为`.jks`格式证书
 ```
keytool -v -importkeystore -srckeystore keystore.store -srcstoretype PKCS12 -destkeystore keystore.jks -deststoretype JKS
```
4、C2文件中配置

```
# 修改成你的证书名称和证书密码，并去除注释
#code-signer{
#    set keystore "keystore.jks";
#    set password "123456";
#    set alias "google";
#}
```


## 参考文献

[CS官方文档](https://hstechdocs.helpsystems.com/manuals/cobaltstrike/current/userguide/content/topics/malleable-c2_main.htm?cshid=1062)

[Randomized Malleable C2 Profiles Made Easy](https://bluescreenofjeff.com/2017-08-30-randomized-malleable-c2-profiles-made-easy/)

[How to Write Malleable C2 Profiles for Cobalt Strike](https://bluescreenofjeff.com/2017-01-24-how-to-write-malleable-c2-profiles-for-cobalt-strike/)

[threatexpress/malleable-c2](https://github.com/threatexpress/malleable-c2)


