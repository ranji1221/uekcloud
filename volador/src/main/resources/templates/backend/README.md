# 优逸客 飞鱼平台项目

## 网站适用：PC平台 兼容IE9
## 网页内宽：1140px
## 开发规范
### 注意：网站要兼容IE9，不能使用不兼容IE9的东西。
#### 以下语法不能使用
* flex弹性盒模型

#### 以下语法可以使用
* CSS3选择器

#### js注意事项
* 全局事件为防止冲突使用事件监听添加
    - 错误 `window.onload = function(){}`  
    - 正确 `$(function(){ }) || window.addEventListener('load',function(){})` 
* 不使用 ES6 语法

### 规范
#### 目录结构
> 统一放置对应文件夹中

```
|-------images/
|-------css/
|   |----reset.css    重置默认样式
|   |----wqf_index.css    个人页面
|--------js/
|   |----jquery.min.js
|   |----swiper.min.js
|-------fonts/     阿里巴巴iconfont字体图标
|--------index.html
|--------about.html
|--------news.html
```

#### 命名规范
- css文件  如： 文件名  `wqf_index.css`  <br>
                类名 如 `.wqf_banner #wqf_nav`  **（一定要有后代保护）** <br>
- js文件   如： 文件名  `wqf_index.js`  <br>
  js文件引用统一放置在body结束之前
- image文件
  文件名 如：`wqf_logo.png`<br/>
  所有文件 统一放置到`images`目录下，并以自己姓名简拼开头<br/> 如   `images/wqf_logo.png`<br/> `images/cp-copy.png`

#### 注释
> 必须添加注释并且注释结构清晰

```
<!-- #nav 导航开始  -->
<!-- #nav 导航结束  -->
```

> 项目完成后将无用注释删除

## 工具库
- jQuery v2.4
- 可使用jquery插件、必须有插件说明 接口必须要有描述


## 优化
- 小图标统一使用`iconfont`字体图标
    + 字体图标所有人共用一套，统一在`iconfont`项目中添加需要图标
    + 不要擅自删除他人图标，项目完成后统一清理无用图标
    + 字体图标先使用远程连接，项目完成后下载到本地 (远程链接已在`reset.css`中写出)
- 图片从PS中切图要压缩优化： [图片压缩网址](http://zhitu.isux.us/)
- 代码结构合理，统一使用4空格缩进
- 网站头部统一使用以下标签 (SEO)

```
<meta charset="UTF-8">
<title>飞鱼平台</title>
<meta name="renderer" content="webkit"/>
<meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
<meta name="keywords" content="飞鱼平台" />
<meta name="description" content="飞鱼平台" />
<link rel="shortcut icon" href="favicon.ico" />
```

## 任务分配




