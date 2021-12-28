## JustC2file
### 描述
  可以通过Burp代理选中请求，生成Cobalt Strike的profile文件
### 使用
  在Burp中安装插件之后，同时选中目标站点的一个GET请求和POST请求，将通过这两个请求响应的数据包生成profile文件，然后复制文本到Cobalt Strike服务器上，使用c2lint进行检测，通过之后可使用该文件启动CS，以达到规避流量检测的效果。
