# JustC2file: Burp插件--C2 profile生成器
## 描述
  可以通过Burp代理选中请求，生成Cobalt Strike的profile文件
## 使用
  在Burp中安装插件之后，同时选中目标站点的一个GET请求和POST请求，将通过这两个请求响应的数据包生成profile文件，然后复制文本到Cobalt Strike服务器上，使用c2lint进行检测，通过之后可使用该文件启动CS，以达到规避流量检测的效果。
  
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
 
 ![](https://github.com/Peithon/JustC2file/blob/master-tets/imgs/https-certificate.png)
  
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
