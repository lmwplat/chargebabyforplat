<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/14 0014
  Time: 下午 2:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        // 获取url参数，Bug: 如果是版本，则要同时兼任2.0和2两种情况。
        function getUrlParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|#|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) {
                if (name == 'v') { //版本
                    r[2] = parseFloat(r[2].substr(0, 3));
                    return r[2];
                }
                return encodeURIComponent(decodeURIComponent(unescape(r[2])));
            }
            return null;
        }

        // 区分userAgent浏览器类型，Bug: 微信判断，不能用var weixin = navigator.userAgent.match(/MicroMessenger/i) == "micromessenger";进行判断。
        var browser = navigator.userAgent.toLowerCase(); //userAgent类型;
        var ios = browser.indexOf("iphone") > -1 || browser.indexOf("ipad") > -1 || browser.indexOf("ipod") > -1 || browser.indexOf("itouch") > -1 || browser.indexOf("ios") > -1 || browser.indexOf("mac") > -1; //ios终端
        var Android = browser.indexOf("android") > -1 || browser.indexOf("linux") > -1; //android终端
        var weixin = browser.indexOf('micromessenger') > -1; //微信浏览器
        var other = browser.indexOf('mqqbrowser') > -1 || browser.indexOf('ucweb') > -1; //其它浏览器
        var wp = browser.indexOf('windows phone') > -1; //window phone终端
        var pc = browser.indexOf('windows nt') > -1 || browser.indexOf('macintosh') > -1; //PC终端=window终端+mac终端

        // 设置各个版本的下载链接地址
        var path = {
            169: {
                ios: 'https://itunes.apple.com/us/app/you-zi-jie-shou-ji-shang-nu/id1054877169?l=zh&ls=1&mt=8',
                android: 'http://yzjdl.xixiaoyou.com/169.apk',
                wechat: 'http://a.app.qq.com/o/simple.jsp?pkgname=com.meiyou.youzijie&ckey=CK1319740419600'
            },
            224: {
                ios: 'http://um0.cn/1DDQhs',
                android: 'http://yzjdl.xixiaoyou.com/youzijie224.apk',
                wechat: 'http://a.app.qq.com/o/simple.jsp?pkgname=com.meiyou.youzijie&ckey=CK1319740419600'
            },
            225: {
                ios: 'http://um0.cn/1Vzj5G',
                android: 'http://yzjdl.xixiaoyou.com/youzijie225.apk',
                wechat: 'http://a.app.qq.com/o/simple.jsp?pkgname=com.meiyou.youzijie&ckey=CK1319740419600'
            },
            226: {
                ios: 'http://um0.cn/35VMNr',
                android: 'http://yzjdl.xixiaoyou.com/youzijie226.apk',
                wechat: 'http://a.app.qq.com/o/simple.jsp?pkgname=com.meiyou.youzijie&ckey=CK1319740419600'
            },
            264: {
                ios: 'https://itunes.apple.com/app/apple-store/id1054877169?pt=91900801&ct=fensitong264&mt=8',
                android: 'http://yzjdl.xixiaoyou.com/youzijie264.apk',
                wechat: 'http://a.app.qq.com/o/simple.jsp?pkgname=com.meiyou.youzijie&ckey=CK1319740419600'
            },
            265: {
                ios: 'https://itunes.apple.com/app/apple-store/id1054877169?pt=91900801&ct=fensitong265&mt=8 ',
                android: 'http://yzjdl.xixiaoyou.com/youzijie265.apk',
                wechat: 'http://a.app.qq.com/o/simple.jsp?pkgname=com.meiyou.youzijie&ckey=CK1319740419600'
            },
            423: {
                ios: 'https://itunes.apple.com/app/apple-store/id1054877169?pt=91900801&ct=uctoutiao423&mt=8 ',
                android: 'http://yzjdl.xixiaoyou.com/youzijie423.apk',
                wechat: 'http://a.app.qq.com/o/simple.jsp?pkgname=com.meiyou.youzijie&ckey=CK1319740419600'
            },
            424: {
                ios: 'https://itunes.apple.com/app/apple-store/id1054877169?pt=91900801&ct=uctoutiao424&mt=8',
                android: 'http://yzjdl.xixiaoyou.com/youzijie424.apk',
                wechat: 'http://a.app.qq.com/o/simple.jsp?pkgname=com.meiyou.youzijie&ckey=CK1319740419600'
            }
        };

        // 点击按钮，执行download_meiyou函数，Bug: 其中PC端的统一下载Android的版本。
        // Bug: 在IOS和Android判断中均要优先判断微信类型，否则微信中的链接直接打开ios链接而无反应。
        function download_meiyou() {
            var downloadURL = "";
            var num = getUrlParam('num');
            if (ios) {
                if (weixin)
                    downloadURL = path[num].wechat;
                else
                    downloadURL = path[num].ios;
            } else if (android || other) {
                if (weixin)
                    downloadURL = path[num].wechat;
                else
                    downloadURL = path[num].android;
                // } else if (wp) {
                //     downloadURL = "http://t.cn/RPcmviT";
            } else {
                downloadURL = path[num].android;
            }
            window.location.href = downloadURL;
        }

        // 自动打开APP，Bug: 其中IOS利用window.location直接跳转协议地址，Android利用iframe打开。
        window.onload = function() {
            var url = getUrlParam('url');
            if (url) {
                url = decodeURIComponent(url);
            }
            if (ios) {
                window.location = url;
            } else if (android) {
                var b = document.createElement("iframe");
                b.src = url;
                b.style.display = "none";
                window.setTimeout(function() {
                    document.body.appendChild(b);
                }, 10);
                window.setTimeout(function() {
                    document.body.removeChild(b);
                }, 2000);
            }
        }
    </script>
</head>
<body>
        test
</body>
</html>
